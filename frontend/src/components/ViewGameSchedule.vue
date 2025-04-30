<template>
  <div class="game-list">
    <h2>Game Schedules</h2>

    <div v-if="error" class="error-message">
      Error: {{ error }}
    </div>
    <div v-if="loading" class="loading-message">
      Loading game schedules...
    </div>

    <ul v-if="!loading && !error">
      <li v-for="(game, index) in games" :key="game.gameId" class="game-item">
        <div @click="toggleGame(index)" class="game-summary">
          {{ game.date }} - {{ game.sportType }} vs {{ game.opponent }}
        </div>

        <div v-if="selectedGameIndex === index" class="game-details">
          <p><strong>Game ID:</strong> {{ game.gameId }}</p>
          <p><strong>Sport Type:</strong> {{ game.sportType }}</p>
          <p><strong>Opponent:</strong> {{ game.opponent }}</p>
          <p><strong>Date:</strong> {{ game.date }}</p>
          <p><strong>Time:</strong> {{ game.time }}</p>
          <p><strong>Venue:</strong> {{ game.venue }}</p>
          <p><strong>Crew Positions:</strong> {{ game.crewPositions }}</p>
        </div>
      </li>

      <li v-if="games.length === 0">
        No game schedules found
      </li>
    </ul>

    <!-- Only show add game form for admin users -->
    <div v-if="isAdmin" class="add-game-form">
      <h3>Add New Game</h3>
      <form @submit.prevent="addGame">
        <input v-model="newGame.date" placeholder="Date (e.g., August 30, 2025)" required class="form-input" />
        <input v-model="newGame.time" placeholder="Time (e.g., 7:00 PM)" required class="form-input" />
        <input v-model="newGame.sportType" placeholder="Sport Type (e.g., Football)" required class="form-input" />
        <input v-model="newGame.venue" placeholder="Venue (e.g., Amon G. Carter Stadium)" required class="form-input" />
        <input v-model="newGame.opponent" placeholder="Opponent (e.g., Nevada)" class="form-input" />
        <input v-model="newGame.crewPositions" placeholder="Crew Positions (e.g., Referee, Line Judge)" required class="form-input" />
        <button type="submit" class="submit-button">Add Game</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';

const API_BASE_URL = 'http://localhost:8080/frogcrew/api/v1/gameSchedule';

const games = ref([]);
const newGame = ref({
  date: '',
  time: '',
  sportType: '',
  venue: '',
  opponent: '',
  crewPositions: ''
});

const loading = ref(false);
const error = ref(null);
const selectedGameIndex = ref(null);

// Check if user is admin
const isAdmin = computed(() => {
  const userRole = localStorage.getItem('userRole');
  return userRole === 'ADMIN';
});

// Fetch all games from the backend
const fetchGames = async () => {
  loading.value = true;
  error.value = null;
  try {
    console.log('Fetching games from:', `${API_BASE_URL}/games`);
    const response = await fetch(`${API_BASE_URL}/games`, {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    });

    console.log('Response status:', response.status);
    if (!response.ok) {
      const errorText = await response.text();
      console.error('Server response:', errorText);
      throw new Error(`HTTP error! status: ${response.status}, message: ${errorText}`);
    }

    const contentType = response.headers.get('content-type');
    console.log('Response content type:', contentType);
    
    if (!contentType || !contentType.includes('application/json')) {
      throw new Error('Server did not return JSON');
    }

    const result = await response.json();
    console.log('API Response:', result);

    if (result.flag && Array.isArray(result.data)) {
      games.value = result.data;
      console.log('Games loaded:', games.value.length);
    } else {
      games.value = [];
      console.warn('Unexpected structure or empty data:', result);
    }
  } catch (err) {
    error.value = `Failed to fetch games: ${err.message}`;
    console.error('Error fetching games:', err);
    games.value = [];
  } finally {
    loading.value = false;
  }
};

// Add a new game to a specific schedule
const addGame = async () => {
  if (!isAdmin.value) {
    error.value = "You don't have permission to add games";
    return;
  }

  try {
    console.log(`Adding game to schedule ${scheduleId}:`, newGame.value);
    const response = await fetch(`${API_BASE_URL}/${scheduleId}/games`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newGame.value)
    });

    const result = await response.json();
    console.log('Add game response:', result);

    if (result.flag) {
      await fetchGames();
      // Reset form
      newGame.value = {
        date: '',
        time: '',
        sportType: '',
        venue: '',
        opponent: '',
        crewPositions: ''
      };
    } else {
      throw new Error(result.message || "Failed to add game.");
    }
  } catch (err) {
    error.value = `Failed to add game: ${err.message}`;
    console.error('Error adding game:', err);
  }
};

// Toggle show/hide game details
const toggleGame = (index) => {
  if (selectedGameIndex.value === index) {
    selectedGameIndex.value = null;
  } else {
    selectedGameIndex.value = index;
  }
};

onMounted(() => {
  fetchGames();
});
</script>

<style scoped>
.game-list {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  max-width: 800px;
  margin: 120px auto 24px;
  text-align: center;
  box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
}

ul {
  list-style: none;
  padding: 0;
  width: 100%;
}

li {
  margin: 10px 0;
}

.game-item {
  margin-bottom: 20px;
}

.game-summary {
  cursor: pointer;
  color: #007bff;
  font-weight: bold;
}

.game-summary:hover {
  text-decoration: underline;
}

.game-details {
  background: #f9f9f9;
  padding: 10px;
  margin-top: 5px;
  border-radius: 8px;
  text-align: left;
}

.add-game-form {
  margin-top: 40px;
}

.add-game-form h3 {
  font-size: 20px;
  margin-bottom: 16px;
}

.form-input {
  display: block;
  margin: 8px auto;
  padding: 8px;
  width: 80%;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.submit-button {
  margin-top: 10px;
  padding: 10px 20px;
  background-color: #22c55e;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #16a34a;
}

.error-message {
  color: red;
}

.loading-message {
  color: #666;
}
</style>



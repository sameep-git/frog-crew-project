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
      <li v-for="(game, index) in games" :key="index" class="game-item">
        <div @click="toggleGame(index)" class="game-summary">
          {{ game.date }} - {{ game.team }} vs {{ game.opponent }}
        </div>
        
        <div v-if="selectedGameIndex === index" class="game-details">
          <p><strong>Date:</strong> {{ game.date }}</p>
          <p><strong>Team:</strong> {{ game.team }}</p>
          <p><strong>Opponent:</strong> {{ game.opponent }}</p>
          <p><strong>Time:</strong> {{ game.time }}</p>
        </div>
      </li>

      <li v-if="games.length === 0">
        No game schedules found
      </li>
    </ul>

    <div class="add-game-form">
      <h3>Add New Game</h3>
      <form @submit.prevent="addGame">
        <input v-model="newGame.date" placeholder="Date (e.g., August 30, 2025)" required class="form-input" />
        <select v-model="newGame.team" required class="form-input">
          <option disabled value="">Select Team</option>
          <option>Football</option>
          <option>Basketball</option>
          <option>Volleyball</option>
        </select>
        <input v-model="newGame.opponent" placeholder="Opponent (e.g., Nevada)" required class="form-input" />
        <input v-model="newGame.time" placeholder="Time (e.g., 7:00 PM)" required class="form-input" />
        <button type="submit" class="submit-button">Add Game</button>
      </form>
    </div>

  </div>
</template>

<script setup>
import { ref } from 'vue';

// list of games
const games = ref([
  { date: 'August 30, 2025', team: 'Football', opponent: 'Nevada', time: '7:00 PM' },
  { date: 'September 5, 2025', team: 'Basketball', opponent: 'Duke', time: '6:00 PM' },
  { date: 'September 12, 2025', team: 'Volleyball', opponent: 'Baylor', time: '3:00 PM' }
]);

const newGame = ref({
  date: '',
  team: '',
  opponent: '',
  time: ''
});

const loading = ref(false);
const error = ref(null);
const selectedGameIndex = ref(null);

// click to toggle show/hide game details
const toggleGame = (index) => {
  if (selectedGameIndex.value === index) {
    selectedGameIndex.value = null; // collapse if clicked again
  } else {
    selectedGameIndex.value = index; // expand
  }
};

// add a new game
const addGame = () => {
  if (newGame.value.date && newGame.value.team && newGame.value.opponent && newGame.value.time) {
    games.value.push({ ...newGame.value });
    newGame.value.date = '';
    newGame.value.team = '';
    newGame.value.opponent = '';
    newGame.value.time = '';
  }
};
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

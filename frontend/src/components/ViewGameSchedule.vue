<!--&lt;!&ndash; <template>-->
<!--  <div class="game-list">-->
<!--    <h2>Game Schedules</h2>-->

<!--    <div v-if="error" class="error-message">-->
<!--      Error: {{ error }}-->
<!--    </div>-->
<!--    <div v-if="loading" class="loading-message">-->
<!--      Loading game schedules...-->
<!--    </div>-->

<!--    <ul v-if="!loading && !error">-->
<!--      <li v-for="(game, index) in games" :key="index" class="game-item">-->
<!--        <div @click="toggleGame(index)" class="game-summary">-->
<!--          {{ game.date }} - {{ game.team }} vs {{ game.opponent }}-->
<!--        </div>-->
<!--        -->
<!--        <div v-if="selectedGameIndex === index" class="game-details">-->
<!--          <p><strong>Date:</strong> {{ game.date }}</p>-->
<!--          <p><strong>Team:</strong> {{ game.team }}</p>-->
<!--          <p><strong>Opponent:</strong> {{ game.opponent }}</p>-->
<!--          <p><strong>Time:</strong> {{ game.time }}</p>-->
<!--        </div>-->
<!--      </li>-->

<!--      <li v-if="games.length === 0">-->
<!--        No game schedules found-->
<!--      </li>-->
<!--    </ul>-->

<!--    <div class="add-game-form">-->
<!--      <h3>Add New Game</h3>-->
<!--      <form @submit.prevent="addGame">-->
<!--        <input v-model="newGame.date" placeholder="Date (e.g., August 30, 2025)" required class="form-input" />-->
<!--        <select v-model="newGame.team" required class="form-input">-->
<!--          <option disabled value="">Select Team</option>-->
<!--          <option>Football</option>-->
<!--          <option>Basketball</option>-->
<!--          <option>Volleyball</option>-->
<!--        </select>-->
<!--        <input v-model="newGame.opponent" placeholder="Opponent (e.g., Nevada)" required class="form-input" />-->
<!--        <input v-model="newGame.time" placeholder="Time (e.g., 7:00 PM)" required class="form-input" />-->
<!--        <button type="submit" class="submit-button">Add Game</button>-->
<!--      </form>-->
<!--    </div>-->

<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref } from 'vue';-->

<!--// list of games-->
<!--const games = ref([-->
<!--  { date: 'August 30, 2025', team: 'Football', opponent: 'Nevada', time: '7:00 PM' },-->
<!--  { date: 'September 5, 2025', team: 'Basketball', opponent: 'Duke', time: '6:00 PM' },-->
<!--  { date: 'September 12, 2025', team: 'Volleyball', opponent: 'Baylor', time: '3:00 PM' }-->
<!--]);-->

<!--const newGame = ref({-->
<!--  date: '',-->
<!--  team: '',-->
<!--  opponent: '',-->
<!--  time: ''-->
<!--});-->

<!--const loading = ref(false);-->
<!--const error = ref(null);-->
<!--const selectedGameIndex = ref(null);-->

<!--// click to toggle show/hide game details-->
<!--const toggleGame = (index) => {-->
<!--  if (selectedGameIndex.value === index) {-->
<!--    selectedGameIndex.value = null; // collapse if clicked again-->
<!--  } else {-->
<!--    selectedGameIndex.value = index; // expand-->
<!--  }-->
<!--};-->

<!--// add a new game-->
<!--const addGame = () => {-->
<!--  if (newGame.value.date && newGame.value.team && newGame.value.opponent && newGame.value.time) {-->
<!--    games.value.push({ ...newGame.value });-->
<!--    newGame.value.date = '';-->
<!--    newGame.value.team = '';-->
<!--    newGame.value.opponent = '';-->
<!--    newGame.value.time = '';-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.game-list {-->
<!--  background: #fff;-->
<!--  padding: 20px;-->
<!--  border-radius: 10px;-->
<!--  max-width: 800px;-->
<!--  margin: 120px auto 24px;-->
<!--  text-align: center;-->
<!--  box-shadow: 0px 4px 6px rgba(0,0,0,0.1);-->
<!--}-->

<!--ul {-->
<!--  list-style: none;-->
<!--  padding: 0;-->
<!--  width: 100%;-->
<!--}-->

<!--li {-->
<!--  margin: 10px 0;-->
<!--}-->

<!--.game-item {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.game-summary {-->
<!--  cursor: pointer;-->
<!--  color: #007bff;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.game-summary:hover {-->
<!--  text-decoration: underline;-->
<!--}-->

<!--.game-details {-->
<!--  background: #f9f9f9;-->
<!--  padding: 10px;-->
<!--  margin-top: 5px;-->
<!--  border-radius: 8px;-->
<!--}-->

<!--.add-game-form {-->
<!--  margin-top: 40px;-->
<!--}-->

<!--.add-game-form h3 {-->
<!--  font-size: 20px;-->
<!--  margin-bottom: 16px;-->
<!--}-->

<!--.form-input {-->
<!--  display: block;-->
<!--  margin: 8px auto;-->
<!--  padding: 8px;-->
<!--  width: 80%;-->
<!--  border: 1px solid #ccc;-->
<!--  border-radius: 4px;-->
<!--}-->

<!--.submit-button {-->
<!--  margin-top: 10px;-->
<!--  padding: 10px 20px;-->
<!--  background-color: #22c55e;-->
<!--  color: white;-->
<!--  border: none;-->
<!--  border-radius: 4px;-->
<!--  cursor: pointer;-->
<!--}-->

<!--.submit-button:hover {-->
<!--  background-color: #16a34a;-->
<!--}-->

<!--.error-message {-->
<!--  color: red;-->
<!--}-->

<!--.loading-message {-->
<!--  color: #666;-->
<!--}-->
<!--</style> &ndash;&gt;-->


<!--<template>-->
<!--  <div class="game-list">-->
<!--    <h2>Game Schedules</h2>-->

<!--    <div v-if="error" class="error-message">-->
<!--      Error: {{ error }}-->
<!--    </div>-->
<!--    <div v-if="loading" class="loading-message">-->
<!--      Loading game schedules...-->
<!--    </div>-->

<!--    <ul v-if="!loading && !error">-->
<!--      <li v-for="(game, index) in games" :key="index" class="game-item">-->
<!--        <div @click="toggleGame(index)" class="game-summary">-->
<!--          {{ game.date }} - {{ game.team }} vs {{ game.opponent }}-->
<!--        </div>-->
<!--        -->
<!--        <div v-if="selectedGameIndex === index" class="game-details">-->
<!--          <p><strong>Date:</strong> {{ game.date }}</p>-->
<!--          <p><strong>Team:</strong> {{ game.team }}</p>-->
<!--          <p><strong>Opponent:</strong> {{ game.opponent }}</p>-->
<!--          <p><strong>Time:</strong> {{ game.time }}</p>-->
<!--        </div>-->
<!--      </li>-->

<!--      <li v-if="games.length === 0">-->
<!--        No game schedules found-->
<!--      </li>-->
<!--    </ul>-->

<!--    <div class="add-game-form">-->
<!--      <h3>Add New Game</h3>-->
<!--      <form @submit.prevent="addGame">-->
<!--        <input v-model="newGame.date" placeholder="Date (e.g., August 30, 2025)" required class="form-input" />-->
<!--        <select v-model="newGame.team" required class="form-input">-->
<!--          <option disabled value="">Select Team</option>-->
<!--          <option>Football</option>-->
<!--          <option>Basketball</option>-->
<!--          <option>Volleyball</option>-->
<!--        </select>-->
<!--        <input v-model="newGame.opponent" placeholder="Opponent (e.g., Nevada)" required class="form-input" />-->
<!--        <input v-model="newGame.time" placeholder="Time (e.g., 7:00 PM)" required class="form-input" />-->
<!--        <button type="submit" class="submit-button">Add Game</button>-->
<!--      </form>-->
<!--    </div>-->

<!--  </div>-->
<!--</template>-->

<!--<script setup>-->
<!--import { ref, onMounted } from 'vue';-->

<!--const API_BASE_URL = 'http://localhost:8080/api/v1/frogcrew/gameSchedule'; // <-  backend API-->

<!--const games = ref([]);-->
<!--const newGame = ref({-->
<!--  date: '',-->
<!--  team: '',-->
<!--  opponent: '',-->
<!--  time: ''-->
<!--});-->

<!--const loading = ref(false);-->
<!--const error = ref(null);-->
<!--const selectedGameIndex = ref(null);-->

<!--// Fetch all games from the backend-->
<!--const fetchGames = async () => {-->
<!--  loading.value = true;-->
<!--  error.value = null;-->
<!--  try {-->
<!--    const response = await fetch(`${API_BASE_URL}/games`, {-->
<!--      method: 'GET',-->
<!--      headers: {-->
<!--        'Accept': 'application/json'-->
<!--      }-->
<!--    });-->
<!--    const result = await response.json();-->
<!--    if (response.ok) {-->
<!--      games.value = result.data; // 'data' contains the actual list of games-->
<!--    } else {-->
<!--      throw new Error(result.message || "Failed to fetch games.");-->
<!--    }-->
<!--  } catch (err) {-->
<!--    error.value = err.message;-->
<!--    console.error('Error fetching games:', err);-->
<!--  } finally {-->
<!--    loading.value = false;-->
<!--  }-->
<!--};-->

<!--// Add a new game to a specific schedule-->
<!--const addGame = async () => {-->
<!--  try {-->
<!--    const response = await fetch(`${API_BASE_URL}/1/games`, { // Assume scheduleId = 1-->
<!--      method: 'POST',-->
<!--      headers: {-->
<!--        'Content-Type': 'application/json'-->
<!--      },-->
<!--      body: JSON.stringify(newGame.value)-->
<!--    });-->
<!--    const result = await response.json();-->
<!--    if (response.ok) {-->
<!--      games.value.push(result.data); // Add the newly added game-->
<!--      newGame.value.date = '';-->
<!--      newGame.value.team = '';-->
<!--      newGame.value.opponent = '';-->
<!--      newGame.value.time = '';-->
<!--    } else {-->
<!--      throw new Error(result.message || "Failed to add game.");-->
<!--    }-->
<!--  } catch (err) {-->
<!--    error.value = err.message;-->
<!--    console.error('Error adding game:', err);-->
<!--  }-->
<!--};-->

<!--// Toggle show/hide game details-->
<!--const toggleGame = (index) => {-->
<!--  if (selectedGameIndex.value === index) {-->
<!--    selectedGameIndex.value = null; // Collapse if already selected-->
<!--  } else {-->
<!--    selectedGameIndex.value = index; // Expand-->
<!--  }-->
<!--};-->

<!--onMounted(() => {-->
<!--  fetchGames();-->
<!--});-->
<!--</script>-->

<!--<style scoped>-->
<!--.game-list {-->
<!--  background: #fff;-->
<!--  padding: 20px;-->
<!--  border-radius: 10px;-->
<!--  max-width: 800px;-->
<!--  margin: 120px auto 24px;-->
<!--  text-align: center;-->
<!--  box-shadow: 0px 4px 6px rgba(0,0,0,0.1);-->
<!--}-->

<!--ul {-->
<!--  list-style: none;-->
<!--  padding: 0;-->
<!--  width: 100%;-->
<!--}-->

<!--li {-->
<!--  margin: 10px 0;-->
<!--}-->

<!--.game-item {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.game-summary {-->
<!--  cursor: pointer;-->
<!--  color: #007bff;-->
<!--  font-weight: bold;-->
<!--}-->

<!--.game-summary:hover {-->
<!--  text-decoration: underline;-->
<!--}-->

<!--.game-details {-->
<!--  background: #f9f9f9;-->
<!--  padding: 10px;-->
<!--  margin-top: 5px;-->
<!--  border-radius: 8px;-->
<!--}-->

<!--.add-game-form {-->
<!--  margin-top: 40px;-->
<!--}-->

<!--.add-game-form h3 {-->
<!--  font-size: 20px;-->
<!--  margin-bottom: 16px;-->
<!--}-->

<!--.form-input {-->
<!--  display: block;-->
<!--  margin: 8px auto;-->
<!--  padding: 8px;-->
<!--  width: 80%;-->
<!--  border: 1px solid #ccc;-->
<!--  border-radius: 4px;-->
<!--}-->

<!--.submit-button {-->
<!--  margin-top: 10px;-->
<!--  padding: 10px 20px;-->
<!--  background-color: #22c55e;-->
<!--  color: white;-->
<!--  border: none;-->
<!--  border-radius: 4px;-->
<!--  cursor: pointer;-->
<!--}-->

<!--.submit-button:hover {-->
<!--  background-color: #16a34a;-->
<!--}-->

<!--.error-message {-->
<!--  color: red;-->
<!--}-->

<!--.loading-message {-->
<!--  color: #666;-->
<!--}-->
<!--</style>-->

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

    <div class="add-game-form">
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
import { ref, onMounted } from 'vue';

const API_BASE_URL = 'http://localhost:8080/api/v1/frogcrew/gameSchedule';

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

// Fetch all games from the backend
const fetchGames = async () => {
  loading.value = true;
  error.value = null;
  try {
    const response = await fetch(`${API_BASE_URL}/games`, {
      method: 'GET',
      headers: {
        'Accept': 'application/json'
      }
    });
    const result = await response.json();
    if (response.ok) {
      if (result.data) {
        games.value = result.data; // 'data' contains the actual list of games
      } else {
        games.value = []; // Handle empty data case
      }
    } else {
      throw new Error(result.message || "Failed to fetch games.");
    }
  } catch (err) {
    error.value = err.message;
    console.error('Error fetching games:', err);
  } finally {
    loading.value = false;
  }
};

// Add a new game to a specific schedule
const addGame = async () => {
  try {
    const response = await fetch(`${API_BASE_URL}/1/games`, { // Assume scheduleId = 1
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(newGame.value)
    });
    const result = await response.json();
    if (response.ok) {
      await fetchGames(); // Refresh list after adding
      newGame.value.date = '';
      newGame.value.time = '';
      newGame.value.sportType = '';
      newGame.value.venue = '';
      newGame.value.opponent = '';
      newGame.value.crewPositions = '';
    } else {
      throw new Error(result.message || "Failed to add game.");
    }
  } catch (err) {
    error.value = err.message;
    console.error('Error adding game:', err);
  }
};

// Toggle show/hide game details
const toggleGame = (index) => {
  if (selectedGameIndex.value === index) {
    selectedGameIndex.value = null; // Collapse if already selected
  } else {
    selectedGameIndex.value = index; // Expand
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



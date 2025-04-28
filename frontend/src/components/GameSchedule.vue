<!-- 

<template>
  <div class="container">
    <h1 class="title">Game Schedule</h1>

    <div class="controls">
      <input v-model="searchTerm" class="form-input" placeholder="Search games...">
      <select v-model="sortOption" class="form-input">
        <option value="">Sort by</option>
        <option value="date">Date</option>
        <option value="team">Team</option>
        <option value="opponent">Opponent</option>
        <option value="time">Time</option>
      </select>
    </div>

    <div class="table-wrapper">
      <table>
        <thead>
          <tr class="header-row">
            <th>Date</th>
            <th>Team</th>
            <th>Opponent</th>
            <th>Time</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(game, index) in filteredAndSortedGames" :key="index">
            <td>{{ game.date }}</td>
            <td>{{ game.team }}</td>
            <td>{{ game.opponent }}</td>
            <td>{{ game.time }}</td>
          </tr>
        </tbody>
      </table>
    </div>

    <div class="form-section">
      <h2 class="form-title">Add New Game</h2>
      <form @submit.prevent="addGame" class="form-grid">
        <input v-model="newGame.date" placeholder="Date (e.g., August 30, 2025)" class="form-input" required />
        <select v-model="newGame.team" class="form-input" required>
          <option disabled value="">Select Team</option>
          <option>Football</option>
          <option>Basketball</option>
          <option>Volleyball</option>
        </select>
        <input v-model="newGame.opponent" placeholder="Opponent (e.g., Nevada)" class="form-input" required />
        <input v-model="newGame.time" placeholder="Time (e.g., 3:00 PM)" class="form-input" required />
        <button type="submit" class="submit-button">Add Game</button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, computed } from 'vue';

const games = ref([]);
const newGame = reactive({ date: '', team: '', opponent: '', time: '' });
const searchTerm = ref('');
const sortOption = ref('');

const addGame = () => {
  if (newGame.date && newGame.team && newGame.opponent && newGame.time) {
    games.value.push({ ...newGame });
    newGame.date = '';
    newGame.team = '';
    newGame.opponent = '';
    newGame.time = '';
  }
};

const filteredAndSortedGames = computed(() => {
  let list = games.value.filter(game =>
    game.date.toLowerCase().includes(searchTerm.value.toLowerCase()) ||
    game.team.toLowerCase().includes(searchTerm.value.toLowerCase()) ||
    game.opponent.toLowerCase().includes(searchTerm.value.toLowerCase()) ||
    game.time.toLowerCase().includes(searchTerm.value.toLowerCase())
  );

  if (sortOption.value) {
    list.sort((a, b) => a[sortOption.value].localeCompare(b[sortOption.value]));
  }

  return list;
});
</script>

<style scoped>
.container {
  padding: 24px;
  margin: 100px auto 24px; 
  max-width: 900px; 
}
.title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 16px;
}
.controls {
  margin-bottom: 16px;
  display: flex;
  gap: 16px;
}
.table-wrapper {
  overflow-x: auto;
}
table {
  width: 100%;
  border-collapse: collapse;
  border: 1px solid #e5e7eb;
}
th, td {
  border: 1px solid #e5e7eb;
  padding: 12px;
  text-align: left;
}
.header-row {
  background-color: #2563eb;
  color: white;
}
.form-section {
  margin-top: 32px;
}
.form-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 12px;
}
.form-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
}
.form-input {
  padding: 8px;
  border: 1px solid #e5e7eb;
  border-radius: 4px;
  width: 100%;
}
.submit-button {
  grid-column: span 2;
  background-color: #22c55e;
  color: white;
  padding: 8px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
</style>

 -->


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
      <li v-for="(game, index) in games" :key="index">
        <router-link :to="{ name: 'GameSchedule', params: { id: index, gameData: game } }" class="game-link">
          {{ game.date }} - {{ game.team }} vs {{ game.opponent }}
        </router-link>
      </li>
      <li v-if="games.length === 0">
        No game schedules found
      </li>
    </ul>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

// replace with API later 
const games = ref([
  { date: 'August 30, 2025', team: 'Football', opponent: 'Nevada', time: '7:00 PM' },
  { date: 'September 5, 2025', team: 'Basketball', opponent: 'Duke', time: '6:00 PM' },
  { date: 'September 12, 2025', team: 'Volleyball', opponent: 'Baylor', time: '3:00 PM' }
]);

const loading = ref(false);
const error = ref(null);

// onMounted(() => {
//   // No real loading for now since we use fake data
// });
</script>

<style scoped>
.game-list {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  max-width: 600px;
  margin: 120px auto 24px;
  text-align: center;
  box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
}

ul {
  list-style: none;
  padding: 0;
}

li {
  margin: 10px 0;
}

.game-link {
  text-decoration: none;
  color: #007bff;
  font-weight: bold;
}

.game-link:hover {
  text-decoration: underline;
}

.error-message {
  color: red;
}
.loading-message {
  color: #666;
}
</style>

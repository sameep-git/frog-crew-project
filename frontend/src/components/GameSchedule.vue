<!-- <template>
  <div class="container">
    <h1 class="title">Game Schedule Management</h1>

    <div v-if="error" class="error-message">
      {{ error }}
    </div>

    <div class="form-section">
      <h2 class="form-title">Create New Game Schedule</h2>
      <form @submit.prevent="createGameSchedule" class="form-grid">
        <div class="form-group">
          <label for="sport">Sport Type</label>
          <select v-model="newSchedule.sport" id="sport" class="form-input" required>
            <option value="">Select Sport</option>
            <option value="Football">Football</option>
            <option value="Basketball">Basketball</option>
            <option value="Volleyball">Volleyball</option>
          </select>
        </div>

        <div class="form-group">
          <label for="season">Season</label>
          <input 
            v-model="newSchedule.season" 
            id="season" 
            class="form-input" 
            placeholder="e.g., 2024-2025" 
            required 
          />
        </div>

        <button type="submit" class="submit-button" :disabled="loading">
          {{ loading ? 'Creating...' : 'Create Schedule' }}
        </button>
      </form>
    </div>

    <div v-if="schedules.length > 0" class="schedules-section">
      <h2 class="section-title">Existing Schedules</h2>
      <div class="schedules-grid">
        <div v-for="schedule in schedules" :key="schedule.id" class="schedule-card">
          <h3>{{ schedule.sport }}</h3>
          <p>Season: {{ schedule.season }}</p>
          <p>Status: {{ schedule.isPublished ? 'Published' : 'Draft' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue';

const API_BASE_URL = 'http://localhost:8080/frogcrew/api/v1/gameSchedule';

const newSchedule = reactive({
  sport: '',
  season: ''
});

const schedules = ref([]);
const loading = ref(false);
const error = ref(null);

const createGameSchedule = async () => {
  if (!isAdmin.value) {
    error.value = "You don't have permission to create game schedules";
    return;
  }

  loading.value = true;
  error.value = null;

  try {
    const response = await fetch(API_BASE_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      },
      body: JSON.stringify(newSchedule)
    });

    const result = await response.json();

    if (!response.ok) {
      throw new Error(result.message || 'Failed to create game schedule');
    }

    if (result.success) {
      // Reset form
      newSchedule.sport = '';
      newSchedule.season = '';
      
      // Refresh schedules list
      await fetchSchedules();
    } else {
      throw new Error(result.message || 'Failed to create game schedule');
    }
  } catch (err) {
    error.value = err.message;
    console.error('Error creating game schedule:', err);
  } finally {
    loading.value = false;
  }
};

const fetchSchedules = async () => {
  try {
    const response = await fetch(API_BASE_URL, {
      headers: {
        'Accept': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });

    const result = await response.json();

    if (result.success && Array.isArray(result.data)) {
      schedules.value = result.data;
    } else {
      schedules.value = [];
    }
  } catch (err) {
    console.error('Error fetching schedules:', err);
    schedules.value = [];
  }
};

// Check if user is admin
const isAdmin = computed(() => {
  return localStorage.getItem('userRole') === 'ADMIN';
});

// Fetch schedules on component mount
onMounted(() => {
  fetchSchedules();
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
  margin-bottom: 24px;
  color: #1a1a1a;
}

.form-section {
  background: white;
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 32px;
}

.form-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
  color: #2563eb;
}

.form-grid {
  display: grid;
  gap: 16px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group label {
  font-weight: 500;
  color: #4b5563;
}

.form-input {
  padding: 10px;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  font-size: 16px;
  transition: border-color 0.2s;
}

.form-input:focus {
  outline: none;
  border-color: #2563eb;
  box-shadow: 0 0 0 2px rgba(37, 99, 235, 0.1);
}

.submit-button {
  background-color: #2563eb;
  color: white;
  padding: 12px;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.2s;
}

.submit-button:hover {
  background-color: #1d4ed8;
}

.submit-button:disabled {
  background-color: #93c5fd;
  cursor: not-allowed;
}

.error-message {
  background-color: #fee2e2;
  color: #dc2626;
  padding: 12px;
  border-radius: 6px;
  margin-bottom: 16px;
}

.schedules-section {
  margin-top: 32px;
}

.section-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
  color: #1a1a1a;
}

.schedules-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 16px;
}

.schedule-card {
  background: white;
  padding: 16px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.schedule-card h3 {
  margin: 0 0 8px 0;
  color: #2563eb;
}

.schedule-card p {
  margin: 4px 0;
  color: #4b5563;
}
</style>
 -->

 <template>
  <div class="container">
    <h1 class="title">Game Schedule Management</h1>

    <div v-if="error" class="error-message">
      {{ error }}
    </div>

    <div class="form-section">
      <h2 class="form-title">Create New Game Schedule</h2>
      <form @submit.prevent="createGameSchedule" class="form-grid">
        <div class="form-group">
          <label for="sport">Sport Type</label>
          <select v-model="newSchedule.sport" id="sport" class="form-input" required>
            <option value="">Select Sport</option>
            <option value="Football">Football</option>
            <option value="Basketball">Basketball</option>
            <option value="Volleyball">Volleyball</option>
          </select>
        </div>

        <div class="form-group">
          <label for="season">Season</label>
          <input 
            v-model="newSchedule.season" 
            id="season" 
            class="form-input" 
            placeholder="e.g., 2024-2025" 
            required 
          />
        </div>

        <button type="submit" class="submit-button" :disabled="loading">
          {{ loading ? 'Creating...' : 'Create Schedule' }}
        </button>
      </form>
    </div>

    <div v-if="schedules.length > 0" class="schedules-section">
      <h2 class="section-title">Existing Schedules</h2>
      <div class="schedules-grid">
        <div v-for="schedule in schedules" :key="schedule.id" class="schedule-card">
          <h3>{{ schedule.sport }}</h3>
          <p>Season: {{ schedule.season }}</p>
          <p>Status: {{ schedule.published ? 'Published' : 'Draft' }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const API_BASE_URL = 'http://localhost:8080/frogcrew/api/v1/gameSchedule';

const newSchedule = reactive({
  sport: '',
  season: ''
});

const schedules = ref([]);
const loading = ref(false);
const error = ref(null);

const createGameSchedule = async () => {
  if (!isAdmin.value) {
    error.value = "You don't have permission to create game schedules";
    return;
  }

  loading.value = true;
  error.value = null;

  try {
    const payload = {
      sport: newSchedule.sport,
      season: newSchedule.season
    };

    const response = await fetch(API_BASE_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      },
      body: JSON.stringify(payload)
    });

    const result = await response.json();
    console.log('Create schedule response:', result);

    if (result.flag) {
      // Reset form
      newSchedule.sport = '';
      newSchedule.season = '';
      
      // Refresh schedules list
      await fetchSchedules();
      // Navigate to view schedules
      router.push('/view-game-schedule');
    } else {
      throw new Error(result.message || 'Failed to create game schedule');
    }
  } catch (err) {
    error.value = `Error creating game schedule: ${err.message}`;
    console.error('Error creating game schedule:', err);
  } finally {
    loading.value = false;
  }
};

const fetchSchedules = async () => {
  try {
    const response = await fetch(API_BASE_URL, {
      headers: {
        'Accept': 'application/json'
      }
    });

    const result = await response.json();
    console.log('Fetch schedules response:', result);

    if (result.flag && Array.isArray(result.data)) {
      schedules.value = result.data;
    } else {
      schedules.value = [];
    }
  } catch (err) {
    console.error('Error fetching schedules:', err);
    schedules.value = [];
  }
};

const isAdmin = computed(() => {
  return localStorage.getItem('userRole') === 'ADMIN';
});

onMounted(() => {
  fetchSchedules();
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
  margin-bottom: 24px;
  color: #1a1a1a;
}

.form-section {
  background: white;
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 32px;
}

.form-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
  color: #2563eb;
}

.form-grid {
  display: grid;
  gap: 16px;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 8px;
}

.form-group label {
  font-weight: 500;
  color: #4b5563;
}

.form-input {
  padding: 10px;
  border: 1px solid #e5e7eb;
  border-radius: 6px;
  font-size: 16px;
  transition: border-color 0.2s;
}

.form-input:focus {
  outline: none;
  border-color: #2563eb;
  box-shadow: 0 0 0 2px rgba(37, 99, 235, 0.1);
}

.submit-button {
  background-color: #2563eb;
  color: white;
  padding: 12px;
  border: none;
  border-radius: 6px;
  font-size: 16px;
  font-weight: 500;
  cursor: pointer;
  transition: background-color 0.2s;
}

.submit-button:hover {
  background-color: #1d4ed8;
}

.submit-button:disabled {
  background-color: #93c5fd;
  cursor: not-allowed;
}

.error-message {
  background-color: #fee2e2;
  color: #dc2626;
  padding: 12px;
  border-radius: 6px;
  margin-bottom: 16px;
}

.schedules-section {
  margin-top: 32px;
}

.section-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 16px;
  color: #1a1a1a;
}

.schedules-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
  gap: 16px;
}

.schedule-card {
  background: white;
  padding: 16px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.schedule-card h3 {
  margin: 0 0 8px 0;
  color: #2563eb;
}

.schedule-card p {
  margin: 4px 0;
  color: #4b5563;
}
</style>
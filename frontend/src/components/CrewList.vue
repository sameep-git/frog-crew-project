<template>
  <div class="crew-list">
    <h2>Crew Members</h2>
    <div v-if="error" class="error-message">
      Error: {{ error }}
    </div>
    <div v-if="loading" class="loading-message">
      Loading crew members...
    </div>
    <!-- <div v-if="debugInfo" class="debug-info">
      <p>API URL: {{ API_BASE_URL }}</p>
      <p>Response Status: {{ debugInfo.status }}</p>
      <p>Response Data: {{ JSON.stringify(debugInfo.data, null, 2) }}</p>
    </div> -->
    <ul v-if="!loading && !error">
      <li v-for="member in crewMembers" :key="member.id" @click="selectMember(member)">
        {{ member.firstName }} {{ member.lastName }} - {{ member.role }}
      </li>
      <li v-if="crewMembers.length === 0">
        No crew members found
      </li>
    </ul>

    <div v-if="selectedMember" class="crew-profile">
      <h2>{{ selectedMember.firstName }} {{ selectedMember.lastName }}</h2>
      <p><strong>Role:</strong> {{ selectedMember.role }}</p>
      <p><strong>Email:</strong> {{ selectedMember.email }}</p>
      <p><strong>Phone:</strong> {{ selectedMember.phoneNumber }}</p>
      <button @click="selectedMember = null">Close</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const API_BASE_URL = 'http://localhost:8080/frogcrew/api/v1/crew';

const crewMembers = ref([]);
const selectedMember = ref(null);
const loading = ref(true);
const error = ref(null);
const debugInfo = ref(null);

const selectMember = (member) => {
  selectedMember.value = member;
};

const fetchCrewMembers = async () => {
  loading.value = true;
  error.value = null;
  debugInfo.value = null;
  
  try {
    console.log('Fetching crew members from:', API_BASE_URL);
    const response = await fetch(API_BASE_URL, {
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    });
    
    console.log('Response status:', response.status);
    const result = await response.json();
    console.log('API Response:', result);
    
    debugInfo.value = {
      status: response.status,
      data: result
    };
    
    if (!response.ok) {
      throw new Error(`Failed to fetch crew members: ${response.status} ${response.statusText}`);
    }
    
    // Check if the response has the expected structure
    if (result && result.data) {
      crewMembers.value = result.data;
    } else {
      console.warn('Unexpected API response structure:', result);
      crewMembers.value = [];
    }
  } catch (error) {
    console.error('Error loading crew members:', error);
    error.value = error.message;
  } finally {
    loading.value = false;
  }
};

// Fetch data on mount and every 5 seconds
onMounted(() => {
  fetchCrewMembers();
  // Set up polling to refresh data every 5 seconds
  setInterval(fetchCrewMembers, 5000);
});
</script>

<style scoped>
.crew-list {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 500px;
  margin: 20px auto;
  text-align: center;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.error-message {
  color: red;
  margin: 10px 0;
}

.loading-message {
  color: #666;
  margin: 10px 0;
}

.debug-info {
  background: #f5f5f5;
  padding: 10px;
  margin: 10px 0;
  border-radius: 5px;
  font-family: monospace;
  font-size: 12px;
  text-align: left;
  width: 100%;
  overflow-x: auto;
}

ul {
  list-style: none;
  padding: 0;
  width: 100%;
}

li {
  padding: 10px;
  cursor: pointer;
  border-bottom: 1px solid #ddd;
}

li:hover {
  background: #f0f0f0;
}

.crew-profile {
  margin-top: 20px;
  padding: 15px;
  background: #f9f9f9;
  border-radius: 8px;
  width: 100%;
}

button {
  padding: 8px 12px;
  background: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin: 5px;
}

button:hover {
  background: #0056b3;
}
</style>

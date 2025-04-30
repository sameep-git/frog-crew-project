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
    <div v-if="!loading && !error" class="crew-grid">
      <div v-for="member in paginatedMembers" :key="member.id" class="crew-card" @click="selectMember(member)">
        <div class="crew-card-header">
          <div class="member-name">
            <h3>{{ member.fullName }}</h3>
            <p class="full-name">{{ member.fullName }}</p>
          </div>
          <span class="role-badge">{{ member.role }}</span>
        </div>
        <div class="crew-card-body">
          <p><i class="fas fa-envelope"></i> {{ member.email }}</p>
          <p><i class="fas fa-phone"></i> {{ member.phoneNumber }}</p>
          <div class="qualified-positions">
            <span v-for="position in member.qualifiedPosition" 
                  :key="position" 
                  class="position-tag">
              {{ position }}
            </span>
          </div>
        </div>
      </div>
      <div v-if="crewMembers.length === 0" class="no-members">
        No crew members found
      </div>
    </div>

    <!-- Pagination Controls -->
    <div v-if="crewMembers.length > 0" class="pagination">
      <button 
        @click="currentPage--" 
        :disabled="currentPage === 1"
        class="pagination-button"
      >
        Previous
      </button>
      <span class="page-info">Page {{ currentPage }} of {{ totalPages }}</span>
      <button 
        @click="currentPage++" 
        :disabled="currentPage === totalPages"
        class="pagination-button"
      >
        Next
      </button>
    </div>

    <div v-if="selectedMember" class="crew-profile-modal">
      <div class="modal-content">
        <div class="modal-header">
          <h2>{{ selectedMember.fullName }}</h2>
          <p class="full-name">{{ selectedMember.fullName }}</p>
        </div>
        <div class="profile-details">
          <p><strong>Role:</strong> {{ selectedMember.role }}</p>
          <p><strong>Email:</strong> {{ selectedMember.email }}</p>
          <p><strong>Phone:</strong> {{ selectedMember.phoneNumber }}</p>
          <div class="qualified-positions">
            <h3>Qualified Positions:</h3>
            <div class="position-tags">
              <span v-for="position in selectedMember.qualifiedPosition" 
                    :key="position" 
                    class="position-tag">
                {{ position }}
              </span>
            </div>
          </div>
        </div>
        <button @click="selectedMember = null" class="close-button">Close</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';

const API_BASE_URL = 'http://localhost:8080/frogcrew/api/v1/crew';
const ITEMS_PER_PAGE = 3;

const crewMembers = ref([]);
const selectedMember = ref(null);
const loading = ref(true);
const error = ref(null);
const debugInfo = ref(null);
const currentPage = ref(1);

// Computed properties for pagination
const totalPages = computed(() => Math.ceil(crewMembers.value.length / ITEMS_PER_PAGE));

const paginatedMembers = computed(() => {
  const start = (currentPage.value - 1) * ITEMS_PER_PAGE;
  const end = start + ITEMS_PER_PAGE;
  return crewMembers.value.slice(start, end);
});

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
      // Reset to first page when new data is loaded
      currentPage.value = 1;
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
  max-width: 1200px;
  margin: 20px auto;
  text-align: center;
  width: 100%;
}

.crew-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 20px;
  padding: 20px;
}

.crew-card {
  background: #fff;
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 15px;
  cursor: pointer;
  transition: transform 0.2s, box-shadow 0.2s;
  text-align: left;
}

.crew-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.crew-card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.crew-card-header h3 {
  margin: 0;
  color: #333;
}

.role-badge {
  background: #007bff;
  color: white;
  padding: 4px 8px;
  border-radius: 12px;
  font-size: 0.8em;
}

.crew-card-body {
  color: #666;
}

.crew-card-body p {
  margin: 5px 0;
}

.qualified-positions {
  margin-top: 10px;
}

.position-tag {
  display: inline-block;
  background: #e9ecef;
  color: #495057;
  padding: 4px 8px;
  border-radius: 4px;
  margin: 2px;
  font-size: 0.8em;
}

.crew-profile-modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 30px;
  border-radius: 10px;
  max-width: 500px;
  width: 90%;
  max-height: 90vh;
  overflow-y: auto;
}

.profile-details {
  text-align: left;
  margin: 20px 0;
}

.profile-details p {
  margin: 10px 0;
}

.position-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  margin-top: 10px;
}

.close-button {
  background: #dc3545;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 20px;
}

.close-button:hover {
  background: #c82333;
}

.error-message {
  color: #dc3545;
  margin: 10px 0;
  padding: 10px;
  background: #f8d7da;
  border-radius: 5px;
}

.loading-message {
  color: #666;
  margin: 10px 0;
}

.no-members {
  grid-column: 1 / -1;
  padding: 20px;
  color: #666;
  font-style: italic;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 20px;
  margin-top: 20px;
  padding: 10px;
}

.pagination-button {
  background: #007bff;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.2s;
}

.pagination-button:disabled {
  background: #ccc;
  cursor: not-allowed;
}

.pagination-button:not(:disabled):hover {
  background: #0056b3;
}

.page-info {
  font-size: 0.9em;
  color: #666;
}

.member-name {
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.full-name {
  color: #666;
  font-size: 0.9em;
  margin: 0;
}

.modal-header {
  text-align: center;
  margin-bottom: 20px;
}

.modal-header .full-name {
  color: #666;
  font-size: 1.1em;
  margin-top: 5px;
}
</style>

<template>
  <nav class="navbar">
    <div class="nav-center">
      <button @click="router.push('/')">Home</button>
      <button v-if="isAdmin" @click="router.push('/create-profile')">Create Profile</button>
      <button @click="router.push('/crew-list')">View Crew List</button>
      <button @click="router.push('/game-schedule')">Game Schedule</button>
      <button @click="router.push('/view-game-schedule')">View Game Schedule</button>
    </div>
    <div class="nav-right">
      <template v-if="isAuthenticated">
        <span class="user-info">{{ currentUser.firstName }} {{ currentUser.lastName }} ({{ currentUser.role }})</span>
        <button class="logout-button" @click="handleLogout">Log Out</button>
      </template>
      <template v-else>
        <button class="signup-button" @click="router.push('/create-crew')">Sign Up</button>
        <button class="login-button" @click="router.push('/login')">Log In</button>
      </template>
    </div>
  </nav>

  <router-view />
</template>
  
<script setup>
import { useRouter } from 'vue-router';
import { ref, computed, onMounted } from 'vue';

const router = useRouter();

const isAuthenticated = computed(() => {
  return localStorage.getItem('isAuthenticated') === 'true';
});

const isAdmin = computed(() => {
  return localStorage.getItem('userRole') === 'ADMIN';
});

const currentUser = computed(() => {
  const userStr = localStorage.getItem('currentUser');
  return userStr ? JSON.parse(userStr) : {};
});

const handleLogout = () => {
  localStorage.removeItem('isAuthenticated');
  localStorage.removeItem('userRole');
  localStorage.removeItem('currentUser');
  router.push('/login');
};

// Check authentication on mount
onMounted(() => {
  if (!isAuthenticated.value && router.currentRoute.value.path !== '/login') {
    router.push('/login');
  }
});
</script>

<style scoped>
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #f8f9fa;
  padding: 12px 20px;
  display: flex;
  justify-content: center;
  align-items: center;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
  z-index: 1000;
}

/* Centered buttons */
.nav-center {
  display: flex;
  gap: 12px;
}

/* Push login button to right using absolute positioning */
.nav-right {
  position: absolute;
  right: 20px;
  top: 50%;
  transform: translateY(-50%);
  display: flex;
  align-items: center;
  gap: 12px;
}

/* All nav buttons */
button {
  background: transparent;
  color: #003366;
  border: none;
  padding: 10px 16px;
  border-radius: 6px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #e2e6ea;
}

/* Special styling for login button */
.login-button {
  background-color: #003366;
  color: white;
}

.login-button:hover {
  background-color: #00264d;
}

.logout-button {
  background-color: #dc3545;
  color: white;
}

.logout-button:hover {
  background-color: #c82333;
}

.user-info {
  color: #003366;
  font-size: 14px;
  margin-right: 8px;
}

.signup-button {
  background-color: #28a745;
  color: white;
  margin-right: 8px;
}

.signup-button:hover {
  background-color: #218838;
}
</style>


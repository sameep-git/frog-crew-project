<template>
  <nav class="navbar">
    <div class="nav-center">
      <button @click="router.push('/')">Home</button>
      <button @click="router.push('/create-profile')">Create Profile</button>
      <button @click="router.push('/crew-list')">View Crew List</button>
      <button @click="router.push('/game-schedule')">Game Schedule</button>
      <button @click="router.push('/view-game-schedule')">View Game Schedule</button>
    </div>
    <div class="nav-right">
      <button class="login-button" @click="router.push('/login')">Log In</button>
    </div>
  </nav>

  <router-view />
</template>
  
  <script setup>
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
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
</style>


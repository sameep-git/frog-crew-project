<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="handleLogin" class="login-form">
      <div class="form-group">
        <label for="email">Email</label>
        <input type="text" id="email" v-model="email" required placeholder="Enter your email" />
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" required placeholder="Enter your password" />
      </div>
      <button type="submit" class="login-button">Login</button>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
// import your auth utils or mock them
// import { login, getUserRole } from '@/apis/auth'

const email = ref('')
const password = ref('')
const router = useRouter()

// Temporary mock login using localStorage
async function login(email, password) {
  const storedUsers = localStorage.getItem('users')
  if (!storedUsers) {
    return Promise.reject('No users found')
  }

  const users = JSON.parse(storedUsers)
  const user = users.find(u => u.email === email && u.password === password)
  
  // if (user) {
  //   // Store authentication state and current user info
  //   localStorage.setItem('isAuthenticated', 'true')
  //   localStorage.setItem('userRole', user.role)
  //   localStorage.setItem('currentUser', JSON.stringify({
  //     firstName: user.firstName,
  //     lastName: user.lastName,
  //     email: user.email,
  //     role: user.role,
  //     qualifiedPosition: user.qualifiedPosition
  //   }))
  //   return Promise.resolve()
  // } else {
  //   return Promise.reject('Invalid credentials')
  // }

  if (user) {
  localStorage.setItem('isAuthenticated', 'true')
  localStorage.setItem('userRole', user.role)
  localStorage.setItem('currentUser', JSON.stringify({
    firstName: user.firstName,
    lastName: user.lastName,
    email: user.email,
    role: user.role,
    qualifiedPosition: user.qualifiedPosition
  }))

  // ✅ Add this line:
  localStorage.setItem('token', 'mock-admin-token')

  return Promise.resolve()
} else {
    return Promise.reject('Invalid credentials')
  }


}

function getUserRole() {
  return localStorage.getItem('userRole') || 'USER'
}

async function handleLogin() {
  try {
    await login(email.value, password.value)
    const userRole = getUserRole()

    // Example: Add dynamic routes if needed
    if (userRole === 'ADMIN') {
      // router.addRoute(...) logic here
    }

    router.push('/')
  } catch (error) {
    console.error('Login failed:', error)
    alert('Invalid credentials')
  }
}
</script>

<style scoped>
.login-container {
  padding: 1.5rem;
  background-color: #f5f5f5;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  max-width: 400px;
  margin: 2rem auto;
  text-align: center;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  text-align: left;
}

label {
  margin-bottom: 0.5rem;
  font-size: 1rem;
  color: #555;
}

input {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 6px;
  background-color: #fff;
  transition: all 0.3s ease;
}

input:focus {
  border-color: purple;
  box-shadow: 0 0 4px rgba(0, 123, 255, 0.5);
  outline: none;
}

.login-button {
  padding: 0.75rem;
  border: none;
  border-radius: 6px;
  background-color: purple;
  color: #fff;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
}

.login-button:hover {
  background-color: #5e2a84;
  transform: translateY(-2px);
}
</style>

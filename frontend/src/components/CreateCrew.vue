<template>
  <div class="create-crew">
    <h2>Sign Up</h2>
    <div v-if="error" class="error-message">
      Error: {{ error }}
    </div>
    <form @submit.prevent="submit" class="signup-form">
      <div class="form-group">
        <label for="firstName">First Name</label>
        <input id="firstName" v-model="firstName" placeholder="Enter first name" required />
      </div>
      <div class="form-group">
        <label for="lastName">Last Name</label>
        <input id="lastName" v-model="lastName" placeholder="Enter last name" required/>
      </div>
      <div class="form-group">
        <label for="email">Email</label>
        <input id="email" v-model="email" type="email" placeholder="Enter email" required>
      </div>
      <div class="form-group">
        <label for="phoneNumber">Phone Number</label>
        <input id="phoneNumber" v-model="phoneNumber" placeholder="Enter phone number (10 digits)" pattern="\d{10}" required/>
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input id="password" v-model="password" type="password" placeholder="Enter password" required/>
      </div>
      <div class="form-group">
        <label for="role">Role</label>
        <select id="role" v-model="role" required>
          <option value="" disabled>Select Role</option>
          <option value="USER">User</option>
          <option value="ADMIN">Admin</option>
        </select>
      </div>
      <div class="form-group">
        <label for="qualifiedPosition">Qualified Position</label>
        <select id="qualifiedPosition" v-model="qualifiedPosition" required>
          <option value="" disabled>Select Qualified Position</option>
          <option v-for="position in qualifiedPositions" :key="position" :value="position">
            {{ position }}
          </option>
        </select>
      </div>
      <button type="submit" :disabled="loading" class="submit-button">
        {{ loading ? 'Creating Account...' : 'Sign Up' }}
      </button>
      <p class="login-link">
        Already have an account? <router-link to="/login">Log in</router-link>
      </p>
    </form>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const API_BASE_URL = 'http://localhost:8080/frogcrew/api/v1/crew';

const firstName = ref('');
const lastName = ref('');
const email = ref('');
const phoneNumber = ref('');
const password = ref('');
const role = ref('');
const qualifiedPosition = ref('');
const loading = ref(false);
const error = ref(null);

const qualifiedPositions = [
  'Producer',
  'Asst Prod',
  'Director',
  'Asst Director',
  'Technical Dir',
  'Graphics',
  'Bug Op',
  'Replay EVS',
  'EIC',
  'Video',
  'Audio',
  'Camera',
  'Utility',
  'Tech manager',
  'TOC',
  'Observer'
];

// Format phone number to XXX-XXX-XXXX
const formatPhoneNumber = (phoneNumber) => {
  const cleaned = phoneNumber.replace(/\D/g, '');
  const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
  if (match) {
    return `${match[1]}-${match[2]}-${match[3]}`;
  }
  return phoneNumber;
};

const submit = async () => {
  loading.value = true;
  error.value = null;
  
  try {
    const formattedPhoneNumber = formatPhoneNumber(phoneNumber.value);
    
    // Get existing users or initialize empty array
    const existingUsers = JSON.parse(localStorage.getItem('users') || '[]');
    
    // Check if user with this email already exists
    if (existingUsers.some(user => user.email === email.value)) {
      throw new Error('An account with this email already exists');
    }
    
    // Create new user info
    const newUser = {
      firstName: firstName.value,
      lastName: lastName.value,
      email: email.value,
      phoneNumber: formattedPhoneNumber,
      role: role.value,
      qualifiedPosition: [qualifiedPosition.value],
      password: password.value
    };
    
    // Add new user to array
    existingUsers.push(newUser);
    
    // Store updated users array in localStorage
    localStorage.setItem('users', JSON.stringify(existingUsers));
    
    // Try to create user in backend
    try {
      const response = await fetch(API_BASE_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json'
        },
        body: JSON.stringify({
          firstName: firstName.value,
          lastName: lastName.value,
          email: email.value,
          phoneNumber: formattedPhoneNumber,
          password: password.value,
          role: role.value,
          qualifiedPosition: [qualifiedPosition.value]
        })
      });
      
      if (!response.ok) {
        const result = await response.json();
        throw new Error(result.message || `Failed to create account: ${response.status} ${response.statusText}`);
      }
    } catch (backendError) {
      console.error('Backend error:', backendError);
      // Continue with local storage even if backend fails
    }
    
    // Clear form
    firstName.value = '';
    lastName.value = '';
    email.value = '';
    phoneNumber.value = '';
    password.value = '';
    role.value = '';
    qualifiedPosition.value = '';
    
    // Show success message and redirect to login
    alert('Account created successfully! Please log in.');
    router.push('/login');
    
  } catch (err) {
    console.error('Error creating account:', err);
    error.value = err.message;
  } finally {
    loading.value = false;
  }
};
</script>

<style scoped>
.create-crew {
  background: #fff;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 500px;
  margin: 120px auto 24px;
  text-align: center;
}

h2 {
  color: #333;
  margin-bottom: 1.5rem;
}

.signup-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  text-align: left;
  gap: 0.5rem;
}

label {
  font-weight: 500;
  color: #555;
}

input, select {
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 6px;
  font-size: 1rem;
  transition: all 0.3s ease;
}

input:focus, select:focus {
  border-color: #28a745;
  box-shadow: 0 0 0 2px rgba(40, 167, 69, 0.25);
  outline: none;
}

.submit-button {
  padding: 0.75rem;
  background: #28a745;
  color: white;
  border: none;
  border-radius: 6px;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-top: 1rem;
}

.submit-button:hover:not(:disabled) {
  background: #218838;
  transform: translateY(-1px);
}

.submit-button:disabled {
  background: #ccc;
  cursor: not-allowed;
}

.error-message {
  color: #dc3545;
  background: #f8d7da;
  padding: 0.75rem;
  border-radius: 6px;
  margin-bottom: 1rem;
}

.login-link {
  margin-top: 1rem;
  color: #666;
}

.login-link a {
  color: #28a745;
  text-decoration: none;
  font-weight: 500;
}

.login-link a:hover {
  text-decoration: underline;
}
</style>
  
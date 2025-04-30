<template>
   

      <div class="create-crew">
        <h2>Create Crew Member</h2>
        <div v-if="error" class="error-message">
          Error: {{ error }}
        </div>
        <form @submit.prevent="submit">
          <input v-model="firstName" placeholder="Enter first name" required />
          <input v-model="lastName" placeholder="Enter last name" required/>
          <input v-model="role" placeholder="Enter role" required />
          <input v-model="email" placeholder="Enter email" required>
          <input v-model="phoneNumber" placeholder="Enter phone number" pattern="\d{10}" required/>
          <input v-model="password" type = "password" placeholder="Enter password" required/>
          <select v-model="qualifiedPosition" required class="form-select">
            <option value="" disabled>Select Qualified Position</option>
            <option v-for="position in qualifiedPositions" :key="position" :value="position">
              {{ position }}
            </option>
          </select>
          <button type="submit" :disabled="loading">{{ loading ? 'Adding...' : 'Add Crew Member' }}</button>
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
    // Remove all non-digit characters
    const cleaned = phoneNumber.replace(/\D/g, '');
    // Format as XXX-XXX-XXXX
    const match = cleaned.match(/^(\d{3})(\d{3})(\d{4})$/);
    if (match) {
      return `${match[1]}-${match[2]}-${match[3]}`;
    }
    return phoneNumber; // Return original if format doesn't match
  };
  
  const submit = async () => {
    loading.value = true;
    error.value = null;
    
    try {
      // Format phone number before sending
      const formattedPhoneNumber = formatPhoneNumber(phoneNumber.value);
      
      console.log('Sending request to:', API_BASE_URL);
      console.log('Request payload:', {
        firstName: firstName.value,
        lastName: lastName.value,
        email: email.value,
        phoneNumber: formattedPhoneNumber,
        role: role.value,
        qualifiedPosition: qualifiedPosition.value
      });

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
      
      console.log('Response status:', response.status);
      console.log('Response headers:', Object.fromEntries(response.headers.entries()));
      
      const result = await response.json();
      console.log('Response data:', result);
      
      if (!response.ok) {
        console.error('API Error:', {
          status: response.status,
          statusText: response.statusText,
          data: result
        });
        throw new Error(result.message || `Failed to create crew member: ${response.status} ${response.statusText}`);
      }
      
      // Clear form
      firstName.value = '';
      lastName.value = '';
      email.value = '';
      phoneNumber.value = '';
      password.value = '';
      role.value = '';
      qualifiedPosition.value = '';
      
      // Redirect to crew list
      router.push('/crew-list');
      
    } catch (err) {
      console.error('Error creating crew member:', {
        message: err.message,
        stack: err.stack,
        name: err.name
      });
      error.value = err.message;
    } finally {
      loading.value = false;
    }
  };
  </script>
  
  <style scoped>
  .page-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    height: calc(100vh - 64px); /* Adjust if your navbar has a different height */
    margin-top: 64px; /* Same height as your fixed navbar */
    background-color: #f5f5f5;
  }
  
  .create-crew {
    background: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    max-width: 800px;
    margin: 20px auto;
    text-align: center;
  }
  
  h2 {
    color: #333;
  }
  
  form {
    display: flex;
    flex-direction: column;
  }
  
  input, .form-select {
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ddd;
    border-radius: 5px;
  }
  
  .form-select {
    background-color: white;
    cursor: pointer;
  }
  
  button {
    padding: 10px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:disabled {
    background: #ccc;
    cursor: not-allowed;
  }
  
  button:hover:not(:disabled) {
    background: #0056b3;
  }
  
  .error-message {
    color: red;
    margin: 10px 0;
  }
  </style>
  
<template>
    <div class="create-crew">
      <h2>Create Crew Member</h2>
      <form @submit.prevent="submit">
        <input v-model="firstName" placeholder="Enter first name" required />
        <input v-model="lastName" placeholder="Enter last name" required/>
        <input v-model="role" placeholder="Enter role" required />
        <input v-model="email" placeholder="Enter email" required>
        <input v-model="phoneNumber" placeholder="Enter phone number (999-999-9999)" pattern="\d{10}" required/>
        <input v-model="password" type = "password" placeholder="Enter password" required/>
        <button type="submit">Add Crew Member</button>
      </form>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  defineEmits(['addCrewMember']);
  
    const firstName = ref('');
    const lastName = ref('');
    const email = ref('');
    const phoneNumber = ref('');
    const password = ref('');
    const role = ref('');
  
  const submit = () => {
    emit('addCrewMember', { firstName: firstName.value, lastName: lastName.value, email: email.value, phoneNumber: phoneNumber.value, password: password.value, role: role.value });
    firstName.value = '';
    lastName.value = '';
    email.value = '';
    phoneNumber.value = '';
    password.value = '';
    role.value = '';
  };
  </script>
  
  <style scoped>
  .create-crew {
    background: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    max-width: 400px;
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
  
  input {
    padding: 10px;
    margin: 10px 0;
    border: 1px solid #ddd;
    border-radius: 5px;
  }
  
  button {
    padding: 10px;
    background: #007bff;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  
  button:hover {
    background: #0056b3;
  }
  </style>
  
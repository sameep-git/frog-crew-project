<template>
    <nav class="navbar">
      <button @click="router.push('/')">Home</button>
      <button @click="router.push('/create-profile')">Create Profile</button>
      <button @click="router.push('/crew-list')">View Crew List</button>
    </nav>
    
    <router-view />
  </template>
  
  <script setup>
  import { useRouter } from 'vue-router';
  
  const router = useRouter();
  </script>
  
  <style scoped>
  .navbar {
    display: flex;
    justify-content: center;
    background: #007bff;
    padding: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    position: fixed;
    width: 100%;
    top: 0;
    left: 0;
    z-index: 1000;
  }
  
  button {
    background: white;
    color: #007bff;
    border: none;
    padding: 10px 20px;
    margin: 0 10px;
    border-radius: 5px;
    cursor: pointer;
    font-size: 16px;
    font-weight: bold;
  }
  
  button:hover {
    background: #f0f0f0;
  }
  </style>
  
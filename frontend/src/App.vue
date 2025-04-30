<template>
  <NavBar />
 
</template>

<script setup>
import { ref } from 'vue';

import NavBar from './components/NavBar.vue';

const crewMembers = ref([]);

const addCrewMember = (member) => {
  crewMembers.value.push(member);
};
</script> 



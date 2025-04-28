<template>
  <NavBar />
  <!-- <CreateCrew @addCrewMember="addCrewMember" />
  <CrewList :crewMembers="crewMembers" /> -->
</template>

<script setup>
import { ref } from 'vue';

import NavBar from './components/NavBar.vue';

const crewMembers = ref([]);

const addCrewMember = (member) => {
  crewMembers.value.push(member);
};
</script> 

<!-- 
<template>
  <NavBar />
  <router-view />
</template>

<script setup>
import NavBar from './components/NavBar.vue';
</script>
 -->

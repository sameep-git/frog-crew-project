<template>
  <div class="crew-list">
    <h2>Crew Members</h2>
    <ul>
      <li v-for="member in crewMembers" :key="member.id" @click="selectMember(member)">
        {{ member.firstName }} {{ member.lastName }} - {{ member.role }}
      </li>
    </ul>

    <div v-if="selectedMember" class="crew-profile">
      <h2>{{ selectedMember.firstName }} {{ selectedMember.lastName }}</h2>
      <p><strong>Role:</strong> {{ selectedMember.role }}</p>
      <p><strong>Email:</strong> {{ selectedMember.email }}</p>
      <p><strong>Phone:</strong> {{ selectedMember.phoneNumber }}</p>
      <button @click="selectedMember = null">Close</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const crewMembers = ref([
  { id: 1, firstName: 'John', lastName: 'Doe', role: 'Pilot', email: 'john@example.com', phoneNumber: '123-456-789' },
  { id: 2, firstName: 'Jane', lastName: 'Smith', role: 'Engineer', email: 'jane@example.com', phoneNumber: '987-654-321' }
]);

const selectedMember = ref(null);

const selectMember = (member) => {
  selectedMember.value = member;
};
</script>

<style scoped>
.crew-list {
  background: #fff;
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  max-width: 500px;
  margin: 20px auto;
  text-align: center;
}

ul {
  list-style: none;
  padding: 0;
}

li {
  padding: 10px;
  cursor: pointer;
  border-bottom: 1px solid #ddd;
}

li:hover {
  background: #f0f0f0;
}

.crew-profile {
  margin-top: 20px;
  padding: 15px;
  background: #f9f9f9;
  border-radius: 8px;
}

button {
  padding: 8px 12px;
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

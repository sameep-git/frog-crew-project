
import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import CreateCrew from '../components/CreateCrew.vue';
import CrewList from '../components/CrewList.vue';

const routes = [
  { path: '/', component: HomePage },
  { path: '/create-profile', name: "CreateCrew", component: CreateCrew },
  { path: '/crew-list', component: CrewList }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;


import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../components/HomePage.vue';
import CreateCrew from '../components/CreateCrew.vue';
import CrewList from '../components/CrewList.vue';
import GameSchedule from '@/components/GameSchedule.vue';
import ViewGameSchedule from '../components/ViewGameSchedule.vue';

const routes = [
  { path: '/', component: HomePage },
  { path: '/create-profile', name: "CreateCrew", component: CreateCrew },
  { path: '/crew-list', component: CrewList },
  { path: '/view-game-schedule', component: ViewGameSchedule },
  { path: '/game-schedule', component: GameSchedule},
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;

// import './assets/main.css'

// import { createApp } from 'vue'
// import App from './App.vue'


// createApp(App).mount('#app')
// @tailwind base;
// @tailwind components;
// @tailwind utilities;
import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'  // <-- Import the router

const app = createApp(App)

app.use(router)                // <-- Tell Vue to use the router
app.mount('#app')


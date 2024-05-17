import { createApp } from 'vue' //Da error pero ni puta caso funciona
import { createPinia } from 'pinia' //Da error pero ni puta caso funciona
import router from './router' // Asegúrate de que la ruta es correcta

import App from './App.vue' 

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')
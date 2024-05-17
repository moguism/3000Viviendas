

import { createApp } from 'vue' //Da error pero ni puta caso funciona
import { createPinia } from 'pinia' //Da error pero ni puta caso funciona
import App from './App.vue' 

const app = createApp(App)

app.use(createPinia())

app.mount('#app')

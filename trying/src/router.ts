import { createRouter, createWebHistory } from 'vue-router'
import Home from './components/Home.vue'
import Comunidad from './components/Comunidad.vue'
import Local from './components/Local.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/comunidad/:id',
    name: 'Comunidad',
    component: Comunidad,
    props: true
  },
  {
    path: '/locales/:localesString',
    name: 'Local',
    component: Local,
    props: true
  },  
]

const router = createRouter({
  history: createWebHistory('/'), 
  routes
})

export default router

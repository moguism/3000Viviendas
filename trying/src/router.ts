import { createRouter, createWebHistory } from 'vue-router'
import Home from './components/Home.vue'
import Comunidad from './components/Comunidad.vue'

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
  }
]

const router = createRouter({
  history: createWebHistory('/'), 
  routes
})

export default router

import { createRouter, createWebHistory } from 'vue-router'
import Home from './components/Home.vue'
import Bloque from './components/Bloque.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/bloque/:id',
    name: 'Bloque',
    component: Bloque,
    props: true
  }
]

const router = createRouter({
  history: createWebHistory('/'), // Puedes cambiar '/' por la base URL de tu aplicación si es necesario
  routes
})

export default router

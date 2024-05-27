import { createRouter, createWebHistory } from 'vue-router'
import Home from './components/Home.vue'
import Comunidad from './components/Comunidad.vue'
import Local from './components/Local.vue'
import Contrato from './components/Contrato.vue'
import Bloque from './components/Bloque.vue'
import Mensualidad from './components/Mensualidad.vue'

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
    path: '/locales/:comunidad_id',
    name: 'Local',
    component: Local,
    props: true
  },
  {
    path: '/contratos/:comunidad_id',
    name: 'Contrato',
    component: Contrato,
    props: true
  },
  {
    path: '/bloque/:bloque_id',
    name: 'Bloque',
    component: Bloque,
    props: true
  },
  {
    path: '/bloque/:bloque_id/mensualidad/:mensualidad_id',
    name: 'Mensualidad',
    component: Mensualidad,
    props: true
  }  
]

const router = createRouter({
  history: createWebHistory('/'), 
  routes
})

export default router

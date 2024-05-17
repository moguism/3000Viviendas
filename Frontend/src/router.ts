import Vue from 'vue';
import VueRouter, { RouteConfig } from 'vue-router';
import Comunidad from './components/Comunidad.vue';
import Home from './components/Home.vue';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  { path: '/', component: Home },
  { path: '/comunidad', component: Comunidad }
];

const router = new VueRouter({
  mode: 'history',
  routes
});

export default router;
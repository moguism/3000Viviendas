
<template>
    <p v-if="loading">Cargando datos...</p>
  
    <div id ="Comunidades" v-else>
      <div class="Comunidad" v-for="(community) in communities" :key="community.id">
        <img class="imgComunidad" src="../assets/building.svg" @click="CargarComunidad(community.id)">
        <p style="display:none;">{{community.id}}</p>
        <h3 class="nombreComunidad">{{ community.nombre }}</h3>
        <p class="dirComunidad">{{ community.direccion }}</p>
      </div>
    </div>
  </template>
  
  <script lang="ts" setup>
 
  import { ref, onMounted } from 'vue'
  import type ICommunity from '../interfaces/ICommunity.ts' 
  import type { Ref } from 'vue'
  import CommunityService from '../services/CommunityService'
  import VueRouter from 'vue-router'
import Comunidad from './Comunidad.vue'
  
  const communities: Ref<Array<ICommunity>> = ref([])
  const communityService = new CommunityService()
  const loading = ref(true)
  
  onMounted(async () => {
    loading.value = true
    communities.value = await communityService.listAllCommunities()
    loading.value = false
  })
  
  const CargarComunidad = (id:number) => { 
    const router = [{path: "./Comunidad.vue",name: "Comunidad.vue",component: Comunidad,},];
    // Redirigir hacia Comunidad.vue con el parámetro de id
    this.$router.push({ name: 'Comunidad', params: { id } })
  }
  export default router;

  
  </script>
  <style>
  /*Deberiamos de ponerle al div un borde, que sea clickable y te mande al bloque/bloques de la comunidad*/ 
</style>
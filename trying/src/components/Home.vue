<template>
  <p v-if="loading">Cargando datos...</p>

  <div id="Comunidades" v-else>
    <div class="Comunidad" v-for="community in communities" :key="community.id">
      <img class="imgComunidad" src="../assets/building.svg" @click="CargarComunidad(community.id)">
      <p class="idComunidad">{{ community.id }}</p>
      <h3 class="nombreComunidad">{{ community.nombre }}</h3>
      <p class="dirComunidad">{{ community.direccion }}</p>
    </div>
  </div>
  <div id="insercion">
    <input type="text" v-model="name">
    <input type="text" v-model="address">
    <button @click="CrearComunidad">Pulsa para crear comunidad</button>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import type ICommunity from '../interfaces/ICommunity.ts' 
import type { Ref } from 'vue'
import CommunityService from '../services/CommunityService'
import { useRouter } from 'vue-router'

const name = ref('')
const address = ref('')

const communities: Ref<Array<ICommunity>> = ref([])
const communityService = new CommunityService()
const loading = ref(true)

const router = useRouter()

const fetchCommunities = async () => {
  loading.value = true
  communities.value = await communityService.listAllCommunities()
  loading.value = false
}

onMounted(fetchCommunities)

const CargarComunidad = (id: number) => {
  router.push({ name: 'Bloque', params: { id } })
}

const CrearComunidad = async () => {
  if(!name.value || !address.value) return
  const response = await communityService.createCommunity(name.value, address.value)
  console.log(response)
  await fetchCommunities() // Actualiza el listado de comunidades después de crear una nueva
}
</script>

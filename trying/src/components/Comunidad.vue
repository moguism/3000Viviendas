<template>
  <div>
    <h1>Comunidad ID: {{ id }}</h1>
    <p v-if="loading">Cargando datos...</p>
    <div v-else>
      <p>Hola</p>
      <h2>{{ nombre }}</h2>
      <h3>{{ direccion }}</h3>
    </div>
  </div>
</template>
  
<script lang="ts" setup>
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue' 
import type ICommunity from '../interfaces/ICommunity.ts' 
import type { Ref } from 'vue'
import CommunityService from '../services/CommunityService'
import { useRouter } from 'vue-router'
  
const route = useRoute()
const { id } = toRefs(route.params)

const community = ref()
const nombre = ref('')
const direccion = ref('')

const communityService = new CommunityService()
const loading = ref(true)

const fetchCommunities = async () => {
  loading.value = true
  community.value = await communityService.listCommunityById(Number(id.value))
  console.log(community.value.nombre)
  console.log(community.value.direccion)
  nombre.value = community.value.nombre
  direccion.value = community.value.direccion
  loading.value = false
}

onMounted(fetchCommunities)

</script>
  
<style>
</style>
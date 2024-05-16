<template>
  <p v-if="loading">Cargando datos...</p>
  <ul v-else>
    <li v-for="(community, index) in communities" :key="index">
      <h3>{{ community.nombre }}</h3>
      <p>{{ community.direccion }}</p>
    </li>
  </ul>

</template>

<script lang="ts" setup>

import { ref, onMounted } from 'vue'
import type ICommunity from './interfaces/ICommunity.ts'
import type { Ref } from 'vue'
import CommunityService from './services/CommunityService'

const communities:Ref<Array<ICommunity>>= ref([])
const communityService = new CommunityService()
const loading = ref(true)

onMounted( async () => {
  loading.value = true
  communities.value = await communityService.listAllCommunities()
  loading.value = false
})


</script>
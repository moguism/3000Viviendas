<template>
  <div>
    <h1>Comunidad ID: {{ id }}</h1>
    <div>
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
const communityService = new CommunityService()
const loading = ref(true)

const fetchCommunities = async () => {
  loading.value = true
  community.value = await communityService.listCommunityById(Number(id))
  console.log(community.value)
  loading.value = false
}

onMounted(fetchCommunities)

</script>
  
<style>
</style>
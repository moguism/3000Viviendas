<template>
    <div>
      <h1>Bloque ID: {{ id }}</h1>

    </div>
  </template>
  
  <script lang="ts" setup>
  import { toRefs } from 'vue'
  import { useRoute } from 'vue-router'
  
  const route = useRoute()
  const { id } = toRefs(route.params)
  

  </script>
  
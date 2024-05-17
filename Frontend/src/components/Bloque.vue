<template>
    <div>
      <h1>Bloque ID: {{ id }}</h1>
      <!-- Aquí puedes cargar los detalles del bloque usando el id -->
    </div>
  </template>
  
  <script lang="ts" setup>
  import { toRefs } from 'vue'
  import { useRoute } from 'vue-router'
  
  const route = useRoute()
  const { id } = toRefs(route.params)
  
  // Ahora puedes usar `id` en tu componente para cargar los datos necesarios
  </script>
  
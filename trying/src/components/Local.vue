<template>
  <h1 v-if="valido && !loading">
    <div id="Locales">
      <div class="Local" v-for="local in locales" :key="local.id">
        <h1>ID: {{ local.id }}</h1>
        <h2>Nombre: {{ local.nombre }}</h2>
        <h3>Tipo de Local: {{ tipoLocal }}</h3>
      </div>
    </div>
  </h1>
  <h1 v-else>Cargando datos</h1>
</template>

<script lang="ts" setup>
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import type { Ref } from 'vue'
import { useRouter } from 'vue-router'
import TipoLocalService from '@/services/TipoLocalService'

const route = useRoute()
const { localesString } = toRefs(route.params)

const locales = ref()
const tipoLocal = ref()

const valido = ref(false)
const loading = ref(true)

const localService = new TipoLocalService()

if (typeof localesString.value === 'string') {
  locales.value = JSON.parse(localesString.value)
  valido.value = true
} else if (Array.isArray(localesString.value)) {
  locales.value = localesString.value.map(item => JSON.parse(item))
  valido.value = true
} else {
  locales.value = {}
  console.log(localesString)
}

async function getTipo(id: number) {
  const tipos = await localService.listAllTiposLocales()
  for (const tipo of tipos) {
    for (const local of tipo.locales) {
      if (local.id == id) {
        console.log(tipo.nombre)
        return tipo.nombre
      }
      console.log(locales.value)
    }
  }
}

onMounted(async () => {
  for (const local of locales.value) {
    tipoLocal.value = await getTipo(local.id)
  }
  loading.value = false
})

</script>
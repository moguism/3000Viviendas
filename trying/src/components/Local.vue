<template>
  <div>
    <h1 v-if="!loading" class="container">
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/comprador-de-tienda.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="text" placeholder="Nombre" v-model="nombreInsertar">
            <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
            <button class="actionButton" @click="CrearLocal">Crear Local</button>
          </div>
        </div>
        <div class="communityList">
          <div class="community" v-for="local in locales" :key="local.id">
            <img class="communityIcon" src="../assets/tienda-alt.svg" alt="Community Icon">
            <div class="info">
              <p class="communityID">ID: {{ local.id }}</p>
              <h3 class="communityName">Nombre: {{ local.nombre }}</h3>
              <p class="communityType">Tipo de Local: {{ local.nombreTipo }}</p>
            </div>
            <div class="actions">
              <button @click="BorrarLocal(local.id)">Borrar</button>
              <button @click="ModificarLocal(local.id)">Modificar</button>
            </div>
          </div>
        </div>
      </div>
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/vendedor.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="text" placeholder="Nombre" v-model="nombreTipo">
            <button class="actionButton" @click="CrearTipoLocal">Crear Tipo de Local</button>
          </div>
        </div>
        <div class="typeList">
          <div class="type" v-for="tipoLocal in tipoLocales" :key="tipoLocal.id">
            <img class="typeIcon" src="../assets/tienda-alt.svg" alt="Type Icon">
            <div class="info">
              <p class="typeID">ID: {{ tipoLocal.id }}</p>
              <h3 class="typeName">Nombre: {{ tipoLocal.nombre }}</h3>
            </div>
            <div class="actions">
              <button @click="BorrarTipoLocal(tipoLocal.id)">Borrar</button>
              <button @click="ModificarTipoLocal(tipoLocal.id)">Modificar</button>
            </div>
          </div>
        </div>
      </div>
    </h1>
    <h1 v-else>Cargando datos</h1>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
}

.section {
  flex: 1;
  margin-right: 20px;
}

.addCommunity {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.addIcon {
  width: 50px;
  margin-right: 10px;
}

.insertion {
  display: flex;
  align-items: center;
}

.insertField {
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-right: 10px;
  padding: 5px;
}

.actionButton {
  background-color: #f59550;
  border: 1px solid #f59550;
  color: white;
  border-radius: 4px;
  padding: 5px 10px;
  cursor: pointer;
}

.community {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 10px;
}

.communityIcon {
  width: 50px;
  margin-right: 10px;
}

.info {
  flex: 1;
}

.actions button {
  background-color: #f59550;
  border: 1px solid #f59550;
  color: white;
  border-radius: 4px;
  padding: 5px 10px;
  cursor: pointer;
  margin-left: 10px;
}

.typeList {
  margin-top: 40px;
}

.type {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 10px;
}

.typeIcon {
  width: 50px;
  margin-right: 10px;
}

</style>

<script setup lang="ts">
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import TipoLocalService from '@/services/TipoLocalService'
import CommunityService from '../services/CommunityService'
import LocalService from '@/services/LocalService'

const communityService = new CommunityService()
const localService = new LocalService()
const tipoLocalService = new TipoLocalService()

const route = useRoute()
const { comunidad_id } = toRefs(route.params)

const locales = ref()
const tipoLocales = ref()

const nombreInsertar = ref('')
const tipoInsertar = ref('')

const nombreTipo = ref('')

const loading = ref(true)

const getTipo = async (id: number) => {
  const tipos = await tipoLocalService.listAllTiposLocales()
  for (const tipo of tipos) {
    for (const local of tipo.locales) {
      if (local.id == id) {
        console.log(tipo.nombre)
        return tipo.nombre
      }
    }
  }
}

const fetchLocales = async () => {
  const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))
  locales.value = comunidad.locales
  for (const local of locales.value) {
    local.nombreTipo = await getTipo(local.id)
  }
  fetchTipoLocales()
}

const fetchTipoLocales = async () => {
  tipoLocales.value = await tipoLocalService.listAllTiposLocales()
  loading.value = false
}

onMounted(fetchLocales)

const BorrarLocal = async (id: number) => {
  await localService.deleteLocal(id)
  await fetchLocales()
}

const BorrarTipoLocal = async (id: number) => {
  await tipoLocalService.deleteTipoLocal(id)
  await fetchLocales()
}

const ModificarLocal = async (id: number) => {
  let nombre = prompt('Introduce el nuevo nombre del local')
  while (!nombre) {
    alert('No puede haber campos vacios')
    return
  }
  let tipoLocal = prompt('Introduce el nuevo tipo de local')
  let tipoModificar: any = 0
  let valido = false
  for (const tipo of tipoLocales.value) {
    if (tipoLocal == tipo.nombre) {
      tipoModificar = tipo
      valido = true
      break
    }
  }
  while (!tipoLocal || !valido) {
    alert('No puede haber campos vacios o no existe')
    return
  }

  const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))

  const response = await localService.updateLocal(id, nombre, comunidad, tipoModificar)
  console.log(response)
  await fetchLocales()

}

const ModificarTipoLocal = async (id: number) => {
  let nombre = prompt('Introduce el nuevo nombre del tipo de local')
  while (!nombre) {
    alert('El nombre no puede estar vacío')
    return    
  }
  let valido = true
  for (const tipo of tipoLocales.value) {
    if (nombre == tipo.nombre) {
      valido = false
      break
    }
  }
  if (!valido) {
    alert('El tipo ya existe')
    return
  }
  let tipo = await tipoLocalService.listTipoLocalById(id)
  let locales: any = tipo.locales
  const response = await tipoLocalService.updateTipoLocal(id, nombre, locales)
  console.log(response)
  await fetchLocales()
}

const CrearLocal = async () => {
  if (!nombreInsertar.value || !tipoInsertar.value) {
    alert('No puede haber campos vacios')
    return
  }
  let valido = false
  let tipoId = 0
  for (const tipo of tipoLocales.value) {
    if (tipoInsertar.value == tipo.nombre) {
      tipoId = tipo.id
      valido = true
      break
    }
  }
  if (!valido) {
    alert('El tipo no existe')
    return
  }

  const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))
  const tipo = await tipoLocalService.listTipoLocalById(tipoId)
  const response = await localService.createLocal(nombreInsertar.value, comunidad, tipo)
  console.log(response)
  await fetchLocales()
}

const CrearTipoLocal = async () => {
  if (!nombreTipo.value) {
    alert('No puede haber campos vacios')
    return
  }
  let valido = true
  for (const tipo of tipoLocales.value) {
    if (nombreTipo.value == tipo.nombre) {
      valido = false
      break
    }
  }
  if (!valido) {
    alert('El tipo ya existe')
    return
  }
  let locales: any = [] // Aquí sí va vacío porque lo estoy creando
  const response = await tipoLocalService.createTipoLocal(nombreTipo.value, locales)
  console.log(response)
  await fetchTipoLocales()
}

</script>

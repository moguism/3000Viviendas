<template>
  <Header></Header>
  <div>
    <h1 v-if="!loading" class="container">
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/reunion.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="text" placeholder="Descripción" v-model="descriptionPrompt">
            <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
            <input class="insertField" type="date" placeholder="Fecha" v-model="fechaReunionInsertar">
            <button class="actionButton" @click="CrearReunion">Crear Reunion</button>
          </div>
        </div>
 
        <div class="communityList">
          <div class="community" v-for="reunion in reuniones" :key="reunion.id">
            <img class="communityIcon" src="../assets/respuesta-alternativa.svg" alt="Community Icon">
            <div class="info">
              <h3 class="communityName">Descripción: {{ reunion.descripcion }}</h3>
              <p class="communityType">Tipo de Reunión: {{ reunion.nombreTipo }}</p>
              <p class="communityType">Fecha: {{ stringAfecha(reunion.fecha) }}</p>
            </div>
            <div class="actions">
              <button @click="BorrarReunion(reunion.id)">Borrar</button>
              <button @click="ModificarReunion(reunion.id)">Modificar</button>
            </div>
          </div>
        </div>
      </div>
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/meeting_1_.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="text" placeholder="Nombre" v-model="nombreTipo">
            <button class="actionButton" @click="CrearTipoReunion">Crear Tipo de Reunión</button>
          </div>
        </div>
        <div class="typeList">
          <div class="type" v-for="tipoReunion in tipoReuniones" :key="tipoReunion.id">
            <img class="typeIcon" src="../assets/meeting.svg" alt="Type Icon">
            <div class="info">
              <h3 class="typeName">Nombre: {{ tipoReunion.nombre }}</h3>
            </div>
            <div class="actions">
              <button @click="BorrarTipoReunion(tipoReunion.id)">Borrar</button>
              <button @click="ModificarTipoReunion(tipoReunion.id)">Modificar</button>
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
import TipoReunionService from '@/services/TipoReunionService'
import BloqueService from '../services/BloqueService'
import ReunionService from '@/services/ReunionService'
import Header from './Header.vue' 

const bloqueService = new BloqueService()
const reunionService = new ReunionService()
const tipoReunionService = new TipoReunionService()
 
const route = useRoute()
const { bloque_id1 } = toRefs(route.params)
 
const reuniones = ref()
const tipoReuniones = ref()
 
const descriptionPrompt = ref('')
const tipoInsertar = ref('')
const fechaReunionInsertar = ref('')
 
const nombreTipo = ref('')
 
const loading = ref(true)

const stringAfecha = (str: Date) => {
    const date = new Date(str);
    return date.toLocaleDateString('en-CA'); // Esto devuelve el formato 'YYYY-MM-DD'
}

const getTipo = async (id: number) => {
  const tipos = await tipoReunionService.listAllTiposReunion()
  for (const tipo of tipos) {
    for (const reunion of tipo.reuniones) {
      if (reunion.id == id) {
        console.log(tipo.nombre)
        return tipo.nombre
      }
    }
  }
}
 
const fetchReuniones = async () => {
  const bloque = await bloqueService.listBloqueById(Number(bloque_id1.value))
  console.log(bloque)
  reuniones.value = bloque.reuniones
  for (const reunion of reuniones.value) {
    reunion.nombreTipo = await getTipo(reunion.id)
  }
  fetchTipoReuniones()
}
 
const fetchTipoReuniones = async () => {
  tipoReuniones.value = await tipoReunionService.listAllTiposReunion()
  loading.value = false
}
 
onMounted(fetchReuniones)
 
const BorrarReunion = async (id: number) => {
  await reunionService.deleteReunion(id)
  await fetchReuniones()
}
 
const BorrarTipoReunion = async (id: number) => {
  await tipoReunionService.deleteTipoReunion(id)
  await fetchReuniones()
}
 
const ModificarReunion = async (id: number) => {
  let tipoReunionPrompt = prompt('Introduce el tipo de reunión')
  let descriptionPrompt = prompt('Introduce la descripcion de la reunión')
  if (!descriptionPrompt || !tipoReunionPrompt) {
    alert('No puede haber campos vacios')
    return
  }
  let fechaReunionInsertar = prompt('Introduce la nueva fecha de la reunión (AAAA-MM-DD)')
  if (!fechaReunionInsertar) {
    alert('No puede haber campos vacios')
    return
  }
  let fechaReunion = new Date(fechaReunionInsertar)
  let valido = false
  let tipoId
  for (const tipoReunion of tipoReuniones.value) {
    if (tipoReunionPrompt == tipoReunion.nombre) {
      tipoId = tipoReunion.id
      valido = true
      break
    }
  }
  if (!valido) {
    alert('El tipo no existe')
    return
  }
 
  const bloque = await bloqueService.listBloqueById(Number(bloque_id1.value))
  const tipoInsertar = await tipoReunionService.listTipoReunionById(tipoId)
  const response = await reunionService.updateReunion(id, tipoInsertar, fechaReunion, descriptionPrompt, bloque)
  console.log(response)
  await fetchReuniones()
 
}
 
const ModificarTipoReunion = async (id: number) => {
  let nombre = prompt('Introduce el nuevo nombre del tipo de reunión')
  while (!nombre) {
    alert('El nombre no puede estar vacío')
    return
  }
  let valido = true
  for (const tipo of tipoReuniones.value) {
    if (nombre == tipo.nombre) {
      valido = false
      break
    }
  }
  if (!valido) {
    alert('El tipo ya existe')
    return
  }
  let tipo = await tipoReunionService.listTipoReunionById(id)
  let reuniones: any = tipo.reuniones
  const response = await tipoReunionService.updateTipoReunion(id, nombre, reuniones)
  console.log(response)
  await fetchReuniones()
}
 
const CrearReunion = async () => {
  if (!descriptionPrompt || !tipoInsertar || !fechaReunionInsertar) {
    alert('No puede haber campos vacios')
    return
  }

  let fechaReunion = new Date(fechaReunionInsertar.value)
  let valido = false
  let tipoId
  for (const tipoReunion of tipoReuniones.value) {
    if (tipoInsertar.value == tipoReunion.nombre) {
      tipoId = tipoReunion.id
      valido = true
      break
    }
  }
  if (!valido) {
    alert('El tipo no existe')
    return
  }
 
  const bloque = await bloqueService.listBloqueById(Number(bloque_id1.value))
  const tipo = await tipoReunionService.listTipoReunionById(tipoId)
  const response = await reunionService.createReunion(tipo, fechaReunion, descriptionPrompt.value, bloque)
  console.log(response)
  await fetchReuniones()
}
 
const CrearTipoReunion = async () => {
  if (!nombreTipo.value) {
    alert('No puede haber campos vacios')
    return
  }
  let valido = true
  for (const tipo of tipoReuniones.value) {
    if (nombreTipo.value == tipo.nombre) {
      valido = false
      break
    }
  }
  if (!valido) {
    alert('El tipo ya existe')
    return
  }
  let reuniones: any = [] // Aquí sí va vacío porque lo estoy creando
  const response = await tipoReunionService.createTipoReunion(nombreTipo.value, reuniones)
  console.log(response)
  await fetchTipoReuniones()
}
 
</script>
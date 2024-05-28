<template>
    <div>
      <h1 v-if="!loading" class="container">
        <div class="section">
          <div class="addCommunity">
            <img class="addIcon" src="../assets/reunion.svg" alt="Add Community">
            <div class="insertion">
              <input class="insertField" type="text" placeholder="Nombre" v-model="nombreInsertar">
              <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
              <input class="insertField" type="date" placeholder="Fecha" v-model="fechaInsertar">
              <button class="actionButton" @click="CrearReunion">Crear Reunion</button>
            </div>
          </div>
          
          <div class="communityList">
            <div class="community" v-for="local in locales" :key="local.id">
              <img class="communityIcon" src="../assets/respuesta-alternativa.svg" alt="Community Icon">
              <div class="info">
                <p class="communityID">ID: {{ local.id }}</p>
                <h3 class="communityName">Nombre: {{ local.nombre }}</h3>
                <p class="communityType">Tipo de Local: {{ local.nombreTipo }}</p>
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
            <img class="addIcon" src="../assets/comprador-de-tienda.svg" alt="Add Community">
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
  import TipoReunionService from '@/services/TipoReunionService'
  import BloqueService from '../services/BloqueService'
  import ReunionService from '@/services/ReunionService'
  
  const BloqueService = new BloqueService()
  const BloqueService = new BloqueService()
  const TipoReunionService = new TipoReunionService()
  
  const route = useRoute()
  const { bloque_id } = toRefs(route.params)
  
  const reunion = ref()
  const tipoReunion = ref()
  
  const nombreInsertar = ref('')
  const tipoInsertar = ref('')
  const fechaReunion = ref('')
  
  const nombreTipo = ref('')
  
  const loading = ref(true)
  
  const getTipo = async (id: number) => {
    const tipos = await TipoReunionService.listAllTiposReunion()
    for (const tipo of tipos) {
      for (const reunion of tipo.reunion) {
        if (reunion.id == id) {
          console.log(tipo.nombre)
          return tipo.nombre
        }
      }
    }
  }
  
  const fetchReuniones = async () => {
    const bloque = await bloqueService.listBloqueById(Number(bloque_id.value))
    reunion.value = bloque.reuniones
    for (const local of locales.value) {
      reunion.nombreTipo = await getTipo(reunion.id)
    }
    fetchTipoReuniones()
  }
  
  const fetchTipoReuniones = async () => {
    tipoReunion.value = await tipoReunionService.listAllTiposReunion()
    loading.value = false
  }
  
  onMounted(fetchReuniones)
  
  const BorrarReunion = async (id: number) => {
    await reunionService.deleteReunion(id)
    await fetchReuniones()
  }
  
  const BorrarTipoReunion = async (id: number) => {
    await tipoReunionService.deleteTipoReunion(id)
    await fetchTipoLReuniones()
  }
  
  const ModificarReunion = async (id: number) => {
    let nombre = prompt('Introduce el nuevo nombre del local')
    while (!nombre) {
      alert('No puede haber campos vacios')
      return
    }
    let tipoReunion = prompt('Introduce el nuevo tipo de local')
    let fechaReunion = //Prompt??
    let tipoModificar: any = 0
    let valido = false
    for (const tipo of tipoReunion.value) {
      if (tipoReunion == tipo.nombre) {
        tipoModificar = tipo
        valido = true
        break
      }
    }
    while (!tipoReunion || !valido) {
      alert('No puede haber campos vacios o no existe')
      return
    }
  
    const bloque = await BloqueService.listBloqueById(Number(bloque_id.value))
  
    const response = await reunionService.updateReunion(id, tipoModificar, fecha)
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
    await fetchLocales()
  }
  
  </script>
  
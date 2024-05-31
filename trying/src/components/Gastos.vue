<template>
    <div>
      <h1 v-if="!loading" class="container">
        <div class="section">
          <div class="addCommunity">
            <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="Add Community">
            <div class="insertion">
              <input class="insertField" type="number" placeholder="Monto" v-model="montoPrompt">
              <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
              <input class="insertField" type="date" placeholder="Fecha" v-model="fechaGastoInsertar">
              <button class="actionButton" @click="CrearGasto">Crear Gasto</button>
            </div>
          </div>
   
          <div class="communityList">
            <h1 style="text-align: center;">TOTAL: {{ total }}€ </h1>
            <div class="community" v-for="gasto in gastos" :key="gasto.id">
              <img class="communityIcon" src="../assets/dolar-de-saco.svg" alt="Community Icon">
              <div class="info">
                <h3 class="communityName">Monto: {{ gasto.monto }}</h3>
                <p class="communityType">Tipo de Gasto: {{ gasto.nombreTipo }}</p>
                <p class="communityType">Fecha: {{ gasto.fecha }}</p>
              </div>
              <div class="actions">
                <button @click="BorrarGasto(gasto.id)">Borrar</button>
                <button @click="ModificarGasto(gasto.id)">Modificar</button>
              </div>
            </div>
          </div>
        </div>
        <div class="section">
          <div class="addCommunity">
            <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="Add Community">
            <div class="insertion">
              <input class="insertField" type="text" placeholder="Nombre" v-model="nombreTipo">
              <button class="actionButton" @click="CrearTipoGasto">Crear Tipo de Gasto</button>
            </div>
          </div>
          <div class="typeList">
            <div class="type" v-for="tipoGasto in tipoGastos" :key="tipoGasto.id">
              <img class="typeIcon" src="../assets/dolar-de-saco.svg" alt="Type Icon">
              <div class="info">
                <h3 class="typeName">Nombre: {{ tipoGasto.nombre }}</h3>
              </div>
              <div class="actions">
                <button @click="BorrarTipoGasto(tipoGasto.id)">Borrar</button>
                <button @click="ModificarTipoGasto(tipoGasto.id)">Modificar</button>
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
  import TipoGastoService from '@/services/TipoGastoService'
  import ComunidadService from '@/services/CommunityService'
  import GastoService from '@/services/GastoService'
   
  const comunidadService = new ComunidadService()
  const gastoService = new GastoService()
  const tipoGastoService = new TipoGastoService()
   
  const route = useRoute()
  const { comunidad_id } = toRefs(route.params)
   
  const gastos = ref()
  const tipoGastos = ref()
   
  const montoPrompt = ref('')
  const tipoInsertar = ref('')
  const fechaGastoInsertar = ref('')

  const total = ref(0)
   
  const nombreTipo = ref('')
   
  const loading = ref(true)
   
  const getTipo = async (id: number) => {
    const tipos = await tipoGastoService.listAllTiposGasto()
    for (const tipo of tipos) {
      for (const gasto of tipo.gastos) {
        if (gasto.id == id) {
          console.log(tipo.nombre)
          return tipo.nombre
        }
      }
    }
    return "Mensualidad"
  }
   
  const fetchGastos = async () => {
    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
    console.log(comunidad)
    gastos.value = comunidad.gastos
    total.value = 0
    for (const gasto of gastos.value) {
        gasto.nombreTipo = await getTipo(gasto.id)
      total.value += gasto.monto
    }
    fetchTipoGastos()
  }
   
  const fetchTipoGastos = async () => {
    tipoGastos.value = await tipoGastoService.listAllTiposGasto()
    loading.value = false
  }
   
  onMounted(fetchGastos)
   
  const BorrarGasto = async (id: number) => {
    await gastoService.deleteGasto(id)
    await fetchGastos()
  }
   
  const BorrarTipoGasto = async (id: number) => {
    await tipoGastoService.deleteTipoGasto(id)
    await fetchGastos()
  }
   
  const ModificarGasto = async (id: number) => {
    let tipoGastoPrompt = prompt('Introduce el tipo de gastos')
    let montoPrompt = prompt('Introduce el monto del gasto')
    if (!montoPrompt || !tipoGastoPrompt) {
      alert('No puede haber campos vacios')
      return
    }
    let fechaGastoInsertar = prompt('Introduce la nueva fecha del gasto (AAAA-MM-DD)')
    if (!fechaGastoInsertar) {
      alert('No puede haber campos vacios')
      return
    }
    let fechaGasto = new Date(fechaGastoInsertar)
    let valido = false
    let tipoId
    for (const tipoGasto of tipoGastos.value) {
      if (tipoGastoPrompt == tipoGasto.nombre) {
        tipoId = tipoGasto.id
        valido = true
        break
      }
    }
    if (!valido) {
      alert('El tipo no existe')
      return
    }
   
    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
    const tipoInsertar = await tipoGastoService.listTipoGastoById(tipoId)
    const response = await gastoService.updateGasto(id, Number(montoPrompt), fechaGasto, tipoInsertar, comunidad)
    console.log(response)
    await fetchGastos()
   
  }
   
  const ModificarTipoGasto = async (id: number) => {
    let nombre = prompt('Introduce el nuevo nombre del tipo de gasto')
    while (!nombre) {
      alert('El nombre no puede estar vacío')
      return
    }
    let valido = true
    for (const tipo of tipoGastos.value) {
      if (nombre == tipo.nombre) {
        valido = false
        break
      }
    }
    if (!valido) {
      alert('El tipo ya existe')
      return
    }
    let tipo = await tipoGastoService.listTipoGastoById(id)
    let gastos: any = tipo.gastos
    const response = await tipoGastoService.updateTipoGasto(id, nombre, gastos)
    console.log(response)
    await fetchGastos()
  }
   
  const CrearGasto = async () => {
    if (!montoPrompt || !tipoInsertar || !fechaGastoInsertar) {
      alert('No puede haber campos vacios')
      return
    }
  
    let fechaGasto = new Date(fechaGastoInsertar.value)
    let valido = false
    let tipoId
    for (const tipoGasto of tipoGastos.value) {
      if (tipoInsertar.value == tipoGasto.nombre) {
        tipoId = tipoGasto.id
        valido = true
        break
      }
    }
    if (!valido) {
      alert('El tipo no existe')
      return
    }
   
    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
    const tipo = await tipoGastoService.listTipoGastoById(tipoId)
    const response = await gastoService.createGasto(Number(montoPrompt.value), fechaGasto, tipo, comunidad)
    console.log(response)
    await fetchGastos()
  }
   
  const CrearTipoGasto = async () => {
    if (!nombreTipo.value) {
      alert('No puede haber campos vacios')
      return
    }
    let valido = true
    for (const tipo of tipoGastos.value) {
      if (nombreTipo.value == tipo.nombre) {
        valido = false
        break
      }
    }
    if (!valido) {
      alert('El tipo ya existe')
      return
    }
    let gastos: any = [] // Aquí sí va vacío porque lo estoy creando
    const response = await tipoGastoService.createTipoGasto(nombreTipo.value, gastos)
    console.log(response)
    await fetchTipoGastos()
  }
   
  </script>
  
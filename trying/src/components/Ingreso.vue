<template>
  <Header />
  <div>
    <h1 v-if="!loading" class="container">
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="number" placeholder="Monto" v-model="montoPrompt">
            <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
            <input class="insertField" type="date" placeholder="Fecha" v-model="fechaIngresoInsertar">
            <button class="actionButton" @click="CrearIngreso">Crear Ingreso</button>
          </div>
        </div>

        <div class="communityList">
          <h1 style="text-align: center;">TOTAL: {{ total }}€ </h1>
          <div class="community" v-for="ingreso in ingresos" :key="ingreso.id">
            <img class="communityIcon" src="../assets/dolar-de-saco.svg" alt="Community Icon">
            <div class="info">
              <h3 class="communityName">Monto: {{ ingreso.monto }}</h3>
              <p class="communityType">Tipo de Ingreso: {{ ingreso.nombreTipo }}</p>
              <p class="communityType">Fecha: {{ ingreso.fecha }}</p>
            </div>
            <div class="actions">
              <button @click="BorrarIngreso(ingreso.id)">Borrar</button>
              <button @click="ModificarIngreso(ingreso.id)">Modificar</button>
            </div>
          </div>
        </div>
      </div>
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="text" placeholder="Nombre" v-model="nombreTipo">
            <button class="actionButton" @click="CrearTipoIngreso">Crear Tipo de Ingreso</button>
          </div>
        </div>
        <div class="typeList">
          <div class="type" v-for="tipoIngreso in tipoIngresos" :key="tipoIngreso.id">
            <img class="typeIcon" src="../assets/dolar-de-saco.svg" alt="Type Icon">
            <div class="info">
              <h3 class="typeName">Nombre: {{ tipoIngreso.nombre }}</h3>
            </div>
            <div class="actions">
              <button @click="BorrarTipoIngreso(tipoIngreso.id)">Borrar</button>
              <button @click="ModificarTipoIngreso(tipoIngreso.id)">Modificar</button>
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
import TipoIngresoService from '@/services/TipoIngresoService'
import ComunidadService from '@/services/CommunityService'
import IngresoService from '@/services/IngresoService'
import Header from './Header.vue'

const comunidadService = new ComunidadService()
const ingresoService = new IngresoService()
const tipoIngresoService = new TipoIngresoService()

const route = useRoute()
const { comunidad_id } = toRefs(route.params)

const ingresos = ref()
const tipoIngresos = ref()

const montoPrompt = ref('')
const tipoInsertar = ref('')
const fechaIngresoInsertar = ref('')

const total = ref(0)

const nombreTipo = ref('')

const loading = ref(true)

const getTipo = async (id: number) => {
  const tipos = await tipoIngresoService.listAllTiposIngreso()
  for (const tipo of tipos) {
    for (const ingreso of tipo.ingresos) {
      if (ingreso.id == id) {
        console.log(tipo.nombre)
        return tipo.nombre
      }
    }
  }
  return "Mensualidad"
}

const fetchIngresos = async () => {
  const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
  console.log(comunidad)
  ingresos.value = comunidad.ingresos
  total.value = 0
  for (const ingreso of ingresos.value) {
    ingreso.nombreTipo = await getTipo(ingreso.id)
    total.value += ingreso.monto
  }
  fetchTipoIngresos()
}

const fetchTipoIngresos = async () => {
  tipoIngresos.value = await tipoIngresoService.listAllTiposIngreso()
  loading.value = false
}

onMounted(fetchIngresos)

const BorrarIngreso = async (id: number) => {
  await ingresoService.deleteIngreso(id)
  await fetchIngresos()
}

const BorrarTipoIngreso = async (id: number) => {
  await tipoIngresoService.deleteTipoIngreso(id)
  await fetchIngresos()
}

const ModificarIngreso = async (id: number) => {
  let tipoIngresoPrompt = prompt('Introduce el tipo de ingreso')
  let montoPrompt = prompt('Introduce el monto del ingreso')
  if (!montoPrompt || !tipoIngresoPrompt) {
    alert('No puede haber campos vacios')
    return
  }
  let fechaIngresoInsertar = prompt('Introduce la nueva fecha del ingreso (AAAA-MM-DD)')
  if (!fechaIngresoInsertar) {
    alert('No puede haber campos vacios')
    return
  }
  let fechaIngreso = new Date(fechaIngresoInsertar)
  let valido = false
  let tipoId
  for (const tipoIngreso of tipoIngresos.value) {
    if (tipoIngresoPrompt == tipoIngreso.nombre) {
      tipoId = tipoIngreso.id
      valido = true
      break
    }
  }
  if (!valido) {
    alert('El tipo no existe')
    return
  }

  const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
  const tipoInsertar = await tipoIngresoService.listTipoIngresoById(tipoId)
  const response = await ingresoService.updateIngreso(id, Number(montoPrompt), fechaIngreso, tipoInsertar, comunidad)
  console.log(response)
  await fetchIngresos()

}

const ModificarTipoIngreso = async (id: number) => {
  let nombre = prompt('Introduce el nuevo nombre del tipo de ingreso')
  while (!nombre) {
    alert('El nombre no puede estar vacío')
    return
  }
  let valido = true
  for (const tipo of tipoIngresos.value) {
    if (nombre == tipo.nombre) {
      valido = false
      break
    }
  }
  if (!valido) {
    alert('El tipo ya existe')
    return
  }
  let tipo = await tipoIngresoService.listTipoIngresoById(id)
  let ingresos: any = tipo.ingresos
  const response = await tipoIngresoService.updateTipoIngreso(id, nombre, ingresos)
  console.log(response)
  await fetchIngresos()
}

const CrearIngreso = async () => {
  if (!montoPrompt || !tipoInsertar || !fechaIngresoInsertar) {
    alert('No puede haber campos vacios')
    return
  }

  let fechaIngreso = new Date(fechaIngresoInsertar.value)
  let valido = false
  let tipoId
  for (const tipoIngreso of tipoIngresos.value) {
    if (tipoInsertar.value == tipoIngreso.nombre) {
      tipoId = tipoIngreso.id
      valido = true
      break
    }
  }
  if (!valido) {
    alert('El tipo no existe')
    return
  }

  const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
  const tipo = await tipoIngresoService.listTipoIngresoById(tipoId)
  const response = await ingresoService.createIngreso(Number(montoPrompt.value), fechaIngreso, tipo, comunidad)
  console.log(response)
  await fetchIngresos()
}

const CrearTipoIngreso = async () => {
  if (!nombreTipo.value) {
    alert('No puede haber campos vacios')
    return
  }
  let valido = true
  for (const tipo of tipoIngresos.value) {
    if (nombreTipo.value == tipo.nombre) {
      valido = false
      break
    }
  }
  if (!valido) {
    alert('El tipo ya existe')
    return
  }
  let ingresos: any = [] // Aquí sí va vacío porque lo estoy creando
  const response = await tipoIngresoService.createTipoIngreso(nombreTipo.value, ingresos)
  console.log(response)
  await fetchTipoIngresos()
}

</script>
<template>
  <div class="comunidad-container">
    <h1 class="comunidad-title">Comunidad ID: {{ id }}</h1>
    <p v-if="loading" class="loading-message">Cargando datos...</p>
    <div v-else>
      <div class="comunidad-details">
        <h2>Nombre: {{ nombre }}</h2>
        <h3 class="direccion">Dirección: {{ direccion }}</h3>
        <div class="top-cards">
          <div class="card">
            <p>(var IngresoTotal)</p>
            <p>Ingresos</p>
          </div>
          <!-- Resto de las tarjetas -->
        </div>
        <div class="bottom-cards">
          <!-- Tarjetas inferiores -->
        </div>
      </div>
      <div class="botones">
        <button @click="CargarLocales" class="boton">Pulsa aquí para acceder a los locales</button>
        <button @click="CargarContratos" class="boton">Pulsa aquí para acceder a los contratos</button>
        <button class="boton">Pulsa aquí para acceder a los ingresos</button>
        <button class="boton">Pulsa aquí para acceder a los gastos</button>
        <button class="boton">Pulsa aquí para acceder a las deudas</button>
      </div>
      <h1 class="bloques-title">Bloques</h1>
      <div class="bloques">
        <div class="añadir-bloque">
          <img class="añadir-bloque-icon" src="../assets/flecha-del-circulo-del-edificio-hacia-la-derecha.svg">
          <div class="insercion">
            <button class="boton-bloque" @click="CrearBloque"><img src="../assets/arrow-return-left.svg"></button>
          </div>
        </div>
        <div class="bloque" v-for="bloque in bloques" :key="bloque.id">
          <img @click="CargarBloque(bloque.id)" class="bloque-icon" src="../assets/ciudad.svg">
          <h3 class="nombre-bloque">{{ bloque.id }}</h3>
          <button @click="BorrarBloque(bloque.id)">Eliminar</button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.comunidad-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #f7f7f7;
  border-radius: 8px;
}

.comunidad-title {
  font-size: 24px;
  margin-bottom: 20px;
}

.loading-message {
  font-style: italic;
}

.comunidad-details {
  margin-bottom: 30px;
}

.direccion {
  font-style: normal;
}

.top-cards,
.bottom-cards {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.card {
  width: calc(33.33% - 10px);
  padding: 15px;
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.botones {
  display: flex;
  justify-content: center;
  margin-bottom: 20px;
}

.boton {
  padding: 10px 20px;
  margin: 0 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.bloques-title {
  font-size: 20px;
  margin-bottom: 20px;
}

.bloques {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.añadir-bloque {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.añadir-bloque-icon {
  width: 50px;
  margin-right: 10px;
}

.insercion {
  margin-top: 10px;
}

.bloque {
  width: 100px;
  text-align: center;
  margin-bottom: 20px;
}

.bloque-icon {
  width: 75px;
}

.nombre-bloque {
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
</style>

<script setup lang="ts">
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import type IBloque from '../interfaces/IBloque.ts'
import type { Ref } from 'vue'
import CommunityService from '../services/CommunityService'
import BloqueService from '../services/BloqueService'
import { useRouter } from 'vue-router'

const route = useRoute()
const { id } = toRefs(route.params)

const community = ref()
let comunidad_id: number

const nombre = ref('')
const direccion = ref('')

const locales = ref()

const communityService = new CommunityService()
const bloqueService = new BloqueService()
const loading = ref(true)

const bloques: Ref<Array<IBloque>> = ref([])

const router = useRouter()

const fetchCommunities = async () => {
  community.value = await communityService.listCommunityById(Number(id.value))
  console.log(community.value)
  comunidad_id = community.value.id
  nombre.value = community.value.nombre
  direccion.value = community.value.direccion
  bloques.value = community.value.bloques
  locales.value = community.value.locales
  loading.value = false
}
const ingresos = ref('')
const ingresosComunidad = async () => {}

onMounted(fetchCommunities)

const CargarLocales = () => {
  router.push({ name: 'Local', params: { comunidad_id } })
}

const CargarContratos = () => {
  router.push({ name: 'Contrato', params: { comunidad_id } })
}

const CargarBloque = (bloque_id: number) => {
  router.push({ name: 'Bloque', params: { bloque_id } })
}

const CrearBloque = async () => {
  console.log(comunidad_id)
  const comunidad = await communityService.listCommunityById(comunidad_id)
  let viviendas: any = []
  let reuniones: any = []
  let mensualidades: any = []
  const response = await bloqueService.createBloque(comunidad, viviendas, reuniones, mensualidades)
  console.log(response)
  await fetchCommunities()
}

const BorrarBloque = async (id: number) => {
  const response = await bloqueService.deleteBloque(id)
  console.log(response)
  await fetchCommunities()
}

</script>

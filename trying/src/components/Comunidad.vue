<template>
  <Header />
  <div class="comunidad-container">

    <p v-if="loading" class="loading-message">Cargando datos...</p>
    <div v-else>
      <div class="containerComunidad">
        <div class="comunidad-details">
          <h1 class="nombreCom">{{ nombre }}</h1>
          <p class="direccion">{{ direccion }}</p>
        </div>
        <br>
        <div class="info">
          <div class="cardsInfo">
            <div class="cardsButtons" @click="CargarIngresos"><img class="imgCards" src="../assets/growth.png">
              <p class="tituloCards">INGRESOS</p>
              <p class="ingresoTotal" id="infoCards">{{ totalIngresos }}€</p>

            </div>
            <div class="cardsButtons" @click="CargarGastos"><img class="imgCards" src="../assets/expense.png">
              <p class="tituloCards">GASTOS</p>
              <p class="gastosTotal" id="infoCards">{{ totalGastos }}€</p>
            </div>
            <div class="cardsButtons" @click="CargarDeudas"><img class="imgCards" src="../assets/liability.png">
              <p class="tituloCards">DEUDAS</p>
              <p class="deudaTotal" id="infoCards">{{ totalDeudas }}€</p>

            </div>
          </div>
          <div class="cardsInfo">
            <div @click="CargarLocales" class="cardsButtons"><img class="imgCards" src="../assets/warehouse.png">
              <p class="tituloCards">LOCALES</p>
            </div>

            <div @click="CargarContratos" class="cardsButtons"> <img class="imgCards" src="../assets/invoice.png">
              <p class="tituloCards">CONTRATOS</p>
            </div>

          </div>
        </div>
      </div>


      <h1 class="bloques-title">Bloques</h1>
      <div class="bloques">
        <div class="bloqueAgregar">
          <img class="imgBloqueAgregar" src="../assets/icons8-building-100 (1).png">
          <input id="numeroInsertar" type="number" placeholder="Nº Bloque" v-model="numero"></input>
          <div class="buttonAgregar" @click="CrearBloque">
            <p class="textoAgregar">AGREGAR BLOQUE</p>
            <img class ="imgAgregar" src="../assets/arrow-return-left.svg">
          </div>
        </div>

        <div class="bloque" v-for="bloque in bloques" :key="bloque.id">
          <img @click="CargarBloque(bloque.id)" class="bloque-icon" src="../assets/icons8-building-100.png">
          <h3 class="nombre-bloque">Nº: {{ bloque.numero }}</h3>
          <div class="options">
            <button @click="BorrarBloque(bloque.id)">Eliminar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.containerComunidad {
  text-align: center;
  margin-top: 10px;
  margin-bottom: 20px;
  background-color: rgb(249 138 3 / 30%);
  border-radius: 20px;

}

.nombreCom {
  color: rgb(255, 255, 255);
  text-shadow: rgb(255, 140, 0) 1px 1px 1px;
  margin: 0%;
  margin-top: 2%;
}

.direccion {
  margin: 0%;
  color: rgb(198, 41, 23);
  font-size: large
}

.cardsInfo {
  height: auto;
  justify-content: center;
  margin: auto;
  display: flex;
  padding-bottom: 5px;
}

.cardsButtons {
  text-align: center;
  width: 150px;
  background-color: #f8ef7e;
  margin: 2%;
  padding: 1%;
  border-radius: 10px;

}

.tituloCards {
  margin: 5%;
  font-size: larger;
  font-weight: bold;
}

.ingresoTotal {
  font-weight: bold;
  color: rgb(148, 207, 71);
}

.gastosTotal {
  font-weight: bold;
  color: rgb(200, 49, 49);
}

.deudaTotal {
  font-weight: bold;
  color: rgb(158, 94, 30);
}

#infoCards {
  margin: 5%;
  font-size: larger;
  font-weight: bold;
}

.imgCards {
  width: 50px;
}

.comunidad-container {
  width: 75%;
  margin: auto;
  padding: 30px;
  border-radius: 8px;
}

.loading-message {
  font-style: italic;
}

.comunidad-details {
  padding: 4px;
  border-radius: 20px;
  text-align: center;
  align-items: center;
}

.bloques {
  height: auto;
  justify-content: center;
  flex-wrap: wrap;
  display: flex;
}

.bloques-title {
  text-align: center;
  font-size: 20px;
  margin-bottom: 20px;
}


.bloque {
  display: grid;
  align-items: center;
  justify-content: center;
  text-align: center;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  margin-bottom: 3%;
  margin-right: 3%;
  margin-left: 3%;
  width: 150px;
}

.bloqueAgregar {
  border-radius: 10px;
  background-color: rgb(255, 255, 255);
  width: 150px;
  display: grid;
  align-items: center;
  justify-content: center;
  text-align: center;
  margin-bottom: 3%;
  margin-right: 3%;
  margin-left: 3%;
}

.bloque-icon{
  margin: auto;
  margin-bottom: 10%;
  padding-top: 10%;
  width: 75px;
}

.buttonAgregar {
  border-radius: 10px;
  background-color: rgb(245, 209, 156);
  display: flex;
  font-weight: bold;
  margin: 15%;
  margin-top: 4%;
  margin-bottom: 6%;
  padding-left: 5%;
  padding-right: 5%;
  border-radius: 10px;
  
}
.options{
  margin-bottom: 6%;
}
.imgAgregar{
  margin-left: 10%;
  width: 30px;
}
input {
  width: 70%;
  margin-bottom: 5%;
  margin-left: auto;
  margin-right: auto;
  border-color:rgb(219, 181, 125);
  border-style: solid;
  border-radius: 4px;
}

.textoAgregar {
  font-size: small;
}

.imgBloqueAgregar{
  margin: auto;
  margin-bottom: 10%;
  padding-top: 10%;
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
import Header from './Header.vue'
const route = useRoute()
const { id } = toRefs(route.params)

const community = ref()
let comunidad_id: number

const nombre = ref('')
const direccion = ref('')

const locales = ref()

const ingresos = ref()
const gastos = ref()
const deudas = ref()

const totalIngresos = ref()
const totalGastos = ref()
const totalDeudas = ref()

const communityService = new CommunityService()
const bloqueService = new BloqueService()
const loading = ref(true)

const numero = ref()
const bloques: Ref<Array<IBloque>> = ref([])

const router = useRouter()

const fetchCommunities = async () => {
  community.value = await communityService.listCommunityById(Number(id.value))
  console.log(community.value)
  numero.value = community.value.numero
  comunidad_id = community.value.id
  nombre.value = community.value.nombre
  direccion.value = community.value.direccion
  bloques.value = community.value.bloques
  locales.value = community.value.locales
  fetchIngresos()
  fetchGastos()
  fetchDeudas()
  loading.value = false
}

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

const CargarIngresos = () => {
  router.push({ name: 'Ingreso', params: { comunidad_id } })
}

const CargarDeudas = () => {
  router.push({ name: 'Deudas', params: { comunidad_id } })
}

const CargarGastos = () => {
  router.push({ name: 'Gastos', params: { comunidad_id } })
}

const CrearBloque = async () => {

  console.log(comunidad_id)
  const comunidad = await communityService.listCommunityById(comunidad_id)
  let viviendas: any = []
  let reuniones: any = []
  const response = await bloqueService.createBloque(numero.value, comunidad, viviendas, reuniones)
  console.log(response)
  await fetchCommunities()
}

const BorrarBloque = async (id: number) => {
  const response = await bloqueService.deleteBloque(id)
  console.log(response)
  await fetchCommunities()
}

const fetchIngresos = async () => {
  const comunidad = await communityService.listCommunityById(Number(id.value))
  console.log(comunidad)
  ingresos.value = comunidad.ingresos
  totalIngresos.value = 0
  for (const ingreso of ingresos.value) {
    totalIngresos.value += ingreso.monto
  }
  console.log(totalIngresos.value)
}

const fetchGastos = async () => {
  const comunidad = await communityService.listCommunityById(Number(id.value))
  console.log(comunidad)
  gastos.value = comunidad.gastos
  totalGastos.value = 0
  for (const gasto of gastos.value) {
    totalGastos.value += gasto.monto
  }
}

const fetchDeudas = async () => {
  const comunidad = await communityService.listCommunityById(Number(id.value))
  console.log(comunidad)
  deudas.value = comunidad.deudas
  totalDeudas.value = 0
  for (const deuda of deudas.value) {
    totalDeudas.value += deuda.monto
  }
}

</script>

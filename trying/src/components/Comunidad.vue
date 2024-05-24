<template>
  <div>
    <h1>Comunidad ID: {{ id }}</h1>
    <p v-if="loading">Cargando datos...</p>
    <div v-else>
      <h2>{{ nombre }}</h2>
      <h3>{{ direccion }}</h3>
      <div id="TopCads">
        <div id="TCard1">
          <p>(var IngresoTotal)</p>
          <p>Ingresos</p>
        </div>
        <div id="TCard2">
          <p>(var CosteTotal)</p>
          <p>Costes</p>
        </div>
        <div id="TCard3">
          <p>(var CosteTotal)</p>
          <p>Costes</p>
        </div>
      </div>
      <div id="BottomCads">
        <div id="BCard1">
          <p>(var IngresoTotal)</p>
          <p>Ingresos</p>
        </div>
        <div id="BCard2">
          <p>(var CosteTotal)</p>
          <p>Costes</p>
        </div>
        <div id="BCard3">
          <p>(var CosteTotal)</p>
          <p>Costes</p>
        </div>
      </div>
    </div>
    <h1 style="display: flex; justify-content: center;">Bloques</h1>
    <div id="Bloques">
      <div class="Bloque" v-for="bloque in bloques" :key="bloque.id">
        <img class="imgComunidad1" src="../assets/building.svg">
        <p class="idComunidad">{{ bloque.id }}</p>
        <h3 class="nombreComunidad">{{ bloque.id }}</h3>
      </div>
    </div>
    <button @click="CargarLocales()">Pulsa aquí para acceder a los locales</button>
    <button>Pulsa aquí para acceder a los contratos</button>
  </div>
</template>
  
<script lang="ts" setup>
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue' 
import type IBloque from '../interfaces/IBloque.ts' 
import type { Ref } from 'vue'
import CommunityService from '../services/CommunityService'
import BloqueService from '../services/BloqueService'
import { useRouter } from 'vue-router'
import type ILocal from '@/interfaces/ILocal.js'
  
const route = useRoute()
const { id } = toRefs(route.params)

const community = ref()
const nombre = ref('')
const direccion = ref('')

const locales = ref()

const communityService = new CommunityService()
const loading = ref(true)

const bloques: Ref<Array<IBloque>> = ref([])

const router = useRouter()


const fetchCommunities = async () => {
  community.value = await communityService.listCommunityById(Number(id.value))
  console.log(community.value)
  nombre.value = community.value.nombre
  direccion.value = community.value.direccion
  bloques.value = community.value.bloques
  locales.value = community.value.locales
  loading.value = false
}
const ingresos = ref('')
const ingresosComunidad = async() =>{
  
}
onMounted(fetchCommunities)

const CargarLocales = () => {
  const localesString = JSON.stringify(locales.value)
  router.push({ name: 'Local', params: { localesString } })
}

</script>

<style>
  .imgComunidad1{
    width: 75px;
  }
  .imgAñadirComunidad{
    width: 75px;
    margin-top: 11px;
    margin-left: 12px;
  }
  #insercion{
    margin-top: 10px;
  }
  #lineaInferior{
    display:flex;
  }
  #Bloques{
    display: flex;
    margin-top: 40px;
    flex-wrap: wrap;
    justify-content: center;
    
  }
  .Bloque{
    width: 100px;
    margin-left: 20px;
    text-align: center;
  }
  .dirComunidad{
    font-family: monospace;
  }
  .Bloque p{
    margin: 5px;
    font-style: italic;
  }
  .idComunidad {
    visibility: collapse;
  }
  .Bloque h3{
    margin: 5px;
    margin-top: -15px;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  }
  .Bloque img{
    margin-top: 10px;
  }
  #BotonComunidad{
    background-color: rgb(245, 149, 80);
    font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
    border-style: groove;
    border-radius: 4px;
    width: 30px;
    border-color: rgb(245, 149, 80);
    border-style: double;
  }
  
  .añadirComunidad{
   
    display: grid;
    width: 100px;
    height: 160px;
    margin-left: 20px;
  }
  .insertar{
    border-color: rgb(245, 149, 80);
    border-radius: 4px;
    width: 92%;
    height: 19px;

    
  }
  ::placeholder{
    font-family: Georgia, 'Times New Roman', Times, serif;
  }
</style>
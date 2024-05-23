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
    <div id="Bloques">

    </div>
  </div>
</template>
  
<script lang="ts" setup>
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue' 
import type ICommunity from '../interfaces/ICommunity.ts' 
import type IBloque from '../interfaces/IBloque.ts' 
import type { Ref } from 'vue'
import CommunityService from '../services/CommunityService'
import BloqueService from '../services/BloqueService'
import { useRouter } from 'vue-router'
  
const route = useRoute()
const { id } = toRefs(route.params)

const community = ref()
const nombre = ref('')
const direccion = ref('')

const communityService = new CommunityService()
const loading = ref(true)

const bloques: Ref<Array<IBloque>> = ref([])
const bloqueService = new BloqueService()

const fetchCommunities = async () => {
  community.value = await communityService.listCommunityById(Number(id.value))
  console.log(community.value.nombre)
  console.log(community.value.direccion)
  console.log(community.value.bloques)
  nombre.value = community.value.nombre
  direccion.value = community.value.direccion
  loading.value = false
}
const ingresos = ref('')
const ingresosComunidad = async() =>{
  
}
onMounted(fetchCommunities)

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
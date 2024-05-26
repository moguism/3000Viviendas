<template>
  <p v-if="loading">Cargando datos...</p>

  <div id="Comunidades" v-else>
    <div class="añadirComunidad">
      <img class="imgAñadirComunidad" src="../assets/building-fill-add.svg">
      <div id="insercion">
        <input class="insertar" type="text" placeholder="Nombre" v-model="name"> <br>
        <div id="lineaInferior">
          <input class="insertar" type="text" placeholder="Direccion" v-model="address"> <br>
          <button id="BotonComunidad" @click="CrearComunidad"><img src="../assets/arrow-return-left.svg"></button>
        </div>
      </div>
    </div>
    <div class="Comunidad" v-for="community in communities" :key="community.id">
      <img class="imgComunidad1" src="../assets/building.svg" @click="CargarComunidad(community.id)">
      <p class="idComunidad">{{ community.id }}</p>
      <h3 class="nombreComunidad">{{ community.nombre }}</h3>
      <p class="dirComunidad">{{ community.direccion }}</p>
      <button @click="BorrarComunidad(community.id)">Borrar</button>
      <button @click="ModificarComunidad(community.id)">Modificar</button>
    </div>
  </div>
  
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import type ICommunity from '../interfaces/ICommunity.ts' 
import type { Ref } from 'vue'
import CommunityService from '../services/CommunityService'
import { useRouter } from 'vue-router'

const name = ref('')
const address = ref('')

const communities: Ref<Array<ICommunity>> = ref([])
const communityService = new CommunityService()
const loading = ref(true)

const router = useRouter()

const fetchCommunities = async () => {
  communities.value = await communityService.listAllCommunities()
  loading.value = false
}

onMounted(fetchCommunities)

const CargarComunidad = (id: number) => {
  router.push({ name: 'Comunidad', params: { id } })
}

const CrearComunidad = async () => {
  if(!name.value || !address.value){
    alert('No puede haber campos vacios')
    return
  }
  let bloques: any = []
  let locales: any = []
  let contratos: any = []
  let ingresos: any = []
  let gastos: any = []
  let deudas: any = []
  const response = await communityService.createCommunity(name.value, address.value, bloques, locales, contratos, ingresos, gastos, deudas)
  console.log(response)
  await fetchCommunities() // Actualiza el listado de comunidades después de crear una nueva
}

const BorrarComunidad = async(id: number) => {
  await communityService.deleteComunidad(id)
  await fetchCommunities()

}

const ModificarComunidad = async(id: number) => {
  let nombre = prompt('Introduce el nuevo nombre de la comunidad')
  while (!nombre) {
    alert('El nombre no puede estar vacío')
    return
  }  
  let direccion = prompt('Introduce la nueva direccion de la comunidad')
  while (!direccion) {
    alert('La dirección no puede estar vacía')
    return
  }
  let bloques: any = []
  let locales: any = []
  let contratos: any = []
  let ingresos: any = []
  let gastos: any = []
  let deudas: any = []
  const response = await communityService.updateComunidad(id, nombre, direccion, bloques, locales, contratos, ingresos, gastos, deudas)
  console.log(response)
  await fetchCommunities()
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
  #Comunidades{
    display: flex;
    margin-top: 40px;
    flex-wrap: wrap;
    justify-content: center;
    
  }
  .Comunidad{
    width: 100px;
    margin-left: 20px;
    text-align: center;
  }
  .dirComunidad{
    font-family: monospace;
  }
  .Comunidad p{
    margin: 5px;
    font-style: italic;
  }
  .idComunidad {
    visibility: collapse;
  }
  .Comunidad h3{
    margin: 5px;
    margin-top: -15px;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
  }
  .Comunidad img{
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
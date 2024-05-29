<template>
  <p v-if="loading" class="loading">Cargando datos...</p>

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
      <h3 class="nombreComunidad">{{ community.nombre }}</h3>
      <p class="dirComunidad">{{ community.direccion }}</p>
      <button class="action-button" @click="BorrarComunidad(community.id)">Borrar</button>
      <button class="action-button" @click="ModificarComunidad(community.id)">Modificar</button>
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
  let comunidad = await communityService.listCommunityById(id)
  let bloques: any = comunidad.bloques
  let locales: any = comunidad.locales
  let contratos: any = comunidad.contratos
  let ingresos: any = comunidad.ingresos
  let gastos: any = comunidad.gastos
  let deudas: any = comunidad.deudas
  const response = await communityService.updateComunidad(id, nombre, direccion, bloques, locales, contratos, ingresos, gastos, deudas)
  console.log(response)
  await fetchCommunities()
}
</script>

<style>
body {
  font-family: 'Roboto', sans-serif;
  background-color: #f0f2f5;
  margin: 0;
  padding: 0;
}

.loading {
  text-align: center;
  font-size: 1.5em;
  color: #555;
}

#Comunidades {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  padding: 20px;
  gap: 20px;
}

.añadirComunidad, .Comunidad {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 250px;
  text-align: center;
}

.imgComunidad1, .imgAñadirComunidad {
  width: 50px;
  cursor: pointer;
  margin-bottom: 15px;
}

#insercion {
  margin-top: 10px;
}

#lineaInferior {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 10px;
}

.insertar {
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  width: 70%;
  margin-bottom: 10px;
}

#BotonComunidad {
  background-color: #ff7043;
  border: none;
  border-radius: 4px;
  padding: 10px;
  cursor: pointer;
  color: #fff;
  font-size: 14px;
}

#BotonComunidad img {
  width: 20px;
}

.action-button {
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  padding: 10px 15px;
  color: white;
  font-size: 14px;
  cursor: pointer;
  margin-top: 10px;
}

.action-button:hover {
  background-color: #0056b3;
}

.nombreComunidad {
  font-size: 1.2em;
  color: #333;
}

.dirComunidad {
  font-family: monospace;
  color: #555;
}

.Comunidad h3 {
  margin: 10px 0;
}

.Comunidad p {
  margin: 5px 0;
}

@media (max-width: 600px) {
  #Comunidades {
    flex-direction: column;
    align-items: center;
  }
}
</style>

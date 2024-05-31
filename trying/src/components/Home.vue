<template>
  <Header></Header>
  <p v-if="loading">Cargando datos...</p>
  <div id="Comunidades" v-else>
    <div class="Comunidad">
      <img class="imgComunidadInsertar" src="../assets/building-fill-add.svg">

      <div id="divInsercion">
        <input class="insertarNombre" type="text" placeholder="Nombre" v-model="name">
        <br>
        <input class="insertarDireccion" type="text" placeholder="Direccion" v-model="address">
        <br>
        <button class="createComunidad" @click="CrearComunidad"><p>Agregar Comunidad</p><img class="imagenCreateComunidad" src="../assets/arrow-return-left.svg"></button>
        <div id="dialogoDelete" v-show="false" >

        </div>
      </div>
    </div>


    <div class="Comunidad" v-for="community in communities" :key="community.id">

      <img class="imgComunidad" src="../assets/building.svg" @click="CargarComunidad(community.id)">
      <p class="nombreComunidad">{{ community.nombre }}</p>
      <p class="dirComunidad">{{ community.direccion }}</p>

      <div class="options">
        <img id="delete" class="deleteButton" src="../assets/delete.png" @click="BorrarComunidad(community.id)">
        <img class="modifyButton" src="../assets/edit-button.png" @click="ModificarComunidad(community.id)">
      </div>

    </div>
  </div>

</template>

<style>
#Comunidades {
  display: flex;
  margin: auto;
  margin-top: 10px;
  flex-wrap: wrap;
  justify-content: center;
}

.Comunidad {
  height: auto;
  padding: 1%;
  border-radius: 20px;
  display: grid;
  width: 150px;
  text-align: center;
  align-items: center;
  margin: 1%;
  background-color: white;
}
.Comunidad p {
  margin: 5px;
}

.idComunidad {
  visibility: collapse;
}
.BotonComunidad {
  background-color: rgb(245, 149, 80);
  border-radius: 5px;
  border: none;
}
.nombreComunidad{
  font-weight: bold;
  width: 90%;
  margin: auto;
  word-break: break-all;
  font-size: small;
}
.dirComunidad{
  font-size: small;
  word-break: break-all;
}

button.BotonComunidad {
  height: 30px;
  width: 30px;
  padding: 0%;
  margin: 0%;
}

.imgComunidadInsertar {
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
  width: 75px;
}

#divInsercion {
  align-items: center;
  justify-content:center;
  margin-top: 10px;
}

.insertarNombre {
  margin: 2px 2px 2px 2px;
  border-color:rgb(219, 181, 125);
  border-style: solid;
  border-radius: 4px;
  width: 90%;
  height: 19px;
  margin-bottom: 2%
}

.insertarDireccion {
  margin: 2px 2px 2px 2px;
  border-style: solid;
  border-color: rgb(219, 181, 125);
  border-radius: 4px;
  width: 90%;
  height: 19px;
}

.imagenCreateComunidad {
  margin-right: 5%;
  margin-top: 0%;
  padding: 0%;
}

.createComunidad{
  margin-top: 5%;
  height: auto;
  display: flex;
  justify-content: center;
  align-items: center;
  border: none;
  border-radius: 10px;
  background-color: rgb(245, 209, 156);
  font-weight: bold;
}

.imgComunidad {
  margin-left: auto;
  margin-right: auto;
  margin-top: 20px;
  margin-bottom: 20px;
  width: 75px;
}
.dirComunidad {
  padding: 0 0 0 0;
}

.options {
  display: flex;
  height: 30px;
  justify-content: center;
}
.deleteButton {
  width: 20px;
  margin: auto;
  margin-right: 7%
}

.modifyButton {
  width: 20px;
  margin: auto;
  margin-left: 7%
}

.Comunidad h3 {
  width: 99%;
  margin-top: -15px;

}
</style>
<script lang="ts" setup>
import { ref, onMounted } from 'vue'

import type ICommunity from '../interfaces/ICommunity.ts' 
import type { Ref } from 'vue'
import CommunityService from '../services/CommunityService'
import { useRouter } from 'vue-router'
import Header from './Header.vue'

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

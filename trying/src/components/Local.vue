<template>
  <h1 v-if="!loading">
    <div id="Locales">
      <div class="añadirComunidad">
        <img class="imgAñadirComunidad" src="../assets/building-fill-add.svg">
        <div id="insercion">
          <input class="insertar" type="text" placeholder="Nombre" v-model="nombreInsertar"> <br>
          <div id="lineaInferior">
            <input class="insertar" type="text" placeholder="Tipo" v-model="tipoInsertar"> <br>
            <button id="BotonLocal" @click="CrearLocal"><img src="../assets/arrow-return-left.svg"></button>
          </div>
        </div>
      </div>
      <div class="Local" v-for="local in locales" :key="local.id">
        <img class="imgComunidad1" src="../assets/building.svg">
        <p class="idLocal">ID: {{ local.id }}</p>
        <h3 class="nombreLocal">Nombre: {{ local.nombre }}</h3>
        <p class="tipoLocal">Tipo de Local: {{ local.nombreTipo }}</p>
        <button @click="BorrarLocal(local.id)">Borrar</button>
        <button @click="ModificarLocal(local.id)">Modificar</button>
      </div>
    </div>
    <div id="TiposLocales">
      <div class="añadirComunidad">
        <img class="imgAñadirComunidad" src="../assets/building-fill-add.svg">
        <div id="insercion">
          <input class="insertar" type="text" placeholder="Nombre" v-model="nombreTipo"><br>
          <div id="lineaInferior">
            <button id="BotonLocal" @click="CrearTipoLocal"><img src="../assets/arrow-return-left.svg"></button>
          </div>
        </div>
      </div>
      <div class="TipoLocal" v-for="tipoLocal in tipoLocales" :key="tipoLocal.id">
        <img class="imgComunidad1" src="../assets/building.svg">
        <p class="idLocal">ID: {{ tipoLocal.id }}</p>
        <h3 class="nombreLocal">Nombre: {{ tipoLocal.nombre }}</h3>
        <button @click="BorrarTipoLocal(tipoLocal.id)">Borrar</button>
        <button @click="ModificarTipoLocal(tipoLocal.id)">Modificar</button>
      </div>
    </div>
  </h1>
  <h1 v-else>Cargando datos</h1>
</template>

<script lang="ts" setup>
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import TipoLocalService from '@/services/TipoLocalService'
import CommunityService from '../services/CommunityService'
import LocalService from '@/services/LocalService'

const communityService = new CommunityService()
const localService = new LocalService()
const tipoLocalService = new TipoLocalService()

const route = useRoute()
const { comunidad_id } = toRefs(route.params)

const locales = ref()
const tipoLocales = ref()

const nombreInsertar = ref('')
const tipoInsertar = ref('')

const nombreTipo = ref('')

const loading = ref(true)

const getTipo = async(id: number) => {
  const tipos = await tipoLocalService.listAllTiposLocales()
  for(const tipo of tipos) {
    for(const local of tipo.locales) {
      if(local.id == id) {
        console.log(tipo.nombre)
        return tipo.nombre
      }
    }
  }
}

const fetchLocales = async () => {
  const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))
  locales.value = comunidad.locales
  for(const local of locales.value) {
    local.nombreTipo = await getTipo(local.id)
  }
  fetchTipoLocales()
}

const fetchTipoLocales = async () => {
  tipoLocales.value = await tipoLocalService.listAllTiposLocales()
  loading.value = false
}

onMounted(fetchLocales)

const BorrarLocal = async (id: number) => {
  await localService.deleteLocal(id)
  await fetchLocales()
}

const BorrarTipoLocal = async (id: number) => {
  await tipoLocalService.deleteTipoLocal(id)
  await fetchTipoLocales()
}

const ModificarLocal = async (id: number) => {
  let nombre = prompt('Introduce el nuevo nombre del local')
  while (!nombre) {
    nombre = prompt('El nombre no puede estar vacío, introduce un nuevo nombre')
  }
  let tipoLocal = prompt('Introduce el nuevo tipo de local')
  let tipoModificar: any = 0
  let valido = false
  for (const tipo of tipoLocales.value) {
    if (tipoLocal == tipo.nombre) {
      tipoModificar = tipo
      valido = true
      break
    }
  }
  while (!tipoLocal || !valido) {
    tipoLocal = prompt('El tipo no puede estar vacío o no existe, introduce un nuevo tipo')
    for (const tipo of tipoLocales.value) {
      if (tipoLocal == tipo.nombre) {
        tipoModificar = tipo
        valido = true
        break
      }
    }
  }

  const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))

  const response = await localService.updateLocal(id, nombre, comunidad, tipoModificar)
  console.log(response)
  await fetchLocales()

}

const ModificarTipoLocal = async (id: number) => {
  let nombre = prompt('Introduce el nuevo nombre del tipo de local')
  while (!nombre) {
    nombre = prompt('El nombre no puede estar vacío, introduce un nuevo nombre')
  }
  let locales: any = []
  const response = await tipoLocalService.updateTipoLocal(id, nombre, locales)
  console.log(response)
  await fetchLocales()
}

const CrearLocal = async () => {
  if(!nombreInsertar.value || !tipoInsertar.value){
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
  if(!nombreTipo.value){
    alert('No puede haber campos vacios')
    return
  }
  let locales: any = []
  const response = await tipoLocalService.createTipoLocal(nombreTipo.value, locales)
  console.log(response)
  await fetchLocales()
}

</script>

<style>
.imgComunidad1 {
  width: 75px;
}

.imgAñadirComunidad {
  width: 75px;
  margin-top: 11px;
  margin-left: 12px;
}

#insercion {
  margin-top: 10px;
}

#lineaInferior {
  display: flex;
}

#Locales {
  display: flex;
  margin-top: 40px;
  flex-wrap: wrap;
  justify-content: center;

}

.Local {
  width: 100px;
  margin-left: 20px;
  text-align: center;
}

.tipoLocal {
  font-family: monospace;
}

.Bloque p {
  margin: 5px;
  font-style: italic;
}

.idLocal {
  visibility: collapse;
}

.Bloque h3 {
  margin: 5px;
  margin-top: -15px;
  font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

.Bloque img {
  margin-top: 10px;
}

#BotonLocal {
  background-color: rgb(245, 149, 80);
  font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
  border-style: groove;
  border-radius: 4px;
  width: 30px;
  border-color: rgb(245, 149, 80);
  border-style: double;
}

.añadirComunidad {

  display: grid;
  width: 100px;
  height: 160px;
  margin-left: 20px;
}

.insertar {
  border-color: rgb(245, 149, 80);
  border-radius: 4px;
  width: 92%;
  height: 19px;


}

::placeholder {
  font-family: Georgia, 'Times New Roman', Times, serif;
}
</style>
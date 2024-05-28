<template>
  <div>
    <h1 v-if="!loading" class="container">
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/userAdd.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="text" placeholder="Nombre" v-model="nombreInsertar">
            <input class="insertField" type="text" placeholder="Rol" v-model="rolInsertar">
            <button class="actionButton" @click="CreateVecino">Crear Vecino</button>
          </div>
        </div>

        <div class="communityList">
          <div class="community" v-for="vecino in vecinos" :key="vecino.id">
            <img class="communityIcon" src="../assets/user.svg" alt="Community Icon">
            <div class="info">
              <h3 class="communityName">Nombre: {{ vecino.nombre }}</h3>
              <p class="communityType">Rol: {{ vecino.nombreRol }}</p>
            </div>
            <div class="actions">
              <button @click="BorrarVecino(vecino.id)">Borrar</button>
              <button @click="ModificarVecino(vecino.id)">Modificar</button>
            </div>
          </div>
        </div>
      </div>
      <div class="section">
        <div class="addCommunity">
          <img class="addIcon" src="../assets/roleAdd.svg" alt="Add Community">
          <div class="insertion">
            <input class="insertField" type="text" placeholder="Nombre" v-model="nombreRol">
            <button class="actionButton" @click="CreateRol">Crear Rol</button>
          </div>
        </div>
        <div class="typeList">
          <div class="type" v-for="rol in roles" :key="rol.id">
            <img class="typeIcon" src="../assets/role.svg" alt="Type Icon">
            <div class="info">
              <h3 class="typeName">Nombre: {{ rol.nombre }}</h3>
            </div>
            <div class="actions">
              <button @click="BorrarRol(rol.id)">Borrar</button>
              <button @click="ModificarRol(rol.id)">Modificar</button>
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
import BloqueService from '../services/BloqueService'
import RolService from '@/services/RolService'
import VecinoService from '@/services/VecinoService'
import type IVecino from '@/interfaces/IVecino'
import type { Ref } from 'vue'

const bloqueService = new BloqueService()
const vecinoService = new VecinoService()
const rolService = new RolService()

const route = useRoute()
const { bloque_id1 } = toRefs(route.params)

const vecinos: Ref<Array<IVecino>> = ref([])
const roles = ref()

const nombreInsertar = ref('')
const rolInsertar = ref('')

const nombreRol = ref('')

const loading = ref(true)

const getRol = async (id: number) => {
  const roles = await rolService.listAllRoles()
  for (const rol of roles) {
    for (const vecino of rol.vecinos) {
      if (vecino.id == id) {
        console.log(rol.nombre)
        return rol.nombre
      }
    }
  }
}


const fetchVecinos = async () => {

  vecinos.value = await vecinoService.listAllVecinos()

  const bloque = await bloqueService.listBloqueById(Number(bloque_id1.value))

  console.log(bloque)

  roles.value = await rolService.listAllRoles()

  for (const vecino of vecinos.value) {
    const rol = await getRol(vecino.id)
    if (rol) {
      vecino.nombreRol = rol
    } else {
      vecino.nombreRol = "Sin rol"
    }
  }

  fetchRoles()
}

const fetchRoles = async () => {
  roles.value = await rolService.listAllRoles()
  loading.value = false
}

onMounted(fetchVecinos)

const BorrarVecino = async (id: number) => {
  await vecinoService.deleteVecino(id)
  await fetchVecinos()
}

const BorrarRol = async (id: number) => {
  await rolService.deleteRol(id)
  await fetchVecinos()
}

const ModificarVecino = async (id: number) => {
  let rolPrompt = prompt('Introduce el rol del vecino')
  let nombrePrompt = prompt('Introduce el nombre del vecino')
  if (!nombrePrompt || !rolPrompt) {
    alert('No puede haber campos vacios')
    return
  }
  let valido = false
  let rolId: any

  for (const rol of roles.value) {
    if (rolPrompt == rol.nombre) {
      rolId = rol.id
      valido = true
      break
    }
  }
  if (!valido) {
    alert('El rol no existe')
    return
  }

  const rolInsertar = await rolService.listRolById(rolId)
  const vecino = await vecinoService.listVecinoById(id)
  const viviendas = vecino.viviendas
  const response = await vecinoService.updateVecino(id, nombrePrompt, viviendas, rolInsertar)
  console.log(response)
  await fetchVecinos()

}

const ModificarRol = async (id: number) => {
  let nombre = prompt('Introduce el nuevo nombre del rol')
  while (!nombre) {
    alert('El nombre no puede estar vacío')
    return
  }

  let valido = true
  for (const rol of roles.value) {
    if (nombre == rol.nombre) {
      valido = false
      break
    }
  }

  if (!valido) {
    alert('El rol ya existe')
    return
  }

  let rol = await rolService.listRolById(id)
  let vecinos: any = rol.vecinos

  const response = await rolService.updateRol(id, nombre, vecinos)
  console.log(response)
  await fetchVecinos()
}

const CreateVecino = async () => {
  if (!nombreInsertar || !rolInsertar) {
    alert('No puede haber campos vacios')
    return
  }

  let valido = false
  let rolId: any

  for (const rol of roles.value) {
    if (rolInsertar.value == rol.nombre) {
      rolId = rol.id
      valido = true
      break
    }
  }
  if (!valido) {
    alert('El tipo no existe')
    return
  }

  const viviendas: any = []
  const rol = await rolService.listRolById(rolId)

  const response = await vecinoService.createVecino(nombreInsertar.value, viviendas, rol)

  console.log(response)
  await fetchVecinos()
}

const CreateRol = async () => {
  if (!nombreRol.value) {
    alert('No puede haber campos vacios')
    return
  }

  let valido = true
  for (const rol of roles.value) {
    if (nombreRol.value == rol.nombre) {
      valido = false
      break
    }
  }

  if (!valido) {
    alert('El rol ya existe')
    return
  }

  let vecinos: any = [] // Aquí sí va vacío porque lo estoy creando

  const response = await rolService.createRol(nombreRol.value, vecinos)
  console.log(response)
  await fetchRoles()

}

</script>
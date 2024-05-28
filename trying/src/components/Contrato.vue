<template>
    <div>
        <h1 v-if="!loading" class="container">
            <div class="section">
                <div class="addCommunity">
                    <img class="addIcon" src="../assets/firma-del-archivo.svg" alt="Add Contrato">
                    <div class="insertion">
                        <input class="insertField" type="text" placeholder="Contratante" v-model="contratanteInsertar">
                        <input class="insertField" type="date" v-model="createdAtInsertar">
                        <button class="actionButton" @click="CrearContrato">Crear Contrato</button>
                    </div>
                </div>
                <div class="contractList">
                    <div class="contract" v-for="contrato in contratos" :key="contrato.id">
                        <img class="contractIcon" src="../assets/diploma.svg" alt="Contract Icon">
                        <div class="info">
                            <p class="contractID">ID: {{ contrato.id }}</p>
                            <p class="contractType">Contratante: {{ contrato.nombreContratante }}</p>
                            <p class="contractDate">Fecha: {{ contrato.created_at }}</p>
                        </div>
                        <div class="actions">
                            <button @click="BorrarContrato(contrato.id)">Borrar</button>
                            <button @click="ModificarContrato(contrato.id)">Modificar</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section">
                <div class="addCommunity">
                    <img class="addIcon" src="../assets/hombre-empleado-alt.svg" alt="Add Contratante">
                    <div class="insertion">
                        <input class="insertField" type="text" placeholder="Nombre" v-model="nombreContratante">
                        <button class="actionButton" @click="CrearContratante">Crear Contratante</button>
                    </div>
                </div>
                <div class="typeList">
                    <div class="type" v-for="contratante in contratantes" :key="contratante.id">
                        <img class="typeIcon" src="../assets/hombre-empleado.svg" alt="Contratante Icon">
                        <div class="info">
                            <p class="typeID">ID: {{ contratante.id }}</p>
                            <h3 class="typeName">Nombre: {{ contratante.nombre }}</h3>
                        </div>
                        <div class="actions">
                            <button @click="BorrarContratante(contratante.id)">Borrar</button>
                            <button @click="ModificarContratante(contratante.id)">Modificar</button>
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

.contract {
  display: flex;
  align-items: center;
  border: 1px solid #ccc;
  border-radius: 4px;
  padding: 10px;
  margin-bottom: 10px;
}

.contractIcon {
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
import ContratanteService from '../services/ContratanteService'
import CommunityService from '../services/CommunityService'
import ContratoService from '../services/ContratoService'

const communityService = new CommunityService()
const contratoService = new ContratoService()
const contratanteService = new ContratanteService()

const route = useRoute()
const { comunidad_id } = toRefs(route.params)

const contratos = ref()
const contratantes = ref()

const contratanteInsertar = ref('')
const createdAtInsertar = ref('')

const nombreContratante = ref('')

const loading = ref(true)

const getContratante = async (id: number) => {
    const contratantesList = await contratanteService.listAllContratantes()
    for (const contratante of contratantesList) {
        for (const contrato of contratante.contratos) {
            if (contrato.id == id) {
                console.log(contratante.nombre)
                return contratante.nombre
            }
        }
    }
}

const fetchContratos = async () => {
    const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))
    contratos.value = comunidad.contratos
    console.log("Estos son los contratos: ")
    console.log(contratos.value)
    for (const contrato of contratos.value) {
        contrato.nombreContratante = await getContratante(contrato.id)
    }
    fetchContratantes()
}

const fetchContratantes = async () => {
    contratantes.value = await contratanteService.listAllContratantes()
    loading.value = false
}

onMounted(fetchContratos)

const BorrarContrato = async (id: number) => {
    await contratoService.deleteContrato(id)
    await fetchContratos()
}

const BorrarContratante = async (id: number) => {
    await contratanteService.deleteContratante(id)
    await fetchContratantes()
}

const ModificarContrato = async (id: number) => {
    let contratanteNombre = prompt('Introduce el nuevo contratante')
    if (!contratanteNombre) {
        alert('No puede haber campos vacíos')
        return
    }
    let contratanteModificar: any = 0
    let valido = false
    for (const contratante of contratantes.value) {
        if (contratanteNombre == contratante.nombre) {
            contratanteModificar = contratante
            valido = true
            break
        }
    }
    while (!contratanteNombre || !valido) {
        alert('El contratante no existe')
        return
    }

    let createdAtString = prompt('Introduce la nueva fecha (fallará si no es válida)', 'YYYY-MM-DD')
    if (!createdAtString) {
        alert('No puede haber campos vacíos')
        return
    }
    let created_at = new Date(createdAtString)

    const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))

    const response = await contratoService.updateContrato(id, comunidad, contratanteModificar, created_at)
    console.log(response)
    await fetchContratos()

}

const ModificarContratante = async (id: number) => {
    let nombre = prompt('Introduce el nuevo nombre del contratante')
    while (!nombre) {
        alert('El nombre no puede estar vacío')
        return
    }
    let valido = true
    for (const contratante of contratantes.value) {
        if (nombre == contratante.nombre) {
            valido = false
            break
        }
    }
    if (!valido) {
        alert('El contratante ya existe')
        return
    }
    let contratante = await contratanteService.listContratanteById(id)
    let contratos: any = contratante.contratos
    const response = await contratanteService.updateContratante(id, nombre, contratos)
    console.log(response)
    await fetchContratos()
}

const CrearContrato = async () => {
    if (!contratanteInsertar.value || !createdAtInsertar.value) {
        alert('No puede haber campos vacíos')
        return
    }
    let valido = false
    let contratanteId = 0
    for (const contratante of contratantes.value) {
        if (contratanteInsertar.value == contratante.nombre) {
            contratanteId = contratante.id
            valido = true
            break
        }
    }
    if (!valido) {
        alert('El contratante no existe')
        return
    }

    const comunidad = await communityService.listCommunityById(Number(comunidad_id.value))
    const contratante = await contratanteService.listContratanteById(contratanteId)
    let createdAt = new Date(createdAtInsertar.value)
    const response = await contratoService.createContrato(contratante, comunidad, createdAt)
    console.log(response)
    await fetchContratos()
}

const CrearContratante = async () => {
    if (!nombreContratante.value) {
        alert('No puede haber campos vacíos')
        return
    }
    let valido = true
    for (const contratante of contratantes.value) {
        if (nombreContratante.value == contratante.nombre) {
            valido = false
            break
        }
    }
    if (!valido) {
        alert('El contratante ya existe')
        return
    }
    let contratos: any = []
    const response = await contratanteService.createContratante(nombreContratante.value, contratos)
    console.log(response)
    await fetchContratos()
}

</script>
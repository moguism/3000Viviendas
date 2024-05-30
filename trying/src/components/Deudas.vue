<template>
    <div>
        <h1 v-if="!loading" class="container">
            <div class="section">
                <div class="addComunity">
                    <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="add Comunity">
                    <div class="insertion">
                        <input class="insertField" type="number" placeholder="Monto" v-model="montoPrompt">
                        <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
                        <input class="insertField" type="date" placeholder="Fecha" v-model="fechaDeudaInsertar">
                        <button class="actionButton" @click="CrearDeuda">Crear Deuda</button>
                    </div>
                </div>

                <div class="communityList">
                    <h1 style="text-align: center;">TOTAL:{{ total }}€</h1>
                    <div class="community" v-for="Deuda in deudas" :key="Deuda.id">
                        <img class="communityIcon" src="../assets/dolar-de-saco.svg" alt="Community Icon">
                        <div class="info">
                            <h3 class="communityName">Monto: {{ deudas.monto }}</h3>
                            <p class="communityType">Tipo de Deuda: {{ deudas.nombreTipo }}</p>
                            <p class="communityType">Fecha: {{ deudas.fecha }}</p>
                        </div>
                        <div class="actions">
                            <button @click="BorrarDeuda(deudas.id)">Borrar</button>
                            <button @click="ModificarDeuda(deudas.id)">Modificar</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section">
                <div class="addCommunity">
                    <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="Add Community">
                    <div class="insertion">
                        <input class="insertField" type="text" placeholder="Nombre" v-model="nombreTipo">
                        <button class="actionButton" @click="CrearTipoDeuda">Crear Tipo de Deuda</button>
                    </div>
                </div>
                <div class="typeList">
                    <div class="type" v-for="tipoDeuda in tipoDeudas" :key="tipoDeuda.id">
                        <img class="typeIcon" src="../assets/dolar-de-saco.svg" alt="Type Icon">
                        <div class="info">
                            <h3 class="typeName">Nombre: {{ tipoDeuda.nombre }}</h3>
                        </div>
                        <div class="actions">
                            <button @click="BorrarTipoDeuda(tipoDeuda.id)">Borrar</button>
                            <button @click="ModificarTipoDeuda(tipoDeuda.id)">Modificar</button>
                        </div>
                    </div>
                </div>
            </div>
        </h1>
        <h1 v-else>Cargando datos</h1>
    </div>
</template>

<script setup lang="ts">
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import TipoDeudaService from '@/services/TipoDeudaService'
import ComunidadService from '@/services/CommunityService'
import DeudaService from '@/services/DeudaService'

const tipoDeudaService = new TipoDeudaService
const deudaService = new DeudaService
const comunidadService = new ComunidadService

const route = useRoute()
const { comunidad_id } = toRefs(route.params)

const deudas = ref()
const tipoDeudas = ref()

const montoPrompt = ref()
const tipoInsertar = ref()
const fechaDeudaInsertar = ref()

const total = ref(0)

const nombreTipo = ref()

const loading = ref(true)

const getTipo = async (id:number) => {
    const tipos = await tipoDeudaService.listAllTiposDeuda()
    for(const tipo of tipos){
        for(const deuda of tipo.deudas){
            if(deuda.id == id){
                console.log(tipo.nombre)
                return tipo.nombre
            }
        }
    }
}

const fetchDeudas = async () => {
    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
}


const BorrarTipoDeuda = async (id: number) => {
    await tipoDeudaService.deleteTipoDeuda(id)
    await fetchDeudas()
}
const ModificarTipoDeuda = async (id: number) => {
    let nombre = prompt('Introduce el nuevo nombre del tipo de deuda')
    while (!nombre){
        alert('El nombre no puede estar vacio')
        return
    }
    let valido = true
    for(const tipo of tipoDeudas.value){
        if (nombre == tipo.nombre){
            valido = false
            break
        }
    }
    if (!valido){
        alert('el tipo ya existe')
        return
    }
    let tipo = await tipoDeudaService.listTipoDeudaById(id)
    let deudas: any = tipo.deudas
    const response = await tipoDeudaService.updateTipoDeuda(id, nombre, deudas)
    console.log(response)
    await fetchDeudas()
}

const CrearDeuda = async () =>{
    if (!montoPrompt || !tipoInsertar || !fechaDeudaInsertar){
        alert('o puede haber campos vacios')
        return
    }

    let fechaDeuda = new Date(fechaDeudaInsertar.value)
    let valido = false
    let tipoId
    for (const tipoDeuda of tipoDeudas.value) {
        if(tipoInsertar.value == tipoDeuda.nombre){
            tipoId = tipoDeuda.id
            valido = true
            break
        }
    }
    if (!valido) {
        alert('el tipo no existe')
        return
    }

    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
    const tipo = await tipoDeudaService.listTipoDeudaById(tipoId)
    const response = await deudaService.createDeuda(tipo, comunidad, fechaDeuda, montoPrompt.value)
    console.log(response)
    await fetchDeudas()
}

const CrearTipoDeuda = async () => {
    if(!nombreTipo.value){
        alert('No puede haber campos vacios')
        return
    }
    let valido = true
    for(const tipo of tipoDeudas.value){
        if (nombreTipo.value == tipo.nombre){
            valido = false
            break
        }
    }
    if(!valido){
        alert('el tipo ya existe')
    }
    let deudas: any = []
    const response = await tipoDeudaService.createTipoDeuda(nombreTipo.value, deudas)
    console.log(response)
    await fetchTipoDeudas()
}
</script>
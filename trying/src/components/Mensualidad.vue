<template>
    <div class="bloque-container">
        <h1 class="bloque-title">Mensualidad ID: {{  }}</h1>
        <p v-if="loading" class="loading-message">Cargando datos...</p>
        <div v-else>
            <h1 class="viviendas-title">Viviendas</h1>
            <div class="viviendas">
                <div class="añadir-vivienda">
                    <img class="añadir-vivienda-icon" src="../assets/casa-chimenea-medica.svg">
                    <div class="insercion">
                        <button class="boton-vivienda" @click="InsertarVivienda">
                            <img src="../assets/arrow-return-left.svg">
                        </button>
                    </div>
                </div>
                <div class="vivienda" v-for="vivienda in viviendas" :key="vivienda.id">
                    <img class="vivienda-icon" src="../assets/usuario-de-la-casa.svg">
                    <h3 class="nombre-vivienda">Vivienda: {{ vivienda.id }}</h3>
                    <div class="botones">
                        <button @click="BorrarVivienda(vivienda.id)" >Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.bloque-container {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    background-color: #f7f7f7;
    border-radius: 8px;
}

.bloque-title {
    font-size: 24px;
    margin-bottom: 20px;
}

.loading-message {
    font-style: italic;
}

.boton {
    padding: 10px 20px;
    margin: 0 10px;
    background-color: #007bff;
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

.viviendas-title {
    font-size: 20px;
    margin-bottom: 20px;
}

.viviendas {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}

.añadir-vivienda {
    display: flex;
    align-items: center;
    margin-right: 20px;
}

.añadir-vivienda-icon {
    width: 50px;
    margin-right: 10px;
}

.insercion {
    margin-top: 10px;
}

.vivienda {
    width: 200px;
    text-align: center;
    margin-bottom: 5px;
}

.vivienda-icon {
    width: 75px;
    margin-bottom: 30px;
}

.nombre-vivienda {
    margin-top: -15px;
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
</style>

<script setup lang="ts">
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import type IVivienda from '../interfaces/IVivienda'
import type { Ref } from 'vue'
import ViviendaService from '../services/ViviendaService'
import BloqueService from '../services/BloqueService'
import { useRouter } from 'vue-router'
import type IBloque from '@/interfaces/IBloque'
import type IMensualidad from '@/interfaces/IMensualidad'
import type IReunion from '@/interfaces/IReunion'
import MensualidadService from '@/services/MensualidadService'
import VecinoService from '@/services/VecinoService'

const route = useRoute()
const { bloque_id1: bloque_id1, mensualidad_id: mensualidad_id} = toRefs(route.params)

const viviendas: Ref<Array<IVivienda>> = ref([])

const viviendaService = new ViviendaService()
const bloqueService = new BloqueService()
const mensualidadService = new MensualidadService()
const vecinoService = new VecinoService()

const loading = ref(true)

const router = useRouter()

const fetchViviendas = async () => {
    const mensualidad = await mensualidadService.listMensualidadById(Number(mensualidad_id.value))

    console.log(mensualidad)

    viviendas.value = mensualidad.viviendas

    loading.value = false
}

onMounted(fetchViviendas)

const InsertarVivienda = async () => {

    let vivienda_id = prompt('Introduce el id de la vivienda')

    if(!vivienda_id){
        alert('No puede haber campos vacíos')
        return
    }

    let valido = false

    let mensualidad = await mensualidadService.listMensualidadById(Number(mensualidad_id.value))

    let bloque = await bloqueService.listBloqueById(Number(bloque_id1.value))

    let vivienda_id1: any

    for (const vivienda of bloque.viviendas){
        if(vivienda.id == Number(vivienda_id)){
            vivienda_id1 = vivienda.id
            valido = true
            break
        }
    }

    if(!valido){
        alert('La vivienda existe en el bloque')
        return
    }

    for(const vivienda of mensualidad.viviendas){
        if(vivienda.id == Number(vivienda_id)){
            valido = false
            break
        }
    }

    if(!valido){
        alert('La vivienda ya existe')
        return
    }

    let vivienda = await viviendaService.listViviendaById(vivienda_id1)
    console.log("Esta es la vivienda: ")
    console.log(vivienda)

    let vecinos = await vecinoService.listAllVecinos()
    let vecinoVivienda: any
    valido = false

    for(const vecino of vecinos){

        for(const vivienda of vecino.viviendas){

            if(vivienda.id = vivienda_id1){

                vecinoVivienda = vecino
                valido = true
                break

            }

        }

    }

    if(!valido){

        alert('Error')
        return

    }

    let viviendaActualizada = await viviendaService.updateVivienda(vivienda.id, bloque, vivienda.escalera, vivienda.planta, vivienda.puerta, vivienda.letra, vecinoVivienda, mensualidad)
    console.log("Esta es la vivienda actualizada: ")
    console.log(viviendaActualizada)

    const viviendasD: Array<IVivienda> = mensualidad.viviendas
    viviendasD.push(viviendaActualizada)

    console.log("Este es el bloque: ")
    console.log(bloque)

    console.log("Viviendas: ")
    console.log(viviendas)

    const response = await mensualidadService.updateMensualidad(Number(mensualidad_id.value), mensualidad.fecha, mensualidad.cuantia, viviendasD, bloque)
    console.log(response)
    await fetchViviendas()

}

const BorrarVivienda = async (id: number) => {

    const mensualidad = await mensualidadService.listMensualidadById(Number(mensualidad_id.value))

    let viviendas = mensualidad.viviendas

    let index = 0
    
    for(let i = 0; i < viviendas.length; i++){
        if(viviendas[i].id == id){
            index = i
            break
        }
    }

    viviendas.splice(index, 1)
    
    const response = await mensualidadService.updateMensualidad(Number(mensualidad_id.value), mensualidad.fecha, mensualidad.cuantia, viviendas, mensualidad.bloque)
    console.log(response)
    await fetchViviendas()
}

</script>
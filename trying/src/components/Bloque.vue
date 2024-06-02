<template>
    <Header></Header>
    <div class="">
        <p v-if="loading" class="loading-message">Cargando datos...</p>
        <div v-else class="divBloque">
            <div class="bloqueInfo">
                <h1 class="bloque-title">Bloque {{ numero }}</h1>
                <div class="botones">
                    <div @click="CargarVecinos" class="boton">
                        <img src="../assets/icons8-vecino-100.png" class="imgInfo" alt="">
                        <p class="tituloInfo">VECINOS</p>
                    </div>
                    <div @click="CargarReuniones" class="boton">
                        <img src="../assets/icons8-sala-de-reuniones-100.png" class="imgInfo" alt="">
                        <p class="tituloInfo">REUNIONES</p>
                    </div>
                </div>
            </div>
        </div>
        <h1 class="viviendas-title">Viviendas</h1>
        <div class="bloques">
            <div class="bloque">
                <img class="bloque-icon" src="../assets/casa-chimenea-medica.svg">
                <input class="numeroInsertar" type="text" placeholder="Escalera" v-model="escalera"></input>
                <input class="numeroInsertar" type="text" placeholder="Planta" v-model="planta"></input>
                <input class="numeroInsertar" type="text" placeholder="Puerta" v-model="puerta"></input>
                <input class="numeroInsertar" type="text" placeholder="Letra" v-model="letra"></input>
                <input class="numeroInsertar" type="text" placeholder="Vecino" v-model="nombreVecino"></input>
                <input class="numeroInsertar" type="number" placeholder="Mensualidad"v-model="ultima_mensualidad"></input>
                <div class="buttonAgregar" @click="CrearVivienda">
                    <p class="textoAgregar">AGREGAR VIVIENDA</p>
                    <img class="imgAgregar" src="../assets/arrow-return-left.svg">
                </div>
            </div>
            <div class="bloque" v-for="vivienda in viviendas" :key="vivienda.id">
                <img class="bloque-icon" src="../assets/chimenea-de-la-casa.svg">
                <h3 class="nombre-bloque">Escalera: {{ vivienda.escalera }}</h3>
                <h3 class="nombre-bloque">Planta: {{ vivienda.planta }}</h3>
                <h3 class="nombre-bloque">Puerta: {{ vivienda.puerta }}</h3>
                <h3 class="nombre-bloque">Letra: {{ vivienda.letra }}</h3>
                <h3 class="nombre-bloque">Vecino: {{ vivienda.nombreVecino }}</h3>
                <h3 class="nombre-bloque">Mensualidad: {{ vivienda.idMensualidad }}</h3>
                <div class="options">
                    <button @click="ModificarVivienda(vivienda.id)">Modificar</button>
                    <button @click="BorrarVivienda(vivienda.id)">Eliminar</button>
                </div>
            </div>
        </div>
        <h1 class="viviendas-title">Mensualidades</h1>
        <div class="bloques">
            <div class="bloque">
                <img class="bloque-icon" src="../assets/salario-del-usuario.svg">
                <input class="numeroInsertar" type="date" placeholder="Fecha" v-model="fecha"></input>
                <input class="numeroInsertar" type="number" placeholder="Cuantia" v-model="cuantia"></input>
                <div class="buttonAgregar" @click="CrearMensualidad">
                    <p class="textoAgregar">AGREGAR MENSUALIDAD</p>
                    <img class="imgAgregar" src="../assets/arrow-return-left.svg">
                </div>
            </div>
            <div class="bloque" v-for="mensualidad in mensualidades" :key="mensualidad.id">
                <img class="bloque-icon" src="../assets/dolar-de-saco.svg">
                <h3 class="nombre-bloque">Fecha: {{ stringAfecha(mensualidad.fecha) }}</h3>
                <h3 class="nombre-bloque">Cuantia: {{ mensualidad.cuantia }}</h3>
                <div class="options">
                    <button @click="ModificarMensualidad(mensualidad.id)">Modificar</button>
                    <button @click="BorrarMensualidad(mensualidad.id)">Eliminar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.bloque-title {
    color: rgb(255, 255, 255);
    text-shadow: rgb(255, 140, 0) 1px 1px 1px;
    margin: 0%;
    margin-top: 2%;
}

.bloqueInfo {
    margin: auto;
    justify-content: center;
    align-items: center;
    text-align: center;
    margin-top: 10px;
    margin-bottom: 20px;
    background-color: #fea13759;
    border-radius: 20px;
    padding-top: 1px;
    padding-bottom: 3%;
}

.divBloque {
    padding-top: 30px;
    width: 75%;
    align-items: center;
    margin-left: auto;
    margin-right: auto;
}

.botones {
    display: flex;
    justify-content: center;
    text-align: center;
    margin-top: 10px;
    margin-bottom: 20px;
    border-radius: 20px;
    padding-top: 1px;
}

.boton {
    min-width: 23%;
    padding: 20px 20px;
    margin: 5px 10px;
    margin-top: 10px;
    background-color: #f8ef7e;
    color: #000000;
    border: none;
    border-radius: 10px;
    cursor: pointer;
}

.imgInfo {
    width: 70px;
}

.tituloInfo {
    margin: 5%;
    font-size: larger;
    font-weight: bold;
}

.viviendas-title {
    text-align: center;
    font-size: 20px;
    margin-bottom: 20px;
}

.bloques {
    height: auto;
    justify-content: center;
    flex-wrap: wrap;
    display: flex;
}

.bloque {
    display: grid;
    align-items: center;
    justify-content: center;
    text-align: center;
    background-color: rgb(255, 255, 255);
    border-radius: 10px;
    margin-bottom: 3%;
    margin-right: 3%;
    margin-left: 3%;
    width: 150px;
}

.bloque-icon {
    margin-right: auto;
    margin-left: auto;
    margin-bottom: 10%;
    padding-top: 10%;
    width: 75px;
}

.buttonAgregar {
    border-radius: 10px;
    background-color: rgb(245, 209, 156);
    display: flex;
    font-weight: bold;
    margin: 15%;
    margin-left: auto;
    margin-right: auto;
    margin-top: 4%;
    margin-bottom: 6%;
    padding-left: 5%;
    padding-right: 5%;
    border-radius: 10px;
}

.options {
    margin-bottom: 6%;
}

.imgAgregar {
    margin-left: 10%;
    width: 30px;
}

input {
    width: 70%;
    margin-bottom: 5%;
    margin-left: auto;
    margin-right: auto;
    border-color: rgb(219, 181, 125);
    border-style: solid;
    border-radius: 4px;
}

.textoAgregar {
    font-size: small;
}

.nombre-bloque {
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}

</style>

<script setup lang="ts">
import { toRefs } from 'vue'
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import type IVivienda from '../interfaces/IVivienda'
import type { Ref } from 'vue'
import BloqueService from '../services/BloqueService'
import ViviendaService from '../services/ViviendaService'
import { useRouter } from 'vue-router'
import VecinoService from '@/services/VecinoService'
import type IMensualidad from '@/interfaces/IMensualidad'
import MensualidadService from '@/services/MensualidadService'
import Header from './Header.vue'

const route = useRoute()
const { bloque_id: bloque_id } = toRefs(route.params)

const viviendas: Ref<Array<IVivienda>> = ref([])
const mensualidades: Ref<Array<IMensualidad>> = ref([])

const bloqueService = new BloqueService()
const viviendaService = new ViviendaService()
const vecinoService = new VecinoService()
const mensualidadService = new MensualidadService()
const numero = ref()

const escalera = ref('')
const puerta = ref('')
const letra = ref('')
const planta = ref('')
const nombreVecino = ref()
const ultima_mensualidad = ref()

const cuantia = ref()
const fecha = ref()

const loading = ref(true)

const router = useRouter()

const getNombreVecino = async (id: number) => {
    const vecinos = await vecinoService.listAllVecinos()
    for (const vecino of vecinos) {
        for (const vivienda of vecino.viviendas) {
            if (vivienda.id == id) {
                return vecino.nombre
            }
        }
    }
}

const stringAfecha = (str: Date) => {
    const date = new Date(str);
    return date.toLocaleDateString('en-CA'); // Esto devuelve el formato 'YYYY-MM-DD'
}

const getMensualidad = async (id: number) => {
    for (const mensualidad of mensualidades.value) {
        for (const vivienda of mensualidad.viviendas) {
            if (vivienda.id == id) {
                console.log("Encontrado")
                return mensualidad.id
            }
        }
    }
}

const fetchBloques = async () => {
    const bloque = await bloqueService.listBloqueById(Number(bloque_id.value))
    mensualidades.value = await mensualidadService.listAllMensualidades()
    numero.value = bloque.numero
    console.log(bloque)
    viviendas.value = bloque.viviendas
    for (const vivienda of viviendas.value) {
        let nombre = await getNombreVecino(vivienda.id)
        if (nombre != undefined) {
            vivienda.nombreVecino = nombre
            console.log(vivienda.nombreVecino)
        }
        let idMensualidad = await getMensualidad(vivienda.id)
        if (idMensualidad != undefined) {
            vivienda.idMensualidad = idMensualidad
            console.log(vivienda.idMensualidad)
        } else {
            console.log("No encontrado")
        }
    }
    loading.value = false
}

onMounted(fetchBloques)

const CrearVivienda = async () => {
    const bloque = await bloqueService.listBloqueById(Number(bloque_id.value))
    if (!nombreVecino.value || !ultima_mensualidad.value) {
        alert('Ciertos campos no pueden estar vacíos')
        return
    }

    let valido = false
    let vecinos = await vecinoService.listAllVecinos()
    let vecinoInsertar: any
    for (const vecino of vecinos) {
        if (nombreVecino.value == vecino.nombre) {
            vecinoInsertar = vecino
            valido = true
            break
        }
    }
    if (!valido) {
        alert('El vecino no existe')
        return
    }
    valido = false
    for (const mensualidad of mensualidades.value) {
        if (mensualidad.id == Number(ultima_mensualidad.value)) {
            valido = true
            break
        }
    }
    if (!valido) {
        alert('No existe la mensualidad')
        return
    }
    const mensualidad = await mensualidadService.listMensualidadById(Number(ultima_mensualidad.value))
    console.log("Mensualidad: ", mensualidad)
    const response = await viviendaService.createVivienda(bloque, escalera.value, letra.value, mensualidad, planta.value, puerta.value, vecinoInsertar)
    console.log(response)
    await fetchBloques()
}

const CrearMensualidad = async () => {
    if (!fecha.value || cuantia.value) {
        alert('No puede haber campos vacíos')
        return
    }
    let viviendas: any = []
    const response = await mensualidadService.createMensualidad(fecha.value, Number(cuantia.value), viviendas)
    console.log(response)
    await fetchBloques()
}

const ModificarVivienda = async (id: number) => {
    let escalera = prompt('Introduce la escalera')
    let planta = prompt('Introduce la planta')
    let puerta = prompt('Introduce la puerta')
    let letra = prompt('Introduce la letra')
    let vecinoNombre = prompt('Introduce el vecino')
    if (!vecinoNombre) {
        alert('Este campo no puede estar vacío')
        return
    }
    let vecinoInsertar: any
    let valido = false
    let vecinos = await vecinoService.listAllVecinos()
    for (const vecino of vecinos) {
        if (vecinoNombre == vecino.nombre) {
            vecinoInsertar = vecino
            valido = true
            break
        }
    }
    if (!valido) {
        alert('El vecino no existe')
        return
    }
    let ultima_mensualidad = prompt('Introduce el id de la última mensualidad')
    valido = false
    const bloque = await bloqueService.listBloqueById(Number(bloque_id.value))
    for (const mensualidad of mensualidades.value) {
        if (mensualidad.id == Number(ultima_mensualidad)) {
            valido = true
            break
        }
    }
    if (!valido) {
        alert('No existe la mensualidad')
        return
    }
    const mensualidad = await mensualidadService.listMensualidadById(Number(ultima_mensualidad))
    console.log("Mensualidad: ")
    console.log(mensualidad)
    const response = await viviendaService.updateVivienda(id, bloque, escalera, letra, mensualidad, planta, puerta, vecinoInsertar)
    console.log(response)
    await fetchBloques()
}

const ModificarMensualidad = async (id: number) => {
    let mensualiadad = await mensualidadService.listMensualidadById(id)
    let viviendas = mensualiadad.viviendas
    let fechaString = prompt('Introduce la nueva fecha (fallará si no es válida)', 'YYYY-MM-DD')
    if (!fechaString) {
        alert('No puede haber campos vacíos')
        return
    }
    let fecha = new Date(fechaString)
    let cuantia = prompt('Introduce la nueva cuantia (fallará si no es válida)')
    if (!cuantia) {
        alert('No puede haber campos vacíos')
        return
    }
    const response = await mensualidadService.updateMensualidad(id, fecha, Number(cuantia), viviendas)
    console.log(response)
    await fetchBloques()
}

const BorrarVivienda = async (id: number) => {
    const response = await viviendaService.deleteVivienda(id)
    console.log(response)
    await fetchBloques()
}

const BorrarMensualidad = async (id: number) => {
    const response = await mensualidadService.deleteMensualidad(id)
    console.log(response)
    await fetchBloques()
}

const CargarReuniones = () => {
    let bloque_id1 = Number(bloque_id.value)
    router.push({ name: 'Reunion', params: { bloque_id1 } })
}

const CargarVecinos = () => {
    let bloque_id1 = Number(bloque_id.value)
    router.push({ name: 'Vecino', params: { bloque_id1 } })
}
</script>

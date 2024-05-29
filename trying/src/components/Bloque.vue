<template>
    <div class="bloque-container">
        <h1 class="bloque-title">Bloque ID: {{ bloque_id }}</h1>
        <p v-if="loading" class="loading-message">Cargando datos...</p>
        <div v-else>
            <div class="botones">
                <button @click="CargarVecinos" class="boton">Pulsa aquí para acceder a los vecinos</button>
                <button @click="CargarReuniones" class="boton">Pulsa aquí para acceder a las reuniones</button>
            </div>
            <h1 class="viviendas-title">Viviendas</h1>
            <div class="viviendas">
                <div class="añadir-vivienda">
                    <img class="añadir-vivienda-icon" src="../assets/casa-chimenea-medica.svg">
                    <div class="insercion">
                        <button class="boton-vivienda" @click="CrearVivienda">
                            <img src="../assets/arrow-return-left.svg">
                        </button>
                    </div>
                </div>
                <div class="vivienda" v-for="vivienda in viviendas" :key="vivienda.id">
                    <img class="vivienda-icon" src="../assets/usuario-de-la-casa.svg">
                    <h6 class="nombre-vivienda">ID: {{ vivienda.id }} </h6>
                    <h6 class="nombre-vivienda">Escalera: {{ vivienda.escalera }}</h6>
                    <h6 class="nombre-vivienda">Planta: {{ vivienda.planta }}</h6>
                    <h6 class="nombre-vivienda">Puerta: {{ vivienda.puerta }}</h6>
                    <h6 class="nombre-vivienda">Letra: {{ vivienda.letra }}</h6>
                    <h6 class="nombre-vivienda">Vecino: {{ vivienda.nombreVecino }}</h6>
                    <h6 class="nombre-vivienda">Última mensualidad: {{ vivienda.idMensualidad }}</h6>
                    <div class="botones">
                        <button @click="ModificarVivienda(vivienda.id)">Modificar</button>
                        <button @click="BorrarVivienda(vivienda.id)">Eliminar</button>
                    </div>
                </div>
            </div>
            <h1 class="viviendas-title">Mensualidades</h1>
            <div class="viviendas">
                <div class="añadir-vivienda">
                    <img class="añadir-vivienda-icon" src="../assets/salario-del-usuario.svg">
                    <div class="insercion">
                        <button class="boton-vivienda" @click="CrearMensualidad">
                            <img src="../assets/arrow-return-left.svg">
                        </button>
                    </div>
                </div>
                <div class="vivienda" v-for="mensualiadad in mensualidades" :key="mensualiadad.id">
                    <img class="vivienda-icon" src="../assets/dolar-de-saco.svg">
                    <h6 class="nombre-vivienda">ID: {{ mensualiadad.id }}</h6>
                    <h6 class="nombre-vivienda">Fecha: {{ mensualiadad.fecha }}</h6>
                    <h6 class="nombre-vivienda">Cuantía: {{ mensualiadad.cuantia }}</h6>
                    <div class="botones">
                        <button @click="ModificarMensualidad(mensualiadad.id)">Modificar</button>
                        <button @click="BorrarMensualidad(mensualiadad.id)">Eliminar</button>
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

.botones {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
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
    margin-right: 100px
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
import BloqueService from '../services/BloqueService'
import ViviendaService from '../services/ViviendaService'
import { useRouter } from 'vue-router'
import VecinoService from '@/services/VecinoService'
import type IBloque from '@/interfaces/IBloque'
import type IMensualidad from '@/interfaces/IMensualidad'
import type IReunion from '@/interfaces/IReunion'
import MensualidadService from '@/services/MensualidadService'

const route = useRoute()
const { bloque_id: bloque_id } = toRefs(route.params)

const viviendas: Ref<Array<IVivienda>> = ref([])
const mensualidades: Ref<Array<IMensualidad>> = ref([])

const bloqueService = new BloqueService()
const viviendaService = new ViviendaService()
const vecinoService = new VecinoService()
const mensualidadService = new MensualidadService()

const loading = ref(true)

const router = useRouter()

const getTipo = async (id: number) => {
    const vecinos = await vecinoService.listAllVecinos()
    for (const vecino of vecinos) {
        for (const vivienda of vecino.viviendas) {
            if (vivienda.id == id) {
                return vecino.nombre
            }
        }
    }
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
    console.log(bloque)

    viviendas.value = bloque.viviendas

    for (const vivienda of viviendas.value) {

        let nombre = await getTipo(vivienda.id)
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

    let escalera = prompt('Introduce la escalera')
    if (!escalera) {
        alert('No puede haber campos vacíos')
        return
    }
    let planta = prompt('Introduce la planta')
    if (!planta) {
        alert('No puede haber campos vacíos')
        return
    }
    let puerta = prompt('Introduce la puerta')
    if (!puerta) {
        alert('No puede haber campos vacíos')
        return
    }
    let letra = prompt('Introduce la letra')
    if (!letra) {
        alert('No puede haber campos vacíos')
        return
    }
    let vecinoNombre = prompt('Introduce el vecino')
    if (!vecinoNombre) {
        alert('No puede haber campos vacíos')
        return
    }

    let valido = false
    let vecinos = await vecinoService.listAllVecinos()

    let vecinoInsertar: any

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
    if (ultima_mensualidad) {

        valido = false

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
        console.log("Mensualidad: ", mensualidad)

        const response = await viviendaService.createVivienda(bloque, escalera, letra, mensualidad, planta, puerta, vecinoInsertar)
        console.log(response)

        await fetchBloques()

    } else {

        const response = await viviendaService.createVivienda(bloque, escalera, letra, null, planta, puerta, vecinoInsertar)
        console.log(response)
        await fetchBloques()

    }

}

const CrearMensualidad = async () => {

    let fechaString = prompt('Introduce la fecha (fallará si no es válida)', 'YYYY-MM-DD')
    if (!fechaString) {
        alert('No puede haber campos vacíos')
        return
    }

    let fecha = new Date(fechaString)

    let cuantia = prompt('Introduce la cuantia (fallará si no es válida)')
    if (!cuantia) {
        alert('No puede haber campos vacíos')
        return
    }

    let viviendas: any = []

    const response = await mensualidadService.createMensualidad(fecha, Number(cuantia), viviendas)
    console.log(response)
    await fetchBloques()

}

const ModificarVivienda = async (id: number) => {

    let escalera = prompt('Introduce la escalera')
    if (!escalera) {
        alert('No puede haber campos vacíos')
        return
    }

    let planta = prompt('Introduce la planta')
    if (!planta) {
        alert('No puede haber campos vacíos')
        return
    }

    let puerta = prompt('Introduce la puerta')
    if (!puerta) {
        alert('No puede haber campos vacíos')
        return
    }

    let letra = prompt('Introduce la letra')
    if (!letra) {
        alert('No puede haber campos vacíos')
        return
    }

    let vecinoNombre = prompt('Introduce el vecino')
    if (!vecinoNombre) {
        alert('No puede haber campos vacíos')
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

    const bloque = await bloqueService.listBloqueById(Number(bloque_id.value))

    if (ultima_mensualidad) {

        valido = false

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

    } else {

        const response = await viviendaService.updateVivienda(id, bloque, escalera, letra, null, planta, puerta, vecinoInsertar)
        console.log(response)
        await fetchBloques()

    }


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

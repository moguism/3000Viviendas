<template>
    <div>
        <h1 v-if="!loading" class="container">
            <div class="section">
                <div class="addCommunity">
                    <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="Add Community">
                    <div class="insertion">
                        <input class="insertField" type="number" placeholder="Monto" v-model="montoPrompt">
                        <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
                        <input class="insertField" type="date" placeholder="Fecha" v-model="fechaDeudaInsertar">
                        <button class="actionButton" @click="CrearDeuda">Crear Deuda</button>
                    </div>
                </div>

                <div class="communityList">
                    <h1 style="text-align: center;">TOTAL: {{ total }}€ </h1>
                    <div class="community" v-for="deuda in deudas" :key="deuda.id">
                        <img class="communityIcon" src="../assets/dolar-de-saco.svg" alt="Community Icon">
                        <div class="info">
                            <h3 class="communityName">Monto: {{ deuda.monto }}</h3>
                            <p class="communityType">Tipo de Deuda: {{ deuda.nombreTipo }}</p>
                            <p class="communityType">Fecha: {{ stringAfecha(deuda.fecha) }}</p>
                        </div>
                        <div class="actions">
                            <button @click="BorrarDeuda(deuda.id)">Borrar</button>
                            <button @click="ModificarDeuda(deuda.id)">Modificar</button>
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
import TipoDeudaService from '@/services/TipoDeudaService'
import ComunidadService from '@/services/CommunityService'
import DeudaService from '@/services/DeudaService'

const comunidadService = new ComunidadService()
const deudaService = new DeudaService()
const tipoDeudaService = new TipoDeudaService()

const route = useRoute()
const { comunidad_id } = toRefs(route.params)

const deudas = ref()
const tipoDeudas = ref()

const montoPrompt = ref('')
const tipoInsertar = ref('')
const fechaDeudaInsertar = ref('')

const total = ref(0)

const nombreTipo = ref('')

const loading = ref(true)

const getTipo = async (id: number) => {
    const tipos = await tipoDeudaService.listAllTiposDeuda()
    for (const tipo of tipos) {
        for (const deuda of tipo.deudas) {
            if (deuda.id == id) {
                console.log(tipo.nombre)
                return tipo.nombre
            }
        }
    }
    return "Mensualidad"
}

const stringAfecha = (str: Date) => {
    const date = new Date(str);
    return date.toLocaleDateString('en-CA'); // Esto devuelve el formato 'YYYY-MM-DD'
}

const fetchDeudas = async () => {
    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
    console.log(comunidad)
    deudas.value = comunidad.deudas
    total.value = 0
    for (const deuda of deudas.value) {
        deuda.nombreTipo = await getTipo(deuda.id)
        total.value += deuda.monto
    }
    fetchTipoDeudas()
}

const fetchTipoDeudas = async () => {
    tipoDeudas.value = await tipoDeudaService.listAllTiposDeuda()
    loading.value = false
}

onMounted(fetchDeudas)

const BorrarDeuda = async (id: number) => {
    await deudaService.deleteDeuda(id)
    await fetchDeudas()
}

const BorrarTipoDeuda = async (id: number) => {
    await tipoDeudaService.deleteTipoDeuda(id)
    await fetchDeudas()
}

const ModificarDeuda = async (id: number) => {
    let tipoDeudaPrompt = prompt('Introduce el tipo de deuda')
    let montoPrompt = prompt('Introduce el monto de la deuda')
    if (!montoPrompt || !tipoDeudaPrompt) {
        alert('No puede haber campos vacios')
        return
    }
    let fechaDeudaInsertar = prompt('Introduce la nueva fecha de la deuda (AAAA-MM-DD)')
    if (!fechaDeudaInsertar) {
        alert('No puede haber campos vacios')
        return
    }
    let fechaDeuda = new Date(fechaDeudaInsertar)
    let valido = false
    let tipoId
    for (const tipoDeuda of tipoDeudas.value) {
        if (tipoDeudaPrompt == tipoDeuda.nombre) {
            tipoId = tipoDeuda.id
            valido = true
            break
        }
    }
    if (!valido) {
        alert('El tipo no existe')
        return
    }

    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
    const tipoInsertar = await tipoDeudaService.listTipoDeudaById(tipoId)
    const response = await deudaService.updateDeuda(id, tipoInsertar, comunidad, fechaDeuda, Number(montoPrompt))
    console.log(response)
    await fetchDeudas()

}

const ModificarTipoDeuda = async (id: number) => {
    let nombre = prompt('Introduce el nuevo nombre del tipo de deuda')
    while (!nombre) {
        alert('El nombre no puede estar vacío')
        return
    }
    let valido = true
    for (const tipo of tipoDeudas.value) {
        if (nombre == tipo.nombre) {
            valido = false
            break
        }
    }
    if (!valido) {
        alert('El tipo ya existe')
        return
    }
    let tipo = await tipoDeudaService.listTipoDeudaById(id)
    let deudas: any = tipo.deudas
    const response = await tipoDeudaService.updateTipoDeuda(id, nombre, deudas)
    console.log(response)
    await fetchDeudas()
}

const CrearDeuda = async () => {
    if (!montoPrompt || !tipoInsertar || !fechaDeudaInsertar) {
        alert('No puede haber campos vacios')
        return
    }

    let fechaDeuda = new Date(fechaDeudaInsertar.value)
    let valido = false
    let tipoId
    for (const tipoDeuda of tipoDeudas.value) {
        if (tipoInsertar.value == tipoDeuda.nombre) {
            tipoId = tipoDeuda.id
            valido = true
            break
        }
    }
    if (!valido) {
        alert('El tipo no existe')
        return
    }

    const comunidad = await comunidadService.listCommunityById(Number(comunidad_id.value))
    const tipo = await tipoDeudaService.listTipoDeudaById(tipoId)
    const response = await deudaService.createDeuda(tipo, comunidad, fechaDeuda, Number(montoPrompt.value))
    console.log(response)
    await fetchDeudas()
}

const CrearTipoDeuda = async () => {
    if (!nombreTipo.value) {
        alert('No puede haber campos vacios')
        return
    }
    let valido = true
    for (const tipo of tipoDeudas.value) {
        if (nombreTipo.value == tipo.nombre) {
            valido = false
            break
        }
    }
    if (!valido) {
        alert('El tipo ya existe')
        return
    }
    let deudas: any = [] // Aquí sí va vacío porque lo estoy creando
    const response = await tipoDeudaService.createTipoDeuda(nombreTipo.value, deudas)
    console.log(response)
    await fetchTipoDeudas()
}

</script>

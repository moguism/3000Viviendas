<template>
    <div>
        <h1 v-if="!loading" class="container">
            <div class="section">
                <div class="addComunity">
                    <img class="addIcon" src="../assets/salario-del-usuario.svg" alt="add Comunity">
                    <div class="insertion">
                        <input class="insertField" type="number" placeholder="Monto" v-model="montoPrompt">
                        <input class="insertField" type="text" placeholder="Tipo" v-model="tipoInsertar">
                        <input class="insertField" type="date" placeholder="Fecha" v-model="fechaIngresoInsertar">
                        <button class="actionButton" @click="CrearDeuda">Crear Deuda</button>
                    </div>
                </div>

                <div class="communityList">
                    <h1 style="text-align: center;">TOTAL:{{ total }}€</h1>
                    <div class="community" v-for="Deuda in Deudas" :key="Deuda.id">
                        <img class="communityIcon" src="../assets/dolar-de-saco.svg" alt="Community Icon">
                        <div class="info">
                            <h3 class="communityName">Monto: {{ deuda.monto }}</h3>
                            <p class="communityType">Tipo de Deuda: {{ deuda.nombreTipo }}</p>
                            <p class="communityType">Fecha: {{ deuda.fecha }}</p>
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
                            <button @click="Modificar(tipoDeuda.id)">Modificar</button>
                        </div>
                    </div>
                </div>
            </div>
        </h1>
        <h1 v-else>Cargando datos</h1>
    </div>
</template>
<template>
    <div id="header">
        <div id="Menu">
            <div id="Logo">
                <img src="" />
            </div>
        </div>
        <div id="HeaderText">
            <!--POdria pone el nombre de la app en el inciio y leugo una ves dentro de una comundiad, mostrar el nombre de la comunida dy el bloque(?)-->
        </div>
        <div id="User">
            <!--Aca podria poner el nombre del usuario y su foto-->
        </div>
    </div>
</template>
<script lang="ts" setup>

</script>

<style>

    #header {
        width: 100%;
        margin: 0%;
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 10px;
        background-color: #f1f1f1;
    }

    #Menu {
        display: flex;
        align-items: center;
    }

    #Logo {
        margin-right: 10px;
    }

    #HeaderText {
        font-size: 18px;
        font-weight: bold;
    }

    #User {
        display: flex;
        align-items: center;
    }

    #User img {
        width: 30px;
        height: 30px;
        border-radius: 50%;
        margin-left: 10px;
    }
</style>

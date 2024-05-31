<template>
    <div class="header">
        
        <div id="HomeButton" @click="goHome()" >
            
            <p class="home">GESTOR DE<br>COMUNIDADES</p>
            <img class="logo" src="../assets/administracion.png" >
        </div>

        <div id="User">
            <img class="logo" src="../assets/profile.png">
            <p class="userName">userName<br>userSurname</p>
        </div>
    </div>


</template>

<style>
.header{
    justify-content: space-between;
    display: flex;
    background-color: rgb(255, 186, 66);
    width: 100%;
}
#HomeButton{
    justify-content: space-between;
    display: flex;
    background-color: rgb(255, 186, 66);
    width: 15%;
    margin-left: 20px;
}

.logo {
    width: 50px;
    height: 50px;
    margin: 10px;
}


.home {
    font-weight: bolder;
    margin: auto;
    color: rgb(255, 255, 255);
}
p.home{
    margin-right: 0%;
    padding: 0 0 0 0;
}
#User {
    float: right;
    display: flex;
    margin: 0 0 0 0;
}
.userName{
    color: rgb(255, 255, 255);
    margin-right: 20px;
    font-weight: bold;
}
</style>


<script lang="ts" setup>
import router from '@/router';
const goHome = () =>{
  router.push({name: 'Home'});
}

</script>

<template>
  <div id="app">
    <Header></Header>
    <router-view></router-view>
  </div>
</template>
<style>
body {
  padding: 0 0 0 0;
  margin: 0 0 0 0;
  background-color: rgb(246, 230, 192);
  font-family: Arial, Helvetica, sans-serif
}
</style>

<script lang="ts">
import Header from './components/Header.vue';
export default {
  name: 'App'
}

</script>
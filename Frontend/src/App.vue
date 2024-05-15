<template>

  <h1> {{ id }} </h1>
  <h1>{{ nombre }}</h1>
  <h1>{{ direccion }}</h1>

</template>

<script>

import axios from "axios"

export default {

  data() {

    return {

      id : Number,
      nombre: String,
      direccion: String

    }

  },

  async mounted() {

    this.id = ""
    this.nombre = ""
    this.direccion = ""

    let response = await axios.get("http://127.0.0.1:8080/api/comunidades")

    console.log(response)
    
    this.id = response.data[0].id
    this.nombre = response.data[0].nombre
    this.direccion = response.data[0].direccion

  }

};

</script>
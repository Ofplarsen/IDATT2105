<template>
  <h3>Log</h3>
  <button id="buttonLeft" @click="lastPage">Left</button>
  <div>Page {{this.$store.state.page}}</div>
  <button id="buttonRight" @click="nextPage">Right</button>
  <ul>
    <li v-for="item in this.$store.state.log" :key="item" >
      {{ item }}
    </li>
  </ul>
</template>

<script>
import * as calculator from "@/services/axios";

export default {
  name: "CalcLog",
  data(){
    return{
      page : 0
    }
  },
  methods: {
    nextPage(){
      this.page++
      this.$store.dispatch("updatePage", this.page)
      document.getElementById("buttonLeft").disabled = false;
      this.updateLog()

      console.log(this.$store.state.log.length)
      console.log(this.$store.state.log)
    },
    lastPage(){
      this.page--
      this.$store.dispatch("updatePage", this.page)
      document.getElementById("buttonRight").disabled = false;
      if(this.$store.state.page === 0){
        document.getElementById("buttonLeft").disabled = true;
      }
      this.updateLog()
    },
    updateLog(){
      calculator.getLog(this.$store.state.page)
          .then(data => {
            this.$store.dispatch("addToLog", data)
            if(data.length < 10){
              document.getElementById("buttonRight").disabled = true;
            }
          })
    }
  },
  mounted() {
    this.$store.dispatch("updatePage", 0)
    this.page = 0
    document.getElementById("buttonLeft").disabled = true;
    calculator.getLog(0)
        .then(data => {
          if(data.length < 10){
            document.getElementById("buttonRight").disabled = true;
          }
          this.$store.dispatch("addToLog", data)
        })
  }
};
</script>

<style scoped>
@import "CalcLog.css";
</style>

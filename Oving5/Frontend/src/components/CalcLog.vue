<template>
  <h3>Log</h3>
  <button>Left</button>
  <button>Right</button>
  <ul>
    <li v-for="item in this.$store.state.log" :key="item" >
      {{ item }}
    </li>
  </ul>
</template>

<script>
export default {
  name: "CalcLog"
};
</script>

<style scoped>
@import "CalcLog.css";
</style>

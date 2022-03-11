<template>
  <button
    :disabled="disabled"
  >
    <slot/>
  </button>
</template>
<script>
export default {
  props: {
    disabled:{
      type: Boolean,
      default: false
    }
  }
}
</script>
<style scoped>
button {
  display: inline-flex;
  align-items: center;
  justify-content: space-between;
  height: 52px;
  padding: 0 40px;
  background: lawngreen;
  border: none;
  border-radius: 6px;
  text-align: center;
  font-weight: 600;
  white-space: nowrap;
  transition: all 0.2s linear;
  color: black;
}

button:disabled {
  border: 1px solid #999999;
  background-color: #cccccc;
  color: #666666;
  pointer-events: none;
}


button:hover{
  -webkit-transform: scale(1.02);
  transform: scale(1.02);
  box-shadow: 0 7px 17px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
}
button:active{
  -webkit-transform: scale(1);
  transform: scale(1);
  box-shadow: none;
}
</style>

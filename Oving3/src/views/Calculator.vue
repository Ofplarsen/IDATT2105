<template>
  <router-link to="/"
               >Hei</router-link>
  <h1>Calculator</h1>
  <div class="screen-split">
    <div class="calculator_container" >
      <input v-model="this.$store.state.calculation" readonly class="calculator_screen" type="text">

      <div class="grid-container">
        <div @click="clear" class="grid-item">C</div>
        <div @click="ans" class="grid-item">ANS</div>
        <div @click="del" class="grid-item">DEL</div>
        <div @click="addOperator($event)" class="grid-item">+</div>
        <div @click="add($event)" class="grid-item">1</div>
        <div @click="add($event)" class="grid-item">2</div>
        <div @click="add($event)" class="grid-item">3</div>
        <div @click="addOperator($event)" class="grid-item">-</div>
        <div @click="add($event)" class="grid-item">4</div>
        <div @click="add($event)" class="grid-item">5</div>
        <div @click="add($event)" class="grid-item">6</div>
        <div @click="addOperator($event)" class="grid-item">*</div>
        <div @click="add($event)" class="grid-item">7</div>
        <div @click="add($event)" class="grid-item">8</div>
        <div @click="add($event)" class="grid-item">9</div>
        <div @click="addOperator($event)" class="grid-item">/</div>
        <div class="grid-item"></div>
        <div @click="add($event)" class="grid-item">0</div>
        <div @click="add($event)" class="grid-item">.</div>
        <div @click="solve" class="grid-item">=</div>
      </div>
    </div>

      <div class="box-container">
        <CalcLog></CalcLog>
      </div>

  </div>

</template>

<script>
import CalcLog from "@/components/CalcLog";
export default {
  components: { CalcLog },
  methods: {
    solver(num1, operator, num2){
      var math_it_up = {
        '+': function (x, y) {return x + y },
        "-": function (x, y) {return x - y },
        "*": function (x, y) {return x * y },
        "/": function (x, y) {return x / y },
      };
      return math_it_up[operator](parseFloat(num1),parseFloat(num2))
    },
    solve(){
      this.$store.dispatch("addToCalculationArray", this.$store.state.tempCalc)


      let solution = parseFloat(this.$store.state.calc[0])

      for (let i = 0; i < this.$store.state.operator.length; i++) {
        solution = this.solver(solution, this.$store.state.operator[i], this.$store.state.calc[i+1])

      }

      this.addToLog(solution)
      this.setSolution(solution)
      this.resetValues()


    },
    add(v) {
      if (!this.$store.state.commaLast || v.target.innerText !== '.') {

        this.$store.dispatch("addToCalculation", v.target.innerText);
        this.$store.dispatch("addToTempCalc", v.target.innerText);

      }
    },

    addOperator(e){

      if(!this.$store.state.operatorLast){

        this.$store.dispatch("addToCalculationArray", this.$store.state.tempCalc);

        this.add(e)

        this.resetTempCalc()

        this.$store.dispatch("addOperator", e.target.innerText)
      }
    },
    del(){
      this.$store.dispatch("deleteFromCalc");
    },
    ans(){
      this.$store.dispatch('addAns')
    },
    resetValues(){
      this.$store.dispatch("resetValues");
    },
    resetTempCalc(){
      this.$store.dispatch("resetTempCalc");
    },
    clear(){
      this.$store.dispatch("clear");
      this.resetValues()

    },
    addToLog(solution){
      this.$store.dispatch("addToLog", solution)
    },
    setSolution(solution){
      this.$store.dispatch("setSolution", solution)
    }
  }

};
</script>

<style scoped>
@import "Calculator.css";
</style>

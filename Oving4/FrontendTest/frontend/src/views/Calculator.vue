<template>
  <router-link to="/"
               >Home</router-link>
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
import * as calculator from "@/services/index"
export default {
  components: { CalcLog },
  methods: {
    async solver(num1, operator, num2){
      return await calculator.postEquation({
        n1: num1,
        n2: num2,
        operator: operator
      })
    },
    async solve() {
      this.$store.dispatch("addToCalculationArray", this.$store.state.tempCalc)

      let solution = parseFloat(this.$store.state.calc[0])

      for (let i = 0; i < this.$store.state.operator.length; i++) {
        solution = this.solver(solution, this.$store.state.operator[i], this.$store.state.calc[i + 1])
      }

      await this.updateLog()
      await this.setSolution(await solution)
      await this.resetValues()


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
    async ans(){
      const ans = await calculator.getAnswer()
      this.$store.dispatch("addAns", ans);
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
    async updateLog() {
      this.$store.dispatch("updateLog", await calculator.getLog())
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

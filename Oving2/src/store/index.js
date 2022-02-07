import { createStore } from "vuex";

export default createStore({
  state: {
    calculation:'',
    log: [],
    operator: [],
    calc: [],
    tempCalc: '',
    solution: 0,
    operatorLast: false,
    commaLast: false,
    ans: 0
  },
  mutations: {
    CLEAR(state){
      state.calculation = ''
    },
    ADD_TO_CALC(state, number){
      state.calculation += number
      console.log(number)
      if(number.toString() === '.'){
        state.commaLast = true;
      }else{
        state.commaLast = false;
      }
      state.operatorLast = false
    },
    ADD_TO_CALC_ARRAY(state, number){
      state.calc.push(number)
    },
    SET_SOLUTION(state, number){

      console.log(state.log)

      state.solution = number
      state.ans = number
      state.calculation = number
      state.tempCalc = number
      console.log(state.tempCalc)
      console.log(state.calculation)
    },
    RESET_VALUES(state){
      state.calc = []
      state.operator = []
      state.tempCalc = ''
      console.log(state.calc)
    },
    RESET_TEMP_CALC(state){
      state.tempCalc = ''
    },
    ADD_OPERATOR(state, operator){
      state.operator.push(operator)
      state.operatorLast = true
    },
    ADD_TO_TEMP_CALC(state,number){
      state.tempCalc += number
    },
    REMOVE_FROM_CALC(state){
      if(state.operatorLast){
        state.operator.pop()
      }
      console.log(state.tempCalc)
      console.log(state.calculation !== undefined)

      state.tempCalc = state.tempCalc.toString().slice(0, -1)

      state.calculation = state.calculation.toString().slice(0, -1)

    },
    ADD_ANS(state){
      if(state.solution.length !== 0 && state.solution !== 0){
        state.calculation += state.ans.toString()
        state.tempCalc += state.ans.toString()
      }
    },
    ADD_TO_LOG(state, solution){
      if(state.calculation + " = " + solution !== state.log[state.log.length-1])
        state.log.push(state.calculation + " = " + solution)
    }
    }
  ,
  actions: {
    addToLog( {commit}, solution ){
      if(solution !== this.state.calculation[this.state.calculation.length-1]){
        return commit('ADD_TO_LOG', solution)
      }
    },
    clear( {commit} ){
      return commit('CLEAR')
    },
    addToCalculation( {commit}, number){
      return commit('ADD_TO_CALC', number)
    },
    addToCalculationArray( {commit}, number){
    return commit('ADD_TO_CALC_ARRAY', number)
    },
    addToTempCalc( {commit}, number){
      return commit('ADD_TO_TEMP_CALC', number)
    },
    setSolution( {commit}, number){
      return commit('SET_SOLUTION', number)
    },
    resetValues({commit}){
      return commit('RESET_VALUES')
    },
    resetTempCalc({commit}){
      return commit('RESET_TEMP_CALC')
    },
    addOperator( {commit}, operator){
      return commit('ADD_OPERATOR', operator)
    },
    deleteFromCalc( {commit}){
      return commit('REMOVE_FROM_CALC')
    },
    addAns ({commit}){
    return commit('ADD_ANS')
    },
  },
  modules: {},
});

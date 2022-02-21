import { createStore } from "vuex";

export default createStore({ state: {
    calculation:'',
    log: [],
    operator: [],
    calc: [],
    tempCalc: '',
    solution: 0,
    operatorLast: false,
    commaLast: false,
    ans: 0,

    contact_form: {
      name: [],
      email: [],
      messageStatus: []
    },

    login:{
      name: "",
      loginStatus: false
    }
  },
  mutations: {
    CLEAR(state) {
      state.calculation = ''
    },
    ADD_TO_CALC(state, number) {
      state.calculation += number

      if (number.toString() === '.') {
        state.commaLast = true;
      } else {
        state.commaLast = false;
      }
      state.operatorLast = false
    },
    ADD_TO_CALC_ARRAY(state, number) {
      state.calc.push(number)
    },
    SET_SOLUTION(state, number) {


      state.solution = number
      state.ans = number
      state.calculation = number
      state.tempCalc = number
    },
    RESET_VALUES(state) {
      state.calc = []
      state.operator = []
      state.tempCalc = ''

    },
    RESET_TEMP_CALC(state) {
      state.tempCalc = ''
    },
    ADD_OPERATOR(state, operator) {
      state.operator.push(operator)
      state.operatorLast = true
    },
    ADD_TO_TEMP_CALC(state, number) {
      state.tempCalc += number
    },
    REMOVE_FROM_CALC(state) {
      if (state.operatorLast) {
        state.operator.pop()
      }


      state.tempCalc = state.tempCalc.toString().slice(0, -1)

      state.calculation = state.calculation.toString().slice(0, -1)

    },
    ADD_ANS(state) {
      if (state.solution.length !== 0 && state.solution !== 0) {
        state.calculation += state.ans.toString()
        state.tempCalc += state.ans.toString()
      }
    },
    UPDATE_LOG(state, log) {
      state.log = log
    },
    ADD_NAME_TO_FORM(state, name) {

      state.contact_form.name.push(name)

    },
    ADD_EMAIL_TO_FORM(state, email) {

      state.contact_form.email.push(email)

    },
    UPDATE_MESSAGE_FORM(state, message) {

      state.contact_form.messageStatus.push(message)

    },
    LOGIN(state, name) {

      state.login.name = name
      state.login.loginStatus = true

    },
  },
  actions: {
    updateLog({commit}, solution ){
      return commit('UPDATE_LOG', solution)
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
    addNameToForm( {commit}, name){

      return commit('ADD_NAME_TO_FORM', name)
    },
    addEmailToForm( {commit}, email){
      return commit('ADD_EMAIL_TO_FORM', email)
    },
    updateMessageStatus({commit}, message) {
      return commit('UPDATE_MESSAGE_FORM', message)
    },
    login({commit}, name) {
      return commit('LOGIN', name)
    },
  },
  modules: {},
});

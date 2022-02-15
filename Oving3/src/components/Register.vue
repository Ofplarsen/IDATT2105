<template>
  <div id="registerTitle">
    <h1>Register</h1>
  </div>
  <BaseInput
    label="Full name"
    type="text"
    v-model="name"
  ></BaseInput>

  <BaseInput
    label="Address"
    type="text"
    v-model="address"
  ></BaseInput>

  <BaseInput
    label="Username"
    type="text"
    v-model="username"
  ></BaseInput>

  <BaseInput
    label="Password"
    type="password"
    v-model="password"
  ></BaseInput>

  <BaseInput
    label="Email"
    type="text"
    v-model="email"
  ></BaseInput>

  <button id="btnRegister" @click="register">Register</button>
</template>

<script>
import BaseInput from "@/components/form/BaseInput";
export default {
  name: "Register",
  components: { BaseInput },
  data() {
    return {
      username: '',
      password: '',
      name: '',
      address: '',
      email: '',
      registerStatus: false,
    }
  },
  methods:{
    register(){
      if(this.name.length !== 0 && this.password.length !== 0 && this.address.length !== 0 && this.email.length !== 0 && this.username.length !== 0){
        this.registerStatus = true
      }
    }
  }
};
</script>

<style scoped>

</style>

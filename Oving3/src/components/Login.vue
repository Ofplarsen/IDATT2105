<template>
  <div id="loginContainer">
    <div id="loginTitle">
      <label>Please login!</label>
    </div>
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

    <div>
      <button v-on:click="handleClickSignin_2" id="submitButton">Sign in</button>
      <label id="loginstatusLabel">{{loginStatus}}</label>
    </div>
  </div>
  <div v-if="!loginStatus" id="registered">Not registered yet? <router-link to="/register">Register</router-link></div>
</template>


<script>
import axios from 'axios';
import BaseInput from "@/components/form/BaseInput";


export default {
  name: 'Login',
  components: { BaseInput },
  methods: {
    async handleClickSignin (){
      //alert("You entered, username: " + this.username);
      const loginRequest = { username:this.username, password: this.password };
      const loginResponse = await axios.post("http://localhost:8085/demo/login", loginRequest);
      console.log(loginResponse);
      alert("Login: " + loginResponse.data.loginStatus);
    },
    handleClickSignin_2 () {
      /*
      const loginRequest = { username: this.username, password: this.password };
      //const loginResponse = axios.post("http://localhost:8085/demo/login", loginRequest);
      const loginResponse = "true"
      console.log(loginResponse)
      loginResponse.then((resolvedResult) => {
        this.loginStatus = resolvedResult.data.loginStatus;
        // alert("Login2: " + resolvedResult.data.loginStatus);
      });
       */
      console.log(this.password, this.username)
      this.loginStatus = this.password === "123" && this.username === "admin";
      if(this.loginStatus){
        this.$store.dispatch("login", this.username)
        this.$router.push({ name: 'Home' })
      }else{
        this.loginStatus = false
      }
    },

  },
  data() {
    return {
      username: '',
      password: '',
      loginStatus: true,
    }
  }

}
</script>

<style scoped>
#loginContainer {
  display: grid;
  justify-content: center;
  margin: 40px;
}

#loginTitle {
  font-size: x-large;
  font-weight: bold;
  margin-bottom: 20px;
}

#username, #password {
  display: flex;
  flex-direction: row;
  align-items: center;
  column-gap: 20px;
}

#usernameLabel, #passwordLabel {
  width: 100px;
}

</style>

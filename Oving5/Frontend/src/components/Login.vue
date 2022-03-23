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
      <button v-on:click="signIn" id="submitButton">Sign in</button>
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
    signIn () {
      this.getToken(this.username, this.password).then(ans =>
      {
        if(ans.data === "Access denied, wrong credentials...."){
          this.loginStatus = false;
          return false
        }

        console.log(ans.data)
        localStorage.setItem("token", ans.data)
        const loginRequest = {username: this.username, password: this.password};
        const config = {
          headers: { Authorization: `Bearer ${localStorage.getItem("token")}` }
        };
        localStorage.setItem("config", ans.data)
        const loginResponse = axios.post("http://localhost:8080/api/login", loginRequest, config);
        loginResponse.then((resolvedResult) => {
          if (resolvedResult.data) {
            this.$store.dispatch("login", this.username)
            this.$router.push({name: 'Home'})
          }
          this.loginStatus = resolvedResult.data;
        }).catch((error) => {
          if (error)
            if (error.response.status === 404) {
              alert("Wrong username or password")
              this.loginStatus = false;
            } else {
              console.log(error)
            }
        });
      })
    },
    getToken(username, password){
      const params = new URLSearchParams();
      params.append('username', username);
      params.append('password', password);
      return axios.post('http://localhost:8080/token', params);
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

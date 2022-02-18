<template>
  <div class="home">
    <h1 v-if="loginStatus">Welcome {{ username }}!</h1>
    <router-link to="/contact-us">Form here</router-link>
    <br/>
    <router-link to="/calc">Calculator here</router-link>
    <br/>
    <router-link to="/login">Login here</router-link>
  </div>
</template>

<script>
// @ is an alias to /src


export default {
  name: "Home",
  components: {
  },
  mounted() {
    this.username = this.$store.state.login.name
    this.loginStatus = this.$store.state.login.loginStatus

  },
  data(){
    return{
      username: "asd",
      loginStatus: true
    }
  }
};
</script>

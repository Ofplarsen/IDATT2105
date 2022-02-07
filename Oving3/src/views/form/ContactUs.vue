<template>
  <h1>Form</h1>
  <form @submit.prevent="onSubmit">

    <BaseInput
      label="Name"
      type="text"
      v-model="name"
      :error="nameError"
    />

    <BaseInput
      label="Email"
      type="email"
      :error="emailError"
      :modelValue="email"
      @change="handleChange"
    />

    <BaseTextarea
      label="Message"
      type="text"
      v-model="message"
      :error="messageError"
    />

    <BaseButton
      type="submit"
      class="-fill-gradient"
      @click="saveUserInfo(name, email)"
    >
      Submit
    </BaseButton>

  </form>
</template>

<script>
import BaseInput from "@/components/form/BaseInput";
import { useField, useForm } from "vee-validate";
import BaseButton from "@/components/form/BaseButton";
import BaseTextarea from "@/components/form/BaseTextarea";
export default {
  name: "ContactUs",
  components: { BaseTextarea, BaseButton, BaseInput },

  setup () {
    function onSubmit () {

    }

    const validations = {
      email: value => {
        if (!value) return "This field is required";

        const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        if (!regex.test(String(value).toLowerCase())) {
          return 'Please enter a valid email address'
        }

        return true
      },
      name: value => {
        const requiredMessage = 'Please enter a valid name'
        if (value === undefined || value === null) return requiredMessage
        if (!String(value).length) return requiredMessage

        return true
      },
      message: value => {
        const requiredMessage = 'This field is required'
        if (value === undefined || value === null) return requiredMessage
        if (!String(value).length) return requiredMessage

        return true
      }
    }

    useForm({
      validationSchema: validations
    })

    const { value: email, errorMessage: emailError, handleChange } = useField('email')
    const { value: name, errorMessage: nameError } = useField("name")
    const { value: message, errorMessage: messageError } = useField('message')

    return {
      onSubmit,
      email,
      emailError,
      name,
      nameError,
      message,
      messageError,
      handleChange
    }
  },
  methods: {
    storeName(name){

      this.$store.dispatch("addNameToForm", name)
      console.log(this.$store.state.contact_form.name)
    },
    storeEmail(email){
      this.$store.dispatch("addEmailToForm", email)
      console.log(this.$store.state.contact_form.email)
    },
    saveUserInfo(name, email){
      this.storeName(name)
      this.storeEmail(email)
    }
  }
};
</script>

<style scoped>

</style>

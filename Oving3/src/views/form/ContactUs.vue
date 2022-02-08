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
      v-model="message"
      :error="messageError"
    />

    <BaseButton
      type="submit"
      class="button"
      :disabled="disabled"

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
import { watch } from "vue";


export default {
  name: "ContactUs",
  components: { BaseTextarea, BaseButton, BaseInput },
  props:{
    submit:{
      disabled: false
    }

  },
  data(){
    return{

    }
  },
  setup () {
    function onSubmit() {
      this.$store.dispatch("addNameToForm", name.value)
      this.$store.dispatch("addEmailToForm", email.value)
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
        if (String(value).length === 0) return requiredMessage

        return true
      },
      message: value => {
        const requiredMessage = 'This field is required'
        if (value === undefined || value === null) return requiredMessage
        if (String(value).length === 0) return requiredMessage

        return true
      }
    }

    useForm({
      validationSchema: validations
    })

    const { value: email, errorMessage: emailError, handleChange } = useField('email')
    const { value: name, errorMessage: nameError} = useField("name")
    const { value: message, errorMessage: messageError } = useField("message");
    let  disabled = false

    watch([emailError, nameError, messageError], ([newE, newN, newM]) => {
      console.log(newE, newN, newM)
      if(newE === undefined && newN === undefined && newM === undefined){
        disabled = false
      }else{
        disabled = true
      }
    })

    return {
      onSubmit,
      email,
      emailError,
      name,
      nameError,
      message,
      messageError,
      handleChange,
      disabled
    }
  },
  methods: {

  }
};
</script>

<style scoped>
</style>

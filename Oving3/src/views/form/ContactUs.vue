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
      :disabled="!isComplete || !errors"
      @click = "onSubmit"
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
  inject: ['GStore'],
  setup() {

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
    const { value: name, errorMessage: nameError } = useField("name")
    const { value: message, errorMessage: messageError } = useField("message");


    return {
      email,
      emailError,
      name,
      nameError,
      message,
      messageError,
      handleChange
    }
  },
  computed: {
    isComplete() {
      //console.log(this.name !== undefined && this.email !== undefined && this.message !== undefined)
      return (this.name !== undefined && this.email !== undefined && this.message !== undefined);
    },
    errors() {
      //console.log(this.nameError === undefined && this.emailError === undefined && this.messageError === undefined)
      return this.nameError === undefined && this.emailError === undefined && this.messageError === undefined
    }
  },
  methods: {
    onSubmit() {
      this.storeContactInfo()
      this.storeMessage()
      this.GStore.flashMessage = "Sending Message..."

      this.showFlashMessage()
      console.log(this.$store.state.contact_form.email)
      console.log(this.$store.state.contact_form.name)
      console.log(this.$store.state.contact_form.messageStatus)
      setTimeout(() => {
        this.toHomePage()
      }, 4000)
    },
    storeContactInfo(){
      this.$store.dispatch("addNameToForm", this.name)
      this.$store.dispatch("addEmailToForm", this.email)
    },
    storeMessage(){
      this.$store.dispatch("updateMessageStatus", this.message)
    },
    toHomePage(){
      this.$router.push('/');
    },
    showFlashMessage(){
      setTimeout(() => {
        this.GStore.flashMessage = "Message sent!"
      }, 2000)

      setTimeout(() => {
        this.GStore.flashMessage = ""
      }, 3000)
    }
  }
};
</script>

<style scoped>
</style>

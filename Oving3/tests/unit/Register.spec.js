import { shallowMount} from "@vue/test-utils";
import store from '@/store'
import router from '@/router'
import Register from "@/components/Register";

describe("Register.vue", () => {
  it('check that LoginComponent renders', () => {

    const wrapper = shallowMount(Register,{
      global:{
        plugins: [store, router]
      }
    })


  })
});

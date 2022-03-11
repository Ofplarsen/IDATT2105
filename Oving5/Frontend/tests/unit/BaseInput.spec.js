import { shallowMount } from "@vue/test-utils";

import BaseInput from "@/components/form/BaseInput";
import store from "@/store";
import router from "@/router";


function mountInput(config = {}){
  config.mountOptions = config.mountOptions || {}
  config.plugins = config.plugins || {}
  return shallowMount(BaseInput, {
    global: {
      plugins: [store, router]
    },
    ...config.mountOptions
  })
}

describe("BaseInput.vue", () => {
  it('check that BaseInput changes value', () => {

  })
});

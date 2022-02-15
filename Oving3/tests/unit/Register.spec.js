import { shallowMount} from "@vue/test-utils";
import store from '@/store'
import router from '@/router'
import Register from "@/components/Register";
import { getMessage } from '@/services/axios'
import flushPromises from "flush-promises";

jest.mock("@/services/axios");

beforeEach(() => {
  jest.clearAllMocks()
})

function mountRegister(config = {}){
  config.mountOptions = config.mountOptions || {}
  config.plugins = config.plugins || {}
  return shallowMount(Register, {
    global: {
      plugins: [store, router]
    },
    ...config.mountOptions
  })
}

describe("Register.vue", () => {
  it('Checks and denies if not all values are filled', async () => {


    const mockMessage = 'Test DB'
    getMessage.mockResolvedValueOnce({ text: mockMessage })
    const wrapper = mountRegister()

    await flushPromises()
    await expect(wrapper.vm.registerStatus).toBe(false)
    const button = wrapper.find("#btnRegister")
    button.trigger('click')
    await expect(wrapper.vm.registerStatus).toBe(false)

  }),

  it('Approves if all values are filled', async () => {


    const mockMessage = 'Test DB'
    getMessage.mockResolvedValueOnce({ text: mockMessage })
    const wrapper = mountRegister()

    await flushPromises()
    await expect(wrapper.vm.registerStatus).toBe(false)
    await wrapper.setData({username: "OlavFPL"})
    await wrapper.setData({name: "Olav"})
    await wrapper.setData({password: "123"})
    await wrapper.setData({email: "Olav@gmail.com"})
    await wrapper.setData({address: "Gloshaugen"})
    const button = wrapper.find("#btnRegister")
    await button.trigger('click')
    await expect(wrapper.vm.registerStatus).toBe(true)

  }),

    it('Denies if some are missing', async () => {


      const mockMessage = 'Test DB'
      getMessage.mockResolvedValueOnce({ text: mockMessage })
      const wrapper = mountRegister()

      await flushPromises()
      await expect(wrapper.vm.registerStatus).toBe(false)
      await wrapper.setData({username: "OlavFPL"})
      await wrapper.setData({name: "Olav"})
      await wrapper.setData({address: "Gloshaugen"})
      const button = wrapper.find("#btnRegister")
      await button.trigger('click')
      await expect(wrapper.vm.registerStatus).toBe(false)

      await wrapper.setData({password: "123"})
      await wrapper.setData({email: "Olav@gmail.com"})
      await expect(wrapper.vm.registerStatus).toBe(false)
    })
});

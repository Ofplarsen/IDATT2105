import { shallowMount} from "@vue/test-utils";
import LoginComponent from "@/components/Login.vue";
import store from '@/store'
import router from '@/router'
import Home from "@/views/Home";

function mountLogin(config = {}){
  config.mountOptions = config.mountOptions || {}
  config.plugins = config.plugins || {}
  return shallowMount(LoginComponent, {
    global: {
      plugins: [store, router]
    },
    ...config.mountOptions
  })
}

describe("LoginComponent.vue", () => {
  it('check that LoginComponent renders', () => {
    const loginTitle = 'Please login!'
    const wrapper = mountLogin()
    expect(wrapper.text()).toMatch(loginTitle)
  }),

    it('Every state is updated when true login', async () => {
      const wrapper = mountLogin()
      await expect(store.state.login.name).toContain("")
      await expect(store.state.login.loginStatus).toBe(false)
      await wrapper.setData({username: 'admin'});
      await wrapper.setData({password: '123'});
      const button =  wrapper.find('#submitButton');
      await button.trigger('click')
      await expect(store.state.login.name).toContain("admin")
      await expect(store.state.login.loginStatus).toBe(true)
    })

  it('Wrong username and password == failed', async () => {
    const wrapper = mountLogin()

    // get loginstatusLabel element
    const loginStatus = wrapper.vm.loginStatus
    // change loginStatus data and check that loginstatusLabel element is updated accordingly
    await wrapper.setData({username: 'wrong username'});
    await wrapper.setData({password: 'wrong password'});
    const button =  wrapper.find('#submitButton');
    await button.trigger('click')
    await expect(wrapper.vm.loginStatus).toBe(false);
  }),

  it('Right username and password == True', async () => {
    const wrapper = mountLogin()
    await wrapper.setData({username: 'admin'});
    await wrapper.setData({password: '123'});
    const button =  wrapper.find('#submitButton');
    await button.trigger('click')
    await expect(wrapper.vm.loginStatus).toBe(true);
  }),

    it('Register pops up if failed to log in', async () => {
      const wrapper = mountLogin()
      await wrapper.setData({username: 'wrong'});
      await wrapper.setData({password: '123'});
      const button =  wrapper.find('#submitButton');
      await button.trigger('click')
      const signInText = wrapper.find('#registered');
      await expect(signInText.element.textContent).toContain('Not registered yet?');
    }),

    it('Gets redirected to home page', async () => {
      const wrapper = mountLogin()

      await wrapper.setData({username: 'admin'});
      await wrapper.setData({password: '123'});
      const button =  wrapper.find('#submitButton');
      await button.trigger('click')
      const wrapperHome = shallowMount(Home, {
        global: {
          plugins: [store, router]
        }
      })
      await expect(wrapperHome.html()).toContain("Welcome")
    })
});

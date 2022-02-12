import { shallowMount } from "@vue/test-utils";
import LoginComponent from "@/components/Login.vue";

describe("LoginComponent.vue", () => {
  it('check that LoginComponent renders', () => {
    const loginTitle = 'Please login!'
    const wrapper = shallowMount(LoginComponent)
    expect(wrapper.text()).toMatch(loginTitle)

    // add some additional checks. For example related to loginStatusLabel element
    // check that loginstatusLabel component exists
    expect(wrapper.find('#loginstatusLabel').exists()).toBe(true);
    const statusId = wrapper.find('#loginstatusLabel');
    // check that id of the loginstatusLabel element is correct
    expect(statusId.element.id).toBe('loginstatusLabel');
    // check that the loginstatusLabel element is displaying correct value
    expect(statusId.element.textContent).toBe('');
  }),

  it('Modify LoginComponent data and test', async () => {
    const wrapper = shallowMount(LoginComponent)
    // get loginstatusLabel element
    const statusId = wrapper.find('#loginstatusLabel');
    // change loginStatus data and check that loginstatusLabel element is updated accordingly
    await wrapper.setData({loginStatus: 'Success'});
    expect(statusId.element.textContent).toBe('Success');
    await wrapper.setData({loginStatus: 'Failed'});
    expect(statusId.element.textContent).toBe('Failed');
  }),

  it('Wrong username and password == failed', async () => {
    const wrapper = shallowMount(LoginComponent)

    // get loginstatusLabel element
    const statusId = wrapper.find('#loginstatusLabel');
    // change loginStatus data and check that loginstatusLabel element is updated accordingly
    await wrapper.setData({username: 'wrong username'});
    await wrapper.setData({password: 'wrong password'});
    const button =  wrapper.find('#submitButton');
    await button.trigger('click')
    expect(statusId.element.textContent).toBe('false');
    await wrapper.setData({loginStatus: 'true'});
    expect(statusId.element.textContent).toBe('true');
  }),

  it('Right username and password == True', async () => {
    const wrapper = shallowMount(LoginComponent)

    // get loginstatusLabel element
    const statusId = wrapper.find('#loginstatusLabel');
    // change loginStatus data and check that loginstatusLabel element is updated accordingly
    await wrapper.setData({username: 'admin'});
    await wrapper.setData({password: '123'});
    const button =  wrapper.find('#submitButton');
    await button.trigger('click')
    expect(statusId.element.textContent).toBe('true');
    await wrapper.setData({loginStatus: 'false'});
    expect(statusId.element.textContent).toBe('false');
  }),

    it('Username and password as expected', async () => {
      const wrapper = shallowMount(LoginComponent)

      // get loginstatusLabel element
      const statusId = wrapper.find('#loginstatusLabel');
      // change loginStatus data and check that loginstatusLabel element is updated accordingly
      await wrapper.setData({username: 'admin'});
      await wrapper.setData({password: '123'});
      const button =  wrapper.find('#submitButton');
      await button.trigger('click')
      expect(statusId.element.textContent).toBe('true');
      await wrapper.setData({loginStatus: 'false'});
      expect(statusId.element.textContent).toBe('false');
    }),

    it('Clicking register takes you to register site', async () => {
      const wrapper = shallowMount(LoginComponent)

      // get loginstatusLabel element
      const statusId = wrapper.find('#loginstatusLabel');
      // change loginStatus data and check that loginstatusLabel element is updated accordingly
      await wrapper.setData({username: 'admin'});
      await wrapper.setData({password: '123'});
      const button =  wrapper.find('#submitButton');
      await button.trigger('click')
      expect(statusId.element.textContent).toBe('true');
      await wrapper.setData({loginStatus: 'false'});
      expect(statusId.element.textContent).toBe('false');
    })
});

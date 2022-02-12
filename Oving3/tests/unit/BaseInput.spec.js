import { shallowMount } from "@vue/test-utils";
import LoginComponent from "@/components/Login";
import BaseInput from "@/components/form/BaseInput";


describe("BaseInput.vue", () => {
  it('check that BaseInput changes value', () => {
    const wrapper = shallowMount(BaseInput)
    const input = wrapper.find("#uuid")
    input.
    expect(wrapper.text()).toMatch(loginTitle)

    // add some additional checks. For example related to loginStatusLabel element
    // check that loginstatusLabel component exists
    expect(wrapper.find('#loginstatusLabel').exists()).toBe(true);
    const statusId = wrapper.find('#loginstatusLabel');
    // check that id of the loginstatusLabel element is correct
    expect(statusId.element.id).toBe('loginstatusLabel');
    // check that the loginstatusLabel element is displaying correct value
    expect(statusId.element.textContent).toBe('');
  })
});

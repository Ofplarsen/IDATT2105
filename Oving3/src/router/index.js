import { createRouter, createWebHistory } from "vue-router";

import Calculator from "@/views/Calculator";
import ContactUs from "@/views/ContactUs";

const routes = [
  {
    path: "/",
    name: "Calculator",
    component: Calculator,
  },
  {
    path: "/contact-us",
    name: "Contact us",
    component: ContactUs
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

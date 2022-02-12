import { createRouter, createWebHistory } from "vue-router";


import ContactUs from "@/views/form/ContactUs";
import Calculator from "@/views/Calculator";
import Home from "@/views/Home";
import Login from "@/components/Login";
import Register from "@/components/Register";

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/contact-us",
    name: "ContactUs",
    component: ContactUs
  },
  {
    path: "/calc",
    name: "Calculator",
    component: Calculator
  },
  {
    path: "/login",
    name: "Login",
    component: Login
  },
  {
    path: "/register",
    name: "Register",
    component: Register
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;

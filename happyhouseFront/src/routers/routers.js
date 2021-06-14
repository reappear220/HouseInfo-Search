import Vue from "vue";
import VueRouter from "vue-router";
import Main from "../components/views/Main.vue";
import Login from "../components/views/Login.vue";
import Register from "../components/views/Register.vue";
import HouseBoard from "../components/views/HouseBoard.vue";
import FreeBoard from "../components/views/FreeBoard.vue";
//import DetailModal from "../components/modals/DetailModal.vue";
//import Pagination from "../views/Pagination.vue";
import UserInfo from "../components/views/UserInfo.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main,
  },
  // {
  //   path: "/main",
  //   name: "Main",
  //   component: Main,
  // },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/register",
    name: "Register",
    component: Register,
  },
  {
    path: "/houseboard",
    name: "HouseBoard",
    component: HouseBoard,
  },
  {
    path: "/userinfo",
    name: "UserInfo",
    component: UserInfo,
  },
  {
    path: "/freeboard",
    name: "FreeBoard",
    component: FreeBoard,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

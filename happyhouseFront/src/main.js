import Vue from "vue";
import App from "./App.vue";
import router from "./routers/routers.js";
import store from "./store/store.js";
import AOS from "aos";
import "aos/dist/aos.css";

import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

AOS.init();
Vue.config.productionTip = false;

new Vue({
  created() {
    AOS.init();
  },
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");

<template>
  <div>
    <section id="hero">
      <div class="hero-container" data-aos="fade-up">
        <h1>Login</h1>

        <h2>로그인하고 부동산 거래를 이용해보세요!</h2>

        <div class="col-lg-6">
          <div class="row">
            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="id_login"
                placeholder="ID"
                required
                v-model="$store.state.login.userId"
              />
            </div>
            <div class="form-group mt-3">
              <input
                type="password"
                class="form-control"
                name="subject"
                id="password_login"
                placeholder="Password"
                required
                v-model="$store.state.login.userPassword"
              />
            </div>
            <br />
            <br />
            <div class="text-center">
              <br />
              <button @click="login" class=" cta-btn" type="submit">로그인하기</button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Hero -->
  </div>
</template>

<script>
import Vue from "vue";
import VueAlertify from "vue-alertify";

Vue.use(VueAlertify);

import http from "@/common/axios.js";

export default {
  name: "Login",
  methods: {
    login() {
      http
        .post("/login", {
          userId: this.$store.state.login.userId,
          userPwd: this.$store.state.login.userPassword,
        })
        .then(({ data }) => {
          console.log("LoginVue: data : ");
          console.log(data);

          this.$store.commit("SET_LOGIN", {
            isLogin: true,
            userName: data.userName,
            userEmail: data.email,
            userAddressGu: data.addressGu,
            userAddressDong: data.addressDong,
          });

          this.$alertify.success("로그인 성공");

          this.$router.push("/");
        })
        .catch((error) => {
          console.log("LoginVue: error : ");
          console.log(error);
          if (error.response.status == "404") {
            this.$alertify.error("이메일 또는 비밀번호를 확인하세요.");
          } else {
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          }
        });
    },
  },
};
</script>

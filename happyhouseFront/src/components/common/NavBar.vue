<template>
  <div>
    <!-- ======= Header ======= -->
    <header id="header" class="fixed-top header-transparent">
      <div class="container d-flex align-items-center justify-content-between">
        <div class="logo">
          <h1 class="text-light" @click="refresh()">
            <router-link to="/"><span>HappyHouse</span></router-link>
          </h1>
          <!-- Uncomment below if you prefer to use an image logo -->
          <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
        </div>

        <nav id="navbar" class="navbar">
          <ul>
            <li>
              <a href="#" @click="refresh()"><span>Home</span></a>
            </li>
            <li>
              <router-link v-show="!isLoginGetters" to="/register">Join Us</router-link>
            </li>
            <!-- <li><a class="nav-link scrollto" href="#services">Services</a></li> -->
            <li>
              <a href="#news"><span>News</span></a>
            </li>
            <li>
              <a href="#team"><span>Teams</span></a>
            </li>
            <li class="dropdown">
              <a href="#services"><span>Services</span> <i class="bi bi-chevron-down"></i></a>
              <ul>
                <li>
                  <router-link to="/houseboard">부동산 정보</router-link>
                </li>
                <li @click="checkLogin()"><router-link to="/freeboard" >자유 게시판</router-link></li>
                <li @click="checkLogin()"><a v-show="$store.state.login.isLogin" href="#">마이 부동산</a></li>
                <li @click="checkLogin()">
                  <router-link v-show="$store.state.login.isLogin" to="/userinfo">마이 페이지</router-link>
                </li>
              </ul>
            </li>
            <li>
              <router-link
                v-show="$store.state.login.isLogin === false"
                to="/login"
                class="nav-link scrollto"
                >Log In</router-link
              >
              <a
                v-show="$store.state.login.isLogin"
                to="/"
                @click="logout()"
                class="nav-link scrollto"
                >Log Out</a
              >
            </li>
            <!-- <li><a class="nav-link scrollto" href="/logout">Log Out</a></li> -->
          </ul>
          <i class="bi bi-list mobile-nav-toggle"></i>
        </nav>
        <!-- .navbar -->
      </div>
    </header>
    <!-- End Header -->
  </div>
</template>

<script>
import http from "@/common/axios.js";
import Vue from "vue";
import VueAlertify from "vue-alertify";
Vue.use(VueAlertify);

export default {
  name: "NavBar",
  computed: {
    // getters 이용 테스트
    isLoginGetters() {
      return this.$store.getters.isLogin; // no isLogin()
    },
  },
  methods: {
  refresh(){
    setTimeout(function(){
        location.href="/";
      });
  },
    checkLogin(){
      
      const loadedToDos = localStorage.getItem('vuex');
      if ( loadedToDos != null){
        const loginObject = JSON.parse(loadedToDos).login;
        console.log("로그인 정보", loginObject.isLogin);

        if(!loginObject.isLogin){
          alert("로그인하세요!");
          location.href="/login";
        }
      }else{
        alert("로그인하세요!");
        location.href="/login";
      }
    },

    logout() {
      http.get("/logout").then(
        this.$store.commit("SET_LOGIN", {
          isLogin: false,
          userName: "",
          userEmail: "",
          userAddressGu: "",
          userAddressDong: "",
        }),
        console.log(sessionStorage),
        console.log(localStorage),
        sessionStorage.clear(),
        localStorage.clear(),
        this.$router.push("/"),
        console.log("logout success!"),
        console.log(sessionStorage),
        console.log(localStorage)
      );
      setTimeout(function() {
        location.reload();
      });
    },
  },
};
</script>

<style></style>

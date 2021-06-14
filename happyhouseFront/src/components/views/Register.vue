<template>
  <div>
    <section id="hero">
      <div class="hero-container" data-aos="fade-up">
        <h1>JOIN</h1>

        <h2>회원가입하고 부동산 거래를 이용해보세요!</h2>
            <div class="mb-3">
              <div class="form-check form-check-inline" v-for="(code, index) in codeList" :key="index">
                <input class="form-check-input" :value="code.code" type="radio" v-model="userClsf">
                <label class="form-check-label">{{ code.codeName}}</label>
              </div>
            </div>
            <br>
            <br>
        <div class="col-lg-6">
          <div class="row">
            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="id_join"
                placeholder="ID"
                :class="{
                  'is-valid': isUserIdFocusAndValid,
                  'is-invalid': isUserIdFocusAndInvalid,
                }"
                v-model="userId"
                @input="validateUserId"
                @focus="isUserId = true"
                required
              />
              <div class="valid-feedback">Valid.</div>
              <div class="invalid-feedback">올바른 아이디를 입력해 주세요.</div>
            </div>
            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="password_join"
                placeholder="Password"
                required
                :class="{
                  'is-valid': isUserPasswordFocusAndValid,
                  'is-invalid': isUserPasswordFocusAndInvalid,
                }"
                v-model="userPwd"
                @input="validatePassword"
                @focus="isUserPasswordFocus = true"
              />
              <div class="valid-feedback">Valid.</div>
              <div class="invalid-feedback">
                1개 이상의 특수문자, 대소문자 및 숫자를 포함하고 8자리 이상이여야 합니다.
              </div>
            </div>
            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="password_join2"
                placeholder="Confirm Password"
                required
                :class="{
                  'is-valid': isUserPassword2FocusAndValid,
                  'is-invalid': isUserPassword2FocusAndInvalid,
                }"
                v-model="userPwd2"
                @input="validatePassword2"
                @focus="isUserPassword2Focus = true"
              />
              <div class="valid-feedback">Valid.</div>
              <div class="invalid-feedback">비밀번호가 일치하지 않습니다.</div>
            </div>

            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="name_join"
                placeholder="Name"
                required
                :class="{
                  'is-valid': isUserNameFocusAndValid,
                  'is-invalid': isUserNameFocusAndInvalid,
                }"
                v-model="userName"
                @input="validateUserName"
                @focus="isUserNameFocus = true"
              />
            </div>
            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="email_join"
                placeholder="Email"
                required
                :class="{
                  'is-valid': isUserEmailFocusAndValid,
                  'is-invalid': isUserEmailFocusAndInValid,
                }"
                v-model="email"
                @input="validateEmail"
                @focus="isUserEmailFocus = true"
              />
            </div>

            <div class="form-group mt-3, gu">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="gu_join"
                placeholder="구"
                required
                v-model="address_gu"
              />
            </div>
            <div class="form-group mt-3, dong">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="dong_join"
                placeholder="동"
                required
                v-model="address_dong"
              />
            </div>
            
            <br>
            <br>
            <hr>
            <br>
            
            <br />
            <div class="text-center">
              <br />
              <button @click="register" class=" cta-btn" type="submit">가입하기</button>
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
  name: "Register",
  data() {
    return {
      // v-model
      userId: "",
      userName: "",
      email: "",
      userPwd: "",
      userPwd2: "",
      address_gu: "",
      address_dong: "",

      // focus
      isUserIdFocus: false,
      isUserNameFocus: false,
      isUserEmailFocus: false,
      isUserPasswordFocus: false,
      isUserPassword2Focus: false,

      // validation
      isUserIdValid: false,
      isUserNameValid: false,
      isUserEmailValid: false,
      isUserPasswordValid: false,
      isUserPassword2Valid: false,

      // 회원 구분
      groupCode: "001",
      codeList: [],
      userClsf: "001", // 일반회원 default
    };
  },
  computed: {
    isUserIdFocusAndValid() {
      return this.isUserIdFocus && this.isUserIdValid;
    },
    isUserIdFocusAndInvalid() {
      return this.isUserIdFocus && !this.isUserIdValid;
    },
    isUserNameFocusAndValid() {
      return this.isUserNameFocus && this.isUserNameValid;
    },
    isUserNameFocusAndInvalid() {
      return this.isUserNameFocus && !this.isUserNameValid;
    },
    isUserEmailFocusAndValid() {
      return this.isUserEmailFocus && this.isUserEmailValid;
    },
    isUserEmailFocusAndInValid() {
      return this.isUserEmailFocus && !this.isUserEmailValid;
    },
    isUserPasswordFocusAndValid() {
      return this.isUserPasswordFocus && this.isUserPasswordValid;
    },
    isUserPasswordFocusAndInvalid() {
      return this.isUserPasswordFocus && !this.isUserPasswordValid;
    },
    isUserPassword2FocusAndValid() {
      return this.isUserPassword2Focus && this.isUserPassword2Valid;
    },
    isUserPassword2FocusAndInvalid() {
      return this.isUserPassword2Focus && !this.isUserPassword2Valid;
    },
  },
  methods: {
    validateUserId() {
      this.isUserIdValid = this.userId.length > 0 && this.userId.length < 12 ? true : false;
      console.log(this.isUserIdValid);
    },
    validateUserName() {
      this.isUserNameValid = this.userName.length > 0 ? true : false;
      console.log(this.isUserNameValid);
    },
    validateEmail() {
      // ^ 시작일치, $ 끝 일치
      // {2, 3} 2개 ~ 3개
      // * 0회 이상, + 1회 이상
      // [-_.] - 또는 _ 또는 .
      // ? 없거나 1회
      let regexp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      this.isUserEmailValid = regexp.test(this.email) ? true : false;
      console.log(this.isUserEmailValid);
    },
    validatePassword() {
      let patternEngAtListOne = new RegExp(/[a-zA-Z]+/); // + for at least one
      let patternSpeAtListOne = new RegExp(/[~!@#$%^&*()_+|<>?:{}]+/); // + for at least one
      let patternNumAtListOne = new RegExp(/[0-9]+/); // + for at least one

      this.isUserPasswordValid =
        patternEngAtListOne.test(this.userPwd) &&
        patternSpeAtListOne.test(this.userPwd) &&
        patternNumAtListOne.test(this.userPwd) &&
        this.userPwd.length >= 8
          ? true
          : false;
    },
    validatePassword2() {
      this.isUserPassword2Valid = this.userPwd == this.userPwd2 ? true : false;
    },
    register() {
      if (
        !this.isUserIdValid ||
        !this.isUserEmailValid ||
        !this.isUserPasswordValid ||
        !this.isUserPassword2Valid
      )
        return;
      http
        .post("/register", {
          userId: this.userId,
          userName: this.userName,
          email: this.email,
          userPwd: this.userPwd,
          userClsf: this.userClsf,
          address_gu: this.address_gu,
          address_dong : this.address_dong
        })
        .then(({ data }) => {
          console.log("RegisterVue: data : ");
          console.log(data);

          let $this = this;
          this.$alertify.alert("회원가입을 축하합니다. 로그인 페이지로 이동합니다", function() {
            $this.$router.push("/login");
          });
        })
        .catch((error) => {
          console.log("RegisterVue: error : ");
          console.log(error);
          if (error.response.status == "404") {
            this.$alertify.error("Opps!! 서버에 문제가 발생했습니다.");
          }
        });
    },
  },
  created() {
    http
      .get("/codes", {
        // get query string
        params: {
          groupCode: this.groupCode,
        },
      })
      .then(({ data }) => {
        console.log("RegisterVue: data : ");
        console.log(data);
        if (data.result == "login") {
          this.$router.push("/login");
        } else {
          this.codeList = data;
        }
      });
  },
};
</script>

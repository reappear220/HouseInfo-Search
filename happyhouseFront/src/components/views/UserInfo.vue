<template>
  <div>
    <section id="hero">
      <div class="hero-container" data-aos="fade-up">
        <h1>My Info</h1>

        <h2>내 정보를 관리하세요!</h2>

        <div class="col-lg-6">
          <div class="row">
            <div class="form-group mt-3">
              <output
                type="text"
                class="form-control"
                id="id_user"
                required
                v-text="$store.state.login.userId"
              >
              </output>
            </div>
            <div class="form-group mt-3">
              <input
                type="password"
                class="form-control"
                name="subject"
                id="password_user"
                placeholder="Password"
                required
                v-model="$store.state.login.userPassword"
              />
            </div>
            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="name_user"
                placeholder="Name"
                required
                v-model="$store.state.login.userName"
              />
            </div>
            <div class="form-group mt-3">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="email_user"
                placeholder="Email"
                required
                v-model="$store.state.login.userEmail"
              />
            </div>
            <br />
            <div class="form-group mt-3, gu">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="gu_user"
                placeholder="구"
                required
                v-model="$store.state.login.userAddressGu"
              />
            </div>
            <div class="form-group mt-3, dong">
              <input
                type="text"
                class="form-control"
                name="subject"
                id="dong_user"
                placeholder="동"
                required
                v-model="$store.state.login.userAddressDong"
              />
            </div>
            <br />
            <br />
            <div class="text-center">
              <br />
              <button @click="userUpdate" class=" cta-btn" type="submit" style="margin-right:35px">
                수정하기
              </button>
              <button @click="userDelete" class=" cta-btn" type="submit">탈퇴하기</button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- End Hero -->
  </div>
</template>

<script>
import http from "@/common/axios.js";

export default {
  name: "UserInfo",
  methods: {
    userUpdate() {
      http
        .put("/members/" + this.$store.state.login.userId, {
          userId: this.$store.state.login.userId,
          userPwd: this.$store.state.login.userPassword,
          userName: this.$store.state.login.userName,
          email: this.$store.state.login.userEmail,
          addressGu: this.$store.state.login.userAddressGu,
          addressDong: this.$store.state.login.userAddressDong,
        })
        .then(({ data }) => {
          console.log("UpdateVue: data : ");
          console.log(data);

          if (data.result == "login") {
            this.$router.push("/login");
          }

          this.$alertify.success("회원정보가 수정되었습니다.");

          this.$store.commit("SET_LOGIN", {
            isLogin: true,
            userPassword: this.$store.state.login.userPassword,
            userName: this.$store.state.login.userName,
            userEmail: this.$store.state.login.userEmail,
            userAddressGu: this.$store.state.login.userAddressGu,
            userAddressDong: this.$store.state.login.userAddressDong,
          });

          this.$router.push("/");
        })
        .catch((error) => {
          console.log("UpdateVue: error");
          console.log(error);
        });
    },
    userDelete() {
      http
        .delete("/members/" + this.$store.state.login.userId, {
          userId: this.$store.state.login.userId,
        })
        .then(({ data }) => {
          console.log("DeleteVue: data : ");
          console.log(data);

          if (data.result == "login") {
            this.$router.push("/login");
          }

          this.$alertify.success("회원정보가 삭제되었습니다. (탈퇴 완료)");

          this.$store.commit("SET_LOGIN", {
            isLogin: false,
            userPassword: "",
            userName: "",
            userEmail: "",
            userAddressGu: "",
            userAddressDong: "",
          });

          this.$router.push("/");
        })
        .catch((error) => {
          console.log("DeleteVue: error");
          console.log(error);
        });
    },
  },
};
</script>

<template>
  <div id="app">
    <nav-bar />
    <router-view></router-view>
    <Footer />
  </div>
</template>

<script>
import NavBar from "@/components/common/NavBar";
import Footer from "@/components/common/Footer";

export default {
  name: "Main",
  components: {
    NavBar,
    Footer,
  },
};
</script>

<style>
/* Google Fonts */
@import "https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i";

/* Vendor CSS Files */
@import "assets/vendor/aos/aos.css";
@import "assets/vendor/bootstrap/css/bootstrap.min.css";
@import "assets/vendor/bootstrap-icons/bootstrap-icons.css";
@import "assets/vendor/boxicons/css/boxicons.min.css";
@import "assets/vendor/glightbox/css/glightbox.min.css";
@import "assets/vendor/swiper/swiper-bundle.min.css";

/* Template Main CSS File */
@import "assets/css/style.css";
</style>

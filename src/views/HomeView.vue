<template>
  <div class="home">
    <the-header-nav></the-header-nav>
    <h2>홈뷰</h2>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeaderNav from "../components/common/TheHeaderNav.vue";

export default {
  name: "HomeView",
  components: {
    TheHeaderNav,
  },
  created() {
    // 만약 세션에 로그인이 등록되어 있지 않으면
    // this.$router.push({ name: "LoginView" });
  },
};
</script>

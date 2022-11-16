<template>
  <div class="home">
    <the-header-nav></the-header-nav>
    <h2>SSAFYGRAM</h2>
    <router-view></router-view>
  </div>
</template>

<script>
import TheHeaderNav from "../components/common/TheHeaderNav.vue";
import axios from "@/util/http-common.js";

export default {
  name: "HomeView",
  components: {
    TheHeaderNav,
  },
  created() {
    if (!sessionStorage.getItem("access-token")) {
      this.$router.push({ name: "LoginView" });
    } else {
      axios.get(`userApi/detail`, this.u_id).then((res) => {
        this.$store.commit("SET_MY_USER", res.data);
      });
    }
    // 등록되어 있으면 axios로 지금 로그인 한 계정 갖고와서 myUser 업데이트 시켜주기
  },
};
</script>

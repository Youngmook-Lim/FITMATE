<template>
  <div class="header">
    <router-link :to="{ name: 'HomeView' }">SSAFYGRAM</router-link>
    <div>
      <span>{{ myUser.name }}님 안녕하세요!</span>
      <router-link
        :to="{
          name: 'UserViewMain',
          params: {
            id: myUser.u_id,
          },
        }"
        >내 정보</router-link
      >
      <button @click="logout">로그아웃</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "TheHeaderNav",
  methods: {
    logout() {
      this.$store.commit("CLEAR_ALL");

      // JWT 토큰 지우기
      sessionStorage.removeItem("access-token");
      this.$router.push({ name: "LoginView" });
    },
  },
  computed: {
    ...mapState(["myUser"]),
  },
};
</script>

<style scoped>
.header {
  display: flex;
  justify-content: space-between;
  padding: 20px;
  background-color: grey;
}
</style>

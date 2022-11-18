<template>
  <header>
    <router-link :to="{ name: 'HomeView' }">FITMATE</router-link>
    <div class="menu-right">
      <span>{{ myUser.name }}님 안녕하세요!</span>
      <router-link :to="{ name: 'MessageReceived' }">💌</router-link>
      <router-link
        :to="{
          name: 'UserViewMain',
          params: {
            id: myUser.u_id ? myUser.u_id : 0,
          },
        }"
        >내 정보</router-link
      >
      <button @click="logout">로그아웃</button>
    </div>
  </header>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "TheHeader",
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
header {
  display: flex;
  justify-content: space-between;
  padding: 20px;
  background-color: grey;
}

.menu-right {
  display: flex;
  gap: 16px;
}
</style>

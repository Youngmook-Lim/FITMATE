<template>
  <div>
    <the-header></the-header>
    <the-message-nav></the-message-nav>
    <router-view :isMyUserLoaded="isMyUserLoaded"></router-view>
  </div>
</template>

<script>
import TheHeader from "../components/common/TheHeader.vue";
import TheMessageNav from "../components/message/TheMessageNav.vue";
import axios from "../util/http-common.js";
export default {
  name: "MessageView",
  components: {
    TheHeader,
    TheMessageNav,
  },
  data() {
    return {
      isMyUserLoaded: false,
    };
  },
  methods: {
    parseJWT(token) {
      var base64Url = token.split(".")[1];
      var base64 = base64Url.replace(/-/g, "+").replace(/_/g, "/");
      var jsonPayload = decodeURIComponent(
        window
          .atob(base64)
          .split("")
          .map((c) => "%" + ("00" + c.charCodeAt(0).toString(16)).slice(-2))
          .join("")
      );
      return JSON.parse(jsonPayload);
    },
  },
  created() {
    const token = sessionStorage.getItem("access-token");
    if (token) {
      const payload = this.parseJWT(token);
      // console.log(payload);
      axios
        .get(`userApi/detail`, { params: { id: payload.id } })
        .then((res) => this.$store.commit("SET_MY_USER", res.data))
        .then(() => (this.isMyUserLoaded = true));
    }
    // else {
    // this.$router.push({ name: "LoginView" });
    // }
  },
};
</script>

<style></style>

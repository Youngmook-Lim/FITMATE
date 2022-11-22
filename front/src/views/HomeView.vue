<template>
  <div class="home">
    <the-header></the-header>
    <div class="navi">
      <the-nav></the-nav>
    </div>
    <router-view class="innercontainer"></router-view>
    <the-footer-vue></the-footer-vue>
  </div>
</template>

<script>
import TheHeader from "../components/common/TheHeader.vue";
import TheNav from "../components/common/TheNav.vue";
import axios from "@/util/http-common.js";
import TheFooterVue from "@/components/common/TheFooter.vue";

export default {
  name: "HomeView",
  components: {
    TheHeader,
    TheNav,
    TheFooterVue,
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
    // sessionStorage.setItem(
    //   "access-token",
    //   "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJteU1lc3NhZ2UiOiJXVEZGRkZGIEJST09PT08ifQ.Tf3M9Y8v6vzhDENFgucVt4c9KN1GQn0avqBEpMR_FRQ"
    // );

    const token = sessionStorage.getItem("access-token");
    if (token) {
      const payload = this.parseJWT(token);
      // console.log(payload);
      axios
        .get(`userApi/detail`, { params: { id: payload.id } })
        .then((res) => this.$store.commit("SET_MY_USER", res.data))
        .then(() => (this.$store.state.loadedVideoSearch = true));
      this.$emit("start-timer");
    }
    // else {
    // this.$router.push({ name: "LoginView" });
    // }
  },
};
</script>

<style scoped>
.innercontainer {
  margin: auto;
  align-items: center;
  justify-content: center;
  transition: background-color 0.2s ease, border-top-left-radius 0.2s ease,
    border-top-right-radius 0.2s ease, padding 0.2s ease;
  width: 64em;
  padding: 4em 1em 1em 1em;
  text-align: center;
  display: block;
  border: 1px solid white;
  border-bottom-left-radius: 1em;
  border-bottom-right-radius: 1em;
  border-top-left-radius: 1em;
  border-top-right-radius: 1em;
}

.navi {
  display: flex;
  justify-content: center;
}
</style>

<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import axios from "@/util/http-common.js";

export default {
  name: "App",
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
  mounted() {
    // sessionStorage.setItem(
    //   "access-token",
    //   "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJteU1lc3NhZ2UiOiJXVEZGRkZGIEJST09PT08ifQ.Tf3M9Y8v6vzhDENFgucVt4c9KN1GQn0avqBEpMR_FRQ"
    // );
    const token = sessionStorage.getItem("access-token");
    if (token) {
      const payload = this.parseJWT(token);
      console.log(payload);
      axios
        .get(`userApi/detail`, payload.id)
        .then((res) => this.$store.commit("SET_MY_USER", res.data));
    }
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
}

nav a {
  font-weight: bold;
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #42b983;
}
</style>

<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";
export default {
  computed: {
    ...mapState(["myUser", "curUser"]),
  },
  created() {
    if (!sessionStorage.getItem("access-token")) {
      alert("로그아웃 되었습니다 - 로그인 페이지로 이동합니다.");
      this.$router.push("/login");
    } else {
      axios
        .get(`userApi/detail`, this.$route.params.id)
        .then((res) => this.$store.commit("SET_CUR_USER", res.data));

      axios
        .get(`followApi`, { from_user: this.myUser })
        .then((res) => this.$store.commit("SET_MY_USER_FOLLOWERS", res.data));
    }
  },
};
</script>

<style></style>

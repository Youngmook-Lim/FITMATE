<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";
export default {
  computed: {
    ...mapState(["myUser", "curUser", "API_URL"]),
  },
  created() {
    axios
      .get(`${this.API_URL}/userApi/detail`, this.$route.params.id)
      .then((res) => this.$store.commit("SET_CUR_USER", res.data));

    axios
      .get(`${this.API_URL}/followApi`, { from_user: this.myUser })
      .then((res) => this.$store.commit("SET_MY_USER_FOLLOWERS", res.data));
  },
};
</script>

<style></style>

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
    ...mapState(["curUser", "API_URL"]),
  },
  created() {
    axios
      .get(`${this.API_URL}/${this.$route.params.id}`)
      .then((res) => (this.curUser = res.data));
  },
};
</script>

<style></style>

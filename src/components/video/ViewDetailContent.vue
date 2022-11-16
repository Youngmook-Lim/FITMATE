<template>
  <div>
    <h5>비디오 상세 정보</h5>
    <iframe
      :src="`https://www.youtube.com/embed/${video.v_id}`"
      frameborder="0"
    ></iframe>
    <p>{{ video.title }}</p>
    <p>{{ video.v_writer }}</p>
    <p>{{ video.reg_date }}</p>
    <p>{{ video.category }}</p>
    <p>{{ video.view_cnt }}</p>
    <p>{{ video.favorite_cnt }}</p>

    <button v-if="checkIfFavorite()" @click="unfavorite">💔</button>
    <button v-else @click="favorite">❤</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(["video", "myUser", "videoFavoriteUsers"]),
  },
  methods: {
    checkIfFavorite() {
      for (let user of this.videoFavoriteUsers) {
        if (user.u_id === this.myUser.u_id) return true;
      }
      return false;
    },
    favorite() {
      axios
        .post(`favoriteApi/favoriteUp`, {
          u_id: this.myUser.u_id,
          v_id: this.video.v_id,
        })
        .then(() => this.$store.commit("FAVORITE"));
    },
    unfavorite() {
      axios
        .post(`favoriteApi/favoriteDown`, {
          u_id: this.myUser.u_id,
          v_id: this.video.v_id,
        })
        .then(() => this.$store.commit("UNFAVORITE"));
    },
  },
};
</script>

<style></style>

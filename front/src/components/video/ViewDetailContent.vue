<template>
  <div>
    <h5>비디오 상세 정보</h5>
    <iframe
      :src="`https://www.youtube.com/embed/${video.v_id}`"
      frameborder="0"
      width="500px"
      height="300px"
    ></iframe>
    <p>제목 : {{ video.title }}</p>
    <p>채널명 : {{ video.v_writer }}</p>
    <p>등록일자 : {{ video.reg_date }}</p>
    <p>카테고리 : {{ video.category }}</p>
    <p>조회수 : {{ video.view_cnt }}</p>
    <p>좋아요 : {{ videoFavoriteUsers.length }}</p>

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
  watch: {
    videoFavoriteUsers() {},
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
        .post(`favoriteApi/favoriteUp`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
          },
        })
        .then(() => this.$store.commit("FAVORITE"));
    },
    unfavorite() {
      axios
        .post(`favoriteApi/favoriteDown`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
          },
        })
        .then(() => this.$store.commit("UNFAVORITE"));
    },
  },
};
</script>

<style></style>

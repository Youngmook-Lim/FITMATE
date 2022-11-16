<template>
  <div>
    <h4>얘는 비디오 상세보기 페이지</h4>
    <view-detail-content></view-detail-content>
    <view-detail-comments></view-detail-comments>
  </div>
</template>

<script>
import ViewDetailContent from "./ViewDetailContent.vue";
import ViewDetailComments from "./ViewDetailComments.vue";
import { mapState } from "vuex";
import axios from "@/util/http-common.js";
export default {
  components: {
    ViewDetailContent,
    ViewDetailComments,
  },
  created() {
    let id = this.$route.params.id;
    axios
      .get(`videoApi/video/${id}`)
      .then((res) => this.$store.commit("SET_VIDEO", res.data));

    axios
      .get(`commentApi`, { v_id: this.video.v_id })
      .then((res) => this.$store.commit("SET_COMMENTS", res.data));

    axios
      .get(`favoriteApi/favoritePeople`, {
        v_id: this.video.v_id,
      })
      .then((res) => this.$store.commit("SET_VIDEO_FAVORITE_USERS", res.data));
  },
  computed: {
    ...mapState(["video", "comments", "videoFavoriteUsers"]),
  },
};
</script>

<style></style>

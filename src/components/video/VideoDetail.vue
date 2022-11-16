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
import axios from "axios";
export default {
  components: {
    ViewDetailContent,
    ViewDetailComments,
  },
  created() {
    let id = this.$route.params.id;
    axios
      .get(`${this.API_URL}/videoApi/video/${id}`)
      .then((res) => (this.video = res.data));

    axios
      .get(`${this.API_URL}/commentApi`, { v_id: this.video.v_id })
      .then((res) => this.$store.commit("SET_COMMENTS", res.data));

    axios
      .get(`${this.API_URL}/favoriteApi/favoritePeople`, {
        v_id: this.video.v_id,
      })
      .then((res) => (this.videoFavoriteUsers = res.data));
  },
  computed: {
    ...mapState(["API_URL", "video", "comments", "videoFavoriteUsers"]),
  },
};
</script>

<style></style>

<template>
  <div>
    <h4>얘는 비디오 상세보기 페이지</h4>
    <view-detail-content></view-detail-content>
    <view-detail-reviews></view-detail-reviews>
  </div>
</template>

<script>
import ViewDetailContent from "./ViewDetailContent.vue";
import ViewDetailReviews from "./ViewDetailReviews.vue";
import { mapState } from "vuex";
import axios from "axios";
export default {
  components: {
    ViewDetailContent,
    ViewDetailReviews,
  },
  created() {
    let id = this.$route.params.id;
    axios.get(this.API_URL, id).then((res) => (this.video = res.data));
    axios.get(this.API_URL, id).then((res) => (this.reviews = res.data));
  },
  computed: {
    ...mapState(["API_URL", "video", "reviews"]),
  },
};
</script>

<style></style>

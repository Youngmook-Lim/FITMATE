<template>
  <div>
    <h5>비디오 댓글들</h5>
    <view-detail-reviews-row
      v-for="review in reviews"
      :key="review.c_id"
      :review="review"
    ></view-detail-reviews-row>
  </div>
</template>

<script>
import ViewDetailReviewsRow from "@/components/video/ViewDetailReviewsRow.vue";
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["reviews"]),
  },
  components: {
    ViewDetailReviewsRow,
  },
};
</script>

<style></style>

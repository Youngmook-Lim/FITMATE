<template>
  <div>
    <h5>비디오 검색 결과</h5>
    <div v-for="video in videos" :key="video.id">
      <search-video-result-row :video="video"></search-video-result-row>
    </div>
  </div>
</template>

<script>
import SearchVideoResultRow from "@/components/video/SearchVideoResultRow.vue";
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["videos"]),
  },
  components: {
    SearchVideoResultRow,
  },
};
</script>

<style></style>

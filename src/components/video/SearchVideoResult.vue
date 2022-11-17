<template>
  <div>
    <h5>비디오 검색 결과</h5>
    <label for="sort">정렬기준 : </label>

    <!-- <select id="sort" v-model="sortCriteria">
      <option>----</option>
      <option>제목</option>
      <option>조회수</option>
      <option>등록일</option>
      <option>좋아요수</option>
    </select> -->

    <div>
      <button @click="sortCriteria = '제목'">제목</button>
      <button @click="sortCriteria = '조회수'">조회수</button>
      <button @click="sortCriteria = '등록일'">등록일</button>
      <button @click="sortCriteria = '좋아요수'">좋아요수</button>
    </div>
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
  data() {
    return {
      sortCriteria: "",
    };
  },
  watch: {
    sortCriteria(newVal) {
      this.$store.commit("SORT_VIDEOS", newVal);
    },
  },
};
</script>

<style></style>

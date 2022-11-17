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
    <!-- pagination -->

    <input type="number" v-model="curPage" />

    <div v-for="video in curVideos" :key="video.id">
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
      curPage: 1,
      curVideos: [],
      PAGE_SIZE: 3,
    };
  },
  watch: {
    sortCriteria(newVal) {
      this.$store.commit("SORT_VIDEOS", newVal);
    },
    curPage(newVal) {
      this.curVideos = this.videos.slice(
        (newVal - 1) * this.PAGE_SIZE,
        newVal * this.PAGE_SIZE
      );
    },
    videos() {
      this.curPage = 1;
      this.curVideos = this.videos.slice(
        (this.curPage - 1) * this.PAGE_SIZE,
        this.curPage * this.PAGE_SIZE
      );
    },
  },
  created() {
    this.curVideos = this.videos.slice(
      (this.curPage - 1) * this.PAGE_SIZE,
      this.curPage * this.PAGE_SIZE
    );
  },
};
</script>

<style></style>

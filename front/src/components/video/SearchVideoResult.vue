<template>
  <div>
    <div v-if="videosExist()">
      <label for="sort">정렬기준 : </label>

      <select id="sort" v-model="sortCriteria">
        <option disabled>----</option>
        <option>제목</option>
        <option>조회수</option>
        <option>등록일</option>
        <option>좋아요수</option>
      </select>

      <!-- <div>
      <button @click="sortCriteria = '제목'">제목</button>
      <button @click="sortCriteria = '조회수'">조회수</button>
      <button @click="sortCriteria = '등록일'">등록일</button>
      <button @click="sortCriteria = '좋아요수'">좋아요수</button>
    </div> -->
      <!-- pagination -->

      <!-- <input type="number" v-model="curPage" /> -->
      <button @click="decreasePage">◀</button>
      <span> {{ curPage }} </span>
      <!-- <input v-model="curPage" /> -->
      <button @click="increasePage">▶</button>

      <div v-for="video in curVideos" :key="video.id">
        <search-video-result-row :video="video"></search-video-result-row>
      </div>
    </div>
  </div>
</template>

<script>
import SearchVideoResultRow from "@/components/video/SearchVideoResultRow.vue";
import { mapState } from "vuex";

export default {
  computed: {
    ...mapState(["videos"]),
    maxPage() {
      return Math.floor((this.videos.length - 1) / this.PAGE_SIZE) + 1;
    },
  },
  components: {
    SearchVideoResultRow,
  },
  data() {
    return {
      sortCriteria: "----",
      curPage: 1,
      curVideos: [],
      PAGE_SIZE: 6,
    };
  },
  methods: {
    videosExist() {
      console.log(this.videos.length);
      const res = !(this.videos.length === 0);
      console.log(res);
      return res;
    },
    increasePage() {
      if (this.curPage === this.maxPage) return;
      this.curPage++;
    },
    decreasePage() {
      if (this.curPage === 1) return;
      this.curPage--;
    },
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

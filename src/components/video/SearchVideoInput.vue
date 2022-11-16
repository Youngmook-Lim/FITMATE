<template>
  <div>
    <h5>비디오 검색 창</h5>
    <form @submit.prevent="searchVideo">
      <input type="checkbox" id="전신" value="전신" v-model="category" />
      <label for="전신">전신</label>
      <input type="checkbox" id="가슴" value="가슴" v-model="category" />
      <label for="가슴">가슴</label>
      <input type="checkbox" id="상체" value="상체" v-model="category" />
      <label for="상체">상체</label>
      <input type="checkbox" id="어깨" value="어깨" v-model="category" />
      <label for="어깨">어깨</label>
      <input type="checkbox" id="등" value="등" v-model="category" />
      <label for="등">등</label>
      <input type="checkbox" id="하체" value="하체" v-model="category" />
      <label for="하체">하체</label>
      <input type="checkbox" id="복근" value="복근" v-model="category" />
      <label for="복근">복근</label>
      <input type="checkbox" id="이두" value="이두" v-model="category" />
      <label for="이두">이두</label>
      <input type="checkbox" id="삼두" value="삼두" v-model="category" />
      <label for="삼두">삼두</label>
      <input type="checkbox" id="팔뚝" value="팔뚝" v-model="category" />
      <label for="팔뚝">팔뚝</label>
      <br />
      <select v-model="range">
        <option>전체</option>
        <option>제목</option>
        <option>채널명</option>
      </select>
      <input type="text" v-model="keyword" />
      <button>검색</button>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  data() {
    return {
      range: "전체",
      category: [],
      keyword: "",
    };
  },
  methods: {
    searchVideo() {
      axios
        .get(`${this.API_URL}/videoApi/video`, {
          keyword: this.keyword,
          category: this.category,
          range: this.range,
        })
        .then((res) => this.$store.commit("SET_VIDEOS", res.data));
    },
  },
  computed: {
    ...mapState(["API_URL", "videos"]),
  },
};
</script>

<style></style>

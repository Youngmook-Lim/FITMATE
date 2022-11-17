<template>
  <div>
    <h3>{{ myUser.nickname }}</h3>
    <textarea
      cols="30"
      rows="3"
      v-model="curComment"
      placeholder="내용을 입력하세요"
    >
    </textarea>
    <button @click="registComment">등록</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "ViewDetailCommentsRegist",
  data() {
    return {
      curComment: "",
    };
  },
  methods: {
    registComment() {
      axios
        .post(`commentApi/`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
            content: this.curComment,
          },
        })
        .then(() => {
          axios
            .get(`commentApi/`, { params: { v_id: this.video.v_id } })
            .then((res) => this.$store.commit("SET_COMMENTS", res.data));
        })
        .then(() => (this.curComment = ""));
    },
  },
  computed: {
    ...mapState(["API_URL", "myUser", "video", "comments"]),
  },
};
</script>

<style></style>

<template>
  <div>
    <div class="profile-pic">
      <img
        v-if="loadedVideoSearch"
        :src="require('../../assets/profileImgs/' + myUser.img + '.png')"
        alt=""
      />
    </div>
    <textarea
      cols="30"
      rows="3"
      v-model.trim="curComment"
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
      if (!this.curComment) return;
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
    ...mapState([
      "API_URL",
      "myUser",
      "video",
      "comments",
      "loadedVideoSearch",
    ]),
  },
  watch: {
    // loadedVideoSearch(newVal) {
    //   if (newVal) {
    //   }
    // },
  },
};
</script>

<style scoped>
.profile-pic {
  border: 1px solid black;
  width: 50px;
  height: 50px;
  margin: 0 auto;
  border-radius: 50%;
  overflow: hidden;
  display: inline-block;
}

.profile-pic img {
  max-width: 100%;
  max-height: 100%;
}
</style>

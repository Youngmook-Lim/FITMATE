<template>
  <div>
    <div>{{ comment.nickname }}</div>
    <textarea
      cols="30"
      rows="3"
      v-model="tmpContent"
      :readonly="isReadonly"
    ></textarea>
    <div>{{ comment.reg_date }}</div>
    <br />
    <div v-if="myUser.u_id === comment.u_id">
      <button @click="updateComment">수정</button>
      <button @click="deleteComment">삭제</button>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "ViewDetailCommentsRow",
  props: {
    comment: Object,
  },
  data() {
    return {
      tmpContent: "",
      isReadonly: true,
    };
  },
  methods: {
    updateComment() {
      if (this.isReadonly) {
        this.isReadonly = false;
        return;
      }
      this.isReadonly = true;
      axios
        .put(`commentApi/`, null, {
          params: {
            c_id: this.comment.c_id,
            content: this.tmpContent,
          },
        })
        .then(() => {
          console.log(this.video);
          axios
            .get(`commentApi/`, { params: { v_id: this.video.v_id } })
            .then((res) => this.$store.commit("SET_COMMENTS", res.data));
        });
    },
    deleteComment() {
      axios
        .delete(`commentApi/${this.comment.c_id}`)
        .then(() => this.$store.commit("DELETE_COMMENT", this.comment.c_id));
    },
  },
  computed: {
    ...mapState(["myUser", "video"]),
  },
  created() {
    this.tmpContent = this.comment.content;
  },
};
</script>

<style scoped>
textarea {
  border: 2px solid red;
}
textarea[readonly] {
  border: 1px solid black;
}
</style>

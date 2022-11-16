<template>
  <div>
    <div>{{ comment.nickname }}</div>
    <textarea cols="30" rows="3" v-model="tmpContent"></textarea>
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
import axios from "axios";

export default {
  name: "ViewDetailCommentsRow",
  props: {
    comment: Object,
  },
  data() {
    return {
      tmpContent: "",
    };
  },
  methods: {
    updateComment() {
      axios
        .put(`${this.API_URL}/commentApi`, {
          c_id: this.comment.c_id,
          content: this.tmpContent,
        })
        .then(() => {
          axios
            .get(`${this.API_URL}/commentApi`, { v_id: this.video.v_id })
            .then((res) => this.$store.commit("SET_COMMENTS", res.data));
        });
    },
    deleteComment() {
      axios
        .delete(`${this.API_URL}/commentApi/${this.comment.c_id}`)
        .then(() => this.$store.commit("DELETE_COMMENT", this.comment.c_id));
    },
  },
  computed: {
    ...mapState(["myUser"]),
  },
  created() {
    this.tmpContent = this.comment.content;
  },
};
</script>

<style></style>

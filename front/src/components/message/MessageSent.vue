<template>
  <div>
    <h2>보낸편지함</h2>
    <message-content
      v-for="msg in sentMsgs"
      :key="msg.m_id"
      :msg="msg"
      type="sent"
    ></message-content>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";
import MessageContent from "./MessageContent.vue";
import { mapState } from "vuex";

export default {
  name: "MessageSent",
  components: {
    MessageContent,
  },
  props: {
    isMyUserLoaded: Boolean,
  },
  computed: {
    ...mapState(["myUser", "sentMsgs"]),
  },
  watch: {
    isMyUserLoaded() {
      axios({
        url: "messageApi/received",
        method: "GET",
        params: {
          id: this.myUser.u_id,
        },
      }).then((res) => {
        this.$store.commit("SET_RECEIVED_MSGS", res.data);
      });

      axios({
        url: "messageApi/sent",
        method: "GET",
        params: {
          id: this.myUser.u_id,
        },
      }).then((res) => {
        this.$store.commit("SET_SENT_MSGS", res.data);
      });
    },
  },
  // created() {
  //   axios({
  //     url: "messageApi/sent",
  //     method: "GET",
  //     params: {
  //       id: this.myUser.u_id,
  //     },
  //   }).then((res) => {
  //     this.$store.commit("SET_SENT_MSGS", res.data);
  //   });
  // },
};
</script>

<style></style>

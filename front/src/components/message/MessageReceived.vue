<template>
  <div>
    <h2>받은편지함</h2>
    <message-content
      v-for="msg in receivedMsgs"
      :key="msg.m_id"
      :msg="msg"
      type="received"
    ></message-content>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";
import MessageContent from "./MessageContent.vue";
import { mapState } from "vuex";

export default {
  name: "MessageReceived",
  components: {
    MessageContent,
  },
  props: {
    isMyUserLoaded: Boolean,
  },
  computed: {
    ...mapState(["myUser", "receivedMsgs"]),
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
  created() {
    // @@@@@@ TMP VARIABLE @@@@@@
    this.$store.state.receivedMsgs = [
      {
        m_id: 1,
        from_user: "id06",
        to_user: "id04",
        content: "What's up man how's it going? I'm fine and you?",
      },
    ];
  },
};
</script>

<style></style>

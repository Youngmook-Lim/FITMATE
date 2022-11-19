<template>
  <div @click="showModal = true">
    <span>✉ </span>
    <span>{{ displayedMsg }}</span>
    <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
    <span v-if="type === 'received'">From : {{ msg.from_user }}</span>
    <span v-else>To : {{ msg.to_user }}</span>
    <message-modal v-if="showModal" @close="showModal = false">
      <h3 v-if="type === 'received'" slot="header">
        From : {{ msg.from_user }}
      </h3>
      <h3 v-else slot="header">To : {{ msg.to_user }}</h3>
      <p slot="body">{{ msg.content }}</p>
      <button slot="footer" @click.stop="goToProfile">프로필 보러가기</button>
    </message-modal>
  </div>
</template>

<script>
import MessageModal from "./MessageModal.vue";
const MAX_DISPLAY_LENGTH = 20;

export default {
  name: "MessageContent",
  components: {
    MessageModal,
  },
  props: {
    msg: Object,
    type: String,
  },
  data() {
    return {
      showModal: false,
    };
  },
  computed: {
    displayedMsg() {
      let dispMsg = this.msg.content;
      const len = dispMsg.length;
      if (len > MAX_DISPLAY_LENGTH) {
        dispMsg = dispMsg.slice(0, MAX_DISPLAY_LENGTH) + "...";
      }
      return dispMsg;
    },
  },
  methods: {
    goToProfile() {
      if (this.type === "received") {
        this.$router.push({
          name: "UserViewMain",
          params: {
            id: this.msg.from_user,
          },
        });
      } else {
        this.$router.push({
          name: "UserViewMain",
          params: {
            id: this.msg.to_user,
          },
        });
      }
    },
  },
};
</script>

<style scoped>
div {
  border: 1px solid black;
  padding: 12px;
  cursor: pointer;
}
</style>

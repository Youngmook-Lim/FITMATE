<template>
  <div>
    <h1 class="detailtitle">DETAIL VIDEO</h1>
    <div class="container">
      <iframe
        :src="`https://www.youtube.com/embed/${video.v_id}`"
        frameborder="0"
        width="800px"
        height="450px"
      ></iframe>
      <div class="text">
        <span class="title">{{ video.title }}</span>
        <div class="binding">
          <span class="category">{{ video.category }} 운동</span>
          <span>{{ video.v_writer }}</span>
        </div>
        <div class="binding">
          <span class="regdate">{{ video.reg_date }}</span>
          <span>조회수 {{ video.view_cnt }}</span>
        </div>

        <div class="like">
          <p>좋아요 {{ videoFavoriteUsers.length }}</p>

          <button v-if="checkIfFavorite()" @click="unfavorite">💔</button>
          <button v-else @click="favorite">❤</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(["video", "myUser", "videoFavoriteUsers"]),
  },
  watch: {
    videoFavoriteUsers() {},
  },
  methods: {
    checkIfFavorite() {
      for (let user of this.videoFavoriteUsers) {
        if (user.u_id === this.myUser.u_id) return true;
      }
      return false;
    },
    favorite() {
      axios
        .post(`favoriteApi/favoriteUp`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
          },
        })
        .then(() => this.$store.commit("FAVORITE"));
    },
    unfavorite() {
      axios
        .post(`favoriteApi/favoriteDown`, null, {
          params: {
            u_id: this.myUser.u_id,
            v_id: this.video.v_id,
          },
        })
        .then(() => this.$store.commit("UNFAVORITE"));
    },
  },
};
</script>

<style scoped>
.detailtitle {
  margin-top: 8%;
  text-align: start;
  margin-left: 5%;
  font-weight: 500;
  margin-bottom: 0%;
}

.container {
  width: 900px;
  margin: auto;
}

iframe {
  margin: 0px 0px;
}
.binding {
  width: auto;
  display: flex;
  justify-content: space-between;
}

.text {
  text-align: start;
  width: 800px;
  margin: auto;
}

.like {
  display: flex;
  gap: 2%;
  justify-content: end;
}
</style>

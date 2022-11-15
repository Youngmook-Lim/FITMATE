<template>
  <div>
    <h3 v-if="curUser.id === myUser.id">마이 페이지</h3>
    <h3 v-else>유저 메인페이지</h3>
    <p>아이디 : {{ curUser.u_id }}</p>
    <p>이름 : {{ curUser.name }}</p>
    <p>e-mail : {{ curUser.email }}</p>
    <p>성별 : {{ curUser.gender }}</p>
    <p>연락처 : {{ curUser.phone_no }}</p>
    <p>닉네임 : {{ curUser.nickname }}</p>

    <router-link
      v-if="curUser.id === myUser.id"
      :to="{ name: 'UserViewUpdate' }"
      >수정</router-link
    >
    <button v-else @click="follow">팔로우</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  computed: {
    ...mapState(["curUser", "myUser", "API_URL"]),
  },
  methods: {
    follow() {
      axios.post(this.API_URL);
    },
  },
};
</script>

<style></style>

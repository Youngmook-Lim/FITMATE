<template>
  <div>
    <h3 v-if="curUser.u_id === myUser.u_id">마이 페이지</h3>
    <h3 v-else>유저 메인페이지</h3>
    <table>
      <tr>
        <th>아이디</th>
        <td>{{ curUser.u_id }}</td>
      </tr>
      <tr>
        <th>성명</th>
        <td>{{ curUser.name }}</td>
      </tr>
      <tr>
        <th>닉네임</th>
        <td>{{ curUser.nickname }}</td>
      </tr>
      <tr>
        <th>이메일</th>
        <td>{{ curUser.email }}</td>
      </tr>
      <tr>
        <th>성별</th>
        <td>{{ curUser.gender }}</td>
      </tr>
      <tr>
        <th>연락처</th>
        <td>{{ curUser.phone_no }}</td>
      </tr>
    </table>

    <router-link
      v-if="curUser.u_id === myUser.u_id"
      :to="{ name: 'UserViewUpdate' }"
      >회원정보 수정</router-link
    >
    <button v-else-if="checkIfFollow()" @click="unfollow">팔로우 취소</button>
    <button v-else @click="follow">팔로우</button>
    <hr />
    <button @click="deleteUser">회원탈퇴</button>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  computed: {
    ...mapState(["curUser", "myUser", "myUserFollowers", "API_URL"]),
  },
  methods: {
    follow() {
      axios
        .post(`${this.API_URL}/followApi`, {
          from_user: this.myUser.u_id,
          to_user: this.curUser.u_id,
        })
        .then(() => this.$store.commit("ADD_FOLLOWER"));
    },
    unfollow() {
      axios
        .delete(`${this.API_URL}/followApi`, {
          from_user: this.myUser.u_id,
          to_user: this.curUser.u_id,
        })
        .then(() => this.$store.commit("DELETE_FOLLOWER"));
    },
    checkIfFollow() {
      for (let f of this.myUserFollowers) {
        if (f.u_id === this.curUser.u_id) return true;
      }
      return false;
    },
    deleteUser() {
      axios
        .post(`${this.API_URL}/userApi/delete`, this.myUser.u_id)
        .then(() => {
          this.$store.commit("CLEAR_ALL");
          // 세션정보 지우기 : 로그아웃
        })
        .then(() => this.$router.push("/"));
    },
  },
};
</script>

<style></style>

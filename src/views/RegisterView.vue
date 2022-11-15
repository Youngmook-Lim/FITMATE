<template>
  <div>
    <h2>회원가입</h2>
    <form @submit.prevent="registUser">
      <table>
        <tr>
          <th>아이디</th>
          <td><input type="text" v-model="user.u_id" /></td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td><input type="password" v-model="user.pw" /></td>
        </tr>
        <tr>
          <th>이름</th>
          <td><input type="text" v-model="user.name" /></td>
        </tr>
        <tr>
          <th>이메일</th>
          <td><input type="email" v-model="user.email" /></td>
        </tr>
        <tr>
          <th>성별</th>
          <td>
            <div>
              <label for="men" value="남">남</label>
              <input
                type="radio"
                v-model="user.gender"
                name="gender"
                value="men"
                id="men"
              />

              <label for="women">여</label>
              <input
                type="radio"
                v-model="user.gender"
                name="gender"
                value="women"
                id="women"
              />
            </div>
          </td>
        </tr>
        <tr>
          <th>전화번호</th>
          <td><input type="number" v-model="user.phone_no" /></td>
        </tr>
        <tr>
          <th>닉네임</th>
          <td><input type="text" v-model="user.nickname" /></td>
        </tr>
      </table>
      <button>회원가입</button>
      <input type="button" @click="toLogin" value="로그인" />
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  data() {
    return {
      user: {
        u_id: "",
        pw: "",
        name: "",
        email: "",
        gender: "",
        phone_no: "",
        nickname: "",
      },
    };
  },
  methods: {
    registUser() {
      axios
        .post(this.API_URL, this.user)
        .then(() => this.$router.push({ name: "login" }));
    },
    toLogin() {
      this.$router.push({ name: "LoginView" });
    },
  },
  computed: {
    ...mapState(["API_URL"]),
  },
};
</script>

<style></style>

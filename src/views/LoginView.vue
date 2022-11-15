<template>
  <div>
    <h2>로그인 페이지</h2>
    <form @submit.prevent="login">
      <label for="id">아이디 : </label>
      <input type="text" v-model="u_id" id="id" required /><br />
      <label for="pw">비밀번호 : </label
      ><input type="password" v-model="pw" id="pw" required /><br />
      <input type="submit" value="로그인" />
      <input type="button" @click="register" value="회원가입" />
    </form>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";

export default {
  data() {
    return {
      u_id: "",
      pw: "",
    };
  },
  methods: {
    login() {
      axios
        .get(`${this.API_URL}/userApi/login`, this.u_id, this.pw)
        .then((res) => {
          if (res.data === "FAIL") {
            alert("등록된 아이디가 없습니다.");
            throw new Error("등록된 아이디가 없습니다.");
          } else if (res.data === "WRONG") {
            alert("비밀번호가 맞지 않습니다.");
            throw new Error("비밀번호가 맞지 않습니다.");
          }
          return axios.get(`${this.API_URL}/userApi/detail`, this.u_id);
        })
        .then((res) => {
          this.$store.commit("SET_MY_USER", res.data);
          // this.myUser = res.data;
          this.$router.push({ name: "HomeView" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    register() {
      this.$router.push({ name: "RegisterView" });
    },
  },
  computed: {
    ...mapState(["API_URL"]),
  },
  created() {
    // 만약 세션에 로그인이 등록되어 있으면
    // this.$router.push({ name: "HomeView" });
  },
};
</script>

<style></style>

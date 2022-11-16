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
import axios from "@/util/http-common.js";

export default {
  name: "LoginView",
  data() {
    return {
      u_id: "",
      pw: "",
    };
  },
  methods: {
    login() {
      axios
        .get(`userApi/login`, this.u_id, this.pw)
        .then((res) => {
          if (res.data.message === "fail") {
            alert("등록된 아이디가 없습니다.");
            throw new Error("등록된 아이디가 없습니다.");
          } else if (res.data.message === "wrong") {
            alert("비밀번호가 맞지 않습니다.");
            throw new Error("비밀번호가 맞지 않습니다.");
          }
          sessionStorage.setItem("access-token", res.data["access-token"]);

          return axios.get(`userApi/detail`, this.u_id);
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
  created() {
    if (sessionStorage.getItem("access-token")) {
      this.$router.push({ name: "HomeView" });
    }
  },
};
</script>

<style></style>

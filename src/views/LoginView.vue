<template>
  <div>
    <h2>로그인</h2>
    <form @submit.prevent="login">
      <label for="id">아이디 : </label>
      <input type="text" v-model="id" id="id" required /><br />
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
      id: "",
      pw: "",
    };
  },
  methods: {
    login() {
      const user = {
        id: this.id,
        pw: this.pw,
      };
      axios
        .get(this.API_URL, user)
        .then((res) => {
          if (res.data === "no_id") {
            alert("등록된 아이디가 없습니다.");
            throw new Error();
          } else if (res.data === "fail") {
            alert("비밀번호가 맞지 않습니다.");
            throw new Error();
          }
          return axios.get(this.API_URL, user.id);
        })
        .then((res) => {
          this.myUser = res.data;
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
    ...mapState(["API_URL", "myUser"]),
  },
  created() {
    // 만약 세션에 로그인이 등록되어 있으면
    // this.$router.push({ name: "HomeView" });
  },
};
</script>

<style></style>

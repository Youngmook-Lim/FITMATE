<template>
  <div>
    <h3>유저 수정페이지</h3>
    <form @submit.prevent="updateUser">
      id : <input type="text" v-model="tmpUser.u_id" readonly /><br />
      pw : <input type="password" v-model="tmpUser.pw" /><br />
      name : <input type="text" v-model="tmpUser.name" /><br />
      email : <input type="email" v-model="tmpUser.email" /><br />
      <div>
        men :
        <input
          type="radio"
          v-model="tmpUser.gender"
          name="gender"
          value="men"
        />
        women :
        <input
          type="radio"
          v-model="tmpUser.gender"
          name="gender"
          value="women"
        /><br />
      </div>
      phone : <input type="number" v-model="tmpUser.phone_no" /><br />
      nickname : <input type="text" v-model="tmpUser.nickname" /><br />
      <button>회원가입</button>
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "axios";

export default {
  data() {
    return {
      tmpUser: {},
    };
  },
  computed: {
    ...mapState(["myUser", "API_URL"]),
  },
  created() {
    this.tmpUser = this.myUser;
  },
  methods: {
    updateUser() {
      axios
        .put(this.API_URL, this.tmpUser)
        .then(() => this.$router.push({ name: "UserViewMain" }));
    },
  },
};
</script>

<style></style>

<template>
  <div>
    <h3>유저 수정페이지</h3>
    <form @submit.prevent="updateUser">
      <table>
        <tr>
          <th>아이디</th>
          <td><input type="text" v-model="tmpUser.u_id" readonly /></td>
          <td>
            <button type="button" @click="checkDuplicate" :class="{ ok: isOk }">
              중복확인
            </button>
          </td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td><input type="password" v-model="tmpUser.pw" required /></td>
        </tr>
        <tr>
          <th>성명</th>
          <td><input type="text" v-model="tmpUser.name" required /></td>
        </tr>
        <tr>
          <th>닉네임</th>
          <td><input type="text" v-model="tmpUser.nickname" /></td>
        </tr>
        <tr>
          <th>이메일</th>
          <td><input type="email" v-model="tmpUser.email" required /></td>
        </tr>
        <tr>
          <th>성별</th>
          <td>
            <div>
              <label for="man" value="남">남</label>
              <input
                type="radio"
                v-model="tmpUser.gender"
                name="gender"
                value="man"
                id="man"
              />

              <label for="woman">여</label>
              <input
                type="radio"
                v-model="tmpUser.gender"
                name="gender"
                value="woman"
                id="woman"
              />
            </div>
          </td>
        </tr>
        <tr>
          <th>전화번호</th>
          <td>
            <input
              type="tel"
              pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}"
              v-model="tmpUser.phone_no"
              placeholder="예 : 010-123(4)-5678"
            />
          </td>
        </tr>
      </table>
      <button>회원가입</button>
      <input type="button" @click="toLogin" value="로그인" />
    </form>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  data() {
    return {
      tmpUser: {},
    };
  },
  computed: {
    ...mapState(["myUser"]),
  },
  created() {
    this.tmpUser = this.myUser;
  },
  methods: {
    updateUser() {
      axios
        .put(`userApi/update`, this.tmpUser)
        .then(() => {
          this.$store.commit("SET_MY_USER", this.tmpUser);
          this.$store.commit("SET_CUR_USER", this.tmpUser);
        })
        .then(() => this.$router.push({ name: "UserViewMain" }));
    },
  },
};
</script>

<style></style>

<template>
  <div>
    <h3>유저 수정페이지</h3>
    <form @submit.prevent="updateUser">
      <table>
        <tr>
          <th>아이디</th>
          <td><input type="text" v-model="tmpUser.u_id" readonly /></td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td><input type="password" v-model="tmpUser.pw" required /></td>
        </tr>
        <tr>
          <th>비밀번호 확인</th>
          <td>
            <input type="password" v-model="passwordCheck" required />
            <p>{{ passwordMessage }}</p>
          </td>
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
              <label for="M" value="남">남</label>
              <input
                type="radio"
                v-model="tmpUser.gender"
                name="gender"
                value="M"
                id="M"
              />

              <label for="F">여</label>
              <input
                type="radio"
                v-model="tmpUser.gender"
                name="gender"
                value="F"
                id="F"
              />
            </div>
          </td>
        </tr>
        <tr>
          <th>연락처</th>
          <td>
            <input
              type="tel"
              pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}"
              v-model="tmpUser.phone_no"
              placeholder="예 : 010-123(4)-5678"
            />
          </td>
        </tr>
        <tr>
          <th>상태메시지</th>
          <td><input type="text" v-model="tmpUser.state_message" /></td>
        </tr>
      </table>
      <button>회원정보수정</button>
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
      passwordCheck: "",
    };
  },
  computed: {
    ...mapState(["myUser"]),
    passwordMessage() {
      if (!this.passwordCheck) return "______";
      if (this.user.pw === this.passwordCheck) {
        console.log(this.user.pw.length);
        if (4 <= this.user.pw.length && this.user.pw.length <= 20) {
          return "비밀번호가 일치합니다.";
        } else {
          return "비밀번호 길이는 4 ~ 20자 이어야 합니다.";
        }
      } else {
        return "비밀번호가 일치하지 않습니다.";
      }
    },
    isPasswordSame() {
      return (
        this.user.pw === this.passwordCheck &&
        4 <= this.user.pw.length &&
        this.user.pw.length <= 20
      );
    },
  },
  created() {
    this.tmpUser = { ...this.myUser };
    this.tmpUser.phone_no = this.parsePhoneNo(this.tmpUser.phone_no);
  },
  watch: {
    myUser(newVal) {
      // console.log(newVal);
      this.tmpUser = { ...newVal };
      this.tmpUser.phone_no = this.parsePhoneNo(this.tmpUser.phone_no);
    },
  },
  methods: {
    updateUser() {
      console.log(this.tmpUser);
      this.tmpUser.phone_no = this.tmpUser.phone_no.split("-").join("");
      axios
        .put(`userApi/update`, null, { params: this.tmpUser })
        .then(() => {
          this.$store.commit("SET_MY_USER", this.tmpUser);
          this.$store.commit("SET_CUR_USER", this.tmpUser);
        })
        .then(() => this.$router.push({ name: "UserViewMain" }));
    },
    parsePhoneNo(phone_no) {
      // console.log(this.myUser.phone_no);
      if (!phone_no) {
        return;
      }
      if (phone_no.length == 10) {
        return (
          phone_no.slice(0, 3) +
          "-" +
          phone_no.slice(3, 6) +
          "-" +
          phone_no.slice(6)
        );
      } else if (phone_no.length == 11) {
        return (
          phone_no.slice(0, 3) +
          "-" +
          phone_no.slice(3, 7) +
          "-" +
          phone_no.slice(7)
        );
      }
    },
  },
};
</script>

<style></style>

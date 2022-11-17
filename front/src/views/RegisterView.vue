<template>
  <div>
    <h2>회원가입</h2>
    <form @submit.prevent="registUser">
      <table>
        <tr>
          <th>아이디</th>
          <td><input type="text" v-model="user.u_id" required /></td>
          <td>
            <button type="button" @click="checkDuplicate" :class="{ ok: isOk }">
              {{ duplicateMsg }}
            </button>
          </td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td><input type="text" v-model="user.pw" required /></td>
        </tr>
        <tr>
          <th>성명</th>
          <td><input type="text" v-model="user.name" required /></td>
        </tr>
        <tr>
          <th>닉네임</th>
          <td><input type="text" v-model="user.nickname" /></td>
        </tr>
        <tr>
          <th>성별</th>
          <td>
            <div>
              <label for="M" value="남">남</label>
              <input
                type="radio"
                v-model="user.gender"
                name="gender"
                value="M"
                id="M"
              />

              <label for="F">여</label>
              <input
                type="radio"
                v-model="user.gender"
                name="gender"
                value="F"
                id="F"
              />
            </div>
          </td>
        </tr>
        <tr>
          <th>이메일</th>
          <td><input type="email" v-model="user.email" required /></td>
        </tr>
        <tr>
          <th>전화번호</th>
          <td>
            <input
              type="tel"
              pattern="[0-9]{3}-[0-9]{3,4}-[0-9]{4}"
              v-model="user.phone_no"
              placeholder="예 : 010-123(4)-5678"
            />
          </td>
        </tr>
        <tr>
          <th>우편번호</th>
          <td>
            <input type="text" v-model="zipcode" readonly />
          </td>
          <td>
            <input type="button" value="우편번호 찾기" @click="kakaopost" />
          </td>
        </tr>
        <tr>
          <th>주소</th>
          <td>
            <input type="text" v-model="user.address" readonly />
          </td>
        </tr>
        <tr>
          <th>상세주소</th>
          <td>
            <input
              type="text"
              v-model="detailAddress"
              placeholder="상세주소를 입력하세요."
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
import axios from "@/util/http-common.js";

export default {
  name: "RegisterView",
  data() {
    return {
      user: {
        u_id: "",
        pw: "",
        name: "",
        email: "",
        gender: "M",
        phone_no: "",
        nickname: "",
        address: "",
      },
      isOk: false,
      zipcode: "",
      detailAddress: "",
    };
  },
  methods: {
    registUser() {
      if (!this.isOk) {
        alert("아이디 중복확인을 완료해 주세요.");
        return;
      }

      this.user.phone_no = this.user.phone_no.split("-").join("");
      this.user.address += ` ${this.detailAddress}`;

      axios({
        url: "userApi/regist",
        method: "POST",
        params: this.user,
      })
        // .post(`userApi/regist`, null, { params: this.user })
        .then(() => this.$router.push({ name: "LoginView" }))
        .catch((err) => {
          console.log(err.response.data);
        });
    },
    checkDuplicate() {
      axios({
        url: "userApi/check",
        method: "GET",
        params: { id: this.user.u_id },
      }).then((res) => {
        console.log(res);
        if (res.data === "fail") {
          alert("이미 사용중인 아이디입니다.");
          this.isOk = false;
        } else {
          alert("사용 가능한 아이디입니다.");
          this.isOk = true;
        }
      });
    },
    toLogin() {
      this.$router.push({ name: "LoginView" });
    },
    kakaopost() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          this.zipcode = data.zonecode;
          this.user.address = data.address;
        },
      }).open();
    },
  },
  created() {
    if (sessionStorage.getItem("access-token")) {
      this.$router.push({ name: "HomeView" });
    }
  },
  computed: {
    duplicateMsg() {
      return this.isOk ? "중복확인 완료" : "중복확인";
    },
  },
};
</script>

<style>
.ok {
  background-color: green;
}

table {
  margin: 0 auto;
}
</style>

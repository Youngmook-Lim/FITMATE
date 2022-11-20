<template>
  <div>
    <h2>회원가입</h2>
    <div class="profile-pic">
      <img v-if="!imgNum" />
      <img
        v-else
        :src="require('../assets/profileImgs/img_' + imgNum + '.png')"
        alt=""
      />
    </div>
    <button @click="showModal = true">사진 등록</button>
    <hr />
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
            <input type="text" v-model="zipcode" disabled />
          </td>
          <td>
            <input type="button" value="우편번호 찾기" @click="kakaopost" />
          </td>
        </tr>
        <tr>
          <th>주소</th>
          <td>
            <input type="text" v-model="user.address" disabled />
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

    <register-view-modal v-if="showModal" @close="showModal = false">
      <h3 slot="header">마음에 드는 프로필 사진을 등록하세요</h3>
      <div slot="body">
        <img
          v-for="n in 10"
          :key="n"
          :src="require('../assets/profileImgs/img_' + n + '.png')"
          alt="Profile pic options"
          width="50px"
          height="50px"
          @click="
            imgNum = n;
            showModal = false;
          "
        />
      </div>
      <button slot="footer" @click.stop="setImage">등록</button>
    </register-view-modal>
  </div>
</template>

<script>
import axios from "@/util/http-common.js";
import axiosRaw from "axios";
import RegisterViewModal from "./RegisterViewModal.vue";

export default {
  name: "RegisterView",
  components: {
    RegisterViewModal,
  },
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
        x: "",
        y: "",
        img: "",
      },
      isOk: false,
      zipcode: "",
      detailAddress: "",
      showModal: false,
      imgNum: "",
    };
  },
  methods: {
    setImage() {},
    registUser() {
      if (!this.isOk) {
        alert("아이디 중복확인을 완료해 주세요.");
        return;
      }

      this.user.phone_no = this.user.phone_no.split("-").join("");
      this.user.address += ` ${this.detailAddress}`;

      const KAKAO_KEY = process.env.VUE_APP_KAKAO_REST_API_KEY;

      axiosRaw({
        url: "https://dapi.kakao.com/v2/local/search/address",
        method: "GET",
        params: {
          query: this.user.address,
        },
        headers: {
          Authorization: `KakaoAK ${KAKAO_KEY}`,
        },
      })
        .then((res) => {
          const coords = res.data.documents[0];
          this.user.x = coords.x;
          this.user.y = coords.y;
          this.user.img = "img_" + this.imgNum;
        })
        .then(() => {
          axios({
            url: "userApi/regist",
            method: "POST",
            params: this.user,
          })
            // .post(`userApi/regist`, null, { params: this.user })
            .then(() => this.$router.push({ name: "LoginView" }))
            .catch((err) => {
              console.log(err.response.data);
              switch (err.response.data) {
                case "dup_email":
                  alert("이미 등록된 이메일 입니다.");
                  break;
                case "dup_nickname":
                  alert("이미 등록된 닉네임 입니다.");
                  break;
                case "dup_phone":
                  alert("이미 등록된 전화번호 입니다.");
                  break;
              }
            });
        });
    },
    checkDuplicate() {
      if (!this.user.u_id) {
        this.isOk = false;
        return;
      }
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

.profile-pic {
  border: 1px solid black;
  width: 150px;
  height: 150px;
  margin: 0 auto;
  border-radius: 50%;
  overflow: hidden;
}

.profile-pic img {
  max-width: 100%;
  max-height: 100%;
}
</style>

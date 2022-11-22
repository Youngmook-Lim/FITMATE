<template>
  <div>
    <h3 v-if="curUser.u_id === myUser.u_id">마이 페이지</h3>
    <h3 v-else>유저 메인페이지</h3>
    <div class="profile-pic">
      <img v-if="!curUser.img" />
      <img
        v-else
        :src="require('../../assets/profileImgs/' + curUser.img + '.png')"
        alt=""
      />
    </div>
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
        <td>{{ curUser.phone_no | parsePhoneNo }}</td>
      </tr>
      <tr>
        <th>위치</th>
        <td>{{ curUser.address | sliceAddress }}</td>
      </tr>
      <tr>
        <th>상태메시지</th>
        <td>{{ curUser.state_message }}</td>
      </tr>
      <tr>
        <th>Followers</th>
        <td>{{ curUserFollowers.length }}명</td>
      </tr>
      <tr>
        <th>Following</th>
        <td>{{ curUserFollowing.length }}명</td>
      </tr>
      <tr>
        <th>좋아요한 비디오</th>
        <td>
          <img
            v-for="v in curFavoriteVideos"
            :key="v.v_id"
            :src="`https://img.youtube.com/vi/${v.v_id}/0.jpg`"
            width="100px"
            style="display: inline-block; cursor: pointer"
            @click="moveDetail(v.v_id)"
          />
        </td>
      </tr>
    </table>

    <router-link
      v-if="curUser.u_id === myUser.u_id"
      :to="{ name: 'UserViewUpdate' }"
      >회원정보 수정</router-link
    >
    <button v-else-if="checkIfFollow()" @click="unfollow">팔로우 취소</button>
    <button v-else @click="follow">팔로우</button>
    <button v-if="curUser.u_id === myUser.u_id" @click="deleteUser">
      회원탈퇴
    </button>
    <router-link
      v-if="curUser.u_id !== myUser.u_id"
      :to="{
        name: 'MessageSend',
        params: { id: curUser.u_id ? curUser.u_id : 0 },
      }"
    >
      메시지 보내기
    </router-link>
  </div>
</template>

<script>
import { mapState } from "vuex";
import axios from "@/util/http-common.js";

export default {
  name: "UserViewMain",
  computed: {
    ...mapState([
      "curUser",
      "myUser",
      "myUserFollowing",
      "curUserFollowers",
      "curUserFollowing",
      "curFavoriteVideos",
    ]),
  },
  filters: {
    sliceAddress(address) {
      if (!address) return;
      const splitAddress = address.split(" ");
      return splitAddress.slice(0, 3).join(" ");
    },
    parsePhoneNo(phone_no) {
      if (!phone_no) {
        return;
      } else if (phone_no.length == 10) {
        return (
          phone_no.substring(0, 3) +
          "-" +
          phone_no.substring(3, 6) +
          "-" +
          phone_no.substring(6)
        );
      } else if (phone_no.length == 11) {
        return (
          phone_no.substring(0, 3) +
          "-" +
          phone_no.substring(3, 7) +
          "-" +
          phone_no.substring(7)
        );
      }
    },
  },
  methods: {
    moveDetail(v_id) {
      this.$router.push({
        name: "VideoDetail",
        params: { id: v_id },
      });
    },
    follow() {
      axios
        .post(`followApi/`, null, {
          params: {
            from_user: this.myUser.u_id,
            to_user: this.curUser.u_id,
          },
        })
        .then(() => this.$store.commit("ADD_FOLLOWER"));
    },
    unfollow() {
      axios
        .delete(`followApi/`, {
          params: {
            from_user: this.myUser.u_id,
            to_user: this.curUser.u_id,
          },
        })
        .then(() => this.$store.commit("DELETE_FOLLOWER"));
    },
    checkIfFollow() {
      for (let f of this.myUserFollowing) {
        if (f.u_id === this.curUser.u_id) return true;
      }
      return false;
    },
    deleteUser() {
      axios
        .delete(`userApi/delete`, { params: { id: this.myUser.u_id } })
        .then(() => {
          this.$store.commit("CLEAR_ALL");
          // 세션정보 지우기 : 로그아웃
          sessionStorage.removeItem("access-token");
        })
        .then(() => this.$router.push("/"));
    },
  },
};
</script>

<style scoped>
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

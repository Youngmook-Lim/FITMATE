<template>
  <div>
    <h3>비디오 검색 기능</h3>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  created() {
    if (!sessionStorage.getItem("access-token")) {
      alert("로그아웃 되었습니다 - 로그인 페이지로 이동합니다.");
      this.$router.push("/login");
    }
  },
};
</script>

<style></style>

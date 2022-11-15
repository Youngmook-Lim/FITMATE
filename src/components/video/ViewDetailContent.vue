<template>
  <div>
    <h5>비디오 상세 정보</h5>
    <iframe
      :src="`https://www.youtube.com/embed/${video.v_id}`"
      frameborder="0"
    ></iframe>
    <p>{{ video.title }}</p>
    <p>{{ video.v_writer }}</p>
    <p>{{ video.reg_date }}</p>
    <p>{{ video.category }}</p>
    <p>{{ video.view_cnt }}</p>
    <p>{{ video.is_like }}</p>
    <button @click="like">❤</button>
    <!-- 좋아요는 추후 작성-->
  </div>
</template>

<script>
import { mapState } from "vuex";
// import axios from "axios";

export default {
  data() {
    return {};
  },
  computed: {
    ...mapState(["video", "myUser"]),
  },
  methods: {
    like() {},
  },
  created() {
    // axios.
  },
};
</script>

<style></style>

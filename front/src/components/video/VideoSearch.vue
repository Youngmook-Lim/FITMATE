<template>
  <div>
    <h4>비디오 검색</h4>
    <search-video-input></search-video-input>
    <search-video-result></search-video-result>
  </div>
</template>

<script>
import SearchVideoInput from "./SearchVideoInput.vue";
import SearchVideoResult from "./SearchVideoResult.vue";
export default {
  components: {
    SearchVideoInput,
    SearchVideoResult,
  },
};
</script>

<style></style>

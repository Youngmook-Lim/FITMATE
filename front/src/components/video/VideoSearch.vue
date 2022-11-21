<template>
  <div>
    <h2 class="video">VIDEO SEARCH</h2>
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

<style scoped>
.video{
  margin-top: 20px;
}
</style>

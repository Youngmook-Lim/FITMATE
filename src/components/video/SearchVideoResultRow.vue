<template>
  <div @click="moveDetail">
    <img :src="`https://img.youtube.com/vi/${video.v_id}/0.jpg`" />
    <p>{{ video.title }}</p>
    <p>{{ video.v_writer }}</p>
    <p>{{ video.reg_date }}</p>
    <p>{{ video.category }}</p>
    <p>{{ video.view_cnt }}</p>
  </div>
</template>

<script>
export default {
  name: "SearchVideoResultRow",
  props: {
    video: Object,
  },
  methods: {
    moveDetail() {
      this.$router.push({ name: "VideoDetail", params: { id: this.video.id } });
    },
  },
};
</script>

<style scoped>
div {
  cursor: pointer;
}
</style>

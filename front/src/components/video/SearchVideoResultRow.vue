<template>
  <div class="row" @click="moveDetail">
    <img
      :src="`https://img.youtube.com/vi/${video.v_id}/0.jpg`"
      width="150px"
      style="display: inline-block"
    />
    <div style="display: inline-block">
      <p>{{ video.title }}</p>
      <p>{{ video.v_writer }}</p>
      <p>{{ video.reg_date }}</p>
      <p>{{ video.category }}</p>
      <p>{{ video.view_cnt }}</p>
      <p>{{ video.favorite_cnt }}</p>
    </div>
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
      this.$router.push({
        name: "VideoDetail",
        params: { id: this.video.v_id },
      });
    },
  },
};
</script>

<style scoped>
div {
  cursor: pointer;
}

.row {
  border: 1px solid black;
}
</style>

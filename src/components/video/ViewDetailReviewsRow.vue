<template>
  <div>
    <div>{{ review.nickname }}</div>
    <div>{{ review.content }}</div>
    <div>{{ review.reg_date }}</div>
    <br />
  </div>
</template>

<script>
export default {
  name: "ViewDetailReviewsRow",
  props: {
    review: Object,
  },
};
</script>

<style></style>

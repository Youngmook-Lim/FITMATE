<template>
  <div class="message-nav">
    <router-link :to="{ name: 'MessageReceived' }">RECEIVED</router-link>
    <router-link :to="{ name: 'MessageSent' }">SENT</router-link>
    <router-link :to="{ name: 'MessageSend', params: { id: 0 } }"
      >✈</router-link
    >
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.message-nav {
  display: flex;
  justify-content: space-around;
  align-items: center;
  padding: 16px;
  background-color: cadetblue;
  margin-top: 20px;
}
</style>

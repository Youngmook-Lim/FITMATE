<template>
  <div class="navdiv">
    <div class="message-nav" id="nav">
      <router-link :to="{ name: 'MessageReceived' }">RECEIVED</router-link>
      <router-link :to="{ name: 'MessageSent' }">SENT</router-link>
      <router-link :to="{ name: 'MessageSend', params: { id: 0 } }"
        >✈</router-link>
    </div>
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

.navdiv{
  display: flex;
  justify-content: center;
}
</style>

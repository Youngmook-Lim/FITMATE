<template>
  <div>
    <header class="header" v-if="showHeader">
      <router-link :to="{ name: 'HomeView' }">FITMATE</router-link>
      <div class="weather-info">
        <div class="weather hot">
          <span class="sun"></span>
          <span class="sunx"></span>
        </div>
        <div class="weather cloudy">
          <span class="cloud"></span>
          <span class="cloudx"></span>
        </div>
        <div class="weather stormy">
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
          <span class="snowe"></span>
          <span class="snowex"></span>
          <span class="stick"></span>
          <span class="stick2"></span>
        </div>
        <div class="weather breezy">
          <ul>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
          </ul>
          <span class="cloudr"></span>
        </div>
        <div class="weather night">
          <span class="moon"></span>
          <span class="spot1"></span>
          <span class="spot2"></span>
          <ul>
            <li></li>
          </ul>
        </div>
      </div>
      <div class="menu-right">
        <span>Welcome {{ myUser.name }}!</span>
        <router-link class="router-link" :to="{ name: 'MessageReceived' }"
          >💌</router-link
        >
        <!-- <router-link
          class="router-link"
          :to="{
            name: 'UserViewMain',
            params: {
              id: myUser.u_id ? myUser.u_id : 0,
            },
          }"
          >🏋️‍♀️</router-link
        > -->
        <button @click="logout">로그아웃</button>
      </div>
    </header>
    <header id="header" class="alt">
      <div class="logoclass">
        <p class="pin"></p>
        <p class="pulse"></p>
      </div>
      <h1>Fit Mate</h1>
      <p>
        Find your exercise mate!<br />
        Working out together makes you healthier.
      </p>
    </header>
  </div>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "TheHeader",
  data() {
    return {
      showHeader: true,
    };
  },
  props: {
    thisIsRegister: String,
  },
  methods: {
    logout() {
      this.$store.commit("CLEAR_ALL");

      // JWT 토큰 지우기
      sessionStorage.removeItem("access-token");
      this.$router.push({ name: "LoginView" });
    },
  },
  computed: {
    ...mapState(["myUser"]),
  },
  created() {
    if (this.thisIsRegister) {
      this.showHeader = false;
    }
  },
};
</script>

<style scoped>
/* 로고 */
.logoclass {
  display: flex;
  justify-content: center;
  padding: 0 0 0 10;
}
.pin {
  display: flex;
  justify-items: center;
  width: 50px;
  height: 50px;
  border-radius: 50% 50% 50% 0;
  background: white;
  opacity: 70%;
  -webkit-transform: rotate(-45deg);
  -moz-transform: rotate(-45deg);
  -o-transform: rotate(-45deg);
  -ms-transform: rotate(-45deg);
  transform: rotate(-45deg);
  left: 50%;
  top: 50%;
  margin: -50px -10px 10px 0px;
  -webkit-animation-name: bounce;
  -moz-animation-name: bounce;
  -o-animation-name: bounce;
  -ms-animation-name: bounce;
  animation-name: bounce;
  -webkit-animation-fill-mode: both;
  -moz-animation-fill-mode: both;
  -o-animation-fill-mode: both;
  -ms-animation-fill-mode: both;
  animation-fill-mode: both;
  -webkit-animation-duration: 1s;
  -moz-animation-duration: 1s;
  -o-animation-duration: 1s;
  -ms-animation-duration: 1s;
  animation-duration: 1s;
}
.pin:after {
  content: "";
  width: 34px;
  height: 34px;
  margin: 8px 0 0 8px;
  background: #969292;
  border-radius: 50%;
}
.pulse {
  display: flex;
  justify-content: center;
  background: rgba(0, 0, 0, 0.2);
  border-radius: 50%;
  height: 24px;
  width: 24px;
  left: 50%;
  top: 50%;
  margin: 13px 0px 0px -26.5px;
  -webkit-transform: rotateX(55deg);
  -moz-transform: rotateX(55deg);
  -o-transform: rotateX(55deg);
  -ms-transform: rotateX(55deg);
  transform: rotateX(55deg);
  z-index: -2;
}
.pulse:after {
  content: "";
  border-radius: 50%;
  height: 50px;
  width: 50px;
  /* position: absolute; */
  margin: -10px -20px -10px -20px;
  -webkit-animation: pulsate 1s ease-out;
  -moz-animation: pulsate 1s ease-out;
  -o-animation: pulsate 1s ease-out;
  -ms-animation: pulsate 1s ease-out;
  animation: pulsate 1s ease-out;
  -webkit-animation-iteration-count: infinite;
  -moz-animation-iteration-count: infinite;
  -o-animation-iteration-count: infinite;
  -ms-animation-iteration-count: infinite;
  animation-iteration-count: infinite;
  opacity: 0;
  -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
  filter: alpha(opacity=0);
  -webkit-box-shadow: 0 0 1px 2px #89849b;
  box-shadow: 0 0 1px 2px #89849b;
  -webkit-animation-delay: 1.1s;
  -moz-animation-delay: 1.1s;
  -o-animation-delay: 1.1s;
  -ms-animation-delay: 1.1s;
  animation-delay: 1.1s;
}
@-moz-keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}
@-webkit-keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}
@-o-keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}
@keyframes pulsate {
  0% {
    -webkit-transform: scale(0.1, 0.1);
    -moz-transform: scale(0.1, 0.1);
    -o-transform: scale(0.1, 0.1);
    -ms-transform: scale(0.1, 0.1);
    transform: scale(0.1, 0.1);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
  50% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
  }
  100% {
    -webkit-transform: scale(1.2, 1.2);
    -moz-transform: scale(1.2, 1.2);
    -o-transform: scale(1.2, 1.2);
    -ms-transform: scale(1.2, 1.2);
    transform: scale(1.2, 1.2);
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
  }
}
@-moz-keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}
@-webkit-keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}
@-o-keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}
@keyframes bounce {
  0% {
    opacity: 0;
    -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";
    filter: alpha(opacity=0);
    -webkit-transform: translateY(-2000px) rotate(-45deg);
    -moz-transform: translateY(-2000px) rotate(-45deg);
    -o-transform: translateY(-2000px) rotate(-45deg);
    -ms-transform: translateY(-2000px) rotate(-45deg);
    transform: translateY(-2000px) rotate(-45deg);
  }
  60% {
    opacity: 1;
    -ms-filter: none;
    filter: none;
    -webkit-transform: translateY(30px) rotate(-45deg);
    -moz-transform: translateY(30px) rotate(-45deg);
    -o-transform: translateY(30px) rotate(-45deg);
    -ms-transform: translateY(30px) rotate(-45deg);
    transform: translateY(30px) rotate(-45deg);
  }
  80% {
    -webkit-transform: translateY(-10px) rotate(-45deg);
    -moz-transform: translateY(-10px) rotate(-45deg);
    -o-transform: translateY(-10px) rotate(-45deg);
    -ms-transform: translateY(-10px) rotate(-45deg);
    transform: translateY(-10px) rotate(-45deg);
  }
  100% {
    -webkit-transform: translateY(0) rotate(-45deg);
    -moz-transform: translateY(0) rotate(-45deg);
    -o-transform: translateY(0) rotate(-45deg);
    -ms-transform: translateY(0) rotate(-45deg);
    transform: translateY(0) rotate(-45deg);
  }
}

.header {
  display: flex;
  justify-content: space-between;
  padding: 2%;
}

#header.alt {
  padding: 10% 0 0 0;
}

.menu-right {
  display: flex;
  gap: 16px;
}

/* 날씨정보 */
@import url("https://fonts.googleapis.com/css?family=Raleway");
body {
  background: #fff;
}

.weather-info {
  display: flex;
  gap: 16px;
}

.weather {
  position: relative;
  width: 50px;
  height: 50px;
  border-radius: 40%;
  z-index: 120;
}

.hot {
  /* position: absolute;
  top: 25%;
  left: 20%; */
  background: linear-gradient(
    to top right,
    rgba(255, 87, 34, 1) 0%,
    rgba(251, 140, 0, 1) 100%
  );
  box-shadow: 1px 1px 30px rgba(255, 111, 0, 1);
}
.sun {
  position: absolute;
  top: -10%;
  left: 65%;
  width: 25px;
  height: 25px;
  border-radius: 100%;
  background: linear-gradient(
    to bottom left,
    rgba(255, 235, 59, 1) 0%,
    rgba(249, 168, 37, 1) 90%
  );
  box-shadow: 1px 1px 30px rgba(255, 160, 0, 1);
  animation: inex 3s infinite linear;
}

.sunx {
  position: absolute;
  top: 30%;
  left: 45%;
  width: 5px;
  height: 5px;
  border-radius: 100%;
  background-color: #fff;
  opacity: 0.2;
}
.cloudy {
  /* position: absolute;
  top: 25%;
  left: 45%; */
  background: linear-gradient(
    to top right,
    rgba(63, 81, 181, 1) 0%,
    rgba(3, 155, 229, 1) 40%
  );
  box-shadow: 1px 1px 30px rgba(2, 119, 189, 1);
}
.cloud {
  position: absolute;
  top: 5%;
  left: 70%;
  width: 30px;
  height: 15px;
  border-radius: 10px;
  background-color: rgba(129, 212, 250, 1);
  box-shadow: 1px 1px 30px rgba(0, 151, 167, 1);
  animation: move 3s infinite linear;
}
.cloudx {
  position: absolute;
  top: 23%;
  left: 55%;
  width: 20px;
  height: 10px;
  border-radius: 10px;
  background-color: rgba(129, 212, 250, 1);
  animation: move 3s infinite linear;
}
.stormy {
  /* position: absolute;
  top: 25%;
  left: 70%; */
  background: linear-gradient(
    to top right,
    rgba(117, 117, 117, 1) 0%,
    rgba(33, 33, 33, 1) 90%
  );
  box-shadow: 1px 1px 30px rgba(33, 33, 33, 1);
}
.stormy li {
  position: absolute;
  list-style: none;
  width: 1px;
  height: 6px;
  border-radius: 50%;
  background-color: #777;
  box-shadow: 1px 1px 30px #555;
  animation: fall 3s infinite linear;
  opacity: 0;
}
.stormy li:nth-child(1) {
  top: 0%;
  left: 100%;
}

.stormy li:nth-child(2) {
  top: 5%;
  left: 90%;
}

.stormy li:nth-child(3) {
  top: -10%;
  left: 80%;
  animation-delay: 2s;
}

.stormy li:nth-child(4) {
  top: 10%;
  left: 100%;
  animation-delay: 2s;
}

.stormy li:nth-child(5) {
  top: 20%;
  left: 80%;
  animation-delay: 0.5s;
}

.stormy li:nth-child(6) {
  top: 35%;
  left: 70%;
  background-color: #bbb;
  animation-delay: 0.5s;
}

.stormy li:nth-child(7) {
  top: 23%;
  left: 60%;
  background-color: #bbb;
  animation-delay: 0.8s;
}

.stormy li:nth-child(8) {
  top: 5%;
  left: 70%;
  background-color: #bbb;
  animation-delay: 0.8s;
}
.snowe {
  position: absolute;
  top: 60%;
  left: 40%;
  width: 20px;
  height: 20px;
  border-radius: 60px;
  background-color: #ddd;
}
.snowex {
  position: absolute;
  top: 50%;
  left: 50%;
  width: 10px;
  height: 10px;
  border-radius: 15px;
  background-color: #bbb;
}
.stick {
  position: absolute;
  top: 60%;
  left: 40%;
  width: 3px;
  height: 15px;
  transform: rotate(-45deg);
  background-color: #333;
  z-index: -10;
}
.stick2 {
  position: absolute;
  top: 60%;
  left: 75%;
  width: 3px;
  height: 15px;
  transform: rotate(45deg);
  background-color: #333;
  z-index: -10;
}
.breezy {
  /* position: absolute;
  top: 60%;
  left: 30%; */
  background: linear-gradient(
    to top right,
    rgba(156, 204, 101, 1) 0%,
    rgba(38, 198, 218, 1) 50%
  );
  box-shadow: 1px 1px 30px rgba(38, 198, 218, 1);
}
.cloudr {
  position: absolute;
  top: 5%;
  left: 60%;
  width: 40px;
  height: 15px;
  border-radius: 10px;
  background-color: rgba(96, 125, 139, 1);
  box-shadow: 1px 1px 30px rgba(84, 110, 122, 1);
  animation: flash 1.5s infinite linear;
}

.breezy li {
  position: absolute;
  list-style: none;
  width: 5px;
  height: 2px;
  border-radius: 10%;
  background-color: #eee;
  opacity: 0;
  animation: meteor 3s infinite linear;
  transform: rotate(-60deg);
}
.breezy li:nth-child(1) {
  top: 10%;
  left: 100%;
}

.breezy li:nth-child(2) {
  top: 5%;
  left: 110%;
  animation-delay: 0.3s;
}

.breezy li:nth-child(3) {
  top: 4%;
  left: 125%;
  animation-delay: 0.6s;
}

.breezy li:nth-child(4) {
  top: 15%;
  left: 150%;
  animation-delay: 1s;
}

.breezy li:nth-child(5) {
  top: 10%;
  left: 140%;
}

.night {
  /* position: absolute;
  top: 60%;
  left: 60%; */
  background: linear-gradient(
    to bottom right,
    rgba(63, 81, 181, 1) 0%,
    rgba(171, 71, 188, 1) 70%
  );
  box-shadow: 1px 1px 30px rgba(81, 45, 168, 1);
}
.moon {
  position: absolute;
  top: -10%;
  left: 65%;
  width: 30px;
  height: 30px;
  border-radius: 100%;
  background: rgba(255, 241, 118, 1);
  box-shadow: 1px 1px 30px rgba(224, 224, 224, 1);
}
.spot1 {
  position: absolute;
  top: 0%;
  left: 85%;
  width: 5px;
  height: 5px;
  border-radius: 100%;
  background-color: #777;
}
.spot2 {
  position: absolute;
  top: 30%;
  left: 75%;
  width: 5px;
  height: 5px;
  border-radius: 100%;
  background-color: #777;
}

.night li {
  position: absolute;
  list-style: none;
  width: 3px;
  height: 3px;
  border-radius: 100%;
  background-color: #fff;

  transform: rotate(-45deg);
}
/* .night li:nth-child(1) {
  top: 30%;
  left: 65%;
}

.night li:nth-child(2) {
  top: 35%;
  left: 53%;
} */

.night li:nth-child(1) {
  opacity: 0;
  top: 20%;
  left: 75%;
  width: 2px;
  height: 2px;
  animation: meteor 1.5s infinite linear;
  animation-delay: 0.6s;
}

/* .night li:nth-child(4) {
  top: 5%;
  left: 50%;
} */

.night li:nth-child(2) {
  opacity: 0;
  top: 20%;
  left: 55%;
  width: 1px;
  height: 1px;
  animation: meteor 1.5s infinite linear;
}

@keyframes inex {
  50% {
    opacity: 0.4;
  }
  60% {
    opacity: 1;
  }
}
@keyframes move {
  50% {
    transform: translateX(-10px);
  }
}
@keyframes fall {
  10% {
    opacity: 0.8;
  }

  50% {
    opacity: 1;
    transform: translate(-10px, 30px);
  }
  100% {
    transform: translate(-25px, 70px);
  }
}
@keyframes flash {
  48% {
    background-color: rgba(96, 125, 139, 1);
  }
  50% {
    background-color: #fff;
  }
  55% {
    background-color: rgba(96, 125, 139, 1);
  }
  57% {
    background-color: #fff;
  }
}
@keyframes meteor {
  10% {
    opacity: 1;
  }
  80% {
    left: 10%;
    top: 75%;
    opacity: 0;
  }
}
</style>

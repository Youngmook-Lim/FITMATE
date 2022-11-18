<template>
  <div>
    <div class="title">
      <h2>FIND YOUR FITMATE</h2>
      <div>
        <span>반경 : </span>
        <select v-model="dist">
          <option value="1">1km</option>
          <option value="3">3km</option>
          <option value="5">5km</option>
          <option value="10">10km</option>
        </select>
      </div>
    </div>
    <div id="map"></div>
  </div>
</template>

<script>
// import axios from "../util/http-common.js";
export default {
  name: "MateView",
  computed: {
    myUser() {
      return this.$store.state.myUser;
    },
  },
  watch: {
    dist() {
      this.getNearbyUsers();
    },
  },
  data() {
    return {
      nearbyUsers: {},
      dist: "1",
    };
  },
  methods: {
    initMap() {
      const container = document.getElementById("map");
      // const myPosition = new kakao.maps.LatLng(this.myUser.y, this.myUser.x);
      const myPosition = new kakao.maps.LatLng(33.450701, 126.570667);

      const options = {
        center: myPosition,
        level: 6,
      };

      this.map = new kakao.maps.Map(container, options);

      for (const jsonUser in this.nearbyUsers) {
        const dist = this.nearbyUsers[jsonUser];
        const user = JSON.parse(jsonUser);
        console.log(user);
        user.y = 33.450701;
        user.x = 126.570667;

        // 마커 표시
        let imgSrc;
        if (user.id === this.myUser.id) {
          imgSrc =
            "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";
        } else if (user.gender === "M") {
          imgSrc = "https://decsnota.sirv.com/pin_blue.png";
        } else {
          imgSrc = "https://decsnota.sirv.com/pin_red.png";
        }

        let imgSize;
        if (user.id === this.myUser.id) {
          imgSize = new kakao.maps.Size(24, 35);
        } else {
          imgSize = new kakao.maps.Size(32, 32);
        }
        // const imgOption = {};
        const markerImage = new kakao.maps.MarkerImage(
          imgSrc,
          imgSize
          // imgOption
        );
        const position = new kakao.maps.LatLng(user.y, user.x);
        const marker = new kakao.maps.Marker({
          position: position,
          image: markerImage,
        });
        marker.setMap(this.map);

        // 커스텀 오버레이(말풍선)
        const content = `
        <div class ="label">
          <span><strong>${user.nickname}</strong></span>
          <br/>
          <span>나와의 거리 : ${dist}km</span>
        </div>
        `;

        const customOverlay = new kakao.maps.CustomOverlay({
          position: position,
          content: content,
          yAnchor: 1.8,
        });
        customOverlay.setMap(this.map);
      }
      this.setMapUi();
    },
    setMapUi() {
      // 지도 유형 (지도, 위성)
      const mapTypeControl = new kakao.maps.MapTypeControl();
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

      // 지도 줌
      const zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },
    getNearbyUsers() {
      // const token = sessionStorage.getItem("access-token");
      // if (token) {
      //   const payload = this.parseJWT(token);
      //   axios
      //     .get(`userApi/detail`, { params: { id: payload.id } })
      //     .then((res) => this.$store.commit("SET_MY_USER", res.data))
      //     .then(() => {
      //       axios({
      //         url: "userApi/findAll",
      //         method: "GET",
      //         params: {
      //           id: this.myUser.u_id,
      //           dist: this.dist,
      //         },
      //       })
      //         .then((res) => {
      //           this.nearbyUsers = res.data;
      //           const myUserJson = JSON.stringify(this.myUser);
      //           this.nearbyUsers[myUserJson] = 0;
      //         })
      //         .then(() => {
      //           if (!window.kakao || !window.kakao.maps) {
      //             const script = document.createElement("script");
      //             script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_JS_API_KEY}`;
      //             /* global kakao */
      //             script.addEventListener("load", () => {
      //               kakao.maps.load(this.initMap);
      //               console.log("만들어짐");
      //             });
      //             document.head.appendChild(script);
      //           } else {
      //             console.log(kakao);
      //             this.initMap();
      //           }
      //         });
      //     });
      // }
    },
  },
  created() {
    if (!sessionStorage.getItem("access-token")) {
      alert("로그아웃 되었습니다 - 로그인 페이지로 이동합니다.");
      this.$router.push("/login");
      return;
    }
  },
  mounted() {
    const myUserJson = JSON.stringify(this.myUser);
    this.nearbyUsers[myUserJson] = 0;

    console.log(this.nearbyUsers);
    if (!window.kakao || !window.kakao.maps) {
      const script = document.createElement("script");
      script.src = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${process.env.VUE_APP_KAKAO_JS_API_KEY}`;

      /* global kakao */
      script.addEventListener("load", () => {
        kakao.maps.load(this.initMap);
        console.log("만들어짐");
      });

      document.head.appendChild(script);
    } else {
      console.log(kakao);
      this.initMap();
    }

    // this.getNearbyUsers();
  },
};
</script>

<style>
#map {
  width: 800px;
  height: 400px;
  margin: 0 auto;
}

.title {
  display: flex;
  width: 800px;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
}

.title select {
  padding: 5px;
}

.label {
  background-color: white;
  padding: 8px 16px;
  border: 1px solid black;
  border-radius: 9px;
}
</style>

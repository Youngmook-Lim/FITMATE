import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    API_URL: "http://localhost:9999/api",
    curUser: {},
    myUser: {},
    myUserFollowers: [],
    videos: [],
    video: {},
    reviews: [],
  },
  getters: {},
  mutations: {
    SET_CUR_USER(state, payload) {
      state.curUser = payload;
    },
    SET_MY_USER(state, payload) {
      state.myUser = payload;
    },
    SET_MY_USER_FOLLOWERS(state, payload) {
      state.myUserFollowers = payload;
    },
    ADD_FOLLOWER(state) {
      state.myUserFollowers.push(state.curUser);
    },
    DELETE_FOLLOWER(state) {
      state.myUserFollowers = state.myUserFollowers.filter(
        (f) => f.u_id !== state.curUser.u_id
      );
    },
    CLEAR_ALL(state) {
      state.curUser = {};
      state.myUser = {};
      state.myUserFollowers = [];
      state.videos = [];
      state.video = {};
      state.reviews = [];
    },
  },
  actions: {},
  modules: {},
});

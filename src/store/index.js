import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    API_URL: "http://localhost:9999/api",
    curUser: {},
    myUser: {},
    videos: [],
    video: {},
    reviews: [],
  },
  getters: {},
  mutations: {},
  actions: {},
  modules: {},
});

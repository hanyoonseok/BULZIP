import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

import qnaStore from "@/store/modules/qnaStore.js";
import userStore from "@/store/modules/userStore.js";
import kakaoStore from "@/store/modules/kakaoStore.js";

const store = new Vuex.Store({
  modules: {
    qnaStore,
    userStore,
    kakaoStore,
  },
  plugins: [
    createPersistedState({
      storage: sessionStorage,
    }),
  ],
});

export default store;

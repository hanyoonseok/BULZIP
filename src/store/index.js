import Vue from "vue";
import Vuex from "vuex";
import http from "@/api/http.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    comment: {},
    comments: [],
    // qna: {},
    // qnas: [],
  },
  getters: {},
  mutations: {
    // GET_QNAS(state, payload) {
    //   state.qnas = payload;
    // },
    // GET_QNA(state, payload) {
    //   state.qna = payload;
    // },
    GET_COMMENTS(state, payload) {
      state.comments = payload;
    },
    GET_COMMENT(state, payload) {
      state.comment = payload;
    },
  },

  actions: {
    getComments(context, payload) {
      // 파라미터 p도 넘어와야 함.
      http
        .get(`/qna/reply/list/` + payload[1] + `/` + payload[0])
        .then((resp) => {
          console.log(resp);
          context.commit("GET_COMMENTS", resp.data.qnaReplyList);
        });
    },
    getComment(context, payload) {
      http.get(`/qna/reply/detail/` + payload).then((resp) => {
        console.log(resp);
        context.commit("GET_COMMENT", resp.data);
      });
    },
    // getQnas(context, payload) {
    //   http.get(`/qna/list`).then((resp) => {
    //     console.log(resp, payload);
    //     context.commit("GET_QNAS", resp.data); //다음 진행 단계(mutation)에 결과물 보고
    //   });
    // },
    // getQna(context, payload) {
    //   http.get(`/qna/detail/` + payload).then((resp) => {
    //     console.log(resp);
    //     context.commit("GET_QNA", resp.data);
    //   });
    // },
  },
  modules: {},
});

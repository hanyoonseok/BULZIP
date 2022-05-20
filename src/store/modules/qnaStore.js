import http from "@/api/http.js";

const qnaStore = {
  namespaced: true,
  state: {
    comment: {},
    comments: [],
  },
  getters: {},
  mutations: {
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
  },
};

export default qnaStore;

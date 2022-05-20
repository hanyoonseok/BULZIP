<template>
  <div class="qna-list-container">
    <QnaRow v-for="(item, index) in items" :key="index" :qna="item" />
  </div>
</template>

<script>
import http from "@/api/http.js";
import QnaRow from "@/components/QnaBoard/item/QnaRow.vue";

export default {
  data() {
    return {
      page: 1,
      pages: "",
      items: [],
    };
  },
  created() {
    this.getList(this.page);
  },
  methods: {
    getList(page = "1") {
      http.get(`/qna/list/${page}`).then((resp) => {
        this.items = resp.data.qnaList; // service에서 map에 저장된 이름으로 호출
        console.log(this.items);
        let el = "";
        if (resp.data.startPage > 1) {
          el += `<a href="#" class="pageList" @click="getList(${
            resp.data.startPage - 1
          })">[이전]</a>`;
        }
        for (let i = resp.data.startPage; i <= resp.data.endPage; i++) {
          el += `<a href="#" class="pageList" @click="getList(${i})">[${i}]</a>`;
        }

        if (resp.data.endPage < resp.data.totalPage) {
          el += `<a href="#" class="pageList" @click="getList(${
            resp.data.endPage + 1
          })">[다음]</a>`;
        }
        this.pages = el;
      });
      this.page = page;
    },
    deleteQna(qna_id) {
      const loginInfo = JSON.parse(localStorage.getItem("loginInfo"));
      if (loginInfo.role === 1) {
        http.get(`/qna/delete/${qna_id}`).then((resp) => {
          alert("삭제되었습니다");
          console.log(resp);
          this.getList();
        });
      }
    },
    goDetail(qna_id) {
      this.$router.push(`/qna/detail/1/${qna_id}`);
    },
  },
  components: {
    QnaRow,
  },
};
</script>

<style scoped src="@/css/qna.css"></style>

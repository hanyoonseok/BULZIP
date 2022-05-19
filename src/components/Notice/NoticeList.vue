<template>
  <div class="notice-list-container">
    <NoticeRow v-for="(item, index) in items" :key="index" :item="item" />
  </div>
</template>

<script>
import http from "@/api/http.js";
import NoticeRow from "@/components/Notice/item/NoticeRow.vue";

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
      http.get(`/notice/list/${page}`).then((resp) => {
        console.log(resp);
        this.items = resp.data.noticeList;
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
  },
  components: {
    NoticeRow,
  },
};
</script>
<style scoped src="@/css/notice.css"></style>

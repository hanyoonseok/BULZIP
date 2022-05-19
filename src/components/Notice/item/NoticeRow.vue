<template>
  <div class="notice-item" @click="toggleItem">
    <div class="notice-item-intro">
      <font-awesome-icon
        :icon="isOpen ? 'angle-down' : 'angle-right'"
        class="notice-item-logo"
      ></font-awesome-icon>
      <label class="notice-item-title vertical-center">{{
        item.notice_title
      }}</label>
      <label class="notice-item-date vertical-center">{{
        item.notice_date
      }}</label>
    </div>

    <p v-if="isOpen">
      <b
        >{{ item.notice_writer
        }}<button
          class="reply-btn"
          v-if="this.loginInfo.role === 1"
          @click="deleteNotice"
        >
          삭제</button
        ><button
          class="reply-btn"
          v-if="this.loginInfo.role === 1"
          @click.stop="goDetail"
        >
          수정
        </button> </b
      ><br />
      <span v-text="content"></span>
    </p>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      isOpen: false,
      loginInfo: null,
    };
  },
  created() {
    this.loginInfo = JSON.parse(localStorage.getItem("loginInfo"));
    this.title = this.item.notice_title;
    this.content = this.item.notice_content;
  },
  methods: {
    toggleItem() {
      this.isOpen = !this.isOpen;
    },
    deleteNotice() {
      const loginInfo = JSON.parse(localStorage.getItem("loginInfo"));
      if (loginInfo.role === 1) {
        http.get(`/notice/delete/${this.item.notice_no}`).then((resp) => {
          alert("삭제되었습니다");
          console.log(resp);
          location.href = "/notice/list";
        });
      }
    },
    goDetail() {
      this.$router.push(`/notice/detail/${this.item.notice_no}`);
    },
  },
  props: {
    item: Object,
  },
};
</script>
<style scoped src="@/css/notice.css"></style>

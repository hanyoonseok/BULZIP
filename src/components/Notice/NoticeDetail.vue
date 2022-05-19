<template>
  <div class="notice-list-container">
    <div class="notice-item">
      <div class="notice-item-intro">
        <font-awesome-icon
          :icon="'angle-down'"
          class="notice-item-logo"
        ></font-awesome-icon>
      </div>
      <p>
        <input type="text" v-model="notice_title" class="notice-input" />
        <b>{{ notice_writer }}</b
        ><br />
        <textarea v-model="notice_content" class="notice-textarea"></textarea>
        <button @click="updateNotice" class="reply-btn" style="float: right">
          수정
        </button>
      </p>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      notice_no: "",
      notice_writer: "",
      notice_date: "",
      notice_content: "",
      notice_title: "",
      user_id: "",
    };
  },
  created() {
    const noticeNo = this.$route.params.notice_no;
    http.get(`/notice/detail/${noticeNo}`).then((resp) => {
      const notice = resp.data;
      this.notice_no = notice.notice_no;
      this.notice_writer = notice.notice_writer;
      this.notice_date = notice.notice_date;
      this.notice_content = notice.notice_content;
      this.notice_title = notice.notice_title;
      this.user_id = notice.user_id;
    });
  },
  methods: {
    updateNotice() {
      http
        .put("/notice/update", {
          notice_no: this.notice_no,
          notice_writer: this.notice_writer,
          notice_date: this.notice_date,
          notice_content: this.notice_content,
          notice_title: this.notice_title,
          user_id: this.user_id,
        })
        .then((resp) => {
          if (resp.data === "success") {
            alert("수정되었습니다.");
            this.$router.push("/notice/list");
          }
        });
    },
  },
};
</script>

<style scoped src="@/css/notice.css"></style>

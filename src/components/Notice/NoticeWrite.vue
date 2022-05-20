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
        <input type="text" v-model="title" class="notice-input" />
        <b>운영자</b><br />
        <textarea v-model="content" class="notice-textarea"></textarea>
        <button @click="createNotice" class="reply-btn" style="float: right">
          작성
        </button>
      </p>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";
import { mapState } from "vuex";

export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
  },
  methods: {
    createNotice() {
      http
        .post(`/notice/write`, {
          notice_title: this.title,
          notice_content: this.content,
          notice_writer: "운영자",
          user_id: this.userInfo.id,
        })
        .then((resp) => {
          console.log(resp);
          alert("작성이 완료되었습니다.");
          this.$router.push("/notice/list");
        });
    },
  },
};
</script>

<style scoped src="@/css/notice.css"></style>

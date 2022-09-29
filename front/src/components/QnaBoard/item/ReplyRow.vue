<template>
  <div class="reply-form-container" @click.stop="">
    <b class="vertical-center">{{ reply.user_id }}</b>
    <div class="vertical-center">
      <img src="@/assets/reply.png" />
      <label v-if="!isOpen">
        {{ content }}
      </label>
      <textarea
        class="qna-textarea"
        style="height: 100px"
        v-if="isOpen"
        v-model="content"
      ></textarea>
      <button class="reply-btn" v-if="isOpen" @click="updateReply">수정</button>
    </div>
    <div v-if="!isOpen">
      <button
        class="reply-btn"
        v-if="reply.user_id === userInfo.userId"
        @click="deleteReply"
      >
        삭제
      </button>
      <button
        class="reply-btn"
        v-if="reply.user_id === userInfo.userId"
        @click="isOpen = !isOpen"
      >
        수정
      </button>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";
export default {
  data() {
    return {
      isOpen: false,
      content: "",
    };
  },
  props: {
    reply: Object,
    userInfo: Object,
  },
  created() {
    console.log("receive reply row", this.reply);
    this.content = this.reply.comment_content;
  },
  methods: {
    deleteReply() {
      console.log(this.reply.comment_id);
      http.delete(`/qna/reply/delete/${this.reply.comment_id}`).then((resp) => {
        if (resp.data === "success") {
          alert("삭제되었습니다.");
          this.$emit("getNew");
        }
      });
    },
    updateReply() {
      http
        .put(`/qna/reply/update`, {
          ...this.reply,
          comment_content: this.content,
        })
        .then((resp) => {
          if (resp.data === "success") {
            alert("수정되었습니다.");
            this.$emit("getNew");
            this.isOpen = false;
          }
        });
    },
  },
};
</script>

<style scoped src="@/css/qna.css"></style>

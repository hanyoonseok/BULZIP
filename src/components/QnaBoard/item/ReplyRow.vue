<template>
  <div class="reply-form-container">
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
        @click.stop=""
      ></textarea>
      <button class="reply-btn" v-if="isOpen" @click.stop="updateReply">
        수정
      </button>
    </div>
    <div v-if="!isOpen">
      <button
        class="reply-btn"
        v-if="reply.user_id === userInfo.userId"
        @click.stop="isOpen = !isOpen"
      >
        수정
      </button>
      <button
        class="reply-btn"
        v-if="reply.user_id === userInfo.userId"
        @click.stop="deleteReply"
      >
        삭제
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
    paramInfo: [],
  },
  created() {
    this.content = this.reply.comment_content;
  },
  methods: {
    deleteReply() {
      http.delete(`/qna/reply/delete/${this.reply.comment_id}`).then((resp) => {
        if (resp.data === "success") {
          alert("삭제되었습니다.");
          this.$emit("getNew", this.paramInfo);
        }
      });
    },
    updateReply() {
      console.log({
        ...this.reply,
        comment_content: this.content,
      });
      http
        .put(`/qna/reply/update`, {
          ...this.reply,
          comment_content: this.content,
        })
        .then((resp) => {
          if (resp.data === "success") {
            alert("수정되었습니다.");
            this.$emit("getNew", this.paramInfo);
            this.isOpen = false;
          }
        });
    },
  },
};
</script>

<style scoped src="@/css/qna.css"></style>

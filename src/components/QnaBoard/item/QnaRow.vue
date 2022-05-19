<template>
  <div class="qna-item" @click="toggleItem">
    <div class="qna-item-intro">
      <font-awesome-icon
        :icon="isOpen ? 'angle-down' : 'angle-right'"
        class="qna-item-logo"
      ></font-awesome-icon>
      <label class="qna-item-title vertical-center">{{ qna.qna_title }}</label>
      <label class="qna-item-date vertical-center">{{ qna.qna_date }}</label>
    </div>
    <div class="qna-detail-container" v-if="isOpen">
      <p>
        <b
          >{{ qna.user_id
          }}<button
            class="reply-btn"
            v-if="this.loginInfo.userId === qna.user_id"
            @click="deleteQna(qna.qna_id)"
          >
            삭제</button
          ><button
            class="reply-btn"
            v-if="this.loginInfo.userId === qna.user_id"
            @click.stop="goDetail(qna.qna_id)"
          >
            수정
          </button></b
        ><br />
        {{ qna.qna_content }}
      </p>
      <div class="qna-item-reply-container">
        <div class="vertical-center">
          <textarea
            class="qna-textarea"
            style="height: 100px"
            @click.stop=""
            v-model="content"
          ></textarea>
          <button class="reply-btn" @click.stop="createReply">작성</button>
        </div>
        <div
          class="reply-form-container"
          v-for="(item, i) in comments"
          :key="i"
        >
          <b class="vertical-center">{{ item.user_id }}</b>
          <div class="vertical-center">
            <img src="@/assets/reply.png" />
            <label>
              {{ item.comment_content }}
            </label>
          </div>
          <div>
            <button class="reply-btn" v-if="item.user_id === loginInfo.userId">
              수정
            </button>
            <button
              class="reply-btn"
              v-if="item.user_id === loginInfo.userId"
              @click.stop="deleteReply(item.comment_id)"
            >
              삭제
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";
// import comment from "@/api/comment.json";

export default {
  data() {
    return {
      isOpen: false,
      loginInfo: null,
      content: "",
    };
  },
  props: {
    qna: Object,
  },
  created() {
    const qna_id = this.qna.qna_id;
    const p = 1;
    const temp = [qna_id, p];
    this.$store.dispatch("getComments", temp);

    this.loginInfo = JSON.parse(localStorage.getItem("loginInfo"));
  },
  methods: {
    toggleItem() {
      this.isOpen = !this.isOpen;
    },
    createReply() {
      http
        .post(`/qna/reply/insert`, {
          qna_id: this.qna.qna_id,
          user_id: this.loginInfo.userId,
          comment_content: this.content,
          comment_date: new Date(),
        })
        .then((resp) => {
          if (resp.data === "success") {
            this.isOpen = false;
          }
        });
    },
    deleteQna(qna_id) {
      http.get(`/qna/delete/${qna_id}`).then((resp) => {
        alert("삭제되었습니다");
        console.log(resp);
        location.href = "/qna/list";
      });
    },
    goDetail(qna_id) {
      this.$router.push(`/qna/detail/1/${qna_id}`);
    },
    deleteReply(no) {
      http.delete(`/qna/reply/delete/${no}`).then((resp) => {
        if (resp.data === "success") {
          alert("삭제되었습니다.");
        }
      });
    },
  },
  computed: {
    comments() {
      return this.$store.state.comments;
    },
  },
};
</script>

<style scoped src="@/css/qna.css"></style>

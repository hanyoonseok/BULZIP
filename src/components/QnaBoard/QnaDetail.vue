<template>
  <div class="qna-list-container">
    <div class="qna-item">
      <div class="qna-item-intro">
        <font-awesome-icon
          :icon="'angle-down'"
          class="qna-item-logo"
        ></font-awesome-icon>
      </div>
      <p>
        <input type="text" v-model="qna_title" class="qna-input" />
        <b>{{ qna_writer }}</b
        ><br />
        <textarea v-model="qna_content" class="qna-textarea"></textarea>
        <button
          @click="updateQna"
          class="reply-btn"
          style="float: right; margin-top: 20px"
        >
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
      qna_id: "",
      qna_title: "",
      qna_content: "",
      user_id: "",
      qna_date: "",
    };
  },
  created() {
    const qna_id = this.$route.params.qna_id;
    const p = this.$route.params.p;
    const temp = [qna_id, p];
    console.log(temp);
    http.get(`/qna/detail/${qna_id}`).then((resp) => {
      console.log(resp.data);
      const qna = resp.data;
      this.qna_id = qna.qna_id;
      this.qna_title = qna.qna_title;
      this.qna_content = qna.qna_content;
      this.user_id = qna.user_id;
      this.qna_date = qna.qna_date;
    });
  },
  methods: {
    updateQna() {
      http
        .put("/qna/update", {
          qna_id: this.qna_id,
          qna_title: this.qna_title,
          qna_content: this.qna_content,
          user_id: this.user_id,
          qna_date: new Date(),
        })
        .then((resp) => {
          if (resp.data === "success") {
            alert("수정되었습니다.");
            this.$router.push("/qna/list");
          }
        });
    },
    UpdateReply() {
      http
        .put(`/qna/reply/update`, {
          comment_id: this.active,
          qna_id: this.qna_id,
          user_id: this.loginInfo.userId,
          comment_content: this.updateComment,
          comment_date: new Date(),
        })
        .then((resp) => {
          if (resp.data === "success") {
            alert("수정되었습니다.");
            const qna_id = this.$route.params.qna_id;
            const p = this.$route.params.p;
            const temp = [qna_id, p];
            this.$store.dispatch("getComments", temp);
            this.updateComment = "";
            this.active = -1;
          }
        });
    },
    InsertReply() {
      console.log(this.loginInfo);
      http
        .post(`/qna/reply/insert`, {
          qna_id: this.qna_id,
          user_id: this.loginInfo.userId,
          comment_content: this.comment,
          comment_date: new Date(),
        })
        .then((resp) => {
          if (resp.data === "success") {
            alert("등록되었습니다.");
            const qna_id = this.$route.params.qna_id;
            const p = this.$route.params.p;
            const temp = [qna_id, p];
            this.$store.dispatch("getComments", temp);
            this.comment = "";
          }
        });
    },
  },
  computed: {
    // book() {
    //   return this.$store.state.qna;
    // },
    comments() {
      return this.$store.state.comments;
    },
  },
};
</script>

<style scoped src="@/css/qna.css"></style>

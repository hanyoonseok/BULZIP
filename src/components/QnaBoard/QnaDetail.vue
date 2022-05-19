<template>
  <div>
    번호 <br />
    <input type="text" v-model="qna_id" disabled /> <br />
    글 제목 <br />
    <input type="text" v-model="qna_title" /> <br />
    글 내용 <br />
    <input type="text" v-model="qna_content" /><br />
    아이디 <br />
    <input type="text" v-model="user_id" disabled /> <br />
    날짜 <br />
    <input type="text" v-model="qna_date" disabled /><br />
    <button @click="updateQna">수정하기</button>

    <div>
      <h3>댓글</h3>
      <input type="text" v-model="comment" />
      <button @click="InsertReply">등록</button>
      <li v-for="(comment, i) in comments" :key="i">
        <b>{{ comment.user_id }}</b> : {{ comment.comment_content }}
        <button
          v-if="loginInfo.userId === comment.user_id"
          @click="toggleActive(comment.comment_id)"
        >
          수정|
        </button>
        <button
          v-if="loginInfo.userId === comment.user_id"
          @click="DeleteReply(comment.comment_id)"
        >
          삭제
        </button>
        <!--날짜를 어떻게 사용할지 고민-->
      </li>
      <div v-if="active > 0">
        <input type="text" v-model="updateComment" />
        <button @click="UpdateReply">수정</button>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      loginInfo: null,
      comment: "",
      updateComment: "",
      qna_id: "",
      qna_title: "",
      qna_content: "",
      user_id: "",
      user_password: "",
      qna_date: "",
      active: -1,
    };
  },
  created() {
    this.loginInfo = JSON.parse(localStorage.getItem("loginInfo"));
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
      this.user_password = qna.user_password;
      this.qna_date = qna.qna_date;
    });
    // 댓글 들 가져오기
    this.$store.dispatch("getComments", temp);
  },
  methods: {
    toggleActive(comment_id) {
      this.active = comment_id;
    },
    updateQna() {
      http
        .put("/qna/update", {
          qna_id: this.qna_id,
          qna_title: this.qna_title,
          qna_content: this.qna_content,
          user_id: this.user_id,
          user_password: this.user_password,
          qna_date: this.qna_date,
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
    DeleteReply(no) {
      http.delete(`/qna/reply/delete/${no}`).then((resp) => {
        if (resp.data === "success") {
          alert("삭제되었습니다.");
          const qna_id = this.$route.params.qna_id;
          const p = this.$route.params.p;
          const temp = [qna_id, p];
          this.$store.dispatch("getComments", temp);
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

<style></style>

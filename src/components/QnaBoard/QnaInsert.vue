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
        <input
          type="text"
          v-model="title"
          class="qna-input"
          placeholder="제목"
        />
        <b>{{ loginInfo.userId }}</b
        ><br />
        <textarea
          v-model="content"
          class="qna-textarea"
          placeholder="내용"
        ></textarea>
        <button
          @click="createQna"
          class="reply-btn"
          style="float: right; margin-top: 20px"
        >
          작성
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
      title: "",
      content: "",
      loginInfo: {
        user_id: "",
      },
    };
  },
  created() {
    const loginInfo = JSON.parse(localStorage.getItem("loginInfo"));
    this.loginInfo = loginInfo;
  },
  methods: {
    createQna() {
      http
        .post(`/qna/insert`, {
          qna_title: this.title,
          qna_content: this.content,
          user_id: this.loginInfo.userId,
          user_password: this.loginInfo.password,
          qna_date: new Date(),
        })
        .then((resp) => {
          console.log(resp);
          alert("작성이 완료되었습니다.");
          this.$router.push("/qna/list");
        });
    },
  },
};
</script>
<style scoped src="@/css/qna.css"></style>

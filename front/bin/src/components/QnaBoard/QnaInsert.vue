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
        <b>{{ userInfo.userId }}</b
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
import { mapState } from "vuex";

export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  methods: {
    createQna() {
      http
        .post(`/qna/insert`, {
          qna_title: this.title,
          qna_content: this.content,
          user_id: this.userInfo.userId,
          user_password: this.userInfo.password,
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

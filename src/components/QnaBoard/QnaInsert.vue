<template>
  <div>
    글 제목 <br />
    <input type="text" id="qna_title" v-model="title" /> <br />
    글 내용 <br />
    <input type="text" id="qna_content" v-model="content" /><br />
    <button @click="createQna">글쓰기</button>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    createQna() {
      const loginInfo = JSON.parse(localStorage.getItem("loginInfo"));

      http
        .post(`/qna/insert`, {
          qna_title: this.title,
          qna_content: this.content,
          user_id: loginInfo.id,
          user_password: loginInfo.password,
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
<style></style>

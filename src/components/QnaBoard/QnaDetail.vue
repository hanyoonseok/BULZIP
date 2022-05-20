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
          v-model="qna.qna_title"
          class="qna-input"
          placeholder="제목"
        />
        <b>{{ qna.qna_writer }}</b
        ><br />
        <textarea
          v-model="qna.qna_content"
          class="qna-textarea"
          placeholder="내용"
        ></textarea>
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
      qna: {
        qna_id: "",
        qna_title: "",
        qna_content: "",
        user_id: "",
        qna_date: "",
      },
    };
  },
  created() {
    const qna_id = this.$route.params.qna_id;

    http.get(`/qna/detail/${qna_id}`).then((resp) => {
      console.log(resp.data);
      this.qna = resp.data;
    });
  },
  methods: {
    updateQna() {
      http.put("/qna/update", this.qna).then((resp) => {
        if (resp.data === "success") {
          alert("수정되었습니다.");
          this.$router.push("/qna/list");
        }
      });
    },
  },
};
</script>

<style scoped src="@/css/qna.css"></style>

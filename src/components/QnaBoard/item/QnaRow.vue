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
    <div class="qna-detail-container" v-if="isOpen" @click.stop="">
      <p>
        <b
          >{{ qna.user_id
          }}<button
            class="reply-btn"
            v-if="this.userInfo.userId === qna.user_id"
            @click="deleteQna()"
          >
            삭제</button
          ><button
            class="reply-btn"
            v-if="this.userInfo.userId === qna.user_id"
            @click="goDetail()"
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
            v-model="content"
          ></textarea>
          <button class="reply-btn" @click="createReply">작성</button>
        </div>
        <ReplyRow
          v-for="(item, i) in comments"
          :key="i"
          :reply="item"
          :userInfo="userInfo"
          @getNew="getComments"
        />
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";
import { mapState } from "vuex";
import ReplyRow from "@/components/QnaBoard/item/ReplyRow.vue";

export default {
  data() {
    return {
      isOpen: false,
      content: "",
      comments: [],
      paramInfo: [1, this.qna.qna_id],
    };
  },
  created() {
    this.getComments();
  },
  props: {
    qna: Object,
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
  },
  methods: {
    getComments() {
      this.comments = [];
      http
        .get(`/qna/reply/list/` + this.paramInfo[0] + `/` + this.paramInfo[1])
        .then((resp) => {
          this.comments = resp.data.qnaReplyList;
        });
    },
    toggleItem() {
      this.isOpen = !this.isOpen;
    },
    createReply() {
      http
        .post(`/qna/reply/insert`, {
          qna_id: this.qna.qna_id,
          user_id: this.userInfo.userId,
          comment_content: this.content,
          comment_date: new Date(),
        })
        .then((resp) => {
          if (resp.data === "success") {
            this.getComments();
            this.content = "";
          }
        });
    },
    deleteQna() {
      http.get(`/qna/delete/${this.qna.qna_id}`).then((resp) => {
        alert("삭제되었습니다");
        console.log(resp);
        this.getComments();
      });
    },
    goDetail() {
      this.$router.push(`/qna/detail/1/${this.qna.qna_id}`);
    },
  },
  components: {
    ReplyRow,
  },
};
</script>

<style scoped src="@/css/qna.css"></style>

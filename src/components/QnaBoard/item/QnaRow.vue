<template>
  <div class="qna-item">
    <!-- <div class="qna-item-intro">
      <i class="fa-solid fa-angle-down qna-item-logo"></i>
      <label class="qna-item-title vertical-center">{{title}}</label>
      <label class="qna-item-date vertical-center">{{date}}</label>
    </div>
    <div class="qna-detail-container">
      <p>
        <b>{{writer}}</b><br />
        {{content}}
      </p>
      <div class="qna-item-reply-container">
        <div class="reply-form-container" v-for="(item, i) in items" :key="i">
          <b class="vertical-center">{{item.writer}}</b>
          <div class="vertical-center">
            <img src="@/assets/reply.png" />
            <label>
              {{item.content}}
            </label>
          </div>
          <div>
            <button class="reply-btn">수정</button>
            <button class="reply-btn">삭제</button>
          </div>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
export default {
  props: {
    qna: Object,
  },
};
</script>

<style></style>

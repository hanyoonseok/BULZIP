<template>
  <div class="page-container">
    <h1>
      QnA 게시판
      <div>
        <router-link to="/qna/insert" class="qna-router"
          ><font-awesome-icon icon="pen"></font-awesome-icon
        ></router-link>
        <router-link to="/qna/list" class="qna-router"
          ><font-awesome-icon icon="list"></font-awesome-icon
        ></router-link>
      </div>
    </h1>

    <router-view></router-view>
  </div>
</template>

<script>
export default {};
</script>

<style scoped src="@/css/qna.css"></style>

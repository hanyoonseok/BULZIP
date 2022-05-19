<template>
  <div class="page-container">
    <h1>
      공지사항
      <div>
        <router-link to="/notice/write" class="notice-router"
          ><font-awesome-icon icon="pen"></font-awesome-icon
        ></router-link>
        <router-link to="/notice/list" class="notice-router"
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

<style>
@import "@/css/notice.css";
</style>

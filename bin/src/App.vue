<template>
  <div id="app">
    <header-nav></header-nav>
    <router-view />
  </div>
</template>

<script>
import HeaderNav from "@/components/HeaderNav.vue"; //@ 는 src 까지의 경로이다.

export default {
  name: "App",
  components: { HeaderNav },
};
</script>

<style>
@import "@/css/global.css";
</style>

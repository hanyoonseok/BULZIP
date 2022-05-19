<template>
  <header id="main-header">
    <section class="header-top-area">
      <ul>
        <router-link to="/"><img src="@/assets/logo.png" /></router-link>
        <router-link to="/" class="li"
          ><font-awesome-icon icon="house-chimney"></font-awesome-icon
          >Home</router-link
        >
        <router-link to="/housedeal/list" class="li">
          <font-awesome-icon icon="building"></font-awesome-icon>Apartment
        </router-link>

        <router-link to="/notice/list" class="li"
          ><font-awesome-icon icon="flag"></font-awesome-icon
          >Notice</router-link
        >
        <router-link to="/qna/list" class="li"
          ><font-awesome-icon icon="question"></font-awesome-icon
          >QnA</router-link
        >
      </ul>
    </section>
    <section class="header-bottom-area">
      <ul>
        <router-link to="/user/login" class="li"
          ><font-awesome-icon icon="user"></font-awesome-icon
          ><label class="header-hover-text">Login</label></router-link
        >
        <router-link to="/user/detail" class="li"
          ><font-awesome-icon icon="pen-to-square"></font-awesome-icon
          ><label class="header-hover-text">Edit</label></router-link
        >
      </ul>
    </section>
  </header>
</template>

<script>
export default {
  data() {
    return {
      loginInfo: null,
    };
  },
  created() {
    if (localStorage.getItem("loginInfo")) {
      //로그인 되어있으면
      const loginUser = JSON.parse(localStorage.getItem("loginInfo"));
      this.loginInfo = loginUser;
    }
  },
  methods: {
    logout() {
      localStorage.removeItem("loginInfo");
      this.loginInfo = null;
      alert("로그아웃 되었습니다");
      location.href = "/";
    },
  },
};
</script>

<style scoped src="@/css/header.css"></style>

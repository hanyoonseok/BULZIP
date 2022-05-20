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
        <router-link to="/user/login" class="li" v-if="!userInfo"
          ><font-awesome-icon icon="user"></font-awesome-icon
          ><label class="header-hover-text">Login</label></router-link
        >
        <router-link
          :to="'/user/info/' + userInfo.userId"
          class="li"
          v-if="userInfo"
          ><font-awesome-icon icon="user"></font-awesome-icon
          ><label class="header-hover-text"
            >{{ userInfo.userId }}님</label
          ></router-link
        >
        <li class="li" v-if="userInfo" @click="logout">
          <font-awesome-icon icon="right-from-bracket"></font-awesome-icon
          ><label class="header-hover-text">Logout</label>
        </li>
        <router-link
          to="/user/list"
          class="li"
          v-if="userInfo && userInfo.role === 1"
          ><font-awesome-icon icon="address-book"></font-awesome-icon
          ><label class="header-hover-text">Admin</label></router-link
        >
      </ul>
    </section>
  </header>
</template>

<script>
import { mapState, mapMutations } from "vuex";

export default {
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations("userStore", ["SET_IS_LOGIN", "SET_USER_INFO"]),
    logout() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      if (this.$route.path != "/") this.$router.push("/");
    },
  },
};
</script>

<style scoped src="@/css/header.css"></style>

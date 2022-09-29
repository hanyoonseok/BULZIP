<template>
  <div class="page-container">
    <form class="left-container all-center" v-on:submit.prevent="login">
      <h1>LOGIN</h1>
      <input
        type="text"
        class="user-input"
        placeholder="아이디"
        v-model="user.id"
        required
      />
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호"
        v-model="user.pw"
        required
      />
      <p class="text-danger" v-show="!isValid">
        아이디 혹은 비밀번호가 일치하지 않습니다
      </p>
      <button class="form-btn">LOGIN</button>

      <span class="small-text-container">
        <router-link to="/user/regist" class="small-text"
          >회원가입 &nbsp;&nbsp;</router-link
        >
        <router-link to="/user/findpw" class="small-text"
          >&nbsp;&nbsp;비밀번호 찾기</router-link
        >
      </span>
    </form>
    <section class="right-container"></section>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  data() {
    return {
      user: {
        id: "",
        pw: "",
      },
      isValid: true,
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError"]),
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    async login() {
      await this.userConfirm(this.user);
      const token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        await this.getUserInfo(token);
        this.$router.push("/");
      } else {
        this.isValid = false;
      }
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

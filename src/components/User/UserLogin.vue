<template>
  <div class="page-container">
    <form class="left-container all-center" v-on:submit.prevent="login">
      <h1>LOGIN</h1>
      <input
        type="text"
        class="user-input"
        placeholder="아이디"
        v-model="id"
        required
      />
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호"
        v-model="pw"
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
import http from "@/api/http.js";

export default {
  data() {
    return {
      id: "",
      pw: "",
      isValid: true,
    };
  },
  methods: {
    login() {
      http.post(`/user/login/${this.id}/${this.pw}`).then((resp) => {
        if (!resp.data) {
          this.isValid = false;
        } else {
          alert("로그인 성공");
          localStorage.setItem("loginInfo", JSON.stringify(resp.data));
          location.href = "/";
        }
      });
      return false;
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

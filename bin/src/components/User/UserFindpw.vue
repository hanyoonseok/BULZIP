<template>
  <div class="page-container">
    <form class="left-container all-center" v-on:submit.prevent="findpw">
      <h1>비밀번호 찾기</h1>
      <input
        type="text"
        class="user-input"
        placeholder="아이디"
        required
        v-model="id"
        ref="id"
      />
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호"
        required
        v-model="pw"
      />
      <input
        type="password"
        class="user-input"
        placeholder="새 비밀번호"
        required
        v-model="newpw"
      />
      <p class="text-danger" v-show="!isValid">
        아이디 혹은 비밀번호가 일치하지 않습니다
      </p>
      <input type="submit" class="form-btn" value="비밀번호 찾기" />

      <span class="small-text-container">
        <router-link to="/user/regist" class="small-text"
          >회원가입 &nbsp;&nbsp;</router-link
        >
        <router-link to="/user/login" class="small-text"
          >&nbsp;&nbsp;로그인</router-link
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
      newpw: "",
      isValid: true,
    };
  },
  methods: {
    findpw() {
      if (this.pw !== this.newpw) {
        this.isValid = false;
      } else {
        http
          .post(`/user/findpw/${this.id}/${this.pw}/${this.newpw}`)
          .then((resp) => {
            if (resp.data == "success") {
              alert("비밀번호 변경 성공");
              this.$router.push("/user/login");
            } else {
              alert(resp.data);
              this.$refs.id.focus();
            }
          });
      }

      return false;
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

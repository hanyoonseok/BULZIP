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
        type="text"
        class="user-input"
        placeholder="이름"
        required
        v-model="name"
      />
      <input
        type="text"
        class="user-input"
        placeholder="전화번호"
        required
        v-model="phone"
      />

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
    <section class="right-container" v-if="tempPw === ''"></section>
    <section class="right-container-noback" v-if="tempPw !== ''">
      아래 임시 비밀번호로 로그인 해주세요. <br />
      <label>{{ tempPw }}</label>
    </section>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      id: "",
      name: "",
      phone: "",
      tempPw: "",
    };
  },
  methods: {
    findpw() {
      console.log(this.id, this.name, this.phone);
      http
        .post(`/user/findpw/${this.id}/${this.name}/${this.phone}`)
        .then((resp) => {
          if (resp.data !== "fail") {
            alert("비밀번호 발급 성공");
            this.tempPw = resp.data;
          } else {
            alert("일치하는 정보가 없습니다.");
            this.$refs.id.focus();
          }
        });

      return false;
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

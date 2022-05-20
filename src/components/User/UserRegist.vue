<template>
  <div class="page-container">
    <form class="left-container all-center" v-on:submit.prevent="regist">
      <h1>REGIST</h1>
      <input
        type="text"
        class="user-input"
        placeholder="아이디 6~16자"
        required
        v-model="userId"
        @keyup="checkId"
      />
      <p
        :class="{
          'text-danger': !isValid,
          'text-primary': isValid,
        }"
      >
        {{ idresult }}
      </p>
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호"
        required
        v-model="password"
      />
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호 재입력"
        required
        v-model="passwordcheck"
        @keyup="checkPw"
      />
      <p class="text-danger" v-show="!pwresult">비밀번호가 일치하지 않습니다</p>
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
      <!-- 체크 박스 -->
      <check-box></check-box>

      <input
        type="submit"
        class="form-btn"
        value="회원가입"
        :disabled="!isValid || !pwresult"
      />

      <span class="small-text-container">
        <router-link to="/user/login" class="small-text"
          >로그인 &nbsp;&nbsp;</router-link
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
import CheckBox from "@/components/User/item/CheckBox.vue";

export default {
  components: {
    CheckBox,
  },

  data() {
    return {
      userId: "",
      password: "",
      passwordcheck: "",
      name: "",
      phone: "",
      idresult: "",
      isValid: false,
      pwresult: true,
    };
  },
  methods: {
    regist() {
      console.log(this.userId, this.password, this.name, this.phone);
      http
        .post(`/user/register`, {
          userId: this.userId,
          password: this.password,
          name: this.name,
          phone: this.phone,
        })
        .then((resp) => {
          if (resp.data == "success") {
            alert("회원가입 성공");
            this.$router.push("/user/login");
          } else {
            alert(resp.data);
          }
        });
      return false;
    },
    checkId() {
      if (this.userId.length < 6 || this.userId.length > 16) {
        this.idresult = "아이디는 6자이상 16자이하입니다.";
        if (this.userId.length === 0) this.idresult = "";
        this.isValid = false;
      } else {
        http.get(`/user/idCheck/${this.userId}`).then((resp) => {
          console.log(resp.data);
          if (resp.data === "avaliable") {
            this.idresult = `${this.userId}는 사용 가능합니다.`;
            this.isValid = true;
          } else {
            this.idresult = `${this.userId}는 사용할 수 없습니다.`;
            this.isValid = false;
          }
        });
      }
    },
    checkPw() {
      if (this.password !== this.passwordcheck) {
        this.pwresult = false;
      } else {
        this.pwresult = true;
      }
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

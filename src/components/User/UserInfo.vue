<template>
  <div id="loginform">
    <form v-on:submit.prevent="update">
      <div id="login-title-wrapper">
        <img src="@/assets/logo.png" class="img_logo" alt="" />
        <h2 class="modal-login-title">회원 정보</h2>
      </div>

      <div class="login-content">
        아이디 :
        <input
          type="text"
          class="member_login_input"
          disabled
          :value="user.userId"
        /><br />
        패스워드 :
        <input
          type="password"
          class="member_login_input"
          disabled
          :value="user.password"
        /><br />
        이름 :
        <input type="text" class="member_login_input" v-model="user.name" />
        전화번호 :
        <input type="text" class="member_login_input" v-model="user.phone" />
      </div>

      <input type="submit" value="수정" class="blueBtn-l" />
    </form>
    <button class="blueBtn-l" @click="deleteMe">회원 정보 삭제</button>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      user: { id: "", userId: "", password: "", name: "", phone: "", role: "" },
    };
  },
  created() {
    http.get(`/user/detail/${this.$route.params.id}`).then((resp) => {
      if (!resp.data) {
        alert("정보 로드 실패");
      } else {
        this.user = resp.data;
      }
    });
  },
  methods: {
    update() {
      console.log(this.user);
      http.put(`/user/update`, this.user).then((resp) => {
        if (resp.data === "success") {
          alert("정보 수정 완료");
          const currentLocation = location.href;
          location.href = currentLocation;
        } else {
          alert("정보 수정 실패");
        }
      });
    },
    deleteMe() {
      http.delete(`/user/delete/${this.user.userId}`).then((resp) => {
        if (resp.data === "success") {
          alert("정보 삭제 완료");
          localStorage.removeItem("loginInfo");
          location.href = "/";
        } else {
          alert("정보 삭제 실패");
        }
      });
    },
  },
};
</script>

<style></style>

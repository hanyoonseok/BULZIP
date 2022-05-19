<template>
  <div class="page-container">
    <div class="left-container all-center">
      <h1>REGIST</h1>
      <input type="text" class="user-input" v-model="user.userId" disabled />
      <input type="password" class="user-input" value="***" disabled />
      <input type="text" class="user-input" v-model="user.name" />
      <input type="text" class="user-input" v-model="user.phone" />
      <button class="form-btn" @click="update">회원정보 수정</button>
      <button class="form-btn" @click="deleteMe">회원정보 삭제</button>
    </div>
    <section class="right-container"></section>
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

<style scoped src="@/css/user.css"></style>

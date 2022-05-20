<template>
  <div id="mainDiv">
    <h2 class="mainDiv-title">유저 관리 페이지</h2>
    <input
      type="text"
      class="search-input"
      v-model="keyword"
      placeholder="아이디, 이름, 전화번호로 검색"
      @keyup="searchUser"
    />
    <table class="table-striped">
      <thead>
        <th>DB_ID</th>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>이름</th>
        <th>전화번호</th>
        <th>구분</th>
      </thead>
      <tbody>
        <tr v-for="user in users" :key="user.id" @click="goDetail(user.id)">
          <td>{{ user.id }}</td>
          <td>{{ user.userId }}</td>
          <td>{{ user.password }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.phone }}</td>
          <td>{{ user.role === 1 ? "관리자" : "유저" }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      users: [],
      keyword: "",
    };
  },
  created() {
    this.getAllUsers();
  },
  methods: {
    searchUser() {
      if (this.keyword === "") this.getAllUsers();
      else {
        http.get(`/user/search/${this.keyword}`).then((resp) => {
          this.users = resp.data;
        });
      }
    },
    getAllUsers() {
      http.get(`/user/list/1`).then((resp) => {
        this.users = resp.data;
      });
    },
    goDetail(id) {
      this.$router.push(`/user/info/${id}`);
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

<template>
  <div class="container-col">
    <section class="header-section vertical-center">
      <font-awesome-icon
        icon="angle-left"
        class="back-btn"
        @click="backToList"
      ></font-awesome-icon>
      내 관심목록
    </section>
    <div id="list-item-container"></div>
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http.js";

export default {
  data() {
    return {
      items: [],
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  created() {
    http.get(`/user/interested/list/${this.userInfo.userId}`).then((resp) => {
      console.log(resp.data);
    });
  },
  methods: {
    backToList() {
      this.$emit("backToList");
    },
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

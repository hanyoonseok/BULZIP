<template>
  <div class="list-item" @click="selectOne(item.no)">
    <div class="test"></div>
    <div class="list-item-detail">
      <label>아파트</label>
      <h5>{{ item.aptName }}</h5>
      <h3>{{ item.dealAmount }}만원</h3>
    </div>
    <font-awesome-icon icon="heart" class="likebtn"></font-awesome-icon>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  methods: {
    selectOne(no) {
      http.get(`/housedeal/read/${no}`).then((resp) => {
        this.selectedItem = resp.data;
        this.$emit("selectOne", this.selectedItem);
        // http
        //   .get(`/housedeal/hospital/${resp.data.lat}/${resp.data.lng}`)
        //   .then((resp) => {
        //     console.log(resp.data);
        //     this.hospitals = resp.data;
        //   });
      });
    },
  },
  props: {
    item: Object,
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

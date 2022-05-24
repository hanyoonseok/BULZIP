<template>
  <div class="list-item" @click="selectOne(item.no)">
    <div class="test"></div>
    <div class="list-item-detail">
      <label>아파트</label>
      <h5>{{ item.aptName }}</h5>
      <h3>{{ item.dealAmount | toWon }}원</h3>
    </div>
    <div class="absolute">
      <i class="fa-brands fa-gratipay"></i>
      {{ item.hit }}
    </div>
  </div>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      selectedItem: null,
    };
  },
  methods: {
    selectOne(no) {
      http.get(`/housedeal/read/${no}`).then((resp) => {
        this.selectedItem = resp.data;
        console.log(this.selectedItem);
        this.$emit("selectOne", this.selectedItem);
      });
    },
    itemover() {
      this.$EventBus.$emit("itemEnter", this.item);
    },
  },
  props: {
    item: Object,
  },
  filters: {
    toWon(num) {
      num = num.replace(",", "");
      let div = 0;
      while (num / Math.pow(10, div) > 1) {
        div++;
      }

      let danwi = "";
      for (let i = 0; i < div % 4; i++) danwi += num.toString()[i];
      if (div >= 3) danwi += "억";

      let rest = "";
      for (let i = div % 4; i < 4; i++) rest += num.toString()[i];

      const len = rest.length;
      for (let i = 0; i < 4 - len; i++) {
        rest += "0";
      }

      if (rest !== "0000") danwi += rest + "만";

      return danwi;
    },
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

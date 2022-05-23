<template>
  <div class="container-col">
    <section class="header-section vertical-center">
      <font-awesome-icon
        icon="angle-left"
        class="back-btn"
        @click="goback"
      ></font-awesome-icon>
      {{ item.aptName }}
      <font-awesome-icon icon="heart" class="likebtn"></font-awesome-icon>
    </section>
    <img src="@/assets/back.jpg" class="detail-main-img" />
    <article class="detail-article">
      <section class="info-section">
        <label class="info-hit-label"
          >현재까지 {{ item.hit }}명의 사용자가 본 매물입니다.</label
        >
        <h3 class="info-price-title">매매 {{ item.dealAmount | toWon }}</h3>
      </section>
      <section class="header-section vertical-center">
        상세정보
        <font-awesome-icon
          class="downbtn"
          :icon="s1Open ? 'angle-up' : 'angle-down'"
          @click="s1Open = !s1Open"
        ></font-awesome-icon>
      </section>
      <section class="info-section vertical-center" v-if="s1Open">
        <div class="info-section-div">
          <div class="info-section-particle">
            <div class="info-section-particle-title">면적</div>
            {{ item.area }}m²
          </div>
          <div class="info-section-particle">
            <div class="info-section-particle-title">층</div>
            {{ item.floor }}층
          </div>
          <div class="info-section-particle">
            <div class="info-section-particle-title">동</div>
            {{ item.dongName }}
          </div>
        </div>
      </section>
      <section>
        <!--class="info-section vertical-center">-->
        <bar-chart :item="item"></bar-chart>
      </section>
    </article>
  </div>
</template>

<script>
import BarChart from "@/components/Charts/BarChart.vue";
export default {
  components: { BarChart },
  data() {
    return {
      s1Open: true,
    };
  },
  props: {
    item: Object,
  },
  methods: {
    goback() {
      this.$emit("backToList");
      this.$EventBus.$emit("closeKeywordTab");
    },
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

<template>
  <section id="main-section">
    <KakaoMap @toggleContainer="toggleContainer" />
    <HouseListContainer :keyword="this.$route.params.keyword" v-if="toggle" />
    <RecommendedList v-if="!toggle" />
  </section>
</template>

<script>
import KakaoMap from "./KakaoMap.vue";
import HouseListContainer from "@/components/Housedeal/items/HouseListContainer.vue";
import RecommendedList from "@/components/Housedeal/items/RecommendedList.vue";

export default {
  data() {
    return {
      toggle: true, //true = 매물 목록, false = 추천 목록
    };
  },
  methods: {
    toggleContainer(type) {
      this.toggle = type;
    },
  },
  components: {
    KakaoMap,
    HouseListContainer,
    RecommendedList,
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

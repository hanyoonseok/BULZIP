<template>
  <section id="main-section">
    <KakaoMap />
    <HouseListContainer :keyword="this.$route.params.keyword" />
  </section>
</template>

<script>
import KakaoMap from "./KakaoMap.vue";
import HouseListContainer from "@/components/Housedeal/items/HouseListContainer.vue";

export default {
  data() {
    return {};
  },
  components: {
    KakaoMap,
    HouseListContainer,
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

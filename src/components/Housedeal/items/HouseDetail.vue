<template>
  <div class="container-col">
    <section class="header-section vertical-center">
      <font-awesome-icon
        icon="angle-left"
        class="back-btn"
        @click="goback"
      ></font-awesome-icon>
      {item.아파트명}
      <font-awesome-icon icon="heart" class="likebtn"></font-awesome-icon>
    </section>
    <img src="@/assets/back.jpg" class=/>
    <article class="detail-article">
      <section class="header-section vertical-center">
        대충 어떤 제목
        <font-awesome-icon
          class="downbtn"
          :icon="s1Open ? 'angle-up' : 'angle-down'"
          @click="s1Open = !s1Open"
        ></font-awesome-icon>
      </section>
      <section class="info-section vertical-center" v-if="s1Open">
        sadfsadf
      </section>
    </article>
  </div>
</template>

<script>
export default {
  data() {
    return {
      s1Open: false,
    };
  },
  props: {
    item: Object,
  },
  methods: {
    goback() {
      this.$emit("backToList");
    },
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

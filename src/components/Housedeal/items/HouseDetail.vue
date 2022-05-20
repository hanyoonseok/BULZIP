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
      sadfsadfasdf
      <div>asdfasdf</div>
    </article>
  </div>
</template>

<script>
export default {
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

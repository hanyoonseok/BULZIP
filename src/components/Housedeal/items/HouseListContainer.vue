<template>
  <article id="main-list">
    <form id="filter-container" v-on:submit.prevent="search">
      <input
        type="text"
        class="form-control"
        id="search-filter"
        placeholder="구, 동, 아파트 명"
        v-model="keyword"
      />
      <button id="filter-btn" type="submit">
        <i class="fa-solid fa-magnifying-glass"></i>
      </button>
    </form>
    <div id="list-item-container">
      <HouseItem v-for="(item, i) in items" :key="i" :item="item" />>
      <button id="moreBtn" v-if="keyword === ''" @click="getList">
        더보기<i class="fa-solid fa-caret-down"></i>
      </button>
    </div>
  </article>
</template>

<script>
import http from "@/api/http.js";
import HouseItem from "@/components/Housedeal/items/HouseItem.vue";

export default {
  data() {
    return {
      selectedItem: null,
      hospitals: [],
      items: [],
      dataIdx: 0,
      keyword: "",
      status: 0, //0=전체목록, -1=관심목록, > 1 상세조회
    };
  },
  created() {
    this.getList(this.param);
  },
  methods: {
    getList(keyword = "") {
      if (keyword) {
        http.post(`/housedeal/list/${keyword}`).then((resp) => {
          console.log(resp);
          this.items = this.items.concat(resp.data);
          this.dataIdx = 0;
        });
      } else {
        http.get(`/housedeal/list/${this.dataIdx}`).then((resp) => {
          console.log(resp);
          this.items = this.items.concat(resp.data);
          this.dataIdx += 100;
        });
      }
    },
    search() {
      const keyword = this.keyword;
      this.items = [];
      this.getList(keyword);
    },
  },
  props: {
    param: String,
  },
  components: {
    HouseItem,
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

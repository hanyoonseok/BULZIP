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
      <div
        v-for="item in items"
        :key="item.no"
        class="list-item"
        @click="selectOne(item.no)"
      >
        <div class="test"></div>
        <div class="list-item-detail">
          <label>아파트</label>
          <h5>{{ item.aptName }}</h5>
          <h3>{{ item.dealAmount }}만원</h3>
        </div>
        <font-awesome-icon icon="heart" class="likebtn"></font-awesome-icon>
      </div>
      <button id="moreBtn" v-if="keyword === ''" @click="getList">
        더보기<i class="fa-solid fa-caret-down"></i>
      </button>
    </div>
  </article>
</template>

<script>
import http from "@/api/http.js";

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
    selectOne(no) {
      http.get(`/housedeal/read/${no}`).then((resp) => {
        this.selectedItem = resp.data;
        http
          .get(`/housedeal/hospital/${resp.data.lat}/${resp.data.lng}`)
          .then((resp) => {
            console.log(resp.data);
            this.hospitals = resp.data;
          });
      });
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
};
</script>

<style scoped src="@/css/housedeal.css"></style>

<template>
  <article id="main-list-container">
    <div class="main-list" v-if="status === 0">
      <form id="filter-container" v-on:submit.prevent="search">
        <input
          type="text"
          class="form-control"
          id="search-filter"
          placeholder="구, 동, 아파트 명"
          v-model="keyword"
        />
        <button id="filter-btn" type="submit">
          <font-awesome-icon icon="magnifying-glass"></font-awesome-icon>
        </button>
      </form>
      <div id="list-item-container">
        <HouseItem
          v-for="(item, i) in items"
          :key="i"
          :item="item"
          @selectOne="selectOne"
        />
        <button id="moreBtn" v-if="keyword === ''" @click="getList">
          더보기<i class="fa-solid fa-caret-down"></i>
        </button>
      </div>
    </div>

    <HouseDetail v-if="status > 0" @backToList="status = 0" />

    <HouseLike v-if="status < 0" />
  </article>
</template>

<script>
import http from "@/api/http.js";
import HouseItem from "@/components/Housedeal/items/HouseItem.vue";
import HouseLike from "@/components/Housedeal/items/HouseLike.vue";
import HouseDetail from "@/components/Housedeal/items/HouseDetail.vue";

export default {
  data() {
    return {
      selectedItem: null,
      items: [],
      dataIdx: 0,
      keyword: "",
      status: 0, //0=전체목록, -1=관심목록, > 1 상세조회
    };
  },
  created() {
    if (this.$route.params.keyword) {
      this.keyword = this.$route.params.keyword;
      this.getList(this.keyword);
    } else {
      this.getList();
    }
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
          this.items = this.items.concat(resp.data);
          this.dataIdx += 100;
        });
      }
      console.log(this.items);
    },

    search() {
      const keyword = this.keyword;
      this.items = [];
      this.getList(keyword);
    },
    selectOne(selectedItem) {
      this.status = selectedItem.no;
    },
  },
  props: {
    param: String,
  },
  components: {
    HouseItem,
    HouseLike,
    HouseDetail,
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

<template>
  <article id="main-list-container" class="yellowborder">
    <div class="main-list" v-if="status === 0 && !selectedItem">
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
      <div class="mylist-container">
        <label @click="status = -1">
          내 관심목록 <i class="fa-solid fa-house-heart"></i>
        </label>
      </div>
      <div
        id="list-item-container"
        @scroll="handleScroll"
        ref="list-item-container"
      >
        <HouseItem
          v-for="(item, i) in items"
          :key="i"
          :item="item"
          :border="'yellow'"
          @selectOne="selectOne"
        />
        <div class="all-center" style="flex-direction: column">
          <img src="@/assets/loading.gif" class="cry-img" v-if="isLoading" />
          <img
            src="@/assets/cry.png"
            class="cry-img"
            v-if="!isLoading && items.length === 0"
          />
          <h5
            style="text-align: center"
            v-if="!isLoading && items.length === 0"
          >
            근처에 매물이 없습니다.
          </h5>
        </div>
      </div>
      <img src="@/assets/매물.png" class="float-img" />
    </div>

    <HouseDetail
      v-if="selectedItem"
      @backToList="
        selectedItem = null;
        isLoading = true;
      "
      :item="selectedItem"
    />

    <HouseLike v-if="status < 0" @backToList="status = 0" />
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
      isKeywordSearch: false,
      isLoading: false,
    };
  },
  created() {
    this.isLoading = true;
    this.$EventBus.$on("getListByLatLng", (range) => {
      this.isLoading = true;
      console.log("receive", range);
      this.isKeywordSearch = false;
      this.items = range;
      this.isLoading = false;
    });
    this.$EventBus.$on("closeDetail", () => {
      this.selectedItem = null;
    });
    this.$EventBus.$on("onMarkerClick", (selectedItem) => {
      this.selectOne(selectedItem);
    });
  },
  methods: {
    getList(keyword) {
      this.isLoading = true;
      console.log(this.dataIdx);
      if (keyword) {
        http.post(`/housedeal/list/${keyword}/${this.dataIdx}`).then((resp) => {
          this.items = this.items.concat(resp.data);
          this.dataIdx += 20;
          this.isLoading = false;
        });
      }
    },
    search() {
      this.items = [];
      this.isKeywordSearch = true;
      this.dataIdx = 0;
      this.getList(this.keyword);
    },
    selectOne(selectedItem) {
      this.selectedItem = selectedItem;
      this.$EventBus.$emit("selectOneItem", selectedItem);
    },
    handleScroll(e) {
      if (!this.isKeywordSearch) return;
      const { scrollHeight, scrollTop, clientHeight } = e.target;
      const isAtTheBottom = scrollHeight === scrollTop + clientHeight;
      // 일정 한도 밑으로 내려오면 함수 실행
      if (isAtTheBottom) this.getList(this.keyword);
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

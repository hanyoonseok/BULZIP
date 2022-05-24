<template>
  <article id="main-list-container">
    <div class="main-list" v-if="status === -1 && !items">
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
        <div class="list-item" v-if="items.length === 0">
          <div class="list-item-detail">
            <img src="@/assets/cry.png" class="cry-img" />
            <h5>추천 매물이 없습니다.</h5>
          </div>
        </div>
        <!-- <button id="moreBtn" v-if="keyword === ''" @click="getList">
          더보기<i class="fa-solid fa-caret-down"></i>
        </button> -->
      </div>
    </div>

    <HouseDetail v-if="items" @backToList="items = null" :item="items" />

    <HouseLike v-if="status < 0" />
  </article>
</template>

<script>
import http from "@/api/http.js";
import HouseItem from "@/components/Housedeal/items/HouseItem.vue";
import HouseLike from "@/components/Housedeal/items/HouseLike.vue";
import HouseDetail from "@/components/Housedeal/items/HouseDetail.vue";
import { mapState } from "vuex";

export default {
  data() {
    return {
      //selectedItem: null,
      items: [], // 여기에 추천 리스트 담긴다.

      status: -1, //0=전체목록, -1=관심목록, > 1 상세조회
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]), // 유저아이디 : this.userInfo.userId
  },

  created() {
    this.getList(userInfo.userId);
  },
  methods: {
    getList(user_id) {
      http.get(`/housedeal/recommend/${user_id}`).then((resp) => {
        this.items = this.items.concat(resp.data);
      });
    },
    selectOne(selectedItem) {
      this.selectedItem = selectedItem;
      this.$EventBus.$emit("openKeywordTab");
      this.$EventBus.$emit("selectOneItem", selectedItem);
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

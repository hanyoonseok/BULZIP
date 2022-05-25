<template>
  <article id="main-list-container" class="blueborder">
    <div class="main-list" v-if="!selectedItem">
      <div id="list-item-container">
        <HouseItem
          v-for="(item, i) in items"
          :key="i"
          :item="item"
          :border="'blue'"
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
      <img src="@/assets/추천.png" class="float-img" />
    </div>

    <HouseDetail
      v-if="selectedItem"
      @backToList="selectedItem = null"
      :item="selectedItem"
    />
  </article>
</template>

<script>
import http from "@/api/http.js";
import HouseItem from "@/components/Housedeal/items/HouseItem.vue";
import HouseDetail from "@/components/Housedeal/items/HouseDetail.vue";
import { mapState } from "vuex";

export default {
  data() {
    return {
      //selectedItem: null,
      items: [], // 여기에 추천 리스트 담긴다.
      selectedItem: null,
      isLoading: false,
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]), // 유저아이디 : this.userInfo.userId
  },

  created() {
    this.isLoading = true;
    http.get(`/housedeal/recommend/${this.userInfo.userId}`).then((resp) => {
      console.log("추천시스템리스트", resp.data);
      this.items = resp.data;
      this.isLoading = false;
    });
    this.$EventBus.$on("closeDetail", () => {
      this.selectedItem = null;
    });
  },
  methods: {
    selectOne(selectedItem) {
      this.selectedItem = selectedItem;
      this.$EventBus.$emit("selectOneItem", selectedItem);
    },
  },
  props: {
    param: String,
  },
  components: {
    HouseItem,
    HouseDetail,
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

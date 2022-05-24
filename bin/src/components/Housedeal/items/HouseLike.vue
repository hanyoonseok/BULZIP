<template>
  <div class="container-col">
    <div class="main-list" v-if="!selectedItem">
      <section class="header-section vertical-center">
        <font-awesome-icon
          icon="angle-left"
          class="back-btn"
          @click="backToList"
        ></font-awesome-icon>
        내 관심목록
      </section>
      <div id="list-item-container">
        <HouseItem
          v-for="(item, i) in items"
          :key="i"
          :item="item"
          @selectOne="selectOne"
        />
        <div class="list-item-detail" v-if="items.length === 0">
          <img src="@/assets/cry.png" class="cry-img" />
          <h5>관심 매물을 등록해보세요</h5>
        </div>
      </div>
    </div>
    <HouseDetail
      v-if="selectedItem"
      @backToList="selectedItem = null"
      :item="selectedItem"
    />
  </div>
</template>

<script>
import { mapState } from "vuex";
import http from "@/api/http.js";
import HouseItem from "@/components/Housedeal/items/HouseItem.vue";
import HouseDetail from "@/components/Housedeal/items/HouseDetail.vue";

export default {
  data() {
    return {
      items: [],
      selectedItem: null,
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  created() {
    http.get(`/user/interested/list/${this.userInfo.userId}`).then((resp) => {
      console.log(resp.data);
      this.items = resp.data;
    });
  },
  methods: {
    backToList() {
      this.$emit("backToList");
    },
    selectOne(selectedItem) {
      this.selectedItem = selectedItem;
      this.$EventBus.$emit("selectOneItem", selectedItem);
    },
  },
  components: {
    HouseItem,
    HouseDetail,
  },
};
</script>

<style scoped src="@/css/housedeal.css"></style>

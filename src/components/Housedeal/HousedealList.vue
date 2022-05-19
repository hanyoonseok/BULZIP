<template>
  <section id="main-section">
    <div id="info-detail" v-if="selectedItem">
      <button @click="closeDetail">X</button>
      <img src="@/assets/logo.png" />
      <table class="table">
        <thead>
          <tr>
            <th>정보</th>
            <th>내용</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>건물명</td>
            <td>{{ selectedItem.aptName }}</td>
          </tr>
          <tr>
            <td>위치</td>
            <td>{{ selectedItem.dongName }}{{ selectedItem.jibun }}</td>
          </tr>
          <tr>
            <td>면적</td>
            <td>{{ selectedItem.area }}m²</td>
          </tr>
          <tr>
            <td>거래금액</td>
            <td>{{ selectedItem.dealAmount }} 만원</td>
          </tr>
          <tr>
            <td>거래일자</td>
            <td>
              {{ selectedItem.dealYear }} 년 {{ selectedItem.dealMonth }}월
              {{ selectedItem.dealDay }}일
            </td>
          </tr>
          <tr>
            <td>층 수</td>
            <td>{{ selectedItem.floor }}층</td>
          </tr>
          <tr>
            <td>건축년도</td>
            <td>{{ selectedItem.buildYear }}</td>
          </tr>
        </tbody>
      </table>
      <h2>주변 병원 목록</h2>
      <table class="table table-hover">
        <thead>
          <tr>
            <th>병원명</th>
            <th>위치</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(hospital, i) in hospitals" :key="i">
            <td>{{ hospital.dutyname }}</td>
            <td>{{ hospital.dutyaddr }}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <KakaoMap />
    <article id="main-list">
      <h1>매물 목록</h1>
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
            <h5>{{ item.aptName }}</h5>
            <h3>{{ item.dealAmount }}만원</h3>
            <div class="list-item-detail-info">
              <label>{{ item.area }}m²</label>
              <label>{{ item.dongName }}</label>
              <label>아파트</label>
              <i class="fa-regular fa-heart press_like"></i>
            </div>
          </div>
        </div>
        <button id="moreBtn" v-if="keyword === ''" @click="getList()">
          더보기<i class="fa-solid fa-caret-down"></i>
        </button>
      </div>
    </article>
  </section>
</template>

<script>
import http from "@/api/http.js";
import KakaoMap from "./KakaoMap.vue";

export default {
  data() {
    return {
      keyword: "",
      selectedItem: null,
      hospitals: [],
      dataIdx: 0,
      itemHTML: "",
      map: Object,
      markets: [],
      overlays: [],
      items: [],
    };
  },
  created() {
    const param = this.$route.params.keyword;
    if (param) this.keyword = param;

    this.getList(param);
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
    closeDetail() {
      this.selectedItem = null;
    },
  },
  components: {
    KakaoMap,
  },
};
</script>

<style></style>

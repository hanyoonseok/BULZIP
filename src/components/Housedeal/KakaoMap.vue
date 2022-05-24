<template>
  <div id="map">
    <div class="kakao-station-container">
      <input
        type="text"
        class="kakao-station-input"
        placeholder="역 검색"
        :value="stationKeyword"
        @input="changeStationKeyword"
        @keyup="searchStation"
      />
      <div class="kakao-station-list" v-if="stations">
        <li
          class="kakao-station-item"
          v-for="(station, i) in stations"
          :key="i"
          @click="selectStation(station)"
        >
          {{ station.station }}
          <label class="kakao-station-line">{{ station.subway_line }}</label>
        </li>
      </div>
    </div>

    <div class="kakao-keyword-container" v-if="isKeywordOpen">
      <div class="between">
        <h1>상권 키워드</h1>
        <font-awesome-icon
          :icon="isKeywordDetailOpen ? 'angle-up' : 'angle-down'"
          @click="isKeywordDetailOpen = !isKeywordDetailOpen"
        ></font-awesome-icon>
      </div>
      <div class="kakao-keyword-list" v-if="isKeywordDetailOpen">
        <div
          class="kakao-keyword-item"
          v-for="(kw, i) in keywordValues"
          :key="i"
        >
          <input
            type="checkbox"
            class="hide-checkbox"
            @change="toggle(getKeyByValue(kw))"
            :id="getKeyByValue(kw)"
            :checked="
              userKeyword['keyword_' + getKeyByValue(kw)] === 1 ? true : false
            "
          />
          <label class="checkbox-btn" :for="getKeyByValue(kw)"
            ><font-awesome-icon icon="check"></font-awesome-icon></label
          >{{ kw }}
        </div>
      </div>
      <!-- slider -->
      <div class="slider">
        <!--<label> 제목</label>-->
        <input
          type="range"
          min="110"
          max="1000"
          step="10"
          v-model="sliderValue"
          @mouseup="selectOneItem()"
        />
        <span>{{ sliderValue }}</span>
      </div>
    </div>

    <section class="kakao-flip-container">
      <div
        class="kakao-flip-relative"
        @click="toggleContainer(false)"
        v-if="listContainerToggle"
      >
        <div class="kakao-flip-img apart1"></div>
        <label class="kakao-flip-text">추천매물</label>
      </div>
      <div
        class="kakao-flip-relative"
        @click="toggleContainer(true)"
        v-if="!listContainerToggle"
      >
        <div class="kakao-flip-img apart2"></div>
        <label class="kakao-flip-text">전체매물</label>
      </div>
    </section>
  </div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import http from "@/api/http.js";
import { mapState } from "vuex";
import { KEYWORD } from "@/constants/index.js";
import RangeSlider from "vue-range-slider";
import "vue-range-slider/dist/vue-range-slider.css";

export default {
  name: "KakaoMap",
  data() {
    return {
      // range slider
      sliderValue: "300", // 초기값 300m
      // kakao
      map: Object,
      markers: [],
      overlays: [],
      range: {
        sw_Lat: 0,
        sw_Lng: 0,
        ne_Lng: 0,
        ne_Lat: 0,
      },
      // template
      stationKeyword: "", //지하철 역 input
      keywordValues: [], //내 키워드 체크박스 텍스트용 배열
      stations: [], //역 검색시의 결과 역 리스트
      selectedKeywords: {}, //내 키워드의 객체 형태 (선택반영)
      selectedStation: null, //선택한 역
      isKeywordOpen: false, //내 키워드 열렸는지 여부
      isKeywordDetailOpen: false, //내 키워드에서 다운바 열렸는지 여부
      listContainerToggle: true, //true일 때 추천매물, false일 때 전체매물
    };
  },
  components: {
    RangeSlider,
  },
  computed: {
    ...mapState("userStore", ["userKeyword"]),
  },
  created() {
    this.$EventBus.$on("closeKeywordTab", () => {
      this.isKeywordOpen = false;
      this.selectedItem = null;
    });
    this.$EventBus.$on("selectOneItem", (selectedItem) => {
      this.isKeywordOpen = true;
      this.selectedItem = selectedItem;
      this.selectOneItem();
    });
    this.$EventBus.$on("itemEnter", (enterItem) => {
      this.onEnterItem(enterItem);
    });

    // this.selectedKeywords = this.userKeyword;
    // const keys = Object.keys(this.userKeyword);
    // keys.splice(0, 1);
    // keys.splice(0, 1);
    // this.keywordValues = keys.map((e) => {
    //   const divByUnderBar = e.split("_");
    //   return KEYWORD[divByUnderBar[1]];
    // });

    const keys = Object.keys(this.userKeyword);
    keys.splice(0, 1);
    keys.splice(0, 1);
    keys.forEach((e) => {
      const divByUnderBar = e.split("_");
      this.keywordValues.push(KEYWORD[divByUnderBar[1]]);
      if (this.userKeyword[e] === 1) this.selectedKeywords[e] = 1;
    });
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  methods: {
    testaaa() {
      console.log("111", 111);
    },
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);

      kakao.maps.event.addListener(this.map, "mouseup", () => {
        this.markers.forEach((e) => e.setMap(null));
        this.setSouthWest();
        this.setNorthEast();
        this.sendListByRange();
        if (this.isKeywordOpen) this.sendCommercialByRange();
      });

      kakao.maps.event.addListener(this.map, "zoom_changed", () => {
        this.markers.forEach((e) => e.setMap(null));
        this.setSouthWest();
        this.setNorthEast();
        this.sendListByRange();
      });

      this.map.setMaxLevel(5);
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=db837c20449f592fb6c253f478a766b8";
      document.head.appendChild(script);
    },
    toggleContainer(type) {
      this.listContainerToggle = type;
      this.$emit("toggleContainer", type);
    },
    selectStation(station) {
      console.log(station);
      this.selectedStation = station;
      this.stations = [];
      this.setSouthWest();
      this.setNorthEast();
      console.log(this.range);
      this.setMapCenter({ lat: station.lat, lng: station.lon });
      this.addMarker(
        { lat: station.lat, lng: station.lon },
        station.station,
        2,
      );

      this.sendListByRange();
    },
    changeStationKeyword(e) {
      this.stationKeyword = e.target.value;
    },
    searchStation() {
      if (this.stationKeyword === "") {
        this.stations = [];
        return;
      }
      http
        .get(`/housedeal/subway/search/${this.stationKeyword}`)
        .then((resp) => {
          this.stations = resp.data;
        });
    },
    getKeyByValue(val) {
      return Object.keys(KEYWORD).find((key) => KEYWORD[key] === val);
    },
    toggle(key) {
      console.log(key, this.selectedKeywords["keyword_" + key]);
      if (!this.selectedKeywords["keyword_" + key]) {
        this.selectedKeywords["keyword_" + key] = 1;
      } else if (this.selectedKeywords["keyword_" + key] === 1) {
        delete this.selectedKeywords["keyword_" + key];
      }

      this.selectOneItem();
      console.log(this.selectedKeywords);
    },
    setMapCenter(pos) {
      console.log(pos);
      this.markers.forEach((e) => e.setMap(null));
      this.overlays.forEach((e) => e.setMap(null));

      const coords = new kakao.maps.LatLng(pos.lat, pos.lng);
      this.map.setCenter(coords);
      this.setSouthWest();
      this.setNorthEast();
    },
    setSouthWest() {
      var bounds = this.map.getBounds();
      this.range.sw_Lat = bounds.getSouthWest().getLat();
      this.range.sw_Lng = bounds.getSouthWest().getLng();
    },
    setNorthEast() {
      var bounds = this.map.getBounds();
      this.range.ne_Lat = bounds.getNorthEast().getLat();
      this.range.ne_Lng = bounds.getNorthEast().getLng();
    },
    addMarker(pos, name, type) {
      var imageSrc = require(`@/assets/${
          type === 0 ? "home" : type === 1 ? "shop" : "subway"
        }.png`), // 마커이미지의 주소입니다
        imageSize = new kakao.maps.Size(50, 50), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(0, 0) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption,
        ),
        markerPosition = new kakao.maps.LatLng(pos.lat, pos.lng); // 마커가 표시될 위치입니다
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
        image: markerImage, // 마커이미지 설정
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
      this.markers.push(marker);

      // custom overlay
      var content = `
      <div class="kakao-overlay-container" style="width:fit-content;padding:0.5rem 0.75rem;border:1.5px solid #fdb814;color:#292929;border-radius: 12px;background-color:#fff;display:flex; justify-content:center; align-items:center; font-size:12px; font-weight:bold">
        ${name}
      </div>
        `;

      // 커스텀 오버레이가 표시될 위치입니다
      var position = new kakao.maps.LatLng(pos.lat, pos.lng);
      const copymap = this.map;

      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
        map: this.map,
        position: position,
        content: content,
        yAnchor: 1,
        xAnchor: 0.25,
      });
      // 여기 작성하자
      // const parent = document.querySelector(
      //   ".kakao-overlay-container",
      // ).parentNode;

      // 커스텀 오버레이를 지도에 표시합니다
      //customOverlay.setMap(this.map);
      this.overlays.push(customOverlay);
    },
    sendListByRange() {
      const whereis = {};
      whereis.sw_lat = this.range.sw_Lat;
      whereis.sw_lng = this.range.sw_Lng;
      whereis.ne_lat = this.range.ne_Lat;
      whereis.ne_lng = this.range.ne_Lng;
      http.post(`/housedeal/boundry`, whereis).then((resp) => {
        this.$EventBus.$emit("getListByLatLng", resp.data);
        resp.data.forEach((e) => {
          this.addMarker({ lat: e.lat, lng: e.lng }, e.aptName, 0);
        });
      });
    },

    sendCommercialByRange() {
      // this.markers.forEach((e) => e.setMap(null));
      // http
      //   .get(
      //     `/housedeal/commercial/boundary/${this.range.sw_Lat}/${this.range.sw_Lng}/${this.range.ne_Lat}/${this.range.ne_Lng}`,
      //   )
      //   .then((resp) => {
      //     this.$EventBus.$emit("getListByLatLng", resp.data);
      //     resp.data.forEach((e) => {
      //       this.addMarker({ lat: e.lat, lng: e.lng });
      //     });
      //   });
    },
    selectOneItem() {
      const myKeywords = {}; //{ ...this.userKeyword };
      this.setMapCenter({
        lat: this.selectedItem.lat,
        lng: this.selectedItem.lng,
      });
      myKeywords.userKeyword = this.selectedKeywords;
      console.log("myKeywords", myKeywords);
      myKeywords.sw_lat = this.range.sw_Lat;
      myKeywords.sw_lng = this.range.sw_Lng;
      myKeywords.ne_lat = this.range.ne_Lat;
      myKeywords.ne_lng = this.range.ne_Lng;
      myKeywords.current_lat = this.selectedItem.lat;
      myKeywords.current_lng = this.selectedItem.lng;
      myKeywords.sliderValue = this.sliderValue;
      if (!myKeywords) return;

      http.post(`/housedeal/commercial`, myKeywords).then((resp) => {
        console.log(resp.data);
        resp.data.forEach((e) => {
          this.addMarker({ lat: e.lat, lng: e.lng }, e.name, 1);
        });
      });
    },
    onEnterItem(enterItem) {
      console.log(enterItem);
      this.markers.forEach((e) => {
        //La가 lng, Ma가 lat
        const lat = e.getPosition().Ma;
        const lng = e.getPosition().La;
        if (enterItem.lat == lat && enterItem.lng == lng) {
          const markerImage = new kakao.maps.MarkerImage(
            require("@/assets/logo.png"),
            new kakao.maps.Size(70, 70),
            new kakao.maps.Point(13, 34),
          );
          e.setImage(markerImage);
        }
      });
    },
  },
};
</script>

<style scoped src="@/css/kakao.css"></style>

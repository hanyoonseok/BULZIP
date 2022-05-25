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
        <input
          type="range"
          min="110"
          max="1000"
          step="10"
          v-model="sliderValue"
          @mouseup="selectOneItem"
        />
        <span>{{ sliderValue }}m</span>
      </div>

      <!-- 병원 정보 조회 버튼 -->
      <button class="hospital_button" @click="getHospitals">
        주변 병원 보기
      </button>
    </div>

    <section class="kakao-flip-container">
      <div
        class="kakao-flip-relative apart1"
        @click="toggleContainer(false)"
        v-if="listContainerToggle"
      >
        추천<br />서비스
      </div>
      <div
        class="kakao-flip-relative apart2"
        @click="toggleContainer(true)"
        v-if="!listContainerToggle"
      >
        매물
      </div>
    </section>
  </div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import http from "@/api/http.js";
import { mapState } from "vuex";
import { KEYWORD } from "@/constants/index.js";

export default {
  name: "KakaoMap",
  data() {
    return {
      // range slider
      sliderValue: "300", // 초기값 300m
      // kakao
      map: Object,
      markers: [],
      clusterer: null,
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
    setMapCenter(pos) {
      const coords = new kakao.maps.LatLng(pos.lat, pos.lng);
      this.map.setCenter(coords);
      this.setNorthEast();
      this.setSouthWest();
    },
    getHospitals() {
      const hospitalmap = {};
      const coords = {
        lat: this.selectedItem.lat,
        lng: this.selectedItem.lng,
      };
      this.setMapCenter(coords);
      // this.map.setCenter(coords);

      hospitalmap.current_lat = this.selectedItem.lat;
      hospitalmap.current_lng = this.selectedItem.lng;
      hospitalmap.sliderValue = this.sliderValue;
      // 현재 매물 마커 찍기
      this.addMarker(
        { lat: this.selectedItem.lat, lng: this.selectedItem.lng },
        this.selectedItem,
        0,
      );

      if (!hospitalmap) return;
      http.post(`/housedeal/hospital`, hospitalmap).then((resp) => {
        console.log("병원 리스트", resp.data);
        resp.data.forEach((e) => {
          this.addMarker(
            { lat: parseFloat(e.lat), lng: parseFloat(e.lon) },
            e,
            3, // 병원용 마크 하나 다시 만들어야 함.
          );
        });
      });
    },

    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);

      kakao.maps.event.addListener(this.map, "dragend", () => {
        this.deleteAllMarkers();
        this.setSouthWest();
        this.setNorthEast();
        this.sendListByRange();
        this.selectedItem = null;
        this.isKeywordOpen = false;
        this.$EventBus.$emit("closeDetail");
        // if (this.isKeywordOpen) this.sendCommercialByRange();
      });

      kakao.maps.event.addListener(this.map, "zoom_changed", () => {
        this.deleteAllMarkers();
        this.setSouthWest();
        this.setNorthEast();
        this.sendListByRange();
      });

      // kakao.maps.event.addListener(this.map, "center_changed", () => {
      //   this.setSouthWest();
      //   this.setNorthEast();
      // });

      this.map.setMaxLevel(5);
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=db837c20449f592fb6c253f478a766b8&libraries=services,clusterer,drawing";
      document.head.appendChild(script);
    },
    toggleContainer(type) {
      this.listContainerToggle = type;
      this.$emit("toggleContainer", type);
      this.isKeywordOpen = false;
      this.selectedItem = null;
    },
    selectStation(station) {
      console.log(station);
      this.selectedStation = station;
      this.stations = [];
      this.setSouthWest();
      this.setNorthEast();
      console.log(this.range);
      const coords = new kakao.maps.LatLng(station.lat, station.lng);
      this.map.setCenter(coords);
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
    addMarker(pos, obj, type) {
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

      //this.clusterer.addMarkers(this.markers);

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
      this.markers.push(marker);

      if (type >= 2) return;

      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
        position: marker.getPosition(),
        yAnchor: 1,
        xAnchor: 0.425,
        clickable: true,
      });

      // <--- make custom overlay
      const overlayContainer =
        type === 0 ? this.getHouseOverlay(obj) : this.getCommercialOverlay(obj);

      customOverlay.setContent(overlayContainer);
      //----> make custom overlay
      kakao.maps.event.addListener(marker, "click", () => {
        if (this.clickedOveray) {
          this.clickedOveray.setMap(null);
        }
        customOverlay.setMap(this.map);
        this.clickedOveray = customOverlay;
      });

      kakao.maps.event.addListener(this.map, "click", () => {
        customOverlay.setMap(null);
      });
    },
    sendListByRange() {
      const whereis = {};
      whereis.sw_lat = this.range.sw_Lat;
      whereis.sw_lng = this.range.sw_Lng;
      whereis.ne_lat = this.range.ne_Lat;
      whereis.ne_lng = this.range.ne_Lng;

      let posDatas = {
        positions: [],
      };
      http.post(`/housedeal/boundry`, whereis).then((resp) => {
        this.$EventBus.$emit("getListByLatLng", resp.data);
        resp.data.forEach((e) => {
          this.addMarker({ lat: e.lat, lng: e.lng }, e, 0);
          posDatas.positions.push({ lat: e.lat, lng: e.lng });
        });
        console.log(posDatas);
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
      this.deleteAllMarkers();
      const myKeywords = {}; //{ ...this.userKeyword };
      const coords = new kakao.maps.LatLng(
        this.selectedItem.lat,
        this.selectedItem.lng,
      );
      this.map.setCenter(coords);

      myKeywords.userKeyword = this.selectedKeywords;
      console.log("myKeywords", myKeywords);
      myKeywords.sw_lat = this.range.sw_Lat;
      myKeywords.sw_lng = this.range.sw_Lng;
      myKeywords.ne_lat = this.range.ne_Lat;
      myKeywords.ne_lng = this.range.ne_Lng;
      myKeywords.current_lat = this.selectedItem.lat;
      myKeywords.current_lng = this.selectedItem.lng;
      myKeywords.sliderValue = this.sliderValue;
      this.addMarker(
        { lat: this.selectedItem.lat, lng: this.selectedItem.lng },
        this.selectedItem,
        0,
      );

      if (!myKeywords) return;

      http.post(`/housedeal/commercial`, myKeywords).then((resp) => {
        console.log("상권리스트", resp.data);
        resp.data.forEach((e) => {
          this.addMarker({ lat: e.lat, lng: e.lng }, e, 1);
        });
      });
    },
    deleteAllMarkers() {
      this.markers.forEach((e) => e.setMap(null));
      this.markers = [];
    },
    getHouseOverlay(apt) {
      const overlayContainer = document.createElement("div");
      overlayContainer.className = "kakao-overlay-container";

      const bigImg = document.createElement("img");
      bigImg.setAttribute("src", require("@/assets/back1.jpg"));
      bigImg.className = "overlay-img";

      const detailContainer = document.createElement("div");
      detailContainer.className = "overlay-detail-container";

      const title = document.createElement("div");
      title.innerHTML = apt.aptName;
      title.className = "overlay-title";

      const hitContainer = document.createElement("div");
      const icon = document.createElement("i");
      const hit = document.createElement("label");
      hitContainer.className = "overlay-hit-container";
      hit.innerHTML = `${apt.hit}`;
      icon.className = "fa-brands fa-gratipay";
      hitContainer.append(icon, hit);

      const price = document.createElement("h3");
      price.className = "overlay-detail-subtitle";
      price.innerHTML = `${apt.dealAmount}만원`;

      const infoContainer = document.createElement("div");
      const infoItem1 = document.createElement("div");
      const infoItem2 = document.createElement("div");
      const infoItem3 = document.createElement("div");
      infoContainer.className = "overlay-info-container";
      infoItem1.className = "overlay-info-item";
      infoItem2.className = "overlay-info-item";
      infoItem3.className = "overlay-info-item";

      const infotitle1 = document.createElement("b");
      const infotitle2 = document.createElement("b");
      const infotitle3 = document.createElement("b");
      const infocontent1 = document.createElement("label");
      const infocontent2 = document.createElement("label");
      const infocontent3 = document.createElement("label");
      infotitle1.innerHTML = "면적";
      infotitle2.innerHTML = "층";
      infotitle3.innerHTML = "동";
      infocontent1.innerHTML = `${apt.area}m²`;
      infocontent2.innerHTML = `${apt.floor}층`;
      infocontent3.innerHTML = `${apt.dongName}`;
      infotitle1.className = "flexcenter";
      infotitle2.className = "flexcenter";
      infotitle3.className = "flexcenter";
      infocontent1.className = "flexcenter";
      infocontent2.className = "flexcenter";
      infocontent3.className = "flexcenter";

      infoItem1.append(infotitle1, infocontent1);
      infoItem2.append(infotitle2, infocontent2);
      infoItem3.append(infotitle3, infocontent3);

      infoContainer.append(infoItem1, infoItem2, infoItem3);
      detailContainer.append(title, price, infoContainer);
      overlayContainer.append(hitContainer, bigImg, detailContainer);

      return overlayContainer;
    },
    getCommercialOverlay(commercial) {
      const overlayContainer = document.createElement("div");
      overlayContainer.className = "kakao-overlay-container";

      const title = document.createElement("div");
      title.className = "overlay-title";
      title.innerHTML = commercial.name;

      const boon = document.createElement("div");
      boon.className = "overlay-detail-subtitle";
      boon.innerHTML = commercial.sobun;

      const addrContainer = document.createElement("div");
      addrContainer.innerHTML = commercial.jibun_address;
      addrContainer.className = "flexcenter";

      overlayContainer.append(title, boon, addrContainer);

      return overlayContainer;
    },
  },
};
</script>

<style scoped src="@/css/kakao.css"></style>
<style src="@/css/kakaoOverlay.css"></style>

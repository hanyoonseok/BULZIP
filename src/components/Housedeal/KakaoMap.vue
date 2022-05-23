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
        <h1>내 키워드</h1>
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
    </div>
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
      // kakao
      map: Object,
      markers: [],
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
    };
  },
  computed: {
    ...mapState("userStore", ["userKeyword"]),
  },
  created() {
    this.$EventBus.$on("openKeywordTab", () => {
      this.isKeywordOpen = true;
    });
    this.$EventBus.$on("closeKeywordTab", () => {
      this.isKeywordOpen = false;
    });
    this.$EventBus.$on("selectOneItem", (selectedItem) => {
      this.selectOneItem(selectedItem);
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
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);

      kakao.maps.event.addListener(this.map, "mouseup", () => {
        this.setSouthWest();
        this.setNorthEast();
        this.sendListByRange();
        if (this.isKeywordOpen) this.sendCommercialByRange();
      });

      kakao.maps.event.addListener(this.map, "zoom_changed", () => {
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

    selectStation(station) {
      console.log(station);
      this.selectedStation = station;
      this.stations = [];
      this.markers = [];
      this.setSouthWest();
      this.setNorthEast();
      console.log(this.range);
      this.setMapCenter({ lat: station.lat, lng: station.lon });
      this.addMarker(
        { lat: station.lat, lng: station.lon },
        station.station,
        2
      );
      //this.addMarker({ lat: station.lat, lng: station.lon });

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

      console.log(this.selectedKeywords);
    },
    setMapCenter(pos) {
      console.log(pos);
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
        imageSize = new kakao.maps.Size(
          type === 0 ? 100 : 70,
          type === 0 ? 100 : 70
        ), // 마커이미지의 크기입니다
        imageOption = { offset: new kakao.maps.Point(27, 69) }; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

      // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imageOption
        ),
        markerPosition = new kakao.maps.LatLng(pos.lat, pos.lng); // 마커가 표시될 위치입니다

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
        image: markerImage, // 마커이미지 설정
      });

      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(this.map);
    },
    sendListByRange() {
      const whereis = {};
      whereis.sw_lat = this.range.sw_Lat;
      whereis.sw_lng = this.range.sw_Lng;
      whereis.ne_lat = this.range.ne_Lat;
      whereis.ne_lng = this.range.ne_Lng;
      console.log("whereis", whereis);
      this.markers.forEach((e) => e.setMap(null));
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
    selectOneItem(selectedItem) {
      const myKeywords = {}; //{ ...this.userKeyword };
      this.setMapCenter({ lat: selectedItem.lat, lng: selectedItem.lng });
      myKeywords.userKeyword = this.selectedKeywords;
      console.log("myKeywords.userKeyword", myKeywords.userKeyword);
      myKeywords.sw_lat = this.range.sw_Lat;
      myKeywords.sw_lng = this.range.sw_Lng;
      myKeywords.ne_lat = this.range.ne_Lat;
      myKeywords.ne_lng = this.range.ne_Lng;
      myKeywords.current_lat = selectedItem.lat;
      myKeywords.current_lng = selectedItem.lng;
      //console.log(this.range, selectedItem);
      console.log("myKeywords", myKeywords);
      if (!myKeywords) return;

      http.post(`/housedeal/commercial`, myKeywords).then((resp) => {
        console.log(resp.data);
      });
    },
  },
};
</script>

<style scoped src="@/css/kakao.css">
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>

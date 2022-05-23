<template>
  <div id="map">
    <div class="kakao-station-container">
      <input
        type="text"
        class="kakao-station-input"
        placeholder="역 검색"
        :value="keyword"
        @input="changeKeyword"
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
        <div class="kakao-keyword-item">
          <input
            type="checkbox"
            class="hide-checkbox"
            @change="toggle('R09')"
            id="R09"
          />
          <label class="checkbox-btn" for="R09"
            ><font-awesome-icon icon="check"></font-awesome-icon></label
          >기타
        </div>
      </div>
    </div>
  </div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import http from "@/api/http.js";
import { mapState } from "vuex";

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
      keyword: "",
      stations: [],
      selectedStation: null,
      isKeywordOpen: false,
      isKeywordDetailOpen: false,
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
  },
  mounted() {
    //this.created();
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
        // const latlng = mouseEvent.latLng;
        // console.log(latlng.getLat(), latlng.getLng());

        this.setSouthWest();
        this.setNorthEast();
        this.sendListByRange();
        if (this.isKeywordOpen) this.sendCommercialByRange();
      });
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
      this.addMarker({ lat: station.lat, lng: station.lon });

      this.sendListByRange();
    },
    changeKeyword(e) {
      this.keyword = e.target.value;
    },
    searchStation() {
      if (this.keyword === "") {
        this.stations = [];
        return;
      }
      http.get(`/housedeal/subway/search/${this.keyword}`).then((resp) => {
        this.stations = resp.data;
      });
    },
    toggle(key) {
      // if (this.checkbox[key] === 0) {
      //   this.checkbox[key] = 1;
      //   this.checkedCnt++;
      // } else if (this.checkbox[key] === 1) {
      //   this.checkbox[key] = 0;
      //   this.checkedCnt--;
      // }
      console.log(key);
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
    addMarker(pos) {
      const coords = new kakao.maps.LatLng(pos.lat, pos.lng);
      const marker = new kakao.maps.Marker({
        map: this.map,
        position: coords,
      });
      this.markers.push(marker);
    },
    sendListByRange() {
      console.log(this.range);
      this.markers.forEach((e) => e.setMap(null));
      http
        .get(
          `/housedeal/boundary/${this.range.sw_Lat}/${this.range.sw_Lng}/${this.range.ne_Lat}/${this.range.ne_Lng}`,
        )
        .then((resp) => {
          this.$EventBus.$emit("getListByLatLng", resp.data);
          resp.data.forEach((e) => {
            this.addMarker({ lat: e.lat, lng: e.lng });
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
      const myKeywords = { ...this.userKeyword };
      myKeywords.sw_Lat = this.range.sw_Lat;
      myKeywords.sw_Lng = this.range.sw_Lng;
      myKeywords.ne_Lat = this.range.ne_Lat;
      myKeywords.ne_Lng = this.range.ne_Lng;
      myKeywords.selectedItem.lat = this.range.selectedItem.lat;
      myKeywords.selectedItem.lng = this.range.selectedItem.lng;
      console.log(this.range, selectedItem);
      console.log(myKeywords);
      if (!myKeywords) return;

      http.get(`/housedeal/commercial`, myKeywords).then((resp) => {
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

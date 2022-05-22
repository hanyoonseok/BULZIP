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
      <div class="kakao-station-list">
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
  </div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
import http from "@/api/http.js";

export default {
  name: "KakaoMap",
  data() {
    return {
      keyword: "",
      map: null,
      stations: [],
      selectedStation: null,
    };
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  methods: {
    ////////////////////////////////////////////////////
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 3,
      };
      this.map = new kakao.maps.Map(container, options);
      //마커추가하려면 객체를 아래와 같이 하나 만든다.
      // var marker = new kakao.maps.Marker({
      //   position: map.getCenter(),
      // });
      // marker.setMap(map);

      // ///////////////////클러스터 부분 ///////////////////
      // // https://apis.map.kakao.com/web/sample/basicClusterer/
      // var clusterer = new kakao.maps.MarkerClusterer({
      //   map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
      //   averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
      //   minLevel: 10, // 클러스터 할 최소 지도 레벨
      //   /////////////클릭 이벤트 추가//////////////////
      //   disableClickZoom: true, // 클러스터 마커를 클릭했을 때 지도가 확대되지 않도록 설정한다
      // });
      // 데이터를 가져와 마커를 생성하고 클러스터러 객체에 넘겨줍니다

      // 이부분에서 데이터를 가져와야함.........
      // 거래 매물 데이터들을 positions 로 가져와야함.

      // 데이터에서 좌표 값을 가지고 마커를 표시합니다
      // 마커 클러스터러로 관리할 마커 객체는 생성할 때 지도 객체를 설정하지 않습니다
      // var markers = $(data.positions).map(function (i, position) {
      //   return new kakao.maps.Marker({
      //     position: new kakao.maps.LatLng(position.lat, position.lng), // 위도 경도 입력
      //   });
      // });

      // // 클러스터러에 마커들을 추가합니다
      // clusterer.addMarkers(markers);

      // 마커 클러스터러에 클릭이벤트를 등록합니다
      // 마커 클러스터러를 생성할 때 disableClickZoom을 true로 설정하지 않은 경우
      // 이벤트 헨들러로 cluster 객체가 넘어오지 않을 수도 있습니다
      // v-on:click 으로 아래 리스너 호출한다
      // kakao.maps.event.addListener(
      //   clusterer,
      //   "clusterclick",
      //   function (cluster) {
      //     // 현재 지도 레벨에서 1레벨 확대한 레벨
      //     var level = map.getLevel() - 1;

      //     // 지도를 클릭된 클러스터의 마커의 위치를 기준으로 확대합니다
      //     map.setLevel(level, { anchor: cluster.getCenter() });
      //   }
      // );
      ////////////////////////////////////////////////////////
    },

    //////////////////////////////////////////////////////

    addScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=db837c20449f592fb6c253f478a766b8";
      document.head.appendChild(script);
    },

    selectStation(station) {
      this.selectedStation = station;
      this.stations = [];
      this.setMapCenter();
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
    setMapCenter() {
      console.log(this.selectedStation.lon, this.selectedStation.lat);
      const coords = new kakao.maps.LatLng(
        this.selectedStation.lat,
        this.selectedStation.lon,
      );
      // const marker = new kakao.maps.Marker({
      //   map: this.map,
      //   position: coords,
      // });
      // markers.push(marker);
      // const customOverlay = new kakao.maps.CustomOverlay({
      //   map: map,
      //   position: coords,
      //   content: getOverlay(name),
      //   yAnchor: 1.3,
      // });
      // customOverlay.setMap(map);

      // overlays.push(customOverlay);
      this.map.setCenter(coords);
    },
  },
};
</script>

<style scoped src="@/css/kakao.css"></style>

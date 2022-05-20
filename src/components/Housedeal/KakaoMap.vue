<template>
  <div id="map"></div>
</template>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
export default {
  name: "KakaoMap",
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
      var map = new kakao.maps.Map(container, options);
      //마커추가하려면 객체를 아래와 같이 하나 만든다.
      var marker = new kakao.maps.Marker({
        position: map.getCenter(),
      });
      marker.setMap(map);
    },
    addScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=db837c20449f592fb6c253f478a766b8";
      document.head.appendChild(script);
    },
  },
};
</script>

<style scoped src="@/css/kakao.css"></style>

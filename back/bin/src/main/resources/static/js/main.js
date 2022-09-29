const mapContainer = document.getElementById('main-map'), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.5666805, 126.9784147), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
  };
let map = new kakao.maps.Map(mapContainer, mapOption);
let markers = [];
let overlays = [];

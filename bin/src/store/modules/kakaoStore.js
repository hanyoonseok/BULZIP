const kakaoStore = {
  namespaced: true,
  state: {
    map: Object,
    markers: [],
    range: {
      neLat: 0,
      neLng: 0,
      swLat: 0,
      swLng: 0,
    },
  },
  mutations: {
    ADD_MARKER(state, payload) {
      const coords = new kakao.maps.LatLng(payload.lat, payload.lng);
      const marker = new kakao.maps.Marker({
        map: state.map,
        position: coords,
      });
      state.markers.push(marker);
    },
  },
  actions: {
    created({ dispatch }) {
      window.kakao && window.kakao.maps
        ? dispatch("initMap", { root: true })
        : dispatch("addScript", { root: true });
    },
    initMap(context) {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(37.5666805, 126.9784147),
        level: 3,
      };
      context.state.map = null;
      context.state.map = new kakao.maps.Map(container, options);
    },
    addScript(context) {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=db837c20449f592fb6c253f478a766b8";
      document.head.appendChild(script);
      context.state.map = null;
    },
    addMarker(context, pos) {
      console.log(pos);
      context.commit("ADD_MARKER", pos);
    },
    async setMapCenter(context, payload) {
      console.log(context.state.map, payload);

      // const coords = new kakao.maps.LatLng(payload.lat, payload.lng);
      // context.state.map.setCenter(coords);
      // await context.dispatch("setSouthWest");
      // await context.dispatch("setNorthEast");
      // context.dispatch("addMarker", {
      //   lat: payload.lat,
      //   lng: payload.lng,
      // });
    },
    setSouthWest(context) {
      var bounds = context.state.map.getBounds();
      context.state.range.swLat = bounds.getSouthWest().getLat();
      context.state.range.swLng = bounds.getSouthWest().getLng();
    },
    setNorthEast(context) {
      var bounds = context.state.map.getBounds();
      context.state.range.neLat = bounds.getNorthEast().getLat();
      context.state.range.neLng = bounds.getNorthEast().getLng();
    },
  },
};

export default kakaoStore;

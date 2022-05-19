<template>
  <div class="well" id="well">
    <div class="panel">
      <div
        id="main-backgroundimg"
        :style="{
          'background-image': 'url(' + require('../assets/back3.jpg') + ')',
        }"
      ></div>
      <div id="main-container">
        <div class="center-area">
          <div class="center-area-top">
            <div class="text-thin">Hello,</div>
            <div class="text-bold">BULZIP</div>
          </div>
          <div class="center-area-mid">
            벌써 찾았다, 내 집 <br />
            사용자 맞춤 주택 정보 제공 서비스
          </div>
          <button class="center-area-btn">LOGIN</button>
        </div>
        <div
          class="ballon"
          :style="{
            'background-image': 'url(' + require('../assets/back1.jpg') + ')',
            top: '100px',
            left: '160px',
          }"
        ></div>
        <div
          class="ballon"
          :style="{
            'background-image': 'url(' + require('../assets/back6.jpg') + ')',
            top: '10px',
            right: '160px',
          }"
        ></div>
        <div
          class="ballon"
          :style="{
            'background-image': 'url(' + require('../assets/back4.jpg') + ')',
            top: '75%',
            right: '500px',
          }"
        ></div>
      </div>
    </div>

    <div class="panel">
      <span class="top">&uarr;</span>
      <div
        id="main-backgroundimg"
        :style="{
          'background-image': 'url(' + require('../assets/back2.jpg') + ')',
        }"
      ></div>
      <div id="main-container">
        <div id="rolling-container">
          <h1>
            네가 찾던 집 <br />
            여기 다 있다.
          </h1>
          <div class="rollingbanner">
            <div class="wrap">
              <div class="roller">
                <ul>
                  <li class="kapi">
                    <img src="@/assets/back.jpg" alt="" />
                  </li>
                  <li class="kospi">
                    <img src="@/assets/back1.jpg" alt="" />
                  </li>
                  <li class="kapi">
                    <img src="@/assets/back2.jpg" alt="" />
                  </li>
                  <li class="kospi">
                    <img src="@/assets/back3.jpg" alt="" />
                  </li>
                  <li class="kapi">
                    <img src="@/assets/back4.jpg" alt="" />
                  </li>
                  <li class="kospi">
                    <img src="@/assets/back5.jpg" alt="" />
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div class="panel">
      <span class="top">&uarr;</span>
      <div
        id="main-backgroundimg"
        :style="{
          'background-image': 'url(' + require('../assets/back7.jpg') + ')',
        }"
      ></div>
      <div id="main-container">
        <div class="center-big-area">
          <div class="center-area-mid">
            <button class="center-area-btn">
              <i class="fa-solid fa-house-chimney"></i>Home
            </button>
            <button class="center-area-btn">
              <i class="fa-solid fa-building"></i>Apartment
            </button>
            <button class="center-area-btn">
              <i class="fa-solid fa-flag"></i>Notice
            </button>
            <div class="center-area-text">
              <b>벌집</b>에서 제공하는 다양한 서비스, <br />
              지금 만나보세요<br />
            </div>
          </div>
          <div
            class="center-area"
            :style="{
              'background-image': 'url(' + require('../assets/back6.jpg') + ')',
            }"
          ></div>
        </div>
        <div
          class="ballon"
          :style="{
            'background-image': 'url(' + require('../assets/back1.jpg') + ')',
            top: '40px',
            left: '160px',
          }"
        ></div>
        <div
          class="ballon"
          :style="{
            'background-image': 'url(' + require('../assets/back6.jpg') + ')',
            top: '10px',
            right: '160px',
          }"
        ></div>
        <div
          class="ballon"
          :style="{
            'background-image': 'url(' + require('../assets/back2.jpg') + ')',
            top: '75%',
            right: '500px',
          }"
        ></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HomeView",
  mounted() {
    //fullpage
    "use strict";
    /*[pan and well CSS scrolls]*/
    var pnls = document.querySelectorAll(".panel").length,
      scdir,
      hold = false;

    function _scrollY(obj) {
      var slength,
        plength,
        pan,
        step = 100,
        vh = window.innerHeight / 100,
        vmin = Math.min(window.innerHeight, window.innerWidth) / 100;
      if (
        (this !== undefined && this.id === "well") ||
        (obj !== undefined && obj.id === "well")
      ) {
        pan = this || obj;
        plength = parseInt(pan.offsetHeight / vh);
      }
      if (pan === undefined) {
        return;
      }
      plength = plength || parseInt(pan.offsetHeight / vmin);
      slength = parseInt(pan.style.transform.replace("translateY(", ""));
      if (scdir === "up" && Math.abs(slength) < plength - plength / pnls) {
        slength = slength - step;
      } else if (scdir === "down" && slength < 0) {
        slength = slength + step;
      } else if (scdir === "top") {
        slength = 0;
      }
      if (hold === false) {
        hold = true;
        pan.style.transform = "translateY(" + slength + "vh)";
        setTimeout(function () {
          hold = false;
        }, 1000);
      }
      console.log(
        scdir +
          ":" +
          slength +
          ":" +
          plength +
          ":" +
          (plength - plength / pnls),
      );
    }
    /*[swipe detection on touchscreen devices]*/
    function _swipe(obj) {
      var swdir,
        sX,
        sY,
        dX,
        dY,
        threshold = 100,
        /*[min distance traveled to be considered swipe]*/
        slack = 50,
        /*[max distance allowed at the same time in perpendicular direction]*/
        alT = 500,
        /*[max time allowed to travel that distance]*/
        elT /*[elapsed time]*/,
        stT; /*[start time]*/
      obj.addEventListener(
        "touchstart",
        function (e) {
          var tchs = e.changedTouches[0];
          swdir = "none";
          sX = tchs.pageX;
          sY = tchs.pageY;
          stT = new Date().getTime();
          //e.preventDefault();
        },
        false,
      );

      obj.addEventListener(
        "touchmove",
        function (e) {
          e.preventDefault(); /*[prevent scrolling when inside DIV]*/
        },
        false,
      );

      obj.addEventListener(
        "touchend",
        function (e) {
          var tchs = e.changedTouches[0];
          dX = tchs.pageX - sX;
          dY = tchs.pageY - sY;
          elT = new Date().getTime() - stT;
          if (elT <= alT) {
            if (Math.abs(dX) >= threshold && Math.abs(dY) <= slack) {
              swdir = dX < 0 ? "left" : "right";
            } else if (Math.abs(dY) >= threshold && Math.abs(dX) <= slack) {
              swdir = dY < 0 ? "up" : "down";
            }
            if (obj.id === "well") {
              if (swdir === "up") {
                scdir = swdir;
                _scrollY(obj);
              } else if (
                swdir === "down" &&
                obj.style.transform !== "translateY(0)"
              ) {
                scdir = swdir;
                _scrollY(obj);
              }
              e.stopPropagation();
            }
          }
        },
        false,
      );
    }
    /*[assignments]*/
    var well = document.getElementById("well");
    well.style.transform = "translateY(0)";
    well.addEventListener("wheel", function (e) {
      if (e.deltaY < 0) {
        scdir = "down";
      }
      if (e.deltaY > 0) {
        scdir = "up";
      }
      e.stopPropagation();
    });
    well.addEventListener("wheel", _scrollY);
    _swipe(well);
    var tops = document.querySelectorAll(".top");
    for (var i = 0; i < tops.length; i++) {
      tops[i].addEventListener("click", function () {
        scdir = "top";
        _scrollY(well);
      });
    }

    // 무한 롤링 배너
    let roller = document.querySelector(".roller");
    roller.id = "roller1";

    let clone = roller.cloneNode(true);
    clone.id = "roller2";
    document.querySelector(".wrap").appendChild(clone);

    document.querySelector("#roller1").style.left = "0px";
    document.querySelector("#roller2").style.left =
      document.querySelector(".roller ul").offsetWidth + "px";

    roller.classList.add("original");
    clone.classList.add("clone");
  },
};
</script>

<style scoped>
@import "@/css/home.css";
</style>

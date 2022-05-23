<template>
  <Bar
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="styles"
    :width="width"
    :height="height"
  />
</template>

<script>
//import { Line } from "vue-chartjs";
//import { Bar } from "vue-chartjs";
// vue2
import http from "@/api/http.js";
import { Bar } from "vue-chartjs/legacy";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  // eslint-disable-next-line
  BarElement,
  // eslint-disable-next-line
  CategoryScale,
  // eslint-disable-next-line
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  // eslint-disable-next-line
  BarElement,
  // eslint-disable-next-line
  CategoryScale,
  // eslint-disable-next-line
  LinearScale
);

export default {
  name: "BarChart",
  components: { Bar },
  props: {
    chartId: {
      type: String,
      default: "bar-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 400,
    },
    height: {
      type: Number,
      default: 400,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Object,
      default: () => {},
    },
    item: Object, // 여기에 아파트 매물 하나 들어있음.
  },
  data() {
    return {
      labels_list: [],
      datas_list: [],
      // chartData: {
      //   labels: this.labels_list, //["January", "February", "March"],
      //   datasets: [
      //     {
      //       label: "거래 가격", // 데이터 라벨
      //       backgroundColor: "#f87979", // CSS 넣을 때
      //       data: this.datas_list, //[40, 20, 12],
      //     },
      //   ],
      // },
      chartOptions: {
        responsive: true,
      },
    };
  },

  //computed로 데이터 가져올 때 사용
  computed: {
    chartData() {
      let a = {
        labels: this.labels_list, //["January", "February", "March"],
        datasets: [
          {
            label: "거래 가격 (단위:만원)", // 데이터 라벨
            backgroundColor: "#f87979", // CSS 넣을 때
            data: this.datas_list, //[40, 20, 12],
          },
        ],
      };
      return a; /* mutable chart data */
    },
    // chartOptions() {
    //   return this.datas_list; /* mutable chart options */
    // },
  },

  methods: {
    selectList(aptCode, floor) {
      http.get(`/housedeal/timeseries/${aptCode}/${floor}`).then((resp) => {
        let aa = [];
        let bb = [];
        resp.data.forEach(function (element) {
          const each_date =
            String(element.dealYear) +
            "." +
            String(element.dealMonth) +
            "." +
            String(element.dealDay);
          const each_dealAmount = parseInt(element.dealAmount.replace(",", ""));

          aa.push(each_date);
          bb.push(each_dealAmount);
        });
        this.labels_list = aa;
        this.datas_list = bb;
      });
    },
  },
  created() {
    this.selectList(this.item.aptCode, this.item.floor);
  },
};
</script>

<style></style>

import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import UserView from "@/views/UserView.vue";
import HousedealView from "@/views/HousedealView.vue";
import NoticeView from "@/views/NoticeView.vue";
import AdminView from "@/views/AdminView.vue";
import QnaView from "@/views/QnaView.vue";

import CommercialInfo from "@/components/Housedeal/CommercialInfo.vue";
import HousedealDetail from "@/components/Housedeal/HousedealDetail.vue";
import HousedealList from "@/components/Housedeal/HousedealList.vue";

import NoticeList from "@/components/Notice/NoticeList.vue";
import NoticeWrite from "@/components/Notice/NoticeWrite.vue";
import NoticeDetail from "@/components/Notice/NoticeDetail.vue";

import UserFindpw from "@/components/User/UserFindpw.vue";
import UserInfo from "@/components/User/UserInfo.vue";
import UserList from "@/components/User/UserList.vue";
import UserLogin from "@/components/User/UserLogin.vue";
import UserRegist from "@/components/User/UserRegist.vue";

import QnaDetail from "@/components/QnaBoard/QnaDetail.vue";
import QnaInsert from "@/components/QnaBoard/QnaInsert.vue";
import QnaList from "@/components/QnaBoard/QnaList.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/qna",
    name: "qna",
    component: QnaView,
    children: [
      {
        path: "insert",
        name: "qnaInsert",
        component: QnaInsert,
      },
      {
        path: "list",
        name: "qnaList",
        component: QnaList,
      },
      {
        path: "detail/:p/:qna_id",
        name: "qnaDetail",
        component: QnaDetail,
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: UserView,
    children: [
      {
        path: "login",
        name: "login",
        component: UserLogin,
      },
      {
        path: "regist",
        name: "regist",
        component: UserRegist,
      },
      {
        path: "findpw",
        name: "findpw",
        component: UserFindpw,
      },
      {
        path: "info/:id",
        name: "info",
        component: UserInfo,
      },
    ],
  },
  {
    path: "/user",
    name: "admin",
    component: AdminView,
    children: [
      {
        path: "list",
        name: "userlist",
        component: UserList,
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    component: NoticeView,
    redirect: "/notice/list",
    children: [
      {
        path: "list",
        name: "noticelist",
        component: NoticeList,
      },
      {
        path: "write",
        name: "write",
        component: NoticeWrite,
      },
      {
        path: "detail/:notice_no",
        name: "detail",
        component: NoticeDetail,
      },
    ],
  },
  {
    path: "/housedeal",
    name: "housedeal",
    component: HousedealView,
    redirect: "/housedeal/list",
    children: [
      {
        path: "list",
        name: "list",
        component: HousedealList,
      },
      {
        path: "detail/:id",
        name: "detail",
        component: HousedealDetail,
      },
      {
        path: "commercial",
        name: "commercial",
        component: CommercialInfo,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

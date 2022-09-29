import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "@/views/HomeView.vue";
import UserView from "@/views/UserView.vue";
import HousedealView from "@/views/HousedealView.vue";
import NoticeView from "@/views/NoticeView.vue";
import AdminView from "@/views/AdminView.vue";
import QnaView from "@/views/QnaView.vue";

import CommercialInfo from "@/components/Housedeal/CommercialInfo.vue";
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

import store from "@/store/index.js";

Vue.use(VueRouter);

// https://router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfo = store._actions["userStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다..");
    next("/user/login");
  } else {
    next();
  }
};

const onlyAdmin = async (to, from, next) => {
  // console.log(store);
  const checkUserInfo = store.getters["userStore/checkUserInfo"];
  const getUserInfo = store._actions["userStore/getUserInfo"];
  let token = sessionStorage.getItem("access-token");
  if (checkUserInfo == null && token) {
    await getUserInfo(token);
  }
  if (checkUserInfo.role !== 1) {
    alert("관리자만 입장 가능한 페이지입니다..");
    next("/");
  } else {
    next();
  }
};

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
    redirect: "/qna/list",
    children: [
      {
        path: "insert",
        name: "qnaInsert",
        beforeEnter: onlyAuthUser,
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
        beforeEnter: onlyAuthUser,
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
        path: "info/:userid",
        name: "info",
        beforeEnter: onlyAuthUser,
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
        beforeEnter: onlyAdmin,
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
        name: "noticewrite",
        beforeEnter: onlyAdmin,
        component: NoticeWrite,
      },
      {
        path: "detail/:notice_no",
        name: "noticedetail",
        beforeEnter: onlyAdmin,
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
        name: "housedeallist",
        component: HousedealList,
        beforeEnter: onlyAuthUser,
      },
      {
        path: "commercial",
        name: "housedealcommercial",
        beforeEnter: onlyAuthUser,
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

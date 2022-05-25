<template>
  <div class="page-container">
    <div class="left-container all-center">
      <h1>회원정보</h1>
      <input type="text" class="user-input" v-model="user.userId" disabled />
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호"
        v-model="user.password"
      />
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호 확인"
        v-model="user.newpassword"
      />
      <input type="text" class="user-input" v-model="user.name" />
      <input type="text" class="user-input" v-model="user.phone" />
      <button class="form-btn" @click="update">회원정보 수정</button>
      <button class="form-btn" @click="deleteMe">회원정보 삭제</button>
    </div>
    <section
      class="right-container"
      style="
        background-image: none;
        padding: 4rem 4rem 4rem 0;
        flex: 1.5;
        overflow-y: auto;
      "
    >
      <CheckBox :userKeyword="userKeyword" @toggle="toggle" />
    </section>
  </div>
</template>

<script>
import http from "@/api/http.js";
import { mapState, mapActions } from "vuex";
import CheckBox from "@/components/User/item/CheckBox.vue";

export default {
  data() {
    return {
      user: {
        id: "",
        userId: "",
        password: "",
        newpassword: "",
        name: "",
        phone: "",
        role: "",
      },
      checkbox: {
        keyword_N01: 0,
        keyword_D06: 0,
        keyword_F11: 0,
        keyword_D10: 0,
        keyword_N07: 0,
        keyword_Q05: 0,
        keyword_F09: 0,
        keyword_R10: 0,
        keyword_O02: 0,
        keyword_N02: 0,
        keyword_O05: 0,
        keyword_Q10: 0,
        keyword_Q15: 0,
        keyword_Q04: 0,
        keyword_D08: 0,
        keyword_F06: 0,
        keyword_D02: 0,
        keyword_F02: 0,
        keyword_N04: 0,
        keyword_P02: 0,
        keyword_P03: 0,
        keyword_D09: 0,
        keyword_Q06: 0,
        keyword_N03: 0,
        keyword_N05: 0,
        keyword_D14: 0,
        keyword_R08: 0,
        keyword_D11: 0,
        keyword_Q09: 0,
        keyword_D01: 0,
        keyword_Q13: 0,
        keyword_D05: 0,
        keyword_D20: 0,
        keyword_F01: 0,
        keyword_Q03: 0,
        keyword_Q08: 0,
        keyword_D03: 0,
        keyword_F16: 0,
        keyword_Q02: 0,
        keyword_D13: 0,
        keyword_D21: 0,
        keyword_D04: 0,
        keyword_O03: 0,
        keyword_Q12: 0,
        keyword_Q07: 0,
        keyword_R13: 0,
        keyword_R01: 0,
        keyword_R04: 0,
        keyword_R07: 0,
        keyword_R05: 0,
        keyword_R03: 0,
        keyword_R02: 0,
        keyword_R06: 0,
        keyword_R09: 0,
        keyword_Q01: 0,
        keyword_O01: 0,
        keyword_D16: 0,
      },
      checkedCnt: 0,
    };
  },
  components: {
    CheckBox,
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo", "userKeyword"]),
  },
  created() {
    http.get(`/user/detail/${this.$route.params.userid}`).then((resp) => {
      if (!resp.data) {
        alert("정보 로드 실패");
      } else {
        this.user = resp.data;
        this.user.password = "";
        this.user.newpassword = "";
      }
    });
    if (this.userKeyword) {
      this.checkbox = this.userKeyword;
      const keys = Object.keys(this.userKeyword);
      keys.forEach((e) => {
        if (this.userKeyword[e] === 1) {
          this.checkedCnt++;
        }
      });
    }
  },
  methods: {
    ...mapActions("userStore", ["getUserInfo"]),
    update() {
      console.log(this.user);
      console.log(this.checkbox, this.checkedCnt);
      if (this.checkedCnt < 10) {
        alert("키워드를 10개 이상 체크해주세요!.");
        return;
      } else if (this.user.password !== this.user.newpassword) {
        alert("새 비밀번호가 일치하지 않습니다");
        return;
      }

      http.put(`/user/update`, this.user).then((resp) => {
        if (resp.data === "success") {
          console.log(this.checkbox);
          http.put(`/user/keyword/update`, this.checkbox).then((resp) => {
            if (resp.data === "success") {
              alert("정보 수정 완료");
              const token = sessionStorage.getItem("access-token");
              this.getUserInfo(token);
            } else {
              alert("에러 발생");
            }
            this.$router.push("/");
          });
        } else {
          alert("정보 수정 실패");
        }
      });
    },
    deleteMe() {
      http.delete(`/user/delete/${this.user.userId}`).then((resp) => {
        if (resp.data === "success") {
          alert("정보 삭제 완료");
          localStorage.removeItem("loginInfo");
          location.href = "/";
        } else {
          alert("정보 삭제 실패");
        }
      });
    },
    toggle(key) {
      console.log(key, this.checkedCnt);
      if (this.checkbox[key] === 0) {
        this.checkbox[key] = 1;
        this.checkedCnt++;
      } else if (this.checkbox[key] === 1) {
        this.checkbox[key] = 0;
        this.checkedCnt--;
      }
      console.log(this.checkbox);
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

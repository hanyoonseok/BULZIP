<template>
  <div class="page-container">
    <form class="left-container" v-on:submit.prevent="regist">
      <h1>REGIST</h1>
      <input
        type="text"
        class="user-input"
        placeholder="아이디 6~16자"
        required
        v-model="userId"
        @keyup="checkId"
      />
      <p
        :class="{
          'text-danger': !isValid,
          'text-primary': isValid,
        }"
      >
        {{ idresult }}
      </p>
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호"
        required
        v-model="password"
      />
      <input
        type="password"
        class="user-input"
        placeholder="비밀번호 재입력"
        required
        v-model="passwordcheck"
        @keyup="checkPw"
      />
      <p class="text-danger" v-show="!pwresult">비밀번호가 일치하지 않습니다</p>
      <input
        type="text"
        class="user-input"
        placeholder="이름"
        required
        v-model="name"
      />
      <input
        type="text"
        class="user-input"
        placeholder="전화번호"
        required
        v-model="phone"
      />

      <input
        type="submit"
        class="form-btn"
        value="회원가입"
        :disabled="!isValid || !pwresult"
      />

      <span class="small-text-container">
        <router-link to="/user/login" class="small-text"
          >로그인 &nbsp;&nbsp;</router-link
        >
        <router-link to="/user/findpw" class="small-text"
          >&nbsp;&nbsp;비밀번호 찾기</router-link
        >
      </span>
    </form>
    <section
      class="right-container"
      style="
        background-image: none;
        padding: 4rem 4rem 4rem 0;
        flex: 1.5;
        overflow-y: auto;
      "
    >
      <!-- 체크 박스 -->
      <CheckBox @toggle="toggle" />
    </section>
  </div>
</template>

<script>
import http from "@/api/http.js";
import CheckBox from "@/components/User/item/CheckBox.vue";

export default {
  components: {
    CheckBox,
  },

  data() {
    return {
      userId: "",
      password: "",
      passwordcheck: "",
      name: "",
      phone: "",
      idresult: "",
      isValid: false,
      pwresult: true,
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
  methods: {
    regist() {
      console.log(this.userId, this.password, this.name, this.phone);
      if (this.checkedCnt < 10) {
        alert("키워드를 10개 이상 체크해주세요!.");
        return;
      }
      http
        .post(`/user/register`, {
          userId: this.userId,
          password: this.password,
          name: this.name,
          phone: this.phone,
        })
        .then((resp) => {
          if (resp.data == "success") {
            this.checkbox.user_id = this.userId;
            console.log("check ", this.checkbox);
            http.post(`/user/keyword/insert`, this.checkbox).then((resp) => {
              console.log(resp.data);
              console.log(this.checkbox);
              alert("회원가입 성공");
              this.$router.push("/user/login");
            });
          } else {
            alert(resp.data);
          }
        });

      return false;
    },
    checkId() {
      if (this.userId.length < 6 || this.userId.length > 16) {
        this.idresult = "아이디는 6자이상 16자이하입니다.";
        if (this.userId.length === 0) this.idresult = "";
        this.isValid = false;
      } else {
        http.get(`/user/idCheck/${this.userId}`).then((resp) => {
          console.log(resp.data);
          if (resp.data === "avaliable") {
            this.idresult = `${this.userId}는 사용 가능합니다.`;
            this.isValid = true;
          } else {
            this.idresult = `${this.userId}는 사용할 수 없습니다.`;
            this.isValid = false;
          }
        });
      }
    },
    checkPw() {
      if (this.password !== this.passwordcheck) {
        this.pwresult = false;
      } else {
        this.pwresult = true;
      }
    },
    toggle(key) {
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

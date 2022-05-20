<template>
  <!-- 체크박스 -->
  <form method="get" action="">
    <p>관심 상권 키워드 (10개 선택)</p>
    <!-- 소매 : D-->
    <div>대분류 : 소매</div>
    <label
      ><input
        type="checkbox"
        value="D01"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      음/식료품소매</label
    >
    <label
      ><input
        type="checkbox"
        value="D02"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      선물/팬시/기념품</label
    >
    <label
      ><input
        type="checkbox"
        value="D03"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      종합소매점</label
    >
    <label
      ><input
        type="checkbox"
        value="D04"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      취미/오락관련소매</label
    >
    <label
      ><input
        type="checkbox"
        value="D05"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      의복의류</label
    >
    <label
      ><input
        type="checkbox"
        value="D06"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      가방/신발/액세서리</label
    >
    <label
      ><input
        type="checkbox"
        value="D08"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      사무/문구/컴퓨터</label
    >
    <label
      ><input
        type="checkbox"
        value="D09"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      애견/애완/동물</label
    >
    <label
      ><input
        type="checkbox"
        value="D10"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      건강/미용식품</label
    >
    <label
      ><input
        type="checkbox"
        value="D11"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      유아용품</label
    >
    <label
      ><input
        type="checkbox"
        value="D13"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      책/서적/도서</label
    >

    <label
      ><input
        type="checkbox"
        value="D14"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      운동/경기용품소매</label
    >
    <label
      ><input
        type="checkbox"
        value="D16"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      화장품소매</label
    >
    <label
      ><input
        type="checkbox"
        value="D20"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      의약/의료품소매</label
    >
    <label
      ><input
        type="checkbox"
        value="D21"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      철물/난방/건설자재소매</label
    >

    <!-- 생활서비스 : F -->
    <div>대분류 : 생활서비스</div>
    <label
      ><input
        type="checkbox"
        value="F01"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      이/미용/건강</label
    >
    <label
      ><input
        type="checkbox"
        value="F02"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      세탁/가사서비스</label
    >

    <label
      ><input
        type="checkbox"
        value="F06"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      사진</label
    >
    <label
      ><input
        type="checkbox"
        value="F09"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      대중목욕탕/휴게</label
    >
    <label
      ><input
        type="checkbox"
        value="F11"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      개인서비스</label
    >
    <label
      ><input
        type="checkbox"
        value="F16"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      주유소/충전소</label
    >

    <!-- 관광/여가/오락 : N -->
    <div>대분류 : 관광/여가/오락</div>
    <label
      ><input
        type="checkbox"
        value="N01"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      PC/오락/당구/볼링등</label
    >
    <label
      ><input
        type="checkbox"
        value="N02"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      무도/유흥/가무</label
    >
    <label
      ><input
        type="checkbox"
        value="N03"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      연극/영화/극장</label
    >

    <label
      ><input
        type="checkbox"
        value="N04"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      스포츠/운동</label
    >
    <label
      ><input
        type="checkbox"
        value="N05"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      요가/단전/마사지</label
    >
    <label
      ><input
        type="checkbox"
        value="N07"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      놀이/여가/취미</label
    >

    <!-- 숙박 : O -->
    <div>대분류 : 숙박</div>
    <label
      ><input
        type="checkbox"
        value="O01"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      호텔/콘도</label
    >
    <label
      ><input
        type="checkbox"
        value="O02"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      모텔/여관/여인숙</label
    >
    <label
      ><input
        type="checkbox"
        value="O03"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      캠프/별장/펜션</label
    >
    <label
      ><input
        type="checkbox"
        value="O05"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      민박/하숙</label
    >

    <!-- 스포츠 : P -->
    <div>대분류 : 스포츠</div>
    <label
      ><input
        type="checkbox"
        value="P02"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      실내운동시설</label
    >
    <label
      ><input
        type="checkbox"
        value="P03"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      실외운동시설</label
    >

    <!-- 음식 : Q-->
    <div>대분류 : 음식</div>
    <label
      ><input
        type="checkbox"
        value="Q01"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      한식</label
    >
    <label
      ><input
        type="checkbox"
        value="Q02"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      중식</label
    >
    <label
      ><input
        type="checkbox"
        value="Q03"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      일식/수산물</label
    >
    <label
      ><input
        type="checkbox"
        value="Q04"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      분식</label
    >
    <label
      ><input
        type="checkbox"
        value="Q05"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      닭/오리요리</label
    >
    <label
      ><input
        type="checkbox"
        value="Q06"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      양식</label
    >
    <label
      ><input
        type="checkbox"
        value="Q07"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      패스트푸드</label
    >
    <label
      ><input
        type="checkbox"
        value="Q08"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      제과제빵떡케익</label
    >
    <label
      ><input
        type="checkbox"
        value="Q09"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      유흥주점</label
    >
    <label
      ><input
        type="checkbox"
        value="Q10"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      별식/퓨전요리</label
    >
    <label
      ><input
        type="checkbox"
        value="Q12"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      커피점/카페</label
    >
    <label
      ><input
        type="checkbox"
        value="Q13"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      음식배달서비스</label
    >
    <label
      ><input
        type="checkbox"
        value="Q15"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      부페</label
    >

    <!-- 학문/교육 : R-->
    <div>대분류 : 학문/교육</div>
    <label
      ><input
        type="checkbox"
        value="R01"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원-보습교습입시</label
    >

    <label
      ><input
        type="checkbox"
        value="R02"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원-창업취업취미</label
    >
    <label
      ><input
        type="checkbox"
        value="R03"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원-자격/국가고시</label
    >

    <label
      ><input
        type="checkbox"
        value="R04"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원-어학</label
    >
    <label
      ><input
        type="checkbox"
        value="R05"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원-음악미술무용</label
    >
    <label
      ><input
        type="checkbox"
        value="R06"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원-컴퓨터</label
    >
    <label
      ><input
        type="checkbox"
        value="R07"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원-예능취미체육</label
    >
    <label
      ><input
        type="checkbox"
        value="R08"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      유아교육</label
    >
    <label
      ><input
        type="checkbox"
        value="R09"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학원기타</label
    >
    <label
      ><input
        type="checkbox"
        value="R10"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      도서관/독서실</label
    >
    <label
      ><input
        type="checkbox"
        value="R13"
        v-model="array"
        true-value="1"
        false-value="0"
      />
      학문교육기타</label
    >

    <p>
      <input type="submit" value="Submit" />
      <input type="reset" value="Reset" />
    </p>
  </form>
</template>

<script>
import http from "@/api/http.js";

export default {
  data() {
    return {
      array: [],
      //   N01: "",
      //   D06: "",
      //   F11: "",
      //   D10: "",
      //   N07: "",
      //   Q05: "",
      //   F09: "",
      //   R10: "",
      //   O02: "",
      //   N02: "",
      //   O05: "",
      //   Q10: "",
      //   Q15: "",
      //   Q04: "",
      //   D08: "",
      //   F06: "",
      //   D02: "",
      //   F02: "",
      //   N04: "",
      //   P02: "",
      //   P03: "",
      //   D09: "",
      //   Q06: "",
      //   N03: "",
      //   N05: "",
      //   D14: "",
      //   R08: "",
      //   D11: "",
      //   Q09: "",
      //   D01: "",
      //   Q13: "",
      //   D05: "",
      //   D20: "",
      //   F01: "",
      //   Q03: "",
      //   Q08: "",
      //   D03: "",
      //   F16: "",
      //   Q02: "",
      //   D13: "",
      //   D21: "",
      //   D04: "",
      //   O03: "",
      //   Q12: "",
      //   Q07: "",
      //   R13: "",
      //   R01: "",
      //   R04: "",
      //   R07: "",
      //   R05: "",
      //   R03: "",
      //   R02: "",
      //   R06: "",
      //   R09: "",
      //   Q01: "",
      //   O01: "",
      //   D16: "",
    };
  },
  methods: {
    createQna() {
      http
        .post(`/qna/insert`, {
          qna_title: this.title,
          qna_content: this.content,
          user_id: this.userInfo.userId,
          user_password: this.userInfo.password,
          qna_date: new Date(),
        })
        .then((resp) => {
          console.log(resp);
          alert("작성이 완료되었습니다.");
          this.$router.push("/qna/list");
        });
    },
  },
};
</script>

<style></style>

<template>
  <!-- 체크박스 -->
  <section class="checkbox-section">
    <p class="checkbox-title">관심 상권 키워드 (10개 선택)</p>
    <div class="checkbox-select-area">
      <div
        class="checkbox-select-item"
        v-for="(item, index) in array"
        :key="index"
      >
        {{ item }}
      </div>
    </div>
    <div class="checkbox-division-container">
      <div class="checkbox-division">
        <div class="checkbox-division-title">소매</div>
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="음/식료품소매"
          @change="toggle('D01')"
          id="D01"
        />
        <label class="checkbox-btn" for="D01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >음식료품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="선물/팬시"
          @change="toggle('D02')"
          id="D02"
        />
        <label class="checkbox-btn" for="D02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >선물팬시<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="종합소매"
          @change="toggle('D03')"
          id="D03"
        />
        <label class="checkbox-btn" for="D03"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >종합소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="취미/오락소매"
          @change="toggle('D04')"
          id="D04"
        />
        <label class="checkbox-btn" for="D04">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >취미오락소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="의복의류"
          @change="toggle('D05')"
          id="D05"
        />
        <label class="checkbox-btn" for="D05"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >의복의류<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="가방/신발/액세서리"
          @change="toggle('D06')"
          id="D06"
        />
        <label class="checkbox-btn" for="D06">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >가방/신발/액세서리<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="사무/문구/컴퓨터"
          @change="toggle('D08')"
          id="D08"
        />
        <label class="checkbox-btn" for="D08">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >사무/문구/컴퓨터<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="애견/애완/동물"
          @change="toggle('D09')"
          id="D09"
        />
        <label class="checkbox-btn" for="D09">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >애견/애완/동물<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="건강/미용식품"
          @change="toggle('D10')"
          id="D10"
        />
        <label class="checkbox-btn" for="D10">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >건강/미용식품<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유아용품"
          @change="toggle('D11')"
          id="D11"
        />
        <label class="checkbox-btn" for="D11"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >유아용품<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="책/서적/도서"
          @change="toggle('D13')"
          id="D13"
        />
        <label class="checkbox-btn" for="D13">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >책/서적/도서<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="운동/경기용품소매"
          @change="toggle('D14')"
          id="D14"
        />
        <label class="checkbox-btn" for="D14">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >운동/경기용품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="화장품소매"
          @change="toggle('D16')"
          id="D16"
        />
        <label class="checkbox-btn" for="D16"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >화장품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="의약/의료품소매"
          @change="toggle('D20')"
          id="D20"
        />
        <label class="checkbox-btn" for="D20">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >의약/의료품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="철물/난방소매"
          @change="toggle('D21')"
          id="D21"
        />
        <label class="checkbox-btn" for="D21">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >철물/난방소매<br />
      </div>
      <div class="checkbox-division">
        <!-- 생활서비스 : F -->
        <div class="checkbox-division-title">생활서비스</div>
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="미용/건강"
          @change="toggle('F01')"
          id="F01"
        />
        <label class="checkbox-btn" for="F01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >미용/건강<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="세탁/가사서비스"
          @change="toggle('F02')"
          id="F02"
        />
        <label class="checkbox-btn" for="F02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >세탁/가사서비스<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="사진"
          @change="toggle('F06')"
          id="F06"
        />
        <label class="checkbox-btn" for="F06"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >사진<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="대중목욕탕/휴게"
          @change="toggle('F09')"
          id="F09"
        />
        <label class="checkbox-btn" for="F09">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >대중목욕탕/휴게<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="개인서비스"
          @change="toggle('F11')"
          id="F11"
        />
        <label class="checkbox-btn" for="F11"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >개인서비스<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="주유소"
          @change="toggle('F16')"
          id="F16"
        />
        <label class="checkbox-btn" for="F16"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >주유소<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="실내운동시설"
          @change="toggle('P02')"
          id="P02"
        />
        <label class="checkbox-btn" for="P02"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >실내운동시설<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="실외운동시설"
          @change="toggle('P03')"
          id="P03"
        />
        <label class="checkbox-btn" for="P03"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >실외운동시설<br />
      </div>
      <div class="checkbox-division">
        <!-- 관광/여가/오락 : N -->
        <div class="checkbox-division-title">관광/여가/오락</div>
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="PC/오락/당구/볼링"
          @change="toggle('N01')"
          id="N01"
        />
        <label class="checkbox-btn" for="N01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >PC/오락/당구/볼링<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유흥/가무"
          @change="toggle('N02')"
          id="N02"
        />
        <label class="checkbox-btn" for="N02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >유흥/가무<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="연극/영화/극장"
          @change="toggle('N03')"
          id="N03"
        />
        <label class="checkbox-btn" for="N03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >연극/영화/극장<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="스포츠/운동"
          @change="toggle('N04')"
          id="N04"
        />
        <label class="checkbox-btn" for="N04">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >스포츠/운동<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="요가/단전/마사지"
          @change="toggle('N05')"
          id="N05"
        />
        <label class="checkbox-btn" for="N05">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >요가/단전/마사지<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="놀이/여가/취미"
          @change="toggle('N07')"
          id="N07"
        />
        <label class="checkbox-btn" for="N07">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >놀이/여가/취미<br />
      </div>
    </div>
    <div class="checkbox-division-container">
      <div class="checkbox-division">
        <!-- 숙박 : O -->
        <div class="checkbox-division-title">숙박</div>
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="호텔/콘도"
          @change="toggle('O01')"
          id="O01"
        />
        <label class="checkbox-btn" for="O01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >호텔/콘도<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="호텔/모텔"
          @change="toggle('O02')"
          id="O02"
        />
        <label class="checkbox-btn" for="O02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >호텔/모텔<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="캠프/별장/펜션"
          @change="toggle('O03')"
          id="O03"
        />
        <label class="checkbox-btn" for="O03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >캠프/별장/펜션<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="민박/하숙"
          @change="toggle('O05')"
          id="O05"
        />
        <label class="checkbox-btn" for="O05">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >민박/하숙<br />
      </div>
      <div class="checkbox-division">
        <!-- 음식 : Q-->
        <div class="checkbox-division-title">음식</div>
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="한식"
          @change="toggle('Q01')"
          id="Q01"
        />
        <label class="checkbox-btn" for="Q01"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >한식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="중식"
          @change="toggle('Q02')"
          id="Q02"
        />
        <label class="checkbox-btn" for="Q02"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >중식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="일식/수산물"
          @change="toggle('Q03')"
          id="Q03"
        />
        <label class="checkbox-btn" for="Q03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >일식/수산물<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="분식"
          @change="toggle('Q04')"
          id="Q04"
        />
        <label class="checkbox-btn" for="Q04"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >분식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="닭/오리요리"
          @change="toggle('Q05')"
          id="Q05"
        />
        <label class="checkbox-btn" for="Q05">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >닭/오리요리<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="양식"
          @change="toggle('Q06')"
          id="Q06"
        />
        <label class="checkbox-btn" for="Q06"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >양식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="패스트푸드"
          @change="toggle('Q07')"
          id="Q07"
        />
        <label class="checkbox-btn" for="Q07"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >패스트푸드<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="제과제빵"
          @change="toggle('Q08')"
          id="Q08"
        />
        <label class="checkbox-btn" for="Q08"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >제과제빵<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유흥주점"
          @change="toggle('Q09')"
          id="Q09"
        />
        <label class="checkbox-btn" for="Q09"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >유흥주점<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="별식/퓨전요리"
          @change="toggle('Q10')"
          id="Q10"
        />
        <label class="checkbox-btn" for="Q10">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >별식/퓨전요리<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="커피점/카페"
          @change="toggle('Q12')"
          id="Q12"
        />
        <label class="checkbox-btn" for="Q12">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >커피점/카페<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="음식배달"
          @change="toggle('Q13')"
          id="Q13"
        />
        <label class="checkbox-btn" for="Q13"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >음식배달<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="부페"
          @change="toggle('Q15')"
          id="Q15"
        />
        <label class="checkbox-btn" for="Q15"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >부페<br />
      </div>
      <div class="checkbox-division">
        <!-- 학문/교육 : R-->
        <div class="checkbox-division-title">학문/교육</div>
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="입시"
          @change="toggle('R01')"
          id="R01"
        />
        <label class="checkbox-btn" for="R01"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >입시<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="창업취업취미"
          @change="toggle('R02')"
          id="R02"
        />
        <label class="checkbox-btn" for="R02"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >창업취업취미<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="자격/국가고시"
          @change="toggle('R03')"
          id="R03"
        />
        <label class="checkbox-btn" for="R03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >자격/국가고시<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="어학"
          @change="toggle('R04')"
          id="R04"
        />
        <label class="checkbox-btn" for="R04"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >어학<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="예체능"
          @change="toggle('R05')"
          id="R05"
        />
        <label class="checkbox-btn" for="R05"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >예체능<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="컴퓨터"
          @change="toggle('R06')"
          id="R06"
        />
        <label class="checkbox-btn" for="R06"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >컴퓨터<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="예능취미체육"
          @change="toggle('R07')"
          id="R07"
        />
        <label class="checkbox-btn" for="R07"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >예능취미체육<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유아교육"
          @change="toggle('R08')"
          id="R08"
        />
        <label class="checkbox-btn" for="R08"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >유아교육<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="기타"
          @change="toggle('R09')"
          id="R09"
        />
        <label class="checkbox-btn" for="R09"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >기타<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="도서관/독서실"
          @change="toggle('R10')"
          id="R10"
        />
        <label class="checkbox-btn" for="R10">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >도서관/독서실<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="학문교육"
          @change="toggle('R13')"
          id="R13"
        />
        <label class="checkbox-btn" for="R13"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >학문교육<br />
      </div>
    </div>
  </section>
</template>

<script>
export default {
  data() {
    return {
      array: [],
    };
  },
  methods: {
    toggle(key) {
      this.$emit("toggle", key);
    },
  },
};
</script>

<style scoped src="@/css/user.css"></style>

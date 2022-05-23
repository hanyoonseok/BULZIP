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
          @change="toggle('keyword_D01')"
          id="keyword_D01"
          :checked="array.includes('음/식료품소매')"
        />
        <label class="checkbox-btn" for="keyword_D01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >음식료품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="선물/팬시"
          @change="toggle('keyword_D02')"
          id="keyword_D02"
          :checked="array.includes('선물/팬시')"
        />
        <label class="checkbox-btn" for="keyword_D02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >선물팬시<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="종합소매"
          @change="toggle('keyword_D03')"
          id="keyword_D03"
          :checked="array.includes('종합소매')"
        />
        <label class="checkbox-btn" for="keyword_D03"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >종합소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="취미/오락소매"
          @change="toggle('keyword_D04')"
          id="keyword_D04"
          :checked="array.includes('취미/오락소매')"
        />
        <label class="checkbox-btn" for="keyword_D04">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >취미오락소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="의복의류"
          @change="toggle('keyword_D05')"
          id="keyword_D05"
          :checked="array.includes('의복의류')"
        />
        <label class="checkbox-btn" for="keyword_D05"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >의복의류<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="가방/신발/액세서리"
          @change="toggle('keyword_D06')"
          id="keyword_D06"
          :checked="array.includes('가방/신발/액세서리')"
        />
        <label class="checkbox-btn" for="keyword_D06">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >가방/신발/액세서리<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="사무/문구/컴퓨터"
          @change="toggle('keyword_D08')"
          id="keyword_D08"
          :checked="array.includes('사무/문구/컴퓨터')"
        />
        <label class="checkbox-btn" for="keyword_D08">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >사무/문구/컴퓨터<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="애견/애완/동물"
          @change="toggle('keyword_D09')"
          id="keyword_D09"
          :checked="array.includes('애견/애완/동물')"
        />
        <label class="checkbox-btn" for="keyword_D09">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >애견/애완/동물<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="건강/미용식품"
          @change="toggle('keyword_D10')"
          id="keyword_D10"
          :checked="array.includes('건강/미용식품')"
        />
        <label class="checkbox-btn" for="keyword_D10">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >건강/미용식품<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유아용품"
          @change="toggle('keyword_D11')"
          id="keyword_D11"
          :checked="array.includes('유아용품')"
        />
        <label class="checkbox-btn" for="keyword_D11"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >유아용품<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="책/서적/도서"
          @change="toggle('keyword_D13')"
          id="keyword_D13"
          :checked="array.includes('책/서적/도서')"
        />
        <label class="checkbox-btn" for="keyword_D13">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >책/서적/도서<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="운동/경기용품소매"
          @change="toggle('keyword_D14')"
          id="keyword_D14"
          :checked="array.includes('운동/경기용품소매')"
        />
        <label class="checkbox-btn" for="keyword_D14">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >운동/경기용품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="화장품소매"
          @change="toggle('keyword_D16')"
          id="keyword_D16"
          :checked="array.includes('화장품소매')"
        />
        <label class="checkbox-btn" for="keyword_D16"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >화장품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="의약/의료품소매"
          @change="toggle('keyword_D20')"
          id="keyword_D20"
          :checked="array.includes('의약/의료품소매')"
        />
        <label class="checkbox-btn" for="keyword_D20">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >의약/의료품소매<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="철물/난방소매"
          @change="toggle('keyword_D21')"
          id="keyword_D21"
          :checked="array.includes('철물/난방소매')"
        />
        <label class="checkbox-btn" for="keyword_D21">
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
          @change="toggle('keyword_F01')"
          id="keyword_F01"
          :checked="array.includes('미용/건강')"
        />
        <label class="checkbox-btn" for="keyword_F01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >미용/건강<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="세탁/가사서비스"
          @change="toggle('keyword_F02')"
          id="keyword_F02"
          :checked="array.includes('세탁/가사서비스')"
        />
        <label class="checkbox-btn" for="keyword_F02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >세탁/가사서비스<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="사진"
          @change="toggle('keyword_F06')"
          id="keyword_F06"
          :checked="array.includes('사진')"
        />
        <label class="checkbox-btn" for="keyword_F06"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >사진<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="대중목욕탕/휴게"
          @change="toggle('keyword_F09')"
          id="keyword_F09"
          :checked="array.includes('대중목욕탕/휴게')"
        />
        <label class="checkbox-btn" for="keyword_F09">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >대중목욕탕/휴게<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="개인서비스"
          @change="toggle('keyword_F11')"
          id="keyword_F11"
          :checked="array.includes('개인서비스')"
        />
        <label class="checkbox-btn" for="keyword_F11"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >개인서비스<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="주유소"
          @change="toggle('keyword_F16')"
          id="keyword_F16"
          :checked="array.includes('주유소')"
        />
        <label class="checkbox-btn" for="keyword_F16"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >주유소<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="실내운동시설"
          @change="toggle('keyword_P02')"
          id="keyword_P02"
          :checked="array.includes('실내운동시설')"
        />
        <label class="checkbox-btn" for="keyword_P02"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >실내운동시설<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="실외운동시설"
          @change="toggle('keyword_P03')"
          id="keyword_P03"
          :checked="array.includes('실외운동시설')"
        />
        <label class="checkbox-btn" for="keyword_P03"
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
          @change="toggle('keyword_N01')"
          id="keyword_N01"
          :checked="array.includes('PC/오락/당구/볼링')"
        />
        <label class="checkbox-btn" for="keyword_N01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >PC/오락/당구/볼링<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유흥/가무"
          @change="toggle('keyword_N02')"
          id="keyword_N02"
          :checked="array.includes('유흥/가무')"
        />
        <label class="checkbox-btn" for="keyword_N02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >유흥/가무<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="연극/영화/극장"
          @change="toggle('keyword_N03')"
          id="keyword_N03"
          :checked="array.includes('연극/영화/극장')"
        />
        <label class="checkbox-btn" for="keyword_N03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >연극/영화/극장<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="스포츠/운동"
          @change="toggle('keyword_N04')"
          id="keyword_N04"
          :checked="array.includes('스포츠/운동')"
        />
        <label class="checkbox-btn" for="keyword_N04">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >스포츠/운동<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="요가/단전/마사지"
          @change="toggle('keyword_N05')"
          id="keyword_N05"
          :checked="array.includes('요가/단전/마사지')"
        />
        <label class="checkbox-btn" for="keyword_N05">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >요가/단전/마사지<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="놀이/여가/취미"
          @change="toggle('keyword_N07')"
          id="keyword_N07"
          :checked="array.includes('놀이/여가/취미')"
        />
        <label class="checkbox-btn" for="keyword_N07">
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
          @change="toggle('keyword_O01')"
          id="keyword_O01"
          :checked="array.includes('호텔/콘도')"
        />
        <label class="checkbox-btn" for="keyword_O01">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >호텔/콘도<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="호텔/모텔"
          @change="toggle('keyword_O02')"
          id="keyword_O02"
          :checked="array.includes('호텔/모텔')"
        />
        <label class="checkbox-btn" for="keyword_O02">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >호텔/모텔<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="캠프/별장/펜션"
          @change="toggle('keyword_O03')"
          id="keyword_O03"
          :checked="array.includes('캠프/별장/펜션')"
        />
        <label class="checkbox-btn" for="keyword_O03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >캠프/별장/펜션<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="민박/하숙"
          @change="toggle('keyword_O05')"
          id="keyword_O05"
          :checked="array.includes('민박/하숙')"
        />
        <label class="checkbox-btn" for="keyword_O05">
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
          @change="toggle('keyword_Q01')"
          id="keyword_Q01"
          :checked="array.includes('한식')"
        />
        <label class="checkbox-btn" for="keyword_Q01"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >한식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="중식"
          @change="toggle('keyword_Q02')"
          id="keyword_Q02"
          :checked="array.includes('중식')"
        />
        <label class="checkbox-btn" for="keyword_Q02"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >중식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="일식/수산물"
          @change="toggle('keyword_Q03')"
          id="keyword_Q03"
          :checked="array.includes('일식/수산물')"
        />
        <label class="checkbox-btn" for="keyword_Q03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >일식/수산물<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="분식"
          @change="toggle('keyword_Q04')"
          id="keyword_Q04"
          :checked="array.includes('분식')"
        />
        <label class="checkbox-btn" for="keyword_Q04"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >분식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="닭/오리요리"
          @change="toggle('keyword_Q05')"
          id="keyword_Q05"
          :checked="array.includes('닭/오리요리')"
        />
        <label class="checkbox-btn" for="keyword_Q05">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >닭/오리요리<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="양식"
          @change="toggle('keyword_Q06')"
          id="keyword_Q06"
          :checked="array.includes('양식')"
        />
        <label class="checkbox-btn" for="keyword_Q06"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >양식<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="패스트푸드"
          @change="toggle('keyword_Q07')"
          id="keyword_Q07"
          :checked="array.includes('패스트푸드')"
        />
        <label class="checkbox-btn" for="keyword_Q07"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >패스트푸드<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="제과제빵"
          @change="toggle('keyword_Q08')"
          id="keyword_Q08"
          :checked="array.includes('제과제빵')"
        />
        <label class="checkbox-btn" for="keyword_Q08"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >제과제빵<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유흥주점"
          @change="toggle('keyword_Q09')"
          id="keyword_Q09"
          :checked="array.includes('유흥주점')"
        />
        <label class="checkbox-btn" for="keyword_Q09"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >유흥주점<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="별식/퓨전요리"
          @change="toggle('keyword_Q10')"
          id="keyword_Q10"
          :checked="array.includes('별식/퓨전요리')"
        />
        <label class="checkbox-btn" for="keyword_Q10">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >별식/퓨전요리<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="커피점/카페"
          @change="toggle('keyword_Q12')"
          id="keyword_Q12"
          :checked="array.includes('커피점/카페')"
        />
        <label class="checkbox-btn" for="keyword_Q12">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >커피점/카페<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="음식배달"
          @change="toggle('keyword_Q13')"
          id="keyword_Q13"
          :checked="array.includes('음식배달')"
        />
        <label class="checkbox-btn" for="keyword_Q13"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >음식배달<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="부페"
          @change="toggle('keyword_Q15')"
          id="keyword_Q15"
          :checked="array.includes('부페')"
        />
        <label class="checkbox-btn" for="keyword_Q15"
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
          @change="toggle('keyword_R01')"
          id="keyword_R01"
          :checked="array.includes('입시')"
        />
        <label class="checkbox-btn" for="keyword_R01"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >입시<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="창업취업취미"
          @change="toggle('keyword_R02')"
          id="keyword_R02"
          :checked="array.includes('창업취업취미')"
        />
        <label class="checkbox-btn" for="keyword_R02"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >창업취업취미<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="자격/국가고시"
          @change="toggle('keyword_R03')"
          id="keyword_R03"
          :checked="array.includes('자격/국가고시')"
        />
        <label class="checkbox-btn" for="keyword_R03">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >자격/국가고시<br />

        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="어학"
          @change="toggle('keyword_R04')"
          id="keyword_R04"
          :checked="array.includes('어학')"
        />
        <label class="checkbox-btn" for="keyword_R04"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >어학<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="예체능"
          @change="toggle('keyword_R05')"
          id="keyword_R05"
          :checked="array.includes('예체능')"
        />
        <label class="checkbox-btn" for="keyword_R05"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >예체능<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="컴퓨터"
          @change="toggle('keyword_R06')"
          id="keyword_R06"
          :checked="array.includes('컴퓨터')"
        />
        <label class="checkbox-btn" for="keyword_R06"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >컴퓨터<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="예능취미체육"
          @change="toggle('keyword_R07')"
          id="keyword_R07"
          :checked="array.includes('예능취미체육')"
        />
        <label class="checkbox-btn" for="keyword_R07"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >예능취미체육<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="유아교육"
          @change="toggle('keyword_R08')"
          id="keyword_R08"
          :checked="array.includes('유아교육')"
        />
        <label class="checkbox-btn" for="keyword_R08"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >유아교육<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="기타"
          @change="toggle('keyword_R09')"
          id="keyword_R09"
          :checked="array.includes('기타')"
        />
        <label class="checkbox-btn" for="keyword_R09"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >기타<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="도서관/독서실"
          @change="toggle('keyword_R10')"
          id="keyword_R10"
          :checked="array.includes('도서관/독서실')"
        />
        <label class="checkbox-btn" for="keyword_R10">
          <font-awesome-icon icon="check"></font-awesome-icon></label
        >도서관/독서실<br />
        <input
          type="checkbox"
          class="hide-checkbox"
          v-model="array"
          value="학문교육"
          @change="toggle('keyword_R13')"
          id="keyword_R13"
          :checked="array.includes('학문교육')"
        />
        <label class="checkbox-btn" for="keyword_R13"
          ><font-awesome-icon icon="check"></font-awesome-icon></label
        >학문교육<br />
      </div>
    </div>
  </section>
</template>

<script>
import { KEYWORD } from "@/constants/index.js";

export default {
  data() {
    return {
      array: [],
    };
  },
  created() {
    if (this.userKeyword) {
      const keys = Object.keys(this.userKeyword);
      keys.forEach((e) => {
        const divByUnderBar = e.split("_");
        if (this.userKeyword[e] === 1)
          this.array.push(KEYWORD[divByUnderBar[1]]);
      });
    }
  },
  methods: {
    toggle(key) {
      this.$emit("toggle", key);
    },
  },
  props: {
    userKeyword: Object,
  },
};
</script>

<style scoped src="@/css/user.css"></style>

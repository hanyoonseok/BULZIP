package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.BabyDto;
import com.ssafy.happyhouse.model.dto.CommercialDto;
import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.SubwayDto;

public interface IHousedealService {
	HousedealDto selectOne(long no) throws Exception;
	
	// 단어 검색해서 리스트 가져오기
	List<HousedealDto> ListByWord(String word, int idx) throws Exception;
	
	// 주변 정보 가져오기
	List<HospitalDto> selectAllHospitalList(HashMap<String, Object> map) throws Exception;
	
	// 주변 유치원 정보 가져오기
	//List<BabyDto> selectBabyList(String dongName) throws Exception;
	
	// 현재 선택된 매물의 id와 동이름, 중분류 코드로 주변 상권 정보 가져오기
	List<CommercialDto> searchCommercial_joongbun(String dongName, String joongbun_code, int deal_no) throws Exception;
	// 특정 상권 정보 하나 가져오기
	CommercialDto detailCommercial(int no) throws Exception;
	
	// 지하철
	SubwayDto detailSubway(int station_id) throws Exception;
	List<SubwayDto> searchSubwayList(String word) throws Exception;
	
	// 시계열 데이터 리스트 가져오기
	List<HousedealDto> timeSeriesList(long aptCode, String floor) throws Exception;
	
	// 지도의 경계선 위도 경도로 리스트 가져오기
	List<HousedealDto> ListByMapBounds(double sw_lat, double sw_lng, double ne_lat, double ne_lng) throws Exception;
	List<HousedealDto> ListByMapBounds_300m(double sw_lat, double sw_lng, double ne_lat, double ne_lng, double lat,
			double lng) throws Exception;
	
	// 지도 경계선 위도 경도로 주변 상권 가져오기
//	List<CommercialDto> commercialByMapBounds(double sw_lat, double sw_lng, double ne_lat, double ne_lng,
//			List<String> keyword_list) throws Exception;
//	List<CommercialDto> commercialByMapBounds_300m(double sw_lat, double sw_lng, double ne_lat, double ne_lng,
//			double lat, double lng, List<String> keyword_list) throws Exception;
	List<CommercialDto> commercialByMapBounds_300m(HashMap<String, Object> map) throws Exception;
	
	
	
}

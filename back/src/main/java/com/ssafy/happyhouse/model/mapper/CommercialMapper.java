package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ssafy.happyhouse.model.dto.CommercialDto;
//import com.ssafy.happyhouse.model.dto.HousedealDto;


public interface CommercialMapper {
	
	//PK로 특정 상점 검색
	CommercialDto detailCommercialList(int no) throws SQLException;
	
	// 법정동 이름으로 검색으로 리스트 가져오기
	// List<CommercialDto> searchCommercial_legalDong(String dongName);
	
	// 법정동 이름과 중분류 업종 코드로 리스트 가져오기
	List<CommercialDto> searchCommercial_joongbun(String dongName, String joongbun_code);
	
	// 남서쪽 위도 경도, 북동쪽 위도 경도로 리스트 뽑기
	//List<CommercialDto> ListByMapBounds(double  sw_lat, double  sw_lng, double  ne_lat, double  ne_lng, List<String> keyword_list) throws SQLException;
	List<CommercialDto> ListByMapBounds(HashMap<String, Object> map) throws SQLException;
	
	
}

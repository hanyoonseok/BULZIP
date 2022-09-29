package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.UserDto;

public interface HousedealMapper {

	HousedealDto selectOne(long no) throws SQLException;
	
	List<HousedealDto> ListByWord(String word, int idx) throws SQLException;
	
	// 좋아요 수 
	public void housedealHitUpdate(int num, long no) throws Exception;
	
	
	// 특정 아파트(aptCode, 층) 과거 매물 기록 
	List<HousedealDto> timeSeriesList(long aptCode, String floor) throws SQLException;
	
	// 남서쪽 위도 경도, 북동쪽 위도 경도로 리스트 뽑기
	List<HousedealDto> ListByMapBounds(double  sw_lat, double  sw_lng, double  ne_lat, double  ne_lng) throws SQLException;
}

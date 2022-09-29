package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.UserDto;

public interface HospitalMapper {
	List<HospitalDto> selectHospitalList(String dongName);
	
	List<HospitalDto> selectAllHospitalList();
	
	// 남서쪽 위도 경도, 북동쪽 위도 경도로 리스트 뽑기
	List<HospitalDto> ListByMapBounds(double  sw_lat, double  sw_lng, double  ne_lat, double  ne_lng) throws SQLException;
}

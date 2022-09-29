package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.SubwayDto;

public interface SubwayMapper {
	// 특정 지하철 역 하나
	SubwayDto detailSubway(int station_id);
	// 검색해서 지하철역 리스트
	List<SubwayDto> selectSubwayList(String word);
	// 그냥 전체 리스트 
	List<SubwayDto> selectAllSubway();
}

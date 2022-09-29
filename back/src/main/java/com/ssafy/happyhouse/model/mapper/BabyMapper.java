package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.BabyDto;

public interface BabyMapper {
	List<BabyDto> selectBabyList(String dongName) throws SQLException;
}

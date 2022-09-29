package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.InterestedDto;
import com.ssafy.happyhouse.model.dto.UserDto;

public interface InterestedMapper {
	// 관심 매물 등록
	public int insertInterested(InterestedDto interestedDto); 
	
	// 특정 관심 매물 하나 가져오기. id 이용	
	public InterestedDto detailInterestedbyNo(int no) throws SQLException;
	
	// 특정 관심 매물 하나 가져오기. 	
	public InterestedDto detailInterested(String user_id , long housedeal_id) throws SQLException;
	
	// 현재 회원의 관심 매물 리스트 가져오기.       회원의 id를 보낸다. 이 때, user테이블의 user_id이다 PK id가 아님.
	public List<InterestedDto> listInterested(String user_id) throws Exception;
	
	// 관심 매물 삭제 		관심매물의 PK인 no를 보낸다. int 형임.
	public int deleteInterested(int no) throws Exception;
	
}

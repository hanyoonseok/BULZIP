package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.InterestedDto;
import com.ssafy.happyhouse.model.dto.KeywordDto;
import com.ssafy.happyhouse.model.dto.UserDto;


public interface IUserService {
	
	public int register(UserDto user) throws Exception;
	
	public UserDto selectOne(String userId, String password);
	public UserDto detailOne(String user_id);
	
	public String findPw(String user_id, String name,String phone) throws Exception;
	
	List<UserDto> listMember() throws Exception;
	List<UserDto> search(String word) throws Exception;
	void updateMember(UserDto userDto) throws Exception;
	void deleteMember(String userId) throws Exception;
	int idCheck(String checkId) throws Exception;
	
	// 코사인 유사도로 추천 리스트 제공
	Set<HousedealDto> recommended_list(String user_id) throws Exception;

	int insertInterested(InterestedDto interestedDto) throws Exception;

	InterestedDto detailInterestedbyNo(int no) throws SQLException;
	InterestedDto detailInterested( String user_id , long housedeal_id) throws SQLException;

	List<HousedealDto> listInterested(String user_id) throws Exception;

	void deleteInterested(int no) throws Exception;

	int insertKeyword(KeywordDto keywordDto) throws Exception;

	KeywordDto detailKeyword(String user_id) throws SQLException;

	List<KeywordDto> listKeyword() throws Exception;

	void updateKeyword(KeywordDto keywordDto) throws Exception;

	void deleteKeyword(String user_id) throws Exception;

	// 관심 매물 좋아요 수 업데이트
	void housedealHitUpdate(int num, long no) throws Exception;

	

	
}

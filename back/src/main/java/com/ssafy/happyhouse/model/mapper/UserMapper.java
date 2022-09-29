package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.UserDto;

public interface UserMapper {
	public UserDto selectOne(@Param("userId")String userId, @Param("password")String password);
	
	public UserDto detailOne(@Param("user_id")String user_id);
	
	public int register(UserDto user); 
	
	public void findPw(String user_id, String name,String phone, String new_pass);
	
	// 전체 회원 리스트 가져오기
	public List<UserDto> listMember() throws Exception;
	
	// 전체 회원 리스트 가져오기
	public List<UserDto> search(String word) throws Exception;
		
	// 회원 수정
	public void updateMember(UserDto userDto) throws Exception;
	// 회원 삭제
	public void deleteMember(String userId) throws Exception;
	
	// 아이디 중복 검사
	public int idCheck(String checkId) throws Exception;
}

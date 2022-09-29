package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.KeywordDto;

public interface KeywordMapper {
	// 회원 가입 시 등록
	public int insertKeyword(KeywordDto keywordDto); 
	
	// 현재 회원의 키워드 정보 가져오기
	public KeywordDto detailKeyword(String user_id) throws SQLException;
	
	// 전체 회원 키워드리스트 가져오기
	public List<KeywordDto> listKeyword() throws Exception;
	// 회원 정보 수정 시 수정
	public void updateKeyword(KeywordDto keywordDto) throws Exception;
	// 회원 삭제 시 삭제
	public void deleteKeyword(String user_id) throws Exception;
}

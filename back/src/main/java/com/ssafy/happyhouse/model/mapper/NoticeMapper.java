package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeMapper {
	// 게시글 등록
	public int insert(NoticeDto notice);
	// 삭제
	public int delete(int no);
	// 리스트
	public List<NoticeDto> selectList(int start,int count);
	// 전체 개수
	public int selectTotalCount();
	
	// 수정
	public void updateNotice(NoticeDto noticeDto) throws Exception;
	// 게스글 하나
	public NoticeDto detailNotice(@Param("notice_no") int notice_no);

}

package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.QnaDto;

public interface QnaMapper {
		// 게시글 등록
		public int insert(QnaDto qnaDto);
		// 삭제
		public int delete(int qna_id);
		// 수정
		public void updateQna(QnaDto qnaDto) throws Exception;
		// 리스트
		public List<QnaDto> selectList(int start,int count);
		// 전체 개수
		public int selectTotalCount();
		// 게시글 하나
		public QnaDto detailQna(@Param("qna_id") int qna_id);

		
		
}

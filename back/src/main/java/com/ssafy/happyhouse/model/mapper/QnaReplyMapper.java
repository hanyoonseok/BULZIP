package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.QnareplyDto;

public interface QnaReplyMapper {
	// 댓글 등록
	public int qnaReplyInsert(QnareplyDto qnareplyDto);
	// 댓글 수정
	public void updateQnaReply(QnareplyDto qnareplyDto) throws Exception;
	// 댓글 삭제
	public int deleteQnaReply(@Param("comment_id") int comment_id);
	
	// 리스트
	public List<QnareplyDto> selectList(int start,int count, int qna_id);
	// 전체 개수
	public int selectTotalCount();
	
	// 댓글 하나
	public QnareplyDto detailReply(@Param("comment_id") int comment_id);

}

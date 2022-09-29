package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.dto.QnareplyDto;

public interface IQnaService {
	////////////////// 게시글 ////////////////////////
	public int insertQna(QnaDto qnaDto);
	
	public int deleteQna(int qna_id);
	
	public Map<String,Object> selectQnaList(int p);
	
	public QnaDto detailQna(int qna_id);
	
	public void updateQna(QnaDto qnaDto) throws Exception;
	
	////////////////// 댓글 ///////////////////////
	public int insertReply(QnareplyDto qnareplyDto);
	
	public int deleteReply(int comment_id);
	
	public void updateReply(QnareplyDto qnareplyDto) throws Exception;
	
	public Map<String,Object> selectReplyList(int p, int qna_id);
	
	public QnareplyDto detailReply(int comment_id);
	
}	

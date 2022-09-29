package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.dto.QnareplyDto;
import com.ssafy.happyhouse.model.mapper.QnaMapper;
import com.ssafy.happyhouse.model.mapper.QnaReplyMapper;

@Service
public class QnaService implements IQnaService {
	@Autowired
	private QnaMapper qnaMapper;
	
	@Autowired
	private QnaReplyMapper qnareplyMapper;

	///////////////////// qna 게시글 ///////////////////////
	@Override
	public int insertQna(QnaDto qnaDto) {
		int qna_id = qnaMapper.insert(qnaDto);
		return qna_id;
	}

	@Override
	public int deleteQna(int qna_id) {
		return qnaMapper.delete(qna_id);
	}

	@Override
	public Map<String, Object> selectQnaList(int p) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", p);
		int startPage = (p-1)/10*10 +1 ;
		map.put("startPage",startPage);
		
		int totalCount = qnaMapper.selectTotalCount();
		int totalPage = totalCount/10;
		if(totalCount%10>0) {
			totalPage++;
		}
		map.put("totalPage",totalPage);
		
		int endPage = startPage+9;
		if(totalPage<endPage) {
			endPage = totalPage;
		}
		
		map.put("endPage",endPage);
		
		map.put("qnaList",qnaMapper.selectList((p-1)*10, 10));
		
		return map;
	}

	@Override
	public QnaDto detailQna(int qna_id) {
		return qnaMapper.detailQna(qna_id);
	}

	@Override
	public void updateQna(QnaDto qnaDto) throws Exception {
		qnaMapper.updateQna(qnaDto);
	}
	
	
	////////////////////// 댓글 ///////////////////////////
	@Override
	public int insertReply(QnareplyDto qnareplyDto) {
		int comment_no = qnareplyMapper.qnaReplyInsert(qnareplyDto);
		return comment_no;
	}

	@Override
	public int deleteReply(int comment_id) {
		return qnareplyMapper.deleteQnaReply(comment_id);
	}

	@Override
	public void updateReply(QnareplyDto qnareplyDto) throws Exception {
		qnareplyMapper.updateQnaReply(qnareplyDto);
	}

	@Override
	public Map<String, Object> selectReplyList(int p, int qna_id) { // qna_id를 보내서 특정 게시글의 댓글들을 불러온다.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", p);
		int startPage = (p-1)/10*10 +1 ;
		map.put("startPage",startPage);
		
		int totalCount = qnareplyMapper.selectTotalCount();
		int totalPage = totalCount/10;
		if(totalCount%10>0) {
			totalPage++;
		}
		map.put("totalPage",totalPage);
		
		int endPage = startPage+9;
		if(totalPage<endPage) {
			endPage = totalPage;
		}
		
		map.put("endPage",endPage);
		
		map.put("qnaReplyList",qnareplyMapper.selectList((p-1)*10, 10, qna_id));
		System.out.println(map.get("qnaReplyList"));
		return map;
	}

	@Override
	public QnareplyDto detailReply(int comment_id) {
		return qnareplyMapper.detailReply(comment_id);
	}
	
	

}

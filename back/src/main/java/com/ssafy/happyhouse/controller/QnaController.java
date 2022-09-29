package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.QnaDto;
import com.ssafy.happyhouse.model.dto.QnareplyDto;
import com.ssafy.happyhouse.model.service.QnaService;

@RestController
@RequestMapping("/qna")
@CrossOrigin("*")
public class QnaController {
	@Autowired
	public QnaService qnaService;
	
	/////////////////////// qna //////////////////////
	@GetMapping("/list/{p}")
	public ResponseEntity<?> qnaList(@PathVariable("p") int p){
		Map<String, Object> map = new HashMap<String, Object>();
		map = qnaService.selectQnaList(p);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/delete/{no}")
	public String deleteQna(@PathVariable("no") int no) {
		if(qnaService.deleteQna(no)==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@PostMapping("/insert")
	public String insertQna(@RequestBody QnaDto qnaDto) {
		System.out.println("QnaDto : " +qnaDto);
		
		if(qnaService.insertQna(qnaDto)>0) {
			return "success";
		}
		return "fail";
	}
	
	// 게시글 수정
	@PutMapping("/update")
	public String updateQna(@RequestBody QnaDto qnaDto) throws Exception {
		System.out.println(qnaDto);
		qnaService.updateQna(qnaDto);
		return "success";
	}
	
	// 게시글 한 개 
	@GetMapping("/detail/{qna_id}")
	public QnaDto detailQna(@PathVariable("qna_id") int qna_id) {
		QnaDto qnaDto = qnaService.detailQna(qna_id);
		if(qnaDto != null) {
			return qnaDto;
		}else return null;
	}
	
	//////////////////// 댓글 //////////////////////////
	@GetMapping("/reply/list/{p}/{qna_id}")
	public ResponseEntity<?> qnaReplyList(@PathVariable("p") int p, @PathVariable("qna_id") int qna_id){
		Map<String, Object> map = new HashMap<String, Object>();
		map = qnaService.selectReplyList(p, qna_id);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/reply/delete/{no}")
	public String deleteQnaReply(@PathVariable("no") int no) {
		if(qnaService.deleteReply(no)==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@PostMapping("/reply/insert")
	public String insertQnaReply(@RequestBody QnareplyDto qnareplyDto) {
		System.out.println("QnareplyDto : " +qnareplyDto);
		
		if(qnaService.insertReply(qnareplyDto)>0) {
			return "success";
		}
		return "fail";
	}

	@PutMapping("/reply/update")
	public String updateQnaReply(@RequestBody QnareplyDto qnareplyDto) throws Exception {
		System.out.println(qnareplyDto);
		qnaService.updateReply(qnareplyDto);
		return "success";
	}
	
	// 게시글 한 개 
	@GetMapping("/reply/detail/{comment_id}")
	public QnareplyDto detailReply(@PathVariable("comment_id") int comment_id) {
		QnareplyDto qnareplyDto = qnaService.detailReply(comment_id);
		if(qnareplyDto != null) {
			return qnareplyDto;
		}else return null;
	}

}

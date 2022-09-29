package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
@CrossOrigin("*")
public class NoticeController {
	
	@Autowired
	private NoticeService nservice;
	
	@GetMapping("/list/{p}")
	public ResponseEntity<?> list(@PathVariable("p") int p){
		Map<String, Object> map = new HashMap<String, Object>();
		map = nservice.select(p);
		return new ResponseEntity<Map<String, Object>>(map,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/delete/{no}")
	public String delete(@PathVariable("no") int no) {
		if(nservice.delete(no)==1) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@PostMapping("/write")
	public String write(@RequestBody NoticeDto notice) {
		System.out.println("NoticeDto : " +notice);
		
		if(nservice.insert(notice)>0) {
			return "success";
		}
		return "fail";
	}
	
	// 게시글 수정
	@PutMapping("/update")
	public String updateNotice(@RequestBody NoticeDto noticeDto) throws Exception {
		System.out.println(noticeDto);
		nservice.updateNotice(noticeDto);
		return "success";
	}
	
	// 게시글 한 개 
	@GetMapping("/detail/{notice_no}")
	public NoticeDto detailOne(@PathVariable("notice_no") int notice_no) {
		NoticeDto noticeDto = nservice.detailNotice(notice_no);
		if(noticeDto != null) {
			return noticeDto;
		}else return null;
	}
	
}

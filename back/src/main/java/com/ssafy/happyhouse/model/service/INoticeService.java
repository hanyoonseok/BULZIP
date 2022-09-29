package com.ssafy.happyhouse.model.service;

import java.util.Map;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface INoticeService {
	public int insert(NoticeDto notice);
	
	public int delete(int no);
	
	public Map<String,Object> select(int p);
	
	public NoticeDto detailNotice(int notice_no);
	
	public void updateNotice(NoticeDto noticeDto) throws Exception;
}

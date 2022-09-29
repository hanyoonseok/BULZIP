package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeService implements INoticeService{
	@Autowired
	private NoticeMapper noticeMapper;
	
	public int insert(NoticeDto notice) {
		int notice_no = noticeMapper.insert(notice);
		return notice_no;
	}
	
	public int delete(int no) {
		return noticeMapper.delete(no);
	}
	
	public Map<String,Object> select(int p){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", p);
		int startPage = (p-1)/10*10 +1 ;
		map.put("startPage",startPage);
		
		int totalCount = noticeMapper.selectTotalCount();
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
		
		map.put("noticeList",noticeMapper.selectList((p-1)*10, 10));
		
		return map;
	}

	@Override
	public NoticeDto detailNotice(int notice_no) {
		return noticeMapper.detailNotice(notice_no);
	}

	@Override
	public void updateNotice(NoticeDto noticeDto) throws Exception {
		noticeMapper.updateNotice(noticeDto);
	}
}

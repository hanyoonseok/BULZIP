package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssafy.happyhouse.model.dto.CommercialDto;
import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.SubwayDto;
import com.ssafy.happyhouse.model.service.HousedealService;
import com.ssafy.happyhouse.model.service.UserService;

@Controller
@RequestMapping("/housedeal")
@CrossOrigin("*")
public class HousedealController {
	@Autowired
	public HousedealService housedealService;
	@Autowired
	public UserService userService;
	
	// 특정 매물 데이터 한 개 가져오기 -> 해당 매물의 PK인 no를 전달받아야 한다.
	@GetMapping("/read/{no}") 
	@ResponseBody 
	public HousedealDto selectOne(@PathVariable("no") long no) throws Exception{ 
		return housedealService.selectOne(no);
	}
	
	// 단어 검색으로 list 가져오기 -> 검색창에 입력된 word를 전달받아야 한다.
	@PostMapping("/list/{word}/{idx}")
	@ResponseBody
	public List<HousedealDto> ListByWord(@PathVariable("word") String word, @PathVariable("idx") int idx) throws Exception{
		return housedealService.ListByWord(word, idx);
	}
	
	// 지도의 범위 안에 있는 매물 리스트 가져오기
	@PostMapping("/boundry")
	@ResponseBody
	public List<HousedealDto> ListByMapBounds(@RequestBody Map<String, Object> datas) throws Exception{
		double sw_lat = (double) datas.get("sw_lat");
		double sw_lng = (double) datas.get("sw_lng");
		double ne_lat = (double) datas.get("ne_lat");
		double ne_lng = (double) datas.get("ne_lng");
		return housedealService.ListByMapBounds(sw_lat, sw_lng, ne_lat, ne_lng);
	}
	

	///////////////// 병원 ///////////////
	
	@PostMapping("/hospital")
	@ResponseBody
	public List<HospitalDto> selectNearHospital(@RequestBody HashMap<String, Object> map) throws Exception{
		return housedealService.selectAllHospitalList(map);
	}
	/////////////// 지하철 //////////////
	@GetMapping("/subway/{station_id}")
	@ResponseBody
	public SubwayDto detailSubway(@PathVariable("station_id") int station_id) throws Exception {
		return housedealService.detailSubway(station_id);
	}
	@GetMapping("/subway/search/{word}")
	@ResponseBody
	public List<SubwayDto> searchSubwayList(@PathVariable("word") String word) throws Exception {
		return housedealService.searchSubwayList(word);
	}
	//////////////시계열 리스트//////////////////
	@GetMapping("/timeseries/{aptCode}/{floor}")
	@ResponseBody
	public List<HousedealDto> timeSeriesList(@PathVariable("aptCode") long aptCode, @PathVariable("floor") String floor) throws Exception {
		return housedealService.timeSeriesList(aptCode, floor);
	}
	
	///////////// 상권정보 ///////////////
	@GetMapping("/commercial/{no}")
	@ResponseBody
	public CommercialDto detailCommercial(@PathVariable("no") int no) throws Exception {
		return housedealService.detailCommercial(no);
	}
	@GetMapping("/commercial/{dongName}/{joongbun_code}/{deal_no}")
	@ResponseBody
	public List<CommercialDto> searchCommercial_joongbun(@PathVariable("dongName") String dongName, @PathVariable("joongbun_code") String joongbun_code, @PathVariable("deal_no") int deal_no) throws Exception {
		return housedealService.searchCommercial_joongbun(dongName, joongbun_code, deal_no);
	}

	
	// 위도 경도 입력해서 300m안에 있는 상권 리스트 가져오기
	@PostMapping("/commercial")
	@ResponseBody
	public List<CommercialDto> ListByMapBounds_300m(@RequestBody HashMap<String, Object> map) throws Exception{
		
		
		return housedealService.commercialByMapBounds_300m(map);
	}
	
	
	//////////////추천 목록 //////////////////
	@GetMapping("/recommend/{user_id}")
	@ResponseBody
	public Set<HousedealDto> recommended_list(@PathVariable("user_id") String user_id) throws Exception {
	return userService.recommended_list(user_id);
	}
}

package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.happyhouse.model.dto.CommercialDto;
import com.ssafy.happyhouse.model.dto.HospitalDto;
import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.SubwayDto;
import com.ssafy.happyhouse.model.mapper.CommercialMapper;
import com.ssafy.happyhouse.model.mapper.HospitalMapper;
import com.ssafy.happyhouse.model.mapper.HousedealMapper;
import com.ssafy.happyhouse.model.mapper.SubwayMapper;

@Service
public class HousedealService implements IHousedealService {
	@Autowired
	private HousedealMapper housedealMapper;
	@Autowired
	private HospitalMapper hospitalMapper ;
//	@Autowired
//	private BabyMapper babyMapper;
	@Autowired
	private CommercialMapper commercialMapper;
	@Autowired
	private SubwayMapper subwayMapper;
	
	@Override
	public HousedealDto selectOne(long no) throws Exception{
		return housedealMapper.selectOne(no);
	}

	// 검색어로 리스트 가져오기
	@Override
	public List<HousedealDto> ListByWord(String word, int idx) throws Exception{
		
		return housedealMapper.ListByWord(word, idx);
	}
	
	
	////////////////////////////// 지하철 관련 /////////////////////////////////////
	@Override
	public SubwayDto detailSubway(int station_id) throws Exception{
		return subwayMapper.detailSubway(station_id);
	}

	// 검색어로 리스트 가져오기
	@Override
	public List<SubwayDto> searchSubwayList(String word) throws Exception{
		
		return subwayMapper.selectSubwayList(word);
	}
	
	///////////////////////특정 매물 시계열 리스트가져오기 /////////////////////////////////
	@Override
	public List<HousedealDto> timeSeriesList(long aptCode, String floor) throws Exception{
		
		return housedealMapper.timeSeriesList(aptCode, floor);
	}
	
	///////////////////////////////병원 정보 관련 ///////////////////////////////////////
	
	// 위도 경도로 주변 매물 가져오기
	@Override
	public List<HospitalDto> selectAllHospitalList(@RequestBody HashMap<String, Object> map) throws Exception{
		List<HospitalDto> hospitals = hospitalMapper.selectAllHospitalList();
		List<HospitalDto> selected_hospitals = new ArrayList<HospitalDto>();
		// 기준 거리
		int Reference_distance = Integer.parseInt((String) map.get("sliderValue"));
		// 기준 매물 좌표
		double current_lat = Double.parseDouble((String)map.get("current_lat"));
		double current_lng = Double.parseDouble((String)map.get("current_lng"));
		for(HospitalDto hospital : hospitals) {
			double dit = isDistance(current_lat, current_lng, Double.parseDouble(hospital.getLat()), Double.parseDouble(hospital.getLon()));
			if(dit<=Reference_distance) {
				selected_hospitals.add(hospital);
			}
		}
		
		return selected_hospitals;
	}
	
	////////////////////////////////////////// 지도 경계 위도 경도 활용 /////////////////////////////////
	// 위도 경도로 주변 매물 가져오기

	@Override
	public List<HousedealDto> ListByMapBounds(double  sw_lat, double  sw_lng, double  ne_lat, double  ne_lng) throws Exception{
		List<HousedealDto> houses = housedealMapper.ListByMapBounds(sw_lat, sw_lng, ne_lat, ne_lng);
		return houses;
	}
	
	// 위도 경도로 주변 매물 가져오기
	@Override
	public List<HousedealDto> ListByMapBounds_300m(double  sw_lat, double  sw_lng, double  ne_lat, double  ne_lng, double lat, double lng) throws Exception{
		List<HousedealDto> houses = housedealMapper.ListByMapBounds(sw_lat, sw_lng, ne_lat, ne_lng);
		List<HousedealDto> selected_houses = new ArrayList<HousedealDto>();
		// 기준 거리
		int Reference_distance = 300;
		for(HousedealDto house : houses) {
			double dit = isDistance(lat, lng, Double.parseDouble(house.getLat()), Double.parseDouble(house.getLng()));
			if(dit<=Reference_distance) {
				selected_houses.add(house);
			}
		}
		
		return selected_houses;
	}
	
	///////////////////////////////상권 정보 관련 ///////////////////////////////////////////
	
	@Override
	public CommercialDto detailCommercial(int no) throws Exception{
		return commercialMapper.detailCommercialList(no);
	}
	
	
	// 위도 경도로 300m 안 주변 상권 가져오기
	@Override
	public List<CommercialDto> commercialByMapBounds_300m(HashMap<String, Object> map) throws Exception{
		
//		double sw_lat = (double) map.get("sw_lat");
//		double sw_lng = (double) map.get("sw_lng");
//		double ne_lat = (double) map.get("ne_lat");
//		double ne_lng = (double) map.get("ne_lng");
		double current_lat = Double.parseDouble((String)map.get("current_lat"));
		double current_lng = Double.parseDouble((String)map.get("current_lng"));
		//userKeyword를 꺼낸 후 1 값이 있는 것만 필터링하여 다시 map에 저장한다.
//		@SuppressWarnings("unchecked")
		HashMap<String, Object> before_filters = (HashMap<String, Object>) map.get("userKeyword");
		System.out.println("userKeyword 받은 직후:" + before_filters.toString());
		
		List<CommercialDto> commercials = commercialMapper.ListByMapBounds(map);
		System.out.println("commercials : " + commercials.toString());
		List<CommercialDto> selected_commercials = new ArrayList<CommercialDto>();
		
		// 기준 거리 sliderValue
		int Reference_distance =   Integer.parseInt((String) map.get("sliderValue"));
		System.out.println("기준거리 : " + Reference_distance);
		for(CommercialDto each : commercials) {
			double dit = isDistance(current_lat, current_lng, each.getLat(), each.getLng());
			if(dit<=Reference_distance) {
				selected_commercials.add(each);
			}
		}
		
		return selected_commercials;
	}

	//선택된 매물의 아이디, 법정동이름, 중분류코드로 상권 리스트 가져오기
	@Override
	public List<CommercialDto> searchCommercial_joongbun(String dongName, String joongbun_code, int deal_no) throws Exception{
		HousedealDto currentHouse = housedealMapper.selectOne(deal_no); // 현재 선택된 매물. deal_no로 가져온다.
		List<CommercialDto> commercials = commercialMapper.searchCommercial_joongbun(dongName, joongbun_code); // 입력된 동이름과, 중분류코드로 상권 리스트 가져오기
		List<CommercialDto> selected_commercials = new ArrayList<CommercialDto>(); // 전방 특정 거리 안에 매물만 추려서 담을 리스트
		// 기준 거리
		int Reference_distance = 300;
		for(CommercialDto each : commercials) {
			double dit = isDistance(each.getLat(), each.getLng(), Double.parseDouble(currentHouse.getLng()), Double.parseDouble(currentHouse.getLng()));
			if(dit<=Reference_distance) {
				selected_commercials.add(each);
			}
		}
		return selected_commercials;
	}
	
	
	// 위도 경도로 거리 구하는 함수
	public static double isDistance(double lat1, double lon1, double lat2, double lon2) {
		  if ((lat1 == lat2) && (lon1 == lon2)) {
		   return 0;
		  }
		  else {
		   double theta = lon1 - lon2;
		   double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
		   dist = Math.acos(dist);
		   dist = Math.toDegrees(dist);
		   dist = dist * 60 * 1.1515;
		   dist = dist * 1.609344 * 1000;  // 단위 미터로
		   return Math.round(dist);
		  }
	 }
		
}



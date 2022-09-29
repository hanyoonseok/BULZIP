package com.ssafy.happyhouse.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.InterestedDto;
import com.ssafy.happyhouse.model.dto.KeywordDto;
import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.model.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private UserService uservice;
	@Autowired
	private JwtServiceImpl jwtService;


	
	@PostMapping("/register")
	public String register(@RequestBody UserDto user) throws Exception {
		System.out.println(user);
		if(uservice.register(user) > 0) return "success";
		else return "fail";
	}
	
	
	@PostMapping("/findpw/{id}/{name}/{phone}")
	public String findpw(@PathVariable("id")String user_id, @PathVariable("name")String name, @PathVariable("phone")String phone) throws Exception{
		System.out.println("controller " + user_id + " " + name +  " " + phone);
		// 실패했으면 "fail" , 성공했으면 새 password return 됨.
		return uservice.findPw(user_id, name, phone);
	}
	
	
//	@PostMapping("/login/{id}/{pw}")
//	public UserDto login(@PathVariable("id")String userId, @PathVariable("pw")String password, HttpSession session) {
//		System.out.println(userId + " " +  password);
//		UserDto loginInfo = uservice.selectOne(userId, password); 
//		System.out.println(loginInfo);
//		if(loginInfo != null) {
//			return loginInfo;
//		}else return null;
//	}
	
	// jwt 토큰 활용한 로그인
	@PostMapping("/login/{id}/{pw}")
	public ResponseEntity<Map<String, Object>> login(@PathVariable("id")String userId, @PathVariable("pw")String password, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = uservice.selectOne(userId, password);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getUserId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	@GetMapping("/detail/{user_id}")
	public UserDto detailOne(@PathVariable("user_id") String user_id) {
		UserDto userdto = uservice.detailOne(user_id);
		if(userdto != null) {
			return userdto;
		}else return null;
	}
	
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "success";
	}
	
	// 회원 리스트 가져오기 -> 관리자 인증 필요 admin???
	@GetMapping("/list/{role}")
	public ResponseEntity<?> listMember(@PathVariable("role") int role) throws Exception {
		if(role == 1) {
			return new ResponseEntity<List<UserDto>>(uservice.listMember(), HttpStatus.OK); 
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 회원 검색 리스트
	@GetMapping("/search/{word}")
	public ResponseEntity<?> search(@PathVariable("word") String word) throws Exception {
		List<UserDto> memberList = uservice.search(word);
		if(memberList != null) {
			return new ResponseEntity<List<UserDto>>(memberList, HttpStatus.OK); 
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 회원 정보 수정 -> userDto 로 데이터 입력 받은 후 수정 -> 수정 후 회원 리스트 반환
	@PutMapping("/update")
	public String updateMember(@RequestBody UserDto userDto) throws Exception {
		System.out.println(userDto);
		uservice.updateMember(userDto);
		return "success";
	}
	
	// 회원 삭제 -> 수정 후 회원 리스트 반환
	@DeleteMapping("/delete/{userId}")
	public String deleteMember(@PathVariable("userId") String userId) throws Exception {
		uservice.deleteMember(userId);
		return "success";
	}
	// 아이디 중복검사
	@GetMapping("/idCheck/{id}")
	public String idCheck(@PathVariable("id") String checkId) throws Exception {
		int n = uservice.idCheck(checkId);
		System.out.println(checkId + " " + n);
		if(n==1) {
			return "fail";
		}else {
			return "avaliable";
		}
		
	}
	
	///////////////////// 회원 별 관심 매물 등록 /////////////////////
	
	@PostMapping("/interested/insert")
	public String insertInterested(@RequestBody InterestedDto interestedDto) throws Exception {
		System.out.println(interestedDto);
		if(uservice.insertInterested(interestedDto) > 0) {
			uservice.housedealHitUpdate(1, interestedDto.getHousedeal_id());
			return "success";
		}
		
		else return "fail";
	}
	
	@GetMapping("/interested/detail/{user_id}/{housedeal_id}")
	public InterestedDto detailInterested(@PathVariable("user_id") String user_id, @PathVariable("housedeal_id") long housedeal_id) throws SQLException {
		InterestedDto interestedDto = uservice.detailInterested(user_id, housedeal_id);
		if(interestedDto != null) {
			return interestedDto;
		}else return null;
	}
	
	@GetMapping("/interested/list/{user_id}")
	public List<HousedealDto> listInterested(@PathVariable("user_id") String user_id) throws Exception {
		List<HousedealDto> interestedList = uservice.listInterested(user_id);
		if(!interestedList.isEmpty()) {
			return interestedList;
		}else return null;
	}
	
	
	@DeleteMapping("/interested/delete/{no}")
	public String deleteInterested(@PathVariable("no") int no) throws Exception {
		InterestedDto interestedDto = uservice.detailInterestedbyNo(no);
		uservice.housedealHitUpdate(-1, interestedDto.getHousedeal_id());
		uservice.deleteInterested(no);
		return "success";
	}
	
	
	///////////////////// 회원 별 키워드 등록 //////////////////////
	
	@PostMapping("/keyword/insert")
	public String insertKeyword(@RequestBody KeywordDto keywordDto) throws Exception {
		System.out.println(keywordDto);
		if(uservice.insertKeyword(keywordDto) > 0) return "success";
		else return "fail";
	}
	
	@GetMapping("/keyword/detail/{user_id}")
	public KeywordDto detailKeyword(@PathVariable("user_id") String user_id) throws SQLException {
		KeywordDto keywordDto = uservice.detailKeyword(user_id);
		if(keywordDto != null) {
			return keywordDto;
		}else return null;
	}
	
	// 회원 수정 시 키워드 수정
	@PutMapping("/keyword/update")
	public String updateKeyword(@RequestBody KeywordDto keywordDto) throws Exception {
		System.out.println(keywordDto);
		uservice.updateKeyword(keywordDto);
		return "success";
	}
	
	// 회원 삭제 시 키워드 삭제
	@DeleteMapping("/keyword/delete/{user_id}")
	public String deleteKeyword(@PathVariable("user_id") String user_id) throws Exception {
		uservice.deleteKeyword(user_id);
		return "success";
	}
	
	
}

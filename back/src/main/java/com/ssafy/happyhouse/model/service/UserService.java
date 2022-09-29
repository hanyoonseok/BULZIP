package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.happyhouse.model.dto.HousedealDto;
import com.ssafy.happyhouse.model.dto.InterestedDto;
import com.ssafy.happyhouse.model.dto.KeywordDto;
import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.mapper.HousedealMapper;
import com.ssafy.happyhouse.model.mapper.InterestedMapper;
import com.ssafy.happyhouse.model.mapper.KeywordMapper;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private KeywordMapper keywordMapper;
	@Autowired
	private InterestedMapper interestedMapper;
	@Autowired
	private HousedealMapper housedealMapper;

	@Override
	public int register(UserDto user) throws Exception {
		int result = userMapper.register(user);
		return result;
	}

	@Override
	public UserDto selectOne(String userId, String password) {
		return userMapper.selectOne(userId, password);
	}

	@Override
	public UserDto detailOne(String user_id) {
		return userMapper.detailOne(user_id);
	}

	@Override
	public String findPw(String user_id, String name,String phone) throws Exception {
		// user_id로 회원정보 가져오기
		UserDto current_user = userMapper.detailOne(user_id);
		System.out.println(current_user);
		if(current_user == null) return "fail"; // id로 값을 가져왔는데 해당 유저가 없으면 fail
		// 유저는 등록되어 있는데 이름과 폰번호가 하나라도 맞지 않는다면 fail
		System.out.println(current_user.getName().equals(name));
		if(!current_user.getName().equals(name) || !current_user.getPhone().equals(phone)) return "fail";
		
		// 10자리 임시 비밀번호 생성
		Random rnd =new Random();
		StringBuffer buf =new StringBuffer();
		for(int i=0;i<10;i++){
		    if(rnd.nextBoolean()){
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    }else{
		        buf.append((rnd.nextInt(10))); 
		    }
		}
		String temp = buf.toString();
		System.out.println("임시 비밀번호 :"+ temp);
		
		// 임시 비밀번호로 DB Update
		userMapper.findPw(user_id, name, phone, temp);
		return temp; // 임시 비밀번호를 front로 보낸다.

	}

	@Override
	public List<UserDto> listMember() throws Exception {

		return userMapper.listMember();
	}

	@Override
	public List<UserDto> search(String word) throws Exception {
		return userMapper.search(word);
	}

	@Override
	public void updateMember(UserDto userDto) throws Exception {
		userMapper.updateMember(userDto);

	}

	@Override
	public void deleteMember(String userId) throws Exception {
		userMapper.deleteMember(userId);
	}

	@Override
	public int idCheck(String checkId) throws Exception {
		return userMapper.idCheck(checkId);
	}

	//////////////////////////////// 관심 매물 //////////////////////////////
	@Override
	public int insertInterested(InterestedDto interestedDto) throws Exception {
		int result = interestedMapper.insertInterested(interestedDto);
		return result;
	}

	@Override
	public InterestedDto detailInterestedbyNo(int no) throws SQLException {
		return interestedMapper.detailInterestedbyNo(no);
	}

	@Override
	public InterestedDto detailInterested(String user_id, long housedeal_id) throws SQLException {
		return interestedMapper.detailInterested(user_id, housedeal_id);
	}

	// 회원 아이디로 관심 매물 리스트 가져오기
	@Override
	public List<HousedealDto> listInterested(String user_id) throws Exception {
		List<InterestedDto> interestedList = interestedMapper.listInterested(user_id);
		int size = interestedList.size();
		List<HousedealDto> housedealList = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			housedealList.add(housedealMapper.selectOne(interestedList.get(i).getHousedeal_id()));
		}
		return housedealList;

	}

	// 관심 매물 좋아요수 업데이트
	@Override
	public void housedealHitUpdate(int num, long no) throws Exception {
		housedealMapper.housedealHitUpdate(num, no);
	}

	@Override
	public void deleteInterested(int no) throws Exception {
		interestedMapper.deleteInterested(no);
	}

	//////////////////////////// keyword /////////////////////////////////////
	@Override
	public int insertKeyword(KeywordDto keywordDto) throws Exception {
		int result = keywordMapper.insertKeyword(keywordDto);
		return result;
	}

	@Override
	public KeywordDto detailKeyword(String user_id) throws SQLException {
		return keywordMapper.detailKeyword(user_id);
	}

	@Override
	public List<KeywordDto> listKeyword() throws Exception {
		return keywordMapper.listKeyword();
	}

	@Override
	public void updateKeyword(KeywordDto keywordDto) throws Exception {
		keywordMapper.updateKeyword(keywordDto);
	}

	@Override
	public void deleteKeyword(String user_id) throws Exception {
		keywordMapper.deleteKeyword(user_id);
	}

	///////////////////////// 추천 서비스 구현 //////////////////////////////
	@Override
	public Set<HousedealDto> recommended_list(String user_id) throws Exception {
		KeywordDto current_keyword = keywordMapper.detailKeyword(user_id); // 현재 user의 keyword를 가져온다.
		List<KeywordDto> keyword_list = keywordMapper.listKeyword(); // 모든 user의 keywordlist를 가져온다.
		Set<HousedealDto> recommended_housedeal = new HashSet<HousedealDto>(); // 추천 리스트들 담을 리스트
		int size = keyword_list.size();
		List<Cosine> cos_array = new ArrayList<Cosine>();
		// Cosine[] cos_array = new Cosine[size];

		for (int i = 0; i < size; i++) {
			KeywordDto temp = keyword_list.get(i); // 한명씩 keyword를 꺼낸다.
			double current_cos = obtainCos(current_keyword, temp); // cosine 유사도 구한다.
			System.out.println("현재 유사도 : " + current_cos);
			cos_array.add(new Cosine(temp.getUser_id(), current_cos)); // 리스트에 추가
		}
		Collections.sort(cos_array); // 유사도가 높은 순서로 user_id와 유사도가 저장된다. -> 상위 3명. 첫 번째는 자신임(유사도 1)
		System.out.println(cos_array.toString());
		// Arrays.sort(cos_array);
		////////////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!나와 유사도가 1인 유저가 있으면 배열의
		// 첫번째가 내가 아닐 수 있다.
		int max_count = 4;
		int cos_array_size = cos_array.size();
		if (size != 0) { // 유저가 한명도 없을 때는 넘겨준다.
			for (int i = 0; i < cos_array_size; i++) { // 3번 반복문
				if (i == max_count)
					break; // 최대 3명만 취급하기 위해 4명에서 break;
				String current_user_id = cos_array.get(i).user_id; // 상위 세명의 id를 하나씩 꺼내서 관심 리스트를 가져온다.
				if (user_id == current_user_id)
					continue;
				List<InterestedDto> temp_list = interestedMapper.listInterested(current_user_id); // 현재 id의 관심 매물 리스트를
																									// 받아옴.
				int temp_size = temp_list.size();
				for (int j = 0; j < temp_size; j++) {
					long temp_deal_id = temp_list.get(j).getHousedeal_id(); // 관심 매물의 id를 가져온다.
					recommended_housedeal.add(housedealMapper.selectOne(temp_deal_id));
				}
			}
		}
		System.out.println(recommended_housedeal.toString());
		if (!recommended_housedeal.isEmpty())
			return recommended_housedeal; // 추천리스트가 비어있으면 null return
		else
			return null;
	}

	// 코사인 유사도 구하는 함수
	public static double obtainCos(KeywordDto current_keyword, KeywordDto current_keyword2) throws Exception {
		double Bunmo = 0;
		double Bunja = 0;
		Bunmo = Math.sqrt(current_keyword.getKeyword_D01() + current_keyword.getKeyword_D02()
				+ current_keyword.getKeyword_D03() + current_keyword.getKeyword_D04() + current_keyword.getKeyword_D05()
				+ current_keyword.getKeyword_D06() + current_keyword.getKeyword_D08() + current_keyword.getKeyword_D09()
				+ current_keyword.getKeyword_D10() + current_keyword.getKeyword_D11() + current_keyword.getKeyword_D13()
				+ current_keyword.getKeyword_D14() + current_keyword.getKeyword_D16() + current_keyword.getKeyword_D20()
				+ current_keyword.getKeyword_D21() + current_keyword.getKeyword_F01() + current_keyword.getKeyword_F02()
				+ current_keyword.getKeyword_F06() + current_keyword.getKeyword_F09() + current_keyword.getKeyword_F11()
				+ current_keyword.getKeyword_F16() + current_keyword.getKeyword_N01() + current_keyword.getKeyword_N02()
				+ current_keyword.getKeyword_N03() + current_keyword.getKeyword_N04() + current_keyword.getKeyword_N05()
				+ current_keyword.getKeyword_N07() + current_keyword.getKeyword_O01() + current_keyword.getKeyword_O02()
				+ current_keyword.getKeyword_O03() + current_keyword.getKeyword_O05() + current_keyword.getKeyword_P02()
				+ current_keyword.getKeyword_P03() + current_keyword.getKeyword_Q01() + current_keyword.getKeyword_Q02()
				+ current_keyword.getKeyword_Q03() + current_keyword.getKeyword_Q04() + current_keyword.getKeyword_Q05()
				+ current_keyword.getKeyword_Q06() + current_keyword.getKeyword_Q07() + current_keyword.getKeyword_Q08()
				+ current_keyword.getKeyword_Q09() + current_keyword.getKeyword_Q10() + current_keyword.getKeyword_Q12()
				+ current_keyword.getKeyword_Q13() + current_keyword.getKeyword_Q15() + current_keyword.getKeyword_R01()
				+ current_keyword.getKeyword_R02() + current_keyword.getKeyword_R03() + current_keyword.getKeyword_R04()
				+ current_keyword.getKeyword_R05() + current_keyword.getKeyword_R06() + current_keyword.getKeyword_R07()
				+ current_keyword.getKeyword_R08() + current_keyword.getKeyword_R09() + current_keyword.getKeyword_R10()
				+ current_keyword.getKeyword_R13())
				* Math.sqrt(current_keyword2.getKeyword_D01() + current_keyword2.getKeyword_D02()
						+ current_keyword2.getKeyword_D03() + current_keyword2.getKeyword_D04()
						+ current_keyword2.getKeyword_D05() + current_keyword2.getKeyword_D06()
						+ current_keyword2.getKeyword_D08() + current_keyword2.getKeyword_D09()
						+ current_keyword2.getKeyword_D10() + current_keyword2.getKeyword_D11()
						+ current_keyword2.getKeyword_D13() + current_keyword2.getKeyword_D14()
						+ current_keyword2.getKeyword_D16() + current_keyword2.getKeyword_D20()
						+ current_keyword2.getKeyword_D21() + current_keyword2.getKeyword_F01()
						+ current_keyword2.getKeyword_F02() + current_keyword2.getKeyword_F06()
						+ current_keyword2.getKeyword_F09() + current_keyword2.getKeyword_F11()
						+ current_keyword2.getKeyword_F16() + current_keyword2.getKeyword_N01()
						+ current_keyword2.getKeyword_N02() + current_keyword2.getKeyword_N03()
						+ current_keyword2.getKeyword_N04() + current_keyword2.getKeyword_N05()
						+ current_keyword2.getKeyword_N07() + current_keyword2.getKeyword_O01()
						+ current_keyword2.getKeyword_O02() + current_keyword2.getKeyword_O03()
						+ current_keyword2.getKeyword_O05() + current_keyword2.getKeyword_P02()
						+ current_keyword2.getKeyword_P03() + current_keyword2.getKeyword_Q01()
						+ current_keyword2.getKeyword_Q02() + current_keyword2.getKeyword_Q03()
						+ current_keyword2.getKeyword_Q04() + current_keyword2.getKeyword_Q05()
						+ current_keyword2.getKeyword_Q06() + current_keyword2.getKeyword_Q07()
						+ current_keyword2.getKeyword_Q08() + current_keyword2.getKeyword_Q09()
						+ current_keyword2.getKeyword_Q10() + current_keyword2.getKeyword_Q12()
						+ current_keyword2.getKeyword_Q13() + current_keyword2.getKeyword_Q15()
						+ current_keyword2.getKeyword_R01() + current_keyword2.getKeyword_R02()
						+ current_keyword2.getKeyword_R03() + current_keyword2.getKeyword_R04()
						+ current_keyword2.getKeyword_R05() + current_keyword2.getKeyword_R06()
						+ current_keyword2.getKeyword_R07() + current_keyword2.getKeyword_R08()
						+ current_keyword2.getKeyword_R09() + current_keyword2.getKeyword_R10()
						+ current_keyword2.getKeyword_R13());
		Bunja = current_keyword.getKeyword_D01() * current_keyword2.getKeyword_D01()
				+ current_keyword.getKeyword_D02() * current_keyword2.getKeyword_D02()
				+ current_keyword.getKeyword_D03() * current_keyword2.getKeyword_D03()
				+ current_keyword.getKeyword_D04() * current_keyword2.getKeyword_D04()
				+ current_keyword.getKeyword_D05() * current_keyword2.getKeyword_D05()
				+ current_keyword.getKeyword_D06() * current_keyword2.getKeyword_D06()
				+ current_keyword.getKeyword_D08() * current_keyword2.getKeyword_D08()
				+ current_keyword.getKeyword_D09() * current_keyword2.getKeyword_D09()
				+ current_keyword.getKeyword_D10() * current_keyword2.getKeyword_D10()
				+ current_keyword.getKeyword_D11() * current_keyword2.getKeyword_D11()
				+ current_keyword.getKeyword_D13() * current_keyword2.getKeyword_D13()
				+ current_keyword.getKeyword_D14() * current_keyword2.getKeyword_D14()
				+ current_keyword.getKeyword_D16() * current_keyword2.getKeyword_D16()
				+ current_keyword.getKeyword_D20() * current_keyword2.getKeyword_D20()
				+ current_keyword.getKeyword_D21() * current_keyword2.getKeyword_D21()
				+ current_keyword.getKeyword_F01() * current_keyword2.getKeyword_F01()
				+ current_keyword.getKeyword_F02() * current_keyword2.getKeyword_F02()
				+ current_keyword.getKeyword_F06() * current_keyword2.getKeyword_F06()
				+ current_keyword.getKeyword_F09() * current_keyword2.getKeyword_F09()
				+ current_keyword.getKeyword_F11() * current_keyword2.getKeyword_F11()
				+ current_keyword.getKeyword_F16() * current_keyword2.getKeyword_F16()
				+ current_keyword.getKeyword_N01() * current_keyword2.getKeyword_N01()
				+ current_keyword.getKeyword_N02() * current_keyword2.getKeyword_N02()
				+ current_keyword.getKeyword_N03() * current_keyword2.getKeyword_N03()
				+ current_keyword.getKeyword_N04() * current_keyword2.getKeyword_N04()
				+ current_keyword.getKeyword_N05() * current_keyword2.getKeyword_N05()
				+ current_keyword.getKeyword_N07() * current_keyword2.getKeyword_N07()
				+ current_keyword.getKeyword_O01() * current_keyword2.getKeyword_O01()
				+ current_keyword.getKeyword_O02() * current_keyword2.getKeyword_O02()
				+ current_keyword.getKeyword_O03() * current_keyword2.getKeyword_O03()
				+ current_keyword.getKeyword_O05() * current_keyword2.getKeyword_O05()
				+ current_keyword.getKeyword_P02() * current_keyword2.getKeyword_P02()
				+ current_keyword.getKeyword_P03() * current_keyword2.getKeyword_P03()
				+ current_keyword.getKeyword_Q01() * current_keyword2.getKeyword_Q01()
				+ current_keyword.getKeyword_Q02() * current_keyword2.getKeyword_Q02()
				+ current_keyword.getKeyword_Q03() * current_keyword2.getKeyword_Q03()
				+ current_keyword.getKeyword_Q04() * current_keyword2.getKeyword_Q04()
				+ current_keyword.getKeyword_Q05() * current_keyword2.getKeyword_Q05()
				+ current_keyword.getKeyword_Q06() * current_keyword2.getKeyword_Q06()
				+ current_keyword.getKeyword_Q07() * current_keyword2.getKeyword_Q07()
				+ current_keyword.getKeyword_Q08() * current_keyword2.getKeyword_Q08()
				+ current_keyword.getKeyword_Q09() * current_keyword2.getKeyword_Q09()
				+ current_keyword.getKeyword_Q10() * current_keyword2.getKeyword_Q10()
				+ current_keyword.getKeyword_Q12() * current_keyword2.getKeyword_Q12()
				+ current_keyword.getKeyword_Q13() * current_keyword2.getKeyword_Q13()
				+ current_keyword.getKeyword_Q15() * current_keyword2.getKeyword_Q15()
				+ current_keyword.getKeyword_R01() * current_keyword2.getKeyword_R01()
				+ current_keyword.getKeyword_R02() * current_keyword2.getKeyword_R02()
				+ current_keyword.getKeyword_R03() * current_keyword2.getKeyword_R03()
				+ current_keyword.getKeyword_R04() * current_keyword2.getKeyword_R04()
				+ current_keyword.getKeyword_R05() * current_keyword2.getKeyword_R05()
				+ current_keyword.getKeyword_R06() * current_keyword2.getKeyword_R06()
				+ current_keyword.getKeyword_R07() * current_keyword2.getKeyword_R07()
				+ current_keyword.getKeyword_R08() * current_keyword2.getKeyword_R08()
				+ current_keyword.getKeyword_R09() * current_keyword2.getKeyword_R09()
				+ current_keyword.getKeyword_R10() * current_keyword2.getKeyword_R10()
				+ current_keyword.getKeyword_R13() * current_keyword2.getKeyword_R13();
		return Bunja / Bunmo;
	}

	// 유사도 저장할 클래스 생성
	public static class Cosine implements Comparable<Cosine> {
		String user_id;
		double cosine;

		public Cosine() {
		}

		public Cosine(String user_id, double jaccard) {
			this.user_id = user_id;
			this.cosine = jaccard;
		}

		@Override // 내림차순으로 구현
		public int compareTo(Cosine o) {
			if (o.cosine > this.cosine)
				return 1;
			else if (o.cosine < this.cosine)
				return -1;
			return 0;
		}

		@Override
		public String toString() {
			return "Cosine [user_id=" + user_id + ", cosine=" + cosine + "]";
		}

	}

}

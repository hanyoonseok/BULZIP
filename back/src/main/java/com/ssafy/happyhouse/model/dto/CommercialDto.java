package com.ssafy.happyhouse.model.dto;

public class CommercialDto {
	private int no; // autoincrement 아니다!. DB에서 PK설정이 안됨.
	private String name; 
	private String branch_name;  
	private String daebun_code; 
	private String deabun;
	private String joongbun_code ;
	private String joongbun;
	private String sobun_code; 
	private String sobun;
	private String standard_industry_code;
	private String standard_industry; 
	private int sido_code;
	private String sido;
	private int gungu_code;
	private String gungu; 
	private long Admin_dong_code; 
	private String Admin_dong;
	private long Legal_dong_code; 
	private String Legal_dong;
	private String jibun_address;
	private String road_address;
	private String dong_number;
	private String floor_number; 
	private String door_number;
	private double lat;
	private double lng;
	public CommercialDto() {}
	public CommercialDto(String name, String branch_name, String daebun_code, String deabun,
			String joongbun_code, String joongbun, String sobun_code, String sobun, String standard_industry_code,
			String standard_industry, int sido_code, String sido, int gungu_code, String gungu, long admin_dong_code,
			String admin_dong, long legal_dong_code, String legal_dong, String jibun_address, String road_address,
			String dong_number, String floor_number, String door_number, double lat, double lng) {
		this.name = name;
		this.branch_name = branch_name;
		this.daebun_code = daebun_code;
		this.deabun = deabun;
		this.joongbun_code = joongbun_code;
		this.joongbun = joongbun;
		this.sobun_code = sobun_code;
		this.sobun = sobun;
		this.standard_industry_code = standard_industry_code;
		this.standard_industry = standard_industry;
		this.sido_code = sido_code;
		this.sido = sido;
		this.gungu_code = gungu_code;
		this.gungu = gungu;
		Admin_dong_code = admin_dong_code;
		Admin_dong = admin_dong;
		Legal_dong_code = legal_dong_code;
		Legal_dong = legal_dong;
		this.jibun_address = jibun_address;
		this.road_address = road_address;
		this.dong_number = dong_number;
		this.floor_number = floor_number;
		this.door_number = door_number;
		this.lat = lat;
		this.lng = lng;
	}
	public CommercialDto(int no, String name, String branch_name, String daebun_code, String deabun,
			String joongbun_code, String joongbun, String sobun_code, String sobun, String standard_industry_code,
			String standard_industry, int sido_code, String sido, int gungu_code, String gungu, long admin_dong_code,
			String admin_dong, long legal_dong_code, String legal_dong, String jibun_address, String road_address,
			String dong_number, String floor_number, String door_number, double lat, double lng) {
		super();
		this.no = no;
		this.name = name;
		this.branch_name = branch_name;
		this.daebun_code = daebun_code;
		this.deabun = deabun;
		this.joongbun_code = joongbun_code;
		this.joongbun = joongbun;
		this.sobun_code = sobun_code;
		this.sobun = sobun;
		this.standard_industry_code = standard_industry_code;
		this.standard_industry = standard_industry;
		this.sido_code = sido_code;
		this.sido = sido;
		this.gungu_code = gungu_code;
		this.gungu = gungu;
		Admin_dong_code = admin_dong_code;
		Admin_dong = admin_dong;
		Legal_dong_code = legal_dong_code;
		Legal_dong = legal_dong;
		this.jibun_address = jibun_address;
		this.road_address = road_address;
		this.dong_number = dong_number;
		this.floor_number = floor_number;
		this.door_number = door_number;
		this.lat = lat;
		this.lng = lng;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
	public String getDaebun_code() {
		return daebun_code;
	}
	public void setDaebun_code(String daebun_code) {
		this.daebun_code = daebun_code;
	}
	public String getDeabun() {
		return deabun;
	}
	public void setDeabun(String deabun) {
		this.deabun = deabun;
	}
	public String getJoongbun_code() {
		return joongbun_code;
	}
	public void setJoongbun_code(String joongbun_code) {
		this.joongbun_code = joongbun_code;
	}
	public String getJoongbun() {
		return joongbun;
	}
	public void setJoongbun(String joongbun) {
		this.joongbun = joongbun;
	}
	public String getSobun_code() {
		return sobun_code;
	}
	public void setSobun_code(String sobun_code) {
		this.sobun_code = sobun_code;
	}
	public String getSobun() {
		return sobun;
	}
	public void setSobun(String sobun) {
		this.sobun = sobun;
	}
	public String getStandard_industry_code() {
		return standard_industry_code;
	}
	public void setStandard_industry_code(String standard_industry_code) {
		this.standard_industry_code = standard_industry_code;
	}
	public String getStandard_industry() {
		return standard_industry;
	}
	public void setStandard_industry(String standard_industry) {
		this.standard_industry = standard_industry;
	}
	public int getSido_code() {
		return sido_code;
	}
	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public int getGungu_code() {
		return gungu_code;
	}
	public void setGungu_code(int gungu_code) {
		this.gungu_code = gungu_code;
	}
	public String getGungu() {
		return gungu;
	}
	public void setGungu(String gungu) {
		this.gungu = gungu;
	}
	public long getAdmin_dong_code() {
		return Admin_dong_code;
	}
	public void setAdmin_dong_code(long admin_dong_code) {
		Admin_dong_code = admin_dong_code;
	}
	public String getAdmin_dong() {
		return Admin_dong;
	}
	public void setAdmin_dong(String admin_dong) {
		Admin_dong = admin_dong;
	}
	public long getLegal_dong_code() {
		return Legal_dong_code;
	}
	public void setLegal_dong_code(long legal_dong_code) {
		Legal_dong_code = legal_dong_code;
	}
	public String getLegal_dong() {
		return Legal_dong;
	}
	public void setLegal_dong(String legal_dong) {
		Legal_dong = legal_dong;
	}
	public String getJibun_address() {
		return jibun_address;
	}
	public void setJibun_address(String jibun_address) {
		this.jibun_address = jibun_address;
	}
	public String getRoad_address() {
		return road_address;
	}
	public void setRoad_address(String road_address) {
		this.road_address = road_address;
	}
	public String getDong_number() {
		return dong_number;
	}
	public void setDong_number(String dong_number) {
		this.dong_number = dong_number;
	}
	public String getFloor_number() {
		return floor_number;
	}
	public void setFloor_number(String floor_number) {
		this.floor_number = floor_number;
	}
	public String getDoor_number() {
		return door_number;
	}
	public void setDoor_number(String door_number) {
		this.door_number = door_number;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "CommercialDto [no=" + no + ", name=" + name + ", branch_name=" + branch_name + ", daebun_code="
				+ daebun_code + ", deabun=" + deabun + ", joongbun_code=" + joongbun_code + ", joongbun=" + joongbun
				+ ", sobun_code=" + sobun_code + ", sobun=" + sobun + ", standard_industry_code="
				+ standard_industry_code + ", standard_industry=" + standard_industry + ", sido_code=" + sido_code
				+ ", sido=" + sido + ", gungu_code=" + gungu_code + ", gungu=" + gungu + ", Admin_dong_code="
				+ Admin_dong_code + ", Admin_dong=" + Admin_dong + ", Legal_dong_code=" + Legal_dong_code
				+ ", Legal_dong=" + Legal_dong + ", jibun_address=" + jibun_address + ", road_address=" + road_address
				+ ", dong_number=" + dong_number + ", floor_number=" + floor_number + ", door_number=" + door_number
				+ ", lat=" + lat + ", lng=" + lng + "]";
	}
	
	
}

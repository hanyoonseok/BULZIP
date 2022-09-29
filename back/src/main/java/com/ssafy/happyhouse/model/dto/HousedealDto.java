package com.ssafy.happyhouse.model.dto;

public class HousedealDto {
	private long no; 			
	private long aptCode;		
	private String aptName;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String dongCode;
	private String dongName;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	private String type;
	private String rentMoney;
	private int hit;
	public HousedealDto() {}
	public HousedealDto(long aptCode, String aptName, int buildYear, String jibun, String lat, String lng,
			String dongCode, String dongName, String dealAmount, int dealYear, int dealMonth, int dealDay, String area,
			String floor, String type, String rentMoney, int hit) {
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.type = type;
		this.rentMoney = rentMoney;
		this.hit = hit;
	}
	public HousedealDto(long no, long aptCode, String aptName, int buildYear, String jibun, String lat, String lng,
			String dongCode, String dongName, String dealAmount, int dealYear, int dealMonth, int dealDay, String area,
			String floor, String type, String rentMoney, int hit) {
		super();
		this.no = no;
		this.aptCode = aptCode;
		this.aptName = aptName;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.dealAmount = dealAmount;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.area = area;
		this.floor = floor;
		this.type = type;
		this.rentMoney = rentMoney;
		this.hit = hit;
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	@Override
	public String toString() {
		return "HousedealDto [no=" + no + ", aptCode=" + aptCode + ", aptName=" + aptName + ", buildYear=" + buildYear
				+ ", jibun=" + jibun + ", lat=" + lat + ", lng=" + lng + ", dongCode=" + dongCode + ", dongName="
				+ dongName + ", dealAmount=" + dealAmount + ", dealYear=" + dealYear + ", dealMonth=" + dealMonth
				+ ", dealDay=" + dealDay + ", area=" + area + ", floor=" + floor + ", type=" + type + ", rentMoney="
				+ rentMoney + ", hit=" + hit + "]";
	}
	
	
}

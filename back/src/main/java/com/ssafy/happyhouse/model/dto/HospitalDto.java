package com.ssafy.happyhouse.model.dto;

public class HospitalDto {
	private int no;
	private String dutyinf;
	private String dutytel;
	private String lat;
	private String lon;
	private String dutyname;
	private String dutyaddr;
	
	public HospitalDto() {}
	public HospitalDto(String dutyinf, String dutytel, String lat, String lon, String dutyname, String dutyaddr) {
		this.dutyinf = dutyinf;
		this.dutytel = dutytel;
		this.lat = lat;
		this.lon = lon;
		this.dutyname = dutyname;
		this.dutyaddr = dutyaddr;
	}
	public HospitalDto(int no, String dutyinf, String dutytel, String lat, String lon, String dutyname, String dutyaddr) {
		this.no = no;
		this.dutyinf = dutyinf;
		this.dutytel = dutytel;
		this.lat = lat;
		this.lon = lon;
		this.dutyname = dutyname;
		this.dutyaddr = dutyaddr;
	}

	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getDutyinf() {
		return dutyinf;
	}
	
	public void setDutyinf(String dutyinf) {
		this.dutyinf = dutyinf;
	}
	
	public String getDutytel() {
		return dutytel;
	}
	
	public void setDutytel(String dutytel) {
		this.dutytel = dutytel;
	}
	
	public String getLat() {
		return lat;
	}
	
	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public String getLon() {
		return lon;
	}
	
	public void setLon(String lon) {
		this.lon = lon;
	}
	
	public String getDutyname() {
		return dutyname;
	}
	
	public void setDutyname(String dutyname) {
		this.dutyname = dutyname;
	}
	
	public String getDutyaddr() {
		return dutyaddr;
	}
	
	public void setDutyaddr(String dutyaddr) {
		this.dutyaddr = dutyaddr;
	}
}


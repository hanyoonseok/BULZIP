package com.ssafy.happyhouse.model.dto;

public class BabyDto {
	private int no;
	private String telno;
	private String addr;
	private String kindername;
	
	public BabyDto() {}
	public BabyDto(String telno, String addr, String kindername) {
		this.telno = telno;
		this.addr = addr;
		this.kindername = kindername;
	}
	public BabyDto(int no, String telno, String addr, String kindername) {
		this.no = no;
		this.telno = telno;
		this.addr = addr;
		this.kindername = kindername;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTelno() {
		return telno;
	}
	public void setTelno(String telno) {
		this.telno = telno;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getKindername() {
		return kindername;
	}
	public void setKindername(String kindername) {
		this.kindername = kindername;
	}
}


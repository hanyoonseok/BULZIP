package com.ssafy.happyhouse.model.dto;

public class InterestedDto {
	private int no;
	private String user_id; // user 테이블의 user_id. id가 아니다
	private long housedeal_id;
	public InterestedDto() {
		
	}
	public InterestedDto(String user_id, long housedeal_id) {
		this.user_id = user_id;
		this.housedeal_id = housedeal_id;
	}
	public InterestedDto(int no, String user_id, long housedeal_id) {
		this.no = no;
		this.user_id = user_id;
		this.housedeal_id = housedeal_id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public long getHousedeal_id() {
		return housedeal_id;
	}
	public void setHousedeal_id(long housedeal_id) {
		this.housedeal_id = housedeal_id;
	}
	@Override
	public String toString() {
		return "InterestedDto [no=" + no + ", user_id=" + user_id + ", housedeal_id=" + housedeal_id + "]";
	}
	
	
	
}

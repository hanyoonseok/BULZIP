package com.ssafy.happyhouse.model.dto;

public class UserDto {

	private int id;
	private String userId;
	private String password;
	private String name;
	private String phone;
	private int role;
	
	public UserDto(int id, String userId, String password, String name, String phone, int role) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.role = role;
	}
	
	public UserDto(int id, String userId, String password, String name, String phone) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public UserDto(String userId, String password, String name, String phone) {
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phone = phone;
	}

	public UserDto() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userId=" + userId + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", role=" + role + "]";
	}
	
	
}

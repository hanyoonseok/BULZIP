package com.ssafy.happyhouse.model.service;

import java.util.Map;

public interface IJwtService {
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	String getUserId();
	boolean isUsable(String jwt);
}

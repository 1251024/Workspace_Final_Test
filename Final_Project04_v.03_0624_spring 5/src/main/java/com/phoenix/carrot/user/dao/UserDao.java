package com.phoenix.carrot.user.dao;

import com.phoenix.carrot.user.dto.UserDto;

public interface UserDao {
	

	String NAMESPACE = "usermapper.";
	
	public UserDto login(UserDto dto);
	
	public int regist(UserDto dto);
	
	public String idcheck(String userid);

}

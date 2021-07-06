package com.mvc.upgrade.model.dao;


import com.mvc.upgrade.model.dto.UserDto;

public interface UserDao {

	String NAMESPACE = "usermapper.";
	
	public UserDto login(UserDto dto);
	
	public int regist(UserDto dto);
	
	public UserDto idcheck(String userid);
	
	public String find_id(String useremail);
	
	public UserDto selectOneById(String userid);
	
	public UserDto selectOneByEmail(String useremail);
	
	public int updatePw(UserDto dto);
}

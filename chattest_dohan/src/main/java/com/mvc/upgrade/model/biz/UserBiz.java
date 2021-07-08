package com.mvc.upgrade.model.biz;


import javax.servlet.http.HttpServletResponse;

import com.mvc.upgrade.model.dto.UserDto;

public interface UserBiz {

	public UserDto login(UserDto dto);
	
	public int regist(UserDto dto);
	
	public UserDto idcheck(String userid);
	
	public String find_id(String useremail);
	
	//이메일발송
	public void sendEmail(UserDto dto);

	//비밀번호찾기
	public void findPw(UserDto dto) throws Exception;

	
}

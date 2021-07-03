package com.mvc.upgrade.model.biz;


import com.mvc.upgrade.model.dto.MYMemberDto;

public interface MYMemberBiz {


	public MYMemberDto login(MYMemberDto dto);


	//회원가입
	public int insert(MYMemberDto dto);
}

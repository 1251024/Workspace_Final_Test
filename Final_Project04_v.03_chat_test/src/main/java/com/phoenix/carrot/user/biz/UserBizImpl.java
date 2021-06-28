package com.phoenix.carrot.user.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.carrot.user.dao.UserDao;
import com.phoenix.carrot.user.dto.UserDto;

@Service
public class UserBizImpl implements UserBiz {


	@Autowired
	private UserDao dao;
	
	
	@Override
	public UserDto login(UserDto dto) {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}


	@Override
	public int regist(UserDto dto) {
		// TODO Auto-generated method stub
		return dao.regist(dto);
	}


	@Override
	public String idcheck(String userid) {
		// TODO Auto-generated method stub
		return dao.idcheck(userid);
	}

}

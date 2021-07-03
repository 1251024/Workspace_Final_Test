package com.mvc.upgrade.model.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.upgrade.model.dao.MYMemberDao;
import com.mvc.upgrade.model.dto.MYMemberDto;

@Service
public class MYMemberBizImpl implements MYMemberBiz {

	@Autowired
	private MYMemberDao dao;
	
	@Override
	public MYMemberDto login(MYMemberDto dto) {
		return dao.login(dto);
	}

	@Override
	public int insert(MYMemberDto dto) {
		return dao.insert(dto);
	}

}

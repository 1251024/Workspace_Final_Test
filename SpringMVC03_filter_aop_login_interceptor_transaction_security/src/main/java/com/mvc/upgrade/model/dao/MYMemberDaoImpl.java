package com.mvc.upgrade.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.upgrade.model.dto.MYMemberDto;

@Repository
public class MYMemberDaoImpl implements MYMemberDao{

	@Autowired
	private SqlSessionTemplate sqlSession;//applicationContext에 있는 sqlSession가져온거
	
	@Override
	public MYMemberDto login(MYMemberDto dto) {
		
		MYMemberDto res = null;
		
		try {
			res= sqlSession.selectOne(NAMESPACE+"login", dto);//매퍼(NAMESPACE selectOne)를 연결해서 dto값 리턴받아서 연결해주겠다.
			//"res= "은 꼭 해줘야한다!! res= null이 되어버림
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int insert(MYMemberDto dto) {
		int res = 0;
		
		try {
			res= sqlSession.insert(NAMESPACE+"insert",dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

}

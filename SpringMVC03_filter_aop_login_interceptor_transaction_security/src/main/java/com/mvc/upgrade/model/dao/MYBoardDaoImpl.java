package com.mvc.upgrade.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.upgrade.model.dto.MYBoardDto;

@Repository
public class MYBoardDaoImpl implements MYBoardDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<MYBoardDto> selectList() {
		
		List<MYBoardDto> list= new ArrayList<MYBoardDto>();
		
		try {
			list=sqlSession.selectList(NAMESPACE+"selectList");
					
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MYBoardDto selectOne(int myno) {
		
		MYBoardDto dto = new MYBoardDto();
		
		try {
			dto = sqlSession.selectOne(NAMESPACE+"selectOne", myno);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public int insert(MYBoardDto dto) {
		
		int res = 0;
		
		try {
			res = sqlSession.insert(NAMESPACE+"insert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int update(MYBoardDto dto) {

		int res = 0;
		
		try {
			res = sqlSession.update(NAMESPACE+"update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int delete(int myno) {

		int res = 0;
		
		try {
			res = sqlSession.delete(NAMESPACE+"delete", myno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public String test() {
		return null;
	}

}

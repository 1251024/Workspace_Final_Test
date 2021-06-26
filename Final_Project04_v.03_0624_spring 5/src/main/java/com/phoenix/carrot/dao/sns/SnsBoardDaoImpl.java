package com.phoenix.carrot.dao.sns;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.phoenix.carrot.dto.sns.EntireBoardDto;

@Repository
public class SnsBoardDaoImpl implements SnsBoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<EntireBoardDto> snsBoardList() {
		
		List<EntireBoardDto> snsBoardList = new ArrayList<EntireBoardDto>();
		
		
		try {
			snsBoardList = sqlSession.selectList(NAMESPACE + "snsBoardList");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return snsBoardList;
	}

	@Override
	public EntireBoardDto snsBoardOne(int entireBoardSeq) {
		
		EntireBoardDto dto = null;
		
		try {
			dto = sqlSession.selectOne(NAMESPACE + "snsBoardOne", entireBoardSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}


	@Override
	public int snsBoardInsert(EntireBoardDto dto) {
		int res = 0;
		
		try {
			res = sqlSession.insert(NAMESPACE + "snsBoardInsert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int snsBoardUpdate(EntireBoardDto dto) {
		
		int res = 0;
		
		try {
			res = sqlSession.update(NAMESPACE + "snsBoardUpdate", dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public int snsBoardDelete(int entireBoardSeq) {
		
		int res = 0;
		
		try {
			res = sqlSession.delete(NAMESPACE + "snsBoardDelete", entireBoardSeq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}

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
		return null;
	}


	@Override
	public int snsBoardInsert(EntireBoardDto dto) {
		return 0;
	}

	@Override
	public int snsBoardUpdate(EntireBoardDto dto) {
		return 0;
	}

	@Override
	public int snsBoardDelete(int entireBoardSeq) {
		return 0;
	}

}

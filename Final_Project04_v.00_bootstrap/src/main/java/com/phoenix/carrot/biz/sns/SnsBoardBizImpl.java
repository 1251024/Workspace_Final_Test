package com.phoenix.carrot.biz.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.carrot.dao.sns.SnsBoardDao;
import com.phoenix.carrot.dto.sns.EntireBoardDto;

@Service
public class SnsBoardBizImpl implements SnsBoardBiz {
	
	@Autowired
	private SnsBoardDao dao;
	
	@Override
	public List<EntireBoardDto> snsBoardList() {
		
		return dao.snsBoardList();
	}

	@Override
	public EntireBoardDto snsBoardOne(int entireBoardSeq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int snsBoardInsert(EntireBoardDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int snsBoardUpdate(EntireBoardDto dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int snsBoardDelete(int entireBoardSeq) {
		// TODO Auto-generated method stub
		return 0;
	}

}

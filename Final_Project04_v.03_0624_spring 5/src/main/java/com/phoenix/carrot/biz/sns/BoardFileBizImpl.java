package com.phoenix.carrot.biz.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.carrot.dao.sns.BoardFileDao;

@Service
public class BoardFileBizImpl implements BoardFileBiz {
	
	@Autowired
	private BoardFileDao dao;
	
	//첨부파일 목록
	@Override
	public List<String> getAttach(int entireBoardSeq) throws Exception {

		return dao.getAttach(entireBoardSeq);
	}

}

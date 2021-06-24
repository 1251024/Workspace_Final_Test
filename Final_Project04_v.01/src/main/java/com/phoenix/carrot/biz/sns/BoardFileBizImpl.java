package com.phoenix.carrot.biz.sns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.carrot.dao.sns.BoardFileDao;

@Service
public class BoardFileBizImpl implements BoardFileBiz {
	
	@Autowired
	private BoardFileDao dao;

	@Override
	public void addAttach(String boardFileName, int entireBoardSeq) throws Exception {
		dao.addAttach(boardFileName, entireBoardSeq);

	}

	@Override
	public List<String> getAttach(int entireBoardSeq) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateAttach(String boardFileName, int entireBoardSeq) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAttach(String boardFileName) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAllAttach(int entireBoardSeq) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateAttachCnt(int entireBoardSeq) throws Exception {
		// TODO Auto-generated method stub

	}

}

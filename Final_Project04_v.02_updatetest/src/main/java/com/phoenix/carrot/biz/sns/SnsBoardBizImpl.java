package com.phoenix.carrot.biz.sns;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phoenix.carrot.dao.sns.SnsBoardDao;
import com.phoenix.carrot.dao.sns.BoardFileDao;
import com.phoenix.carrot.dto.sns.EntireBoardDto;

@Service
public class SnsBoardBizImpl implements SnsBoardBiz {
	
	private Logger logger = LoggerFactory.getLogger(SnsBoardBizImpl.class);
	
	@Autowired
	private BoardFileDao fileDao;
	
	@Autowired
	private SnsBoardDao dao;
	
	@Override
	public List<EntireBoardDto> snsBoardList() {
		
		return dao.snsBoardList();
	}

	@Override
	public EntireBoardDto snsBoardOne(int entireBoardSeq) {
		
		return dao.snsBoardOne(entireBoardSeq);
	}
	
	@Transactional
	@Override
	public void snsBoardInsert(EntireBoardDto dto) throws Exception{
		/*
		게시글 입력처리와 게시금 첨부파일 입력처리가 동시에 이루어지기 때문에 
		트랜잭션 처리를 반드시 해주어야 한다.
		*/
		//임플리먼트 파일을 예외처리 해주면 비즈도 해줘야한다.. 일단 지켜보자..
		
		String[] files = dto.getFiles();
		
		if (files == null) {
			//파일이 없다면 저장이 안되어야 한다.. 무엇을 리턴할지 고려해보자
			dao.snsBoardInsert(dto);
			return;  
		}
		//dto.setFileCnt(files.length); 조금만 더 고려해보자
		dao.snsBoardInsert(dto);
		logger.info("[snsBoardInsert]"+dto.toString());
		int entireBoardSeq = dto.getEntireBoardSeq();
		for(String boardFileName : files) {
			fileDao.addAttach(boardFileName, entireBoardSeq);
		}
		 
		
	}

	@Override
	public int snsBoardUpdate(EntireBoardDto dto) {
		
		return dao.snsBoardUpdate(dto);
	}

	@Override
	public int snsBoardDelete(int entireBoardSeq) {
	
		return dao.snsBoardDelete(entireBoardSeq);
	}

}

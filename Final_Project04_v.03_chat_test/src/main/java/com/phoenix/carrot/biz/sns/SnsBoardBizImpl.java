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
		
	    // 게시글 입력처리
	    dao.snsBoardInsert(dto);
	    String[] files = dto.getFiles();

	    if (files == null) {
	        return;
	    }
        
        logger.info("snsboardinsert = " + dto.toString());
        int entireBoardSeq = dto.getEntireBoardSeq();
        for (String fileName : files) {
        	fileDao.addAttach(fileName, entireBoardSeq);
        }
	}

	@Override
	public int snsBoardUpdate(EntireBoardDto dto) {
		
		return dao.snsBoardUpdate(dto);
	}
	
	@Transactional
	@Override
	public void snsBoardDelete(int entireBoardSeq) throws Exception {
	
    	fileDao.deleteAllAttach(entireBoardSeq);
        dao.snsBoardDelete(entireBoardSeq);
	}

}

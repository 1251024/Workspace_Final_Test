package com.phoenix.carrot.biz.sns;

import java.util.List;

import com.phoenix.carrot.dto.sns.EntireBoardDto;

public interface SnsBoardBiz {
	
	public List<EntireBoardDto> snsBoardList();
	public EntireBoardDto snsBoardOne(int entireBoardSeq);
	public void snsBoardInsert(EntireBoardDto dto) throws Exception;
	public int snsBoardUpdate(EntireBoardDto dto);
	public void snsBoardDelete(int entireBoardSeq) throws Exception;

}

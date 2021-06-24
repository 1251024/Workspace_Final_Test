package com.phoenix.carrot.biz.sns;

import java.util.List;

import com.phoenix.carrot.dto.sns.EntireBoardDto;

public interface SnsBoardBiz {
	
	public List<EntireBoardDto> snsBoardList();
	public EntireBoardDto snsBoardOne(int entireBoardSeq);
	public int snsBoardInsert(EntireBoardDto dto);
	public int snsBoardUpdate(EntireBoardDto dto);
	public int snsBoardDelete(int entireBoardSeq);

}

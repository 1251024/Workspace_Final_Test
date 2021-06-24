package com.phoenix.carrot.dto.sns;

import java.sql.Date;

public class BoardFileDto {
	//파일 Dto는 필드생성자를 생성하지 않지만 상황에 따라서 생성해주자
	//또한 toString 을 일단 같이 만들어보되, 필요없으면 삭제하자
	private int boardFileSeq;
	private String boardFileName;
	private int entireBoardSeq;
	private Date boardFileDate;
	private int boardFileCnt;
	
	/*
	public boardFileDto() {

	}
	
	

	public boardFileDto(int boardFileSeq, String boardFileName, int entireBoardSeq, Date boardFileDate,
			int boardFileCnt) {

		this.boardFileSeq = boardFileSeq;
		this.boardFileName = boardFileName;
		this.entireBoardSeq = entireBoardSeq;
		this.boardFileDate = boardFileDate;
		this.boardFileCnt = boardFileCnt;
	}
	*/


	public int getBoardFileSeq() {
		return boardFileSeq;
	}
	
	public void setBoardFileSeq(int boardFileSeq) {
		this.boardFileSeq = boardFileSeq;
	}
	
	public String getBoardFileName() {
		return boardFileName;
	}
	
	public void setBoardFileName(String boardFileName) {
		this.boardFileName = boardFileName;
	}
	
	public int getEntireBoardSeq() {
		return entireBoardSeq;
	}
	
	public void setEntireBoardSeq(int entireBoardSeq) {
		this.entireBoardSeq = entireBoardSeq;
	}
	
	public Date getBoardFileDate() {
		return boardFileDate;
	}
	
	public void setBoardFileDate(Date boardFileDate) {
		this.boardFileDate = boardFileDate;
	}
	
	public int getBoardFileCnt() {
		return boardFileCnt;
	}
	
	public void setBoardFileCnt(int boardFileCnt) {
		this.boardFileCnt = boardFileCnt;
	}
	
	@Override
	public String toString() {
		return "boardFileDto [boardFileSeq=" + boardFileSeq + ", boardFileName=" + boardFileName + ", entireBoardSeq="
				+ entireBoardSeq + ", boardFileDate=" + boardFileDate + ", boardFileCnt=" + boardFileCnt + "]";
	}
	
	

}

package com.phoenix.carrot.dto.sns;

import java.util.Date;

public class BoardFileDto {
	
	private int boardFileSeq;
	private String boardFileName;
	private String imgPath;
	private int entireBoardSeq;
	private Date regdate;
	
	/*
	 생성자 필요시에 만들어주기 
	public BoardFileDto() {

	}
		
	public BoardFileDto(int boardFileSeq, String boardFileName, String imgPath, int entireBoardSeq, Date regdate) {

		this.boardFileSeq = boardFileSeq;
		this.boardFileName = boardFileName;
		this.imgPath = imgPath;
		this.entireBoardSeq = entireBoardSeq;
		this.regdate = regdate;
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
	
	public String getImgPath() {
		return imgPath;
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public int getEntireBoardSeq() {
		return entireBoardSeq;
	}
	
	public void setEntireBoardSeq(int entireBoardSeq) {
		this.entireBoardSeq = entireBoardSeq;
	}
	
	public Date getRegdate() {
		return regdate;
	}
	
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}

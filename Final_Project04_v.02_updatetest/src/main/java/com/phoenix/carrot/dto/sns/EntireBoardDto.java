package com.phoenix.carrot.dto.sns;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class EntireBoardDto {
	
	//기본필드
	private int entireBoardSeq;
	private String boardKind;
	private Date boardDate;
	private String boardTitle;
	private String boardContents;
	private String userId;
	private int userSeq;
	private String snsImg;
	private int likeCount;
	
	//파일 업로드 관련 추가
	private String[] files;
	
	//private int boardFileCnt; 쓸지 안쓸지 고민해보자 
	
	public EntireBoardDto() {

	}

	public EntireBoardDto(int entireBoardSeq, String boardKind, Date boardDate, String boardTitle, String boardContents,
			String userId, int userSeq, String snsImg, int likeCount) {
		//기본
		this.entireBoardSeq = entireBoardSeq;
		this.boardKind = boardKind;
		this.boardDate = boardDate;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.userId = userId;
		this.userSeq = userSeq;
		this.snsImg = snsImg;
		this.likeCount = likeCount;

	}



	public int getEntireBoardSeq() {
		return entireBoardSeq;
	}

	public void setEntireBoardSeq(int entireBoardSeq) {
		this.entireBoardSeq = entireBoardSeq;
	}

	public String getBoardKind() {
		return boardKind;
	}

	public void setBoardKind(String boardKind) {
		this.boardKind = boardKind;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContents() {
		return boardContents;
	}

	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getSnsImg() {
		return snsImg;
	}

	public void setSnsImg(String snsImg) {
		this.snsImg = snsImg;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	
	
	public String[] getFiles() {
		return files;
	}
	
	public void setFiles(String[] files) {
		this.files = files;
	}
	
	
}

package com.phoenix.carrot.dto.sns;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class EntireBoardDto {
	
	//기본필드
	private int entireBoardSeq;
	private String boardKind;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date boardDate;
	private String boardTitle;
	private String boardContents;
	private String userId;
	private int userSeq;
	private int likeCount;
	
	private String[] files;
	
	public EntireBoardDto() {

	}

	public EntireBoardDto(int entireBoardSeq, String boardKind, Date boardDate, String boardTitle, String boardContents,
			String userId, int userSeq, int likeCount) {
		//기본
		this.entireBoardSeq = entireBoardSeq;
		this.boardKind = boardKind;
		this.boardDate = boardDate;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.userId = userId;
		this.userSeq = userSeq;
		this.likeCount = likeCount;
		//파일
		//파일생성자는 생각해보고 만들자..
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

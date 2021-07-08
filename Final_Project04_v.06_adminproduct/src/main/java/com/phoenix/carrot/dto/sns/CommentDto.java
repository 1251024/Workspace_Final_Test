package com.phoenix.carrot.dto.sns;

import java.util.Date;

public class CommentDto {
	
	private int commentNoSeq;
	private int userSeq;
	private String userId;
	private String reContent;
	private Date reRegDate;
	private int entireBoardSeq;
	private int groupNo;
	private int reRepSeq;
	
	public CommentDto() {

	}

	public CommentDto(int commentNoSeq, int userSeq, String userId, String reContent, Date reRegDate,
			int entireBoardSeq, int groupNo, int reRepSeq) {

		this.commentNoSeq = commentNoSeq;
		this.userSeq = userSeq;
		this.userId = userId;
		this.reContent = reContent;
		this.reRegDate = reRegDate;
		this.entireBoardSeq = entireBoardSeq;
		this.groupNo = groupNo;
		this.reRepSeq = reRepSeq;
	}

	public int getCommentNoSeq() {
		return commentNoSeq;
	}

	public void setCommentNoSeq(int commentNoSeq) {
		this.commentNoSeq = commentNoSeq;
	}

	public int getUserSeq() {
		return userSeq;
	}

	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public Date getReRegDate() {
		return reRegDate;
	}

	public void setReRegDate(Date reRegDate) {
		this.reRegDate = reRegDate;
	}

	public int getEntireBoardSeq() {
		return entireBoardSeq;
	}

	public void setEntireBoardSeq(int entireBoardSeq) {
		this.entireBoardSeq = entireBoardSeq;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getReRepSeq() {
		return reRepSeq;
	}

	public void setReRepSeq(int reRepSeq) {
		this.reRepSeq = reRepSeq;
	}
	
	
	
	
	
	
	
	
}

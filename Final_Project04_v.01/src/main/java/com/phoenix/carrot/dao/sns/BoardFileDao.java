package com.phoenix.carrot.dao.sns;

import java.util.List;

public interface BoardFileDao {
	//일단 boardFileName으로 줘보고 성공시에 int boardFileSeq로도 해보자
	//dto 를 선언해줄 방법도 고려해보자 
	String NAMESPACE = "boardfile.";
	
	//게시글 첨부파일 추가
	public void addAttach(String boardFileName, int entireBoardSeq) throws Exception;
	
	//게시글 첨부파일 조회
	public List<String> getAttach(int entireBoardSeq) throws Exception;
	
	//게시글 첨부파일 수정
	public void updateAttach(String boardFileName, int entireBoardSeq) throws Exception;
	
	//게시글 첨부파일 삭제
	public void deleteAttach(String boardFileName) throws Exception;
	
	//게시글 첨부파일 일괄 삭제
	public void deleteAllAttach(int entireBoardSeq) throws Exception;
	
	//특정 게시글의 첨부파일 갯수 갱신
	public void updateAttachCnt(int entireBoardSeq) throws Exception;
}

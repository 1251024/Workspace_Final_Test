package com.phoenix.carrot.dao.sns;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardFileDaoImpl implements BoardFileDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//게시글 첨부파일 추가
	@Override
	public void addAttach(String boardFileName, int entireBoardSeq) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardFileName", boardFileName);
		paramMap.put("entireBoardSeq", entireBoardSeq);
		sqlSession.insert(NAMESPACE + "addAttach", paramMap);

	}
	
	//게시글 첨부파일 조회
	@Override
	public List<String> getAttach(int entireBoardSeq) throws Exception {
		
		return sqlSession.selectList(NAMESPACE + "getAttach", entireBoardSeq);
	}
	
	//게시글 첨부파일 수정
	@Override
	public void updateAttach(String boardFileName, int entireBoardSeq) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fullName", boardFileName);
		paramMap.put("entireBoardSeq", entireBoardSeq);
		sqlSession.insert(NAMESPACE + "updateAttach", paramMap);
		
	}
	
	//게시글 첨부파일 삭제
	@Override
	public void deleteAttach(String boardFileName) throws Exception {
		
		sqlSession.delete(NAMESPACE + "deleteAttach", boardFileName);

	}
	
	//게시글 첨부파일 일괄 삭제 
	@Override
	public void deleteAllAttach(int entireBoardSeq) throws Exception {
		sqlSession.delete(NAMESPACE + "deleteAllAttach", entireBoardSeq);

	}
	
	//특정 게시글의 첨부파일 갯수 갱신
	//이건 사실 쓸지 안쓸지는 아직 모르겠다..
	@Override
	public void updateAttachCnt(int entireBoardSeq) throws Exception {
		sqlSession.update(NAMESPACE + "updateAttachCnt", entireBoardSeq);

	}

}

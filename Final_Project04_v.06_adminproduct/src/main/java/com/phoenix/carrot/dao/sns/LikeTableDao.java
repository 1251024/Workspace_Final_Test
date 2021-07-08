package com.phoenix.carrot.dao.sns;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.phoenix.carrot.dto.sns.EntireBoardDto;
import com.phoenix.carrot.dto.sns.LikeTableDto;

@Repository
public class LikeTableDao {
	
	String NAMESPACE = "liketable.";
	
	@Autowired
	private SqlSession sqlSession;
	
	public EntireBoardDto pictureSaveHeart (LikeTableDto dto) {
		//게시판 테이블에 해당 게시물의 좋아요수를 +1 하기 위한 dto 세팅
		EntireBoardDto boardDto = new EntireBoardDto();
		boardDto.setEntireBoardSeq(dto.getEntireBoardSeq());
		
		//해당 게시물의 heart를 +1한다
		sqlSession.update("snsboard.picture_heart_up", boardDto);
		
		//likeTable 테이블에 추가
		int result = sqlSession.insert(NAMESPACE + "picture_heart_save", dto);
		
		if (result == 1) {
			//좋아요 테이블에 새로운 좋아요 추가가 성공한다면 
			//갱신된 하트 갯수를 가져옴 
			boardDto = sqlSession.selectOne("snsboard.picture_heart_count", boardDto);
		}
		
		return boardDto;
	}
	
	public EntireBoardDto pictureRemoveHeart(LikeTableDto dto) {
		// 게시판 테이블에 해당 게시물의 좋아요수를 -1하기위한 세팅
		EntireBoardDto boardDto = new EntireBoardDto();
		boardDto.setEntireBoardSeq(dto.getEntireBoardSeq());
		
		//해당 게시물의 좋아요수를 -1
		sqlSession.update("snsboard.picture_heart_down", boardDto);
		
		//좋아요 테이블에서 삭제 
		int result = sqlSession.delete(NAMESPACE + "picture_heart_remove", dto);
		
		if (result == 1) {
			//좋아요 테이블에 좋아요 삭제가 성공한다면
			boardDto = sqlSession.selectOne("snsboard.picture_heart_count", boardDto);
		}
		
		return boardDto;
	}
}

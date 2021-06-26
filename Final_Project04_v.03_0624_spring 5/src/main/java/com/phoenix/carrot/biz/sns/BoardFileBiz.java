package com.phoenix.carrot.biz.sns;

import java.util.List;

public interface BoardFileBiz {
	

    // 게시글 첨부파일 조회
    public List<String> getAttach(int entireBoardSeq) throws Exception;

}

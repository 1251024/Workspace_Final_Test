package com.phoenix.carrot.model.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.phoenix.carrot.biz.sns.BoardFileBiz;
import com.phoenix.carrot.dto.sns.EntireBoardDto;
import com.phoenix.carrot.utils.MediaUtils;
import com.phoenix.carrot.utils.UploadFileUtils;

@Controller
@RequestMapping(value = "/file")
public class BoardFileController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(BoardFileController.class);
	
	@Autowired
	private BoardFileBiz boardFileBiz;
	
	//파일 저장 기본 경로 bean등록 
	//들어가면 경로를 수정해보자...
	@Resource(name = "uploadPath")
	private String uploadPath;
	
	
	//업로드 파일
	@RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	private ResponseEntity<String> uploadFile(MultipartFile file) throws Exception {
		logger.info("=====================File UPLOAD===================");
		logger.info("ORIGINAL FILE NAME : " + file.getOriginalFilename());
		logger.info("FILE SIZE : " + file.getSize());
		logger.info("CONTENT TYPE : " + file.getContentType());
		logger.info("===================================================");
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.CREATED);
	}
	
	//게시글 첨부파일 조회 
	@RequestMapping(value="snsboardlist.do/{entireBoardSeq}")
	public ResponseEntity<List<String>> fileList(@PathVariable("entireBoardSeq") int entireBoardSeq) throws Exception {
	ResponseEntity<List<String>> entity = null;
	try {
		entity = new ResponseEntity<List<String>>(boardFileBiz.getAttach(entireBoardSeq), HttpStatus.OK);
	} catch (Exception e) {
		e.printStackTrace();
		entity = new ResponseEntity<List<String>>(HttpStatus.BAD_REQUEST);
	}
	return entity;
	}
	
	//업로드 파일 보여주기
	@RequestMapping(value= "/display")
	public ResponseEntity<byte[]> displayFile(String boardFileName) throws Exception {
		//InputStream : 바이트 단위로 데이터를 읽는다. 외부로부터 읽어 들이는 기능관련 클래스 
		InputStream inputStream = null;
		ResponseEntity<byte[]> entity = null;
		logger.info("file name : " + boardFileName);
		
		try {
			//파일 확장자 추출
			String formatName = boardFileName.substring(boardFileName.lastIndexOf(".") + 1);
			//이미지 파일 여부 확인, 이미지 파일일 경우 이미지 MINEType 지정
			MediaType mediaType = MediaUtils.getMediaType(formatName);
			//HttpHeaders 객체 생성
			HttpHeaders httpHeaders = new HttpHeaders();
			//실제 경로의 파일을 읽어들이고 InputStream 객체 생성
			inputStream = new FileInputStream(uploadPath + boardFileName);
			//dlalwl vkdlfdlf ruddn 
			if (mediaType != null) {
				httpHeaders.setContentType(mediaType);
				//이미지파일이 아닐경우
			} else {
				//디렉토리 + UUID 제거
				boardFileName = boardFileName.substring(boardFileName.indexOf("_") + 1);
				//다운로드 MINE Type 지정
				httpHeaders.setContentType(mediaType.APPLICATION_OCTET_STREAM);
				//한글이름의 파일 인코딩 처리 
				httpHeaders.add("Content-Disposition", "attachment: filename=\"" + new String(boardFileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			inputStream.close();
		}
		return entity;
		
	}
	
	//단일 파일 데이터 삭제 1 : 게시글 작성화면
	public ResponseEntity<String> snsBoardInsertRemoveFile(String boardFileName) throws Exception {
		
		//파일 삭제
		
		return null;
	}
	
}
























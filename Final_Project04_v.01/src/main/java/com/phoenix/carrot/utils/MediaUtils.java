package com.phoenix.carrot.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtils {
	//첨부파일의 타입이 이미지인지 다른 파일인지 판별하는 메소드 
	
	private static Map<String, MediaType> mediaMap;
	
	//mediaMap에 이미지 확장자명에 따른 MINEType 저장
	static {
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
		
		//mediaMap.put("mp4", MediaType.ALL); 동영상 게시하는법.. 생각해보자..
	}
	
	public static MediaType getMediaType(String type) {
		//이미지 MINEType 꺼내서 반환, 이미지 파일이 아닐 경우 null반환 
		return mediaMap.get(type.toUpperCase());
	}
}

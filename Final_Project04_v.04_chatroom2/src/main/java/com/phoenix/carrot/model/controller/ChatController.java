package com.phoenix.carrot.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

	private Logger logger = LoggerFactory.getLogger(SnsController.class);
	
	//Header Controller
	@RequestMapping("/header.do")
	public String header(Model model) {
		logger.info("[Controller] : header.do");
		
		return "header";
	}
	
	//chat test 1
	@RequestMapping("/chat.do")
	public String chat(Model model) {
		logger.info("[Controller] : chat.do");
		
		return "chat";
	}
	
	
	//Chatting Board Controller
	//1. 게시판 페이지 이동 메소드
	@RequestMapping(value = "/chatboard_home.do")
	public String chatboard(Model model) {
		logger.info("[Controller] : chatboard_home.do");
		
		return "chatboard_home";
	}
}




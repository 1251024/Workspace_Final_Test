package com.chat.handler.ControllerView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerView {
	private Logger logger = LoggerFactory.getLogger(ControllerView.class);

	// 채팅방 입장
	@RequestMapping(value = "/chat.do", method = RequestMethod.GET)
	public String view_chat(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		return "view_chat";
	}
	
	
//	@RequestMapping("/chat.do")
//	public String chat(Model model) {
//		logger.info("[Controller] : chat.do");
//		
//		return "chat";
//	}
	
	
}

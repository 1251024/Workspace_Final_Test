package com.lee.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
	
	private Logger logger = LoggerFactory.getLogger(ChatController.class);

	@RequestMapping("/chat.do")
	public String chat(Model model) {
		logger.info("[Controller] : chat.do");
		
		return "chat";
	}
	

}

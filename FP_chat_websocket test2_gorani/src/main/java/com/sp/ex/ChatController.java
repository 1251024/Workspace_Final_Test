package com.sp.ex;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jdk.internal.org.jline.utils.Log;

@Controller
public class ChatController {
	
	private Logger logger = LoggerFactory.getLogger(ChatController.class);
//
//	@RequestMapping("/chat.do")
//	public String chat(Model model) {
//		logger.info("[Controller] : chat.do");
//		
//		return "chat";
//	}
	
	@GetMapping("/chat")
	public void chat(Model model) {
		//spring security를 적용, User클래스를 상속받은 CustomUser클래스의 정보(로그인한 ID)를 Model에 담아 뷰로 보냈다.
		CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		logger.info("======================================");
		logger.info("@ChatController, GET Chat / Username : "+ user.getUsername());
		model.addAllAttributes("userid", user.getUsername());
		
	}

}

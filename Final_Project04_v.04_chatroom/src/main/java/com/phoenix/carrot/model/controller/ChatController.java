package com.phoenix.carrot.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {

	private Logger logger = LoggerFactory.getLogger(SnsController.class);
	
	@RequestMapping("/header.do")
	public String header(Model model) {
		logger.info("[Controller] : header.do");
		
		return "header";
	}
}

package com.phoenix.carrot.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phoenix.carrot.biz.sns.SnsBoardBiz;

@Controller
public class SnsController {
	
	private Logger logger = LoggerFactory.getLogger(SnsController.class);
	
	@Autowired
	private SnsBoardBiz biz;
	
	@RequestMapping("/main.do")
	public String mainForm(Model model) {
		logger.info("[Controller] : main.do");
		model.addAttribute("snsBoardList", biz.snsBoardList());
		
		return "main";
	}
	
}

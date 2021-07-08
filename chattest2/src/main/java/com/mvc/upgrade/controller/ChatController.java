package com.mvc.upgrade.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatController {

	// 채팅방 입장
	@RequestMapping(value = "/chat.do", method = RequestMethod.GET)
	public String view_chat(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		return "chat";
	}
	

//	//글작성
//	@RequestMapping("/chatroom.do")
//	public String insertForm() {
//	
//		return "chat.do";
//	}
//	
	
//	
//	@RequestMapping(value = "/writeres.do", method = RequestMethod.POST)
//	public String insertRes(MYBoardDto dto) {
//		
//		if(biz.insert(dto) > 0) {
//			return "redirect:list.do";//"redirect:넘겨줄 context 경로"하면 자동으로 센드 리다이렉트 해줌
//		}
//		
//		
//		return "redirect:writeform.do";
//	}
}
package com.sp.ex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerView {
	private Logger logger = LoggerFactory.getLogger(ControllerView.class);

	// 채팅방 입장
	@RequestMapping(value = "/chat.do", method = RequestMethod.GET)
	public String view_chat(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		return "view_chat";
	}
	
	@RequestMapping("/select.do")
	public String selectOne(Model model, int myno) {
		
		
		return "myboarddetail";
	}

	@RequestMapping("/chat")
	public ModelAndView chat() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat");
		return mv;
	}
}
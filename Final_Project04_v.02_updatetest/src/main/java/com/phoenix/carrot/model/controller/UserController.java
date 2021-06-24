package com.phoenix.carrot.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phoenix.carrot.user.biz.UserBiz;
import com.phoenix.carrot.user.dto.UserDto;

@Controller
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserBiz biz;
	
	@RequestMapping("/loginform.do")
	public String goLoginForm() {
		
		logger.info("[Controller] loginform.do");
		
		return "login";
	}
	

	@RequestMapping(value="/ajaxlogin.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> ajaxLogin(HttpSession session, @RequestBody UserDto dto) {
		logger.info("[Controller] : ajaxlogin.do");
		
		UserDto res = biz.login(dto);

		boolean check = false;
		if(res != null) {
			//matches : 넘어온 값과 저장되어있는 값을 비교
			if(passwordEncoder.matches(dto.getPassword(), res.getPassword())) {
				session.setAttribute("login", res);
				check = true;
			} else {
				logger.info("[Controller] : password failed");
			}
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("check", check);
		
		return map;
	}
	
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("[Controller] : logout.do");
		
		session.invalidate();
		
		return "redirect:index";
	}
	
	@RequestMapping("/registform.do")
	public String registForm() {
		logger.info("[Controller] : registform.do");
		
		return "regist";
	}
	
	@RequestMapping(value="/registPost.do", method=RequestMethod.POST)
	public String registPost(UserDto dto) {
		logger.info("[Controller] : regist.do");
		
		dto.setPassword(passwordEncoder.encode(dto.getPassword()));
		
		int res = biz.regist(dto);
		
		if(res > 0) {
			return "redirect:loginform.do";
		}
		return "redirect:registform.do";
	}
	
	@RequestMapping(value="/idcheck.do", method=RequestMethod.POST)
	@ResponseBody
	public int idcheck(@RequestBody String userid) {
		 String id = userid.trim();
		 System.out.println(id);
		 String res = biz.idcheck(id);
		 
		 if(res != null) {
			 return 1;
		 }
		 
		 return -1;
	}
	
}

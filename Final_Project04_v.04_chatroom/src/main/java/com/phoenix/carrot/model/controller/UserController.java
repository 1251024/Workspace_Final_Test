package com.phoenix.carrot.model.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.phoenix.carrot.recaptcha.VerifyRecaptcha;

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
	
	@ResponseBody
	@RequestMapping(value="/VerifyRecaptcha.do", method=RequestMethod.POST)
	public int VerifyRecaptcha(HttpServletRequest request) {
		
		VerifyRecaptcha.setSecretKey("6LfJBFIbAAAAAKqCWrQ2EgrLHaSnt3bsOccH1_G1");
		
		String gRecaptchaResponse = request.getParameter("recaptcha");
		
		try {
			if(VerifyRecaptcha.verify(gRecaptchaResponse)) {
				return 0; // 성공했을때
			} else {
				return 1; // 실패했을때
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1; // 에러나면 -1
		}
	}
	
	
	
	
	
	
	@RequestMapping("/findidform.do")
	public String findid() {
		logger.info("[Controller] : findid.do");
		
		return "findidform";
	}
	
	@RequestMapping(value="/ajaxfindid.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> ajaxFindid(@RequestBody Map<String, String> usermap) {
		logger.info("[Controller] : ajaxfindid.do");
		
		String email = usermap.get("useremail");
		System.out.println(email);
		String res = biz.find_id(email);
		System.out.println(res);
		Map<String, String> map = new HashMap<String, String>();
		if(res != null) {
			map.put("userid", res);
		} else {
			logger.info("[Controller] : findid error");
			map.put("userid", null);
		}
		
		return map;
	}
	
}

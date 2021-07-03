package com.mvc.upgrade.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mvc.upgrade.model.biz.MYMemberBiz;
import com.mvc.upgrade.model.dto.MYMemberDto;


@Controller
public class MYMemberController {

	//interceptor
	private Logger logger = LoggerFactory.getLogger(MYMemberController.class);

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private MYMemberBiz biz;
	
	@RequestMapping("/loginform.do")
	public String loginForm() {
		//interceptor
		logger.info("[Controller] loginform.do");
		
		return "mymemberlogin";
	}
	
	
	/*jason을 java로
	  @RequestBody	 : request로 넘어오는 데이터를 java Object(객체)로 변환
	  					(json형태를 자바형태로 binding, json key값을 MYMemberdto에 저장됨)
	  					RequestBody와 contentType:"application/json"은 같이 쓰임
	  					json객체를 java객체로 바꿔주는게 jackson(core,mapper)이 바꿔 줌
	  					jackson의 역할 : 리퀘스트의 바디에 담겨서 넘어온 값을 MYMemberdto에 연결해주는것
	  					contentType 역할:  json 문자열을 json object로 바꿔줌
	  -> ajax에서 dataType:json으로 하면 json string형태로 보내주면, jackson이 json에서 java 객체로 바꿔 줌

	java를 json으로
	
	  @ResponseBody	 : java Object(객체)를 response 객체에 데이터로 binding
	  					(MAV에 담겨서 응답되는게 아니라 바로 응답 됨)
	  					컨트롤러에서 바로 resoponsebody에 map으로 응답되는데, 
	  					디스패쳐서블릿의 뷰리졸버 안통하고 밖에서 알아들을 수 있게
	  					Jackson(core,mapper)이 map을 json으로 바꿔주는 것
	  					
	  -> response, request 항상 함께 쓰는 것이 아님! 따로따로 필요할때 쓴다.
	  -> jackson은 gson같은 역할을 해준다
	  
	  
	  
	 */
	@RequestMapping(value = "/ajaxlogin.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> ajaxLogin(HttpSession session, @RequestBody MYMemberDto dto){
		//System.out.println("res=이게 빠진거!!"+dto.getMemberid());
		
		//interceptor
		logger.info("[Controller] ajaxlogin.do");
		
		MYMemberDto res = biz.login(dto);
		boolean check = false;
		if(res != null) {
			
			//암호화된 비밀번호 검증 / 맞는지 틀린지 spring클래스가 내부적으로 알려줄거임
			//matches : 넘어온 값과 저장되어 있는 값을 비교
			if(passwordEncoder.matches(dto.getMemberpw(), res.getMemberpw())) {
				//dto.getMemberpw(): 암호화되기 전 값(평문), res.getMemberpw(): 암호화 된 값
				session.setAttribute("login", res);
				check = true;
			}
			else {
				logger.info("[Controller] password failed");
			}
		}
		
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		map.put("check", check);
		
		return map;//ajax로 다시 응답
	}
	/*memberlogin.jsp에서 
	1.ajax가 ajaxlogin.do해서 controller를 찾는다.

	2.JSON.stringify(loginVal)통해 json문자열로 변환된 데이터를

	3.contentType:"application/json" 으로 json문자열을 json객체로 변환시켜서 

	4.controller의 @RequestBody를 통해서

	request객체에 담긴 json객체를 MYMemberDto에 set시켜놓고

	(이때 jackson이 변환해줌)

	5. 체크 시켜서 map형태로 리턴하는데, resoponse객체에 담아서 jackson이 json으로 바꿔주고 

	6. ajax에서 리턴받은 json을 받아서 

	7. 성공했을땐 list.do, 실패했을땐 id 혹은 pw가 잘못되었습니다.

	------------------------------------------------
	ajax
	contentType:"application/json" 으로 json문자열을 json객체로 변환

	@RequestBody
	jackjson: json객체를 java객체로 변환
	 */

	
	
	//회원가입
	//이미가입된 회원입니다 중복확인은 ajax로 
	
	@RequestMapping("/registform.do")
	public String registFrom() {
		logger.info("[Controller] : registform.do");
		
		return "mymemberregist";
	}
	
	@RequestMapping("/registres.do")
	public String registres(MYMemberDto dto) {
		logger.info("[Controller] : registres.do");
		
		System.out.println("암호화 전 : " +dto.getMemberpw());
		dto.setMemberpw(passwordEncoder.encode(dto.getMemberpw()));
		System.out.println("암호화 후 : " +dto.getMemberpw());
		
		//성공하면
		if(biz.insert(dto) > 0) {
			return "redirect:loginform.do";
		}
		
		//실패하면
		return "redirect:registform.do";
	}
	
}

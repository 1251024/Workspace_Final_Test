package com.mvc.upgrade.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.model.OAuth2AccessToken;

import com.mvc.upgrade.login.NaverLoginBo;
import com.mvc.upgrade.login.VerifyRecaptcha;
import com.mvc.upgrade.model.biz.UserBiz;
import com.mvc.upgrade.model.dao.UserDao;
import com.mvc.upgrade.model.dto.UserDto;

@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	// NaverLoginBo
	private NaverLoginBo naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBo naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	@Autowired
	BCryptPasswordEncoder passwordEncoder; // 자동으로 암호화 걸어준다.

	@Autowired
	private UserBiz biz;
	
	@Autowired
	private UserDao dao;

	@RequestMapping("/loginform.do")
	public String loginForm(Model model, HttpSession session) {
		logger.info("[Controller] loginform.do");
		logger.info("[Controller] : naverlogin.do");
		logger.info("[Controller] : googlelogin.do");
		// 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationURL 메소드 호출
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);

		System.out.println("네이버 : " + naverAuthUrl);

		// 네이버
		model.addAttribute("naverUrl", naverAuthUrl);

		return "mymemberlogin";
	}

	/*
	 * @RequestBody : request로 넘어오는 데이터를 java Object(객체)로 변환
	 * 
	 * @ResponseBody : java Object(객체)를 response 객체에 데이터로 binding /dispatcherservlet
	 * 안에 있는 viewresolver에 안담기고 바로 응답된다. mymemberlogin.jsp로 JackSon 시리즈가 map 을 json
	 * 타입으로 변환시켜 준다. 마지막에
	 * 
	 * 정리 : requesetbody 에 담겨서 json형태로 값이 가고 마지막에 자바 오브젝트로 변경시켜 주는 것이 jackson 또 map
	 * 으로 응답하고 있는데 이걸 json으로 바꿔주는것도 jackson이 한다.
	 */

	@RequestMapping(value = "/ajaxlogin.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> ajaxLogin(HttpSession session, @RequestBody UserDto dto) {
		logger.info("[Controller] : ajaxlogin.do");

		UserDto res = biz.login(dto);

		boolean check = false;
		if (res != null) {
			// matches : 넘어온 값과 저장되어있는 값을 비교
			if (passwordEncoder.matches(dto.getPassword(), res.getPassword())) {
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

	@RequestMapping(value = "/registform.do")
	public String registform() {
		logger.info("[Controller] : registform.do");

		return "registform";
	}

	@RequestMapping(value = "/registPost.do", method = RequestMethod.POST)
	public String registPost(UserDto dto) {
		logger.info("[Controller] : regist.do");

		System.out.println(dto.getUserphone());

		dto.setPassword(passwordEncoder.encode(dto.getPassword()));

		int res = biz.regist(dto);

		if (res > 0) {
			return "redirect:loginform.do";
		}
		return "redirect:registform.do";
	}

	@RequestMapping(value = "/idcheck.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> idcheck(@RequestBody UserDto dto) {

		String id = dto.getUserid().trim();

		UserDto dtoq = biz.idcheck(id);
		System.out.println(dto);
		int res = 0;

		if (dtoq != null) {
			res = 1;
		} else {
			res = -1;
		}

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("res", res);

		return map;

	}

	@ResponseBody
	@RequestMapping(value = "/VerifyRecaptcha.do", method = RequestMethod.POST)
	public int VerifyRecaptcha(HttpServletRequest request) {

		VerifyRecaptcha.setSecretKey("6LfsPmcbAAAAAGOUrzky6N8MztAwHKgD0dlNH_Zn");
		
		String gRecaptchaResponse = request.getParameter("recaptcha");
		System.out.println(gRecaptchaResponse);
		try {
			if (VerifyRecaptcha.verify(gRecaptchaResponse)) {
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

	@RequestMapping(value = "/ajaxfindid.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> ajaxFindid(@RequestBody Map<String, String> usermap) {
		logger.info("[Controller] : ajaxfindid.do");

		String email = usermap.get("useremail");
		System.out.println(email);
		String res = biz.find_id(email);
		System.out.println(res);
		Map<String, String> map = new HashMap<String, String>();
		if (res != null) {
			map.put("userid", res);
		} else {
			logger.info("[Controller] : findid error");
			map.put("userid", null);
		}

		return map;
	}

	@RequestMapping("/findpwform.do")
	public String findpwform() {
		logger.info("[Controller] : findpwform.do");

		return "findpwform";
	}

	/*
	 * @RequestMapping(value="/naverlogin.do", method= {RequestMethod.GET,
	 * RequestMethod.POST}) public String naverLogin(Model model, HttpSession
	 * session) { logger.info("[Controller] : naverlogin.do"); // 네이버아이디로 인증 URL을
	 * 생성하기 위하여 naverLoginBO클래스의 getAuthorizationURL 메소드 호출 String naverAuthUrl =
	 * naverLoginBO.getAuthorizationUrl(session);
	 * 
	 * System.out.println("네이버 : " + naverAuthUrl);
	 * 
	 * // 네이버 model.addAttribute("naverUrl", naverAuthUrl);
	 * 
	 * return "mymemberlogin"; }
	 */
	@RequestMapping(value = "/naverlogincallback.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String naverloginCallback(Model model, @RequestParam String code, @RequestParam String state,
			HttpSession session) throws IOException, ParseException {
		logger.info("[Controller] : naverlogincallback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);

		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String 형식의 json 데이터

		/*
		 * apiResult 구조 {"resultcode":"00", "messege":"success",
		 * "reponse":{"id":"339281","nickname":"kim", "age", "gender","email","name"}} }
		 */

		// 2. String 형식인 apiResult를 json 형태로 바꿔준다.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;

		// 3. 데이터 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		String userid = (String) response_obj.get("id");
		String useremail = (String) response_obj.get("email");
		String username = (String) response_obj.get("name");
		String nickname = (String) response_obj.get("nickname");
		System.out.println(userid);
		System.out.println(useremail);
		System.out.println(username);

		// 4. 파싱 닉네임
		session.setAttribute("login", nickname);

		model.addAttribute("naverloginUser", apiResult);

		return "navercallback";
	}

	@RequestMapping(value = "/googlelogin.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> googleLogin(HttpSession session, @RequestBody UserDto dto) {
		logger.info("[Controller] : googlelogin.do");
		String username = null;
		UserDto res = biz.login(dto);
		dto.setPassword("null");
		dto.setUseraddress("null");
		dto.setUserbirthday("null");
		dto.setUserdetailaddress("null");
		dto.setUseroaddress("null");
		dto.setUserphone("null");
		if (res == null) {
			int regresult = biz.regist(dto);
			if (regresult > 0) {
				System.out.println("회원가입성공");
				session.setAttribute("login", dto);
				username = dto.getUsername();
			}
		} else {
			session.setAttribute("login", dto);
			username = dto.getUsername();
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);

		return map;

	}

	@RequestMapping("/logOut.do")
	public String logOut(HttpSession session) {

		session.invalidate();

		return "redirect:/";
	}
	
	@RequestMapping(value="/kakaologincallback.do",method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public Map<String, String> kakaoLogincallback(HttpSession session, @RequestBody UserDto dto) {
		logger.info("[Controller] : kakaologincallback.do");

		UserDto res = biz.login(dto);
		String username = null;
		dto.setPassword("null");
		dto.setUseraddress("null");
		dto.setUserbirthday("null");
		dto.setUserdetailaddress("null");
		dto.setUseroaddress("null");
		dto.setUserphone("null");
		
		if(res.getUserid() == null) {
			int regresult = biz.regist(dto);
			if (regresult > 0) {
				System.out.println("회원가입성공");
				username = dto.getUsername();
				session.setAttribute("login", dto);
			} else {
				System.out.println("회원가입실패");
			}
		} else {
			username = dto.getUsername();
			session.setAttribute("login", dto);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);

		return map;
	}

	@RequestMapping(value="/findpw.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public int findpw(@RequestBody UserDto dto) throws Exception {
		
		UserDto selectOneById = dao.selectOneById(dto.getUserid());
		UserDto selectOneByEmail = dao.selectOneByEmail(dto.getUseremail());
		int i = 0;
		
		if(selectOneById.getUserid() == null) {
			i = 1;
			
			//msg = "가입된 아이디가 없습니다. 아이디를 확인해 주세요.";
		} else if(selectOneByEmail.getUseremail() == null) {
			i = 2;
			
			//msg = "가입된 이메일이 없습니다. 이메일을 확인해 주세요.";
		} else {
			i = 3;
			//msg = "가입된 이메일로 임시 비밀번호를 전송하였습니다. 메일을 확인해주세요.";
			biz.findPw(dto);
		}
	
		return i;
	}

}
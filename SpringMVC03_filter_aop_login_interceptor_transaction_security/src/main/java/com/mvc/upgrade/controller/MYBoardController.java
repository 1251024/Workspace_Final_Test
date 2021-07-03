package com.mvc.upgrade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mvc.upgrade.model.biz.MYBoardBiz;
import com.mvc.upgrade.model.dto.MYBoardDto;

@Controller
public class MYBoardController {
	
	//interceptor
	private Logger logger = LoggerFactory.getLogger(MYBoardController.class);
	
	
	@Autowired
	private MYBoardBiz biz;
	
	
	@RequestMapping("/list.do")
	public String selectList(Model model) {
		//interceptor
		logger.info("[Controller] list.do");
		
		model.addAttribute("list", biz.selectList());
		
		return "myboardlist";
	}
	
	
	//글작성
	@RequestMapping("/writeform.do")
	public String insertForm() {
		//interceptor
		logger.info("[Controller] writeform.do");
		
		return "myboardinsert";
	}
	
	//글작성 결과 //writeres.do 하면 겟 , 포스트 둘다 받아줌
	@RequestMapping(value = "/writeres.do", method = RequestMethod.POST)
	public String insertRes(MYBoardDto dto) {
		//interceptor
		logger.info("[Controller] writeres.do");
		
		if(biz.insert(dto) > 0) {
			return "redirect:list.do";//"redirect:넘겨줄 context 경로"하면 자동으로 센드 리다이렉트 해줌
		}
		
		
		return "redirect:writeform.do";
	}
	
	@RequestMapping("/select.do")
	public String selectOne(Model model, int myno) {
		
		//interceptor
		logger.info("[Controller] select.do");
		
		
		model.addAttribute("dto", biz.selectOne(myno));
		
		return "myboardselect";//View Name
		
		//Model model(모델객체) + return "myboardselect"(string, View Name) = mav(ModelAndView)
		//mav에 값을 저장해서 DS(DispatcherServlet)로 넘어감
	}
	

	
	@RequestMapping("/delete.do")
	public String delete(Model model, int myno) {
		//interceptor
		logger.info("[Controller] delete.do");
		
		
		if(biz.delete(myno)>0) {
			return "redirect:list.do";
		}
		return "redirect:select.do?myno="+myno;
	}
	
	
	@RequestMapping("/updateform.do")
	public String updateForm(Model model, int myno) {
		
		
		//interceptor
		logger.info("[Controller] updateform.do");
				
				
				
		model.addAttribute("dto", biz.selectOne(myno));
		
		return "myboardupdate";
	}
	
	@RequestMapping("/updateRes.do")
	public String updateRes(MYBoardDto dto) {
		
		//interceptor
		logger.info("[Controller] updateRes.do");
		

		if(biz.update(dto) > 0) {
			return "redirect:select.do?myno="+dto.getMyno();
		}
		return "redirect:updateform.do?myno="+dto.getMyno();
	}
	
	@RequestMapping("/test.do")
	public String test() {
		
		logger.info("[Controller] : test.do");
		biz.test();
		return "redirect:list.do";
	}
	
	
}

package com.phoenix.carrot.model.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.phoenix.carrot.biz.sns.FileValidator;
import com.phoenix.carrot.biz.sns.SnsBoardBiz;
import com.phoenix.carrot.dto.sns.EntireBoardDto;

@Controller
public class SnsController {
	
	private Logger logger = LoggerFactory.getLogger(SnsController.class);
	
	@Autowired
	private FileValidator fileValidator;
	
	@Autowired
	private SnsBoardBiz biz;
	
	@RequestMapping("/main.do")
	public String mainForm(Model model) {
		logger.info("[Controller] : main.do");
		model.addAttribute("snsBoardList", biz.snsBoardList());
		
		return "main";
	}
	
	@RequestMapping("/snsBoardInsertForm.do")
	public String snsBoardInsertForm() {
		
		return "snsboardinsert";
	}
	
	@RequestMapping("/snsBoardInsertRes.do")
	public String snsBoardInsertRes(EntireBoardDto dto, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("[Controller] : snsBoardInsertRes.do");

		biz.snsBoardInsert(dto);
		redirectAttributes.addFlashAttribute("msg", "regSuccess");
		
		return "redirect:main.do";
		
	}
	
	@RequestMapping("/snsBoardOne.do")
	public String snsBoardOne(Model model, int entireBoardSeq) {
		logger.info("[Controller] : snsBoardOne.do");
		
		model.addAttribute("dto", biz.snsBoardOne(entireBoardSeq));
		return "snsboarddetail";
	}
	
	@RequestMapping("/snsBoardUpdateForm.do")
	public String snsBoardUpdateForm(Model model, int entireBoardSeq) {
		logger.info("[Controller] : snsBoardUpdateForm.do");
		
		model.addAttribute("dto", biz.snsBoardOne(entireBoardSeq));
		return "snsboardupdate";
	}
	
	@RequestMapping("/snsBoardUpdateRes.do")
	public String snsBoardUpdateRes(EntireBoardDto dto) {
		logger.info("[Controller] : snsBoardUpdateRes.do");
		
		if (biz.snsBoardUpdate(dto) > 0) {
			return "redirect:snsBoardOne.do?entireBoardSeq="+dto.getEntireBoardSeq();
		}
		
		return "redirect:snsBoardUpdateForm.do";
	}
	
	@RequestMapping("/snsBoardDelete.do")
	public String snsBoardDelete(int entireBoardSeq, RedirectAttributes redirectAttributes) throws Exception {
		logger.info("[Controller] : snsBoardDelete.do");
		
		biz.snsBoardDelete(entireBoardSeq);
		
		redirectAttributes.addFlashAttribute("msg", "delSuccess");
				
		return "redirect:main.do";
	}
	
}

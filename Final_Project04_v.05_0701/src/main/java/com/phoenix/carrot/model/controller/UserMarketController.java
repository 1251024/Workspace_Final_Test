package com.phoenix.carrot.model.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.google.gson.JsonObject;
import com.phoenix.carrot.product.biz.UserProductBiz;
import com.phoenix.carrot.product.dto.ProductDto;


@Controller
public class UserMarketController {
	
	private Logger logger = LoggerFactory.getLogger(UserMarketController.class);
	
	@Autowired
	private UserProductBiz biz;
	
	
	@RequestMapping(value = "/uploadSummernoteImageFile.do", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();
		
		 	//외부경로 저장 희망
		 	//String fileRoot = "C:\\summernote_image\\";
		//내부경로 저장
		String contextRoot = new HttpServletRequestWrapper(request).getRealPath("/");
		String fileRoot = contextRoot+"resources/fileupload/";
		//오리지날 파일명
		String originalFileName = multipartFile.getOriginalFilename();
		//파일 확장자
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		//저장될파일명
		String savedFileName = UUID.randomUUID() + extension; 
		
		File targetFile = new File(fileRoot + savedFileName);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			//파일저장
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
			//contextroot + resources + 저장할 내부 폴더명 
			jsonObject.addProperty("url", "/summernote/resources/fileupload/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
			
		} catch (IOException e) {
			//저장된 파일 삭제
			FileUtils.deleteQuietly(targetFile);
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		String a = jsonObject.toString();
		return a;
	}
	
	@RequestMapping("/jusoPopup.do")
    public String jusoPopup(){

        return "jusoPopup";

    }
	
	@RequestMapping("/usermarketplace.do")
	public String userProductList(Model model) {
		logger.info("[Controller] : usermarketplace.do");
		model.addAttribute("userProductList", biz.userProductList());
		return "userproductmarket";
	}
	
	@RequestMapping("/productinsert.do")
	public String productInsertForm() {
		return "userproductinsert";
	}
	
	@RequestMapping("/userproductinsertres.do")
	public String productInsertRes(ProductDto dto) {
		
		logger.info("[Controller] : userproductinsertres.do");
		
		if (biz.userProductInsert(dto) > 0) {
			return "redirect:usermarketplace.do";
		}
		
		return "redirect:userproductinsert.do";
	}
	
	@RequestMapping("/userproductdetail.do")
	public String productDetail(Model model, int productSeq) {
		logger.info("[Controller] : userproductdetail.do");
		
		model.addAttribute("dto", biz.userProductOne(productSeq));
		return "userproductdetail";
	}
	
	

}

package com.phoenix.carrot.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phoenix.carrot.admin.biz.AdminProductBiz;
import com.phoenix.carrot.product.dto.ProductDto;

@Controller
public class AdminProductController {

	private Logger logger = LoggerFactory.getLogger(AdminProductController.class);

	@Autowired
	private AdminProductBiz adminproductbiz;
	
	@RequestMapping("/adminproduct.do")
	public String adminProduct(Model model) {
		logger.info("[Controller] : adminproduct.do");
		model.addAttribute("adminProductList", adminproductbiz.adminProductList());

		return "adminproduct";
	}
	
	

	@RequestMapping("/adminproductinsert.do")
	public String adminProductInsertForm() {
		return "adminproductinsert";
	}
	
	@RequestMapping("/adminproductinsertres.do")
	public String adminProductInsertRes(ProductDto dto) {
		
		logger.info("[Controller] : adminproductinsertres.do");
		
		if (adminproductbiz.adminProductInsert(dto) > 0) {
			return "redirect:adminproduct.do";
		}
		
		return "redirect:adminproductinsert.do";
	}
	
	@RequestMapping("/adminproductdetail.do")
	public String adminProductDetail(Model model, int productSeq) {
		logger.info("[Controller] : adminproductdetail.do");
		
		model.addAttribute("dto", adminproductbiz.adminProductOne(productSeq));
		return "adminproductdetail";
	}
	
	
}

package com.phoenix.carrot.model.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phoenix.carrot.product.biz.UserProductBiz;
import com.phoenix.carrot.product.dto.ProductDto;

@Controller
public class MarketController {
	
	private Logger logger = LoggerFactory.getLogger(MarketController.class);
	
	@Autowired
	private UserProductBiz biz;
	
	@RequestMapping("/jusoPopup.do")
    public String jusoPopup(){

        return "jusoPopup";

    }
	
	@RequestMapping("/marketplace.do")
	public String userProductList(Model model) {
		logger.info("[Controller] : marketplace.do");
		model.addAttribute("userProductList", biz.userProductList());
		return "productmarket";
	}
	
	@RequestMapping("/productinsert.do")
	public String productInsertForm() {
		return "productinsert";
	}
	
	@RequestMapping("/productinsertres.do")
	public String productInsertRes(ProductDto dto) {
		
		logger.info("[Controller] : productinsertres.do");
		
		if (biz.userProductInsert(dto) > 0) {
			return "redirect:marketplace.do";
		}
		
		return "redirect:productinsert.do";
	}
	
	@RequestMapping("/productdetail.do")
	public String productDetail(Model model, int productSeq) {
		logger.info("[Controller] : productdetail.do");
		
		model.addAttribute("dto", biz.userProductOne(productSeq));
		return "productdetail";
	}

}

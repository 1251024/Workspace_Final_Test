package com.phoenix.carrot.model.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phoenix.carrot.product.biz.UserProductBiz;
import com.phoenix.carrot.product.dto.ProductDto;

@Controller
public class ProductSearch {

	
	private Logger logger = LoggerFactory.getLogger(UserMarketController.class);
	
	@Autowired
	private UserProductBiz biz;
	
	
	@RequestMapping(value="/searchProduct.do", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> searchProduct(HttpSession session,@RequestBody ProductDto dto) {
		
		List<ProductDto> list = new ArrayList<ProductDto>();
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(dto.getSellerAddress() != "" || dto.getSellerAddress() != null) {
			list = biz.selectByaddr(dto.getSellerAddress());
			result.put("list", list);
		}
		
		return result;
	}
	
}

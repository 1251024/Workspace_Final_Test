package com.phoenix.carrot.model.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

	@RequestMapping("/weather.do")
	public String weatherForm() {
		
		
		
		
		return "weather";
	}
	
	@ResponseBody
	public JSONObject getApi(){
		
		
		return null;
	}
	
	@RequestMapping("/payment.do")
	public String payment() {
		return "payment";
	}
	
	@RequestMapping("/loginstart.do")
	public String loginstart() {
		return "loginstart";
	}

}
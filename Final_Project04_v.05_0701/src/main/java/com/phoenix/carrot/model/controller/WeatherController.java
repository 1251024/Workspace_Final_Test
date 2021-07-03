package com.phoenix.carrot.model.controller;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

	public JSONObject getApi(){
		
		return null;
	}
	
	@RequestMapping(value="/weather.do", method=RequestMethod.POST)
	@ResponseBody
	public String weatherForm() {
	
		return "weather";
	}	
	
	@RequestMapping("/payment.do")
	public String payment() {
		return "payment";
	}
	
	/*
	@RequestMapping("/weather.do")
	public String weatherForm() {
		return "weather";
	}
	*/
	
}
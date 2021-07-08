package com.phoenix.carrot.model.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.HTTP;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WeatherController {

	/*
	@GetMapping("/weather.do")
	public JSONObject getApi(){
		
		JSONObject param = new JSONObject();
		//post 방식으로 호출합
		
		
		return null;
	}
*/
	

	@RequestMapping("/weather.do")
	public String weatherForm() {
		return "weather";
	}

	
	/*
	@RequestMapping(value="/weather.do", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> weatherForm(@RequestParam Map<String, Object> params, HttpServletRequest request){
	    List<Map<String, Object>> result = new ArrayList<HashMap<String, Object>>();        
        
	    result = 
	    return result;
	
		return "weather";
	}	
	*/
	
	@RequestMapping("/payment.do")
	public String payment() {
		return "payment";
	}
	
	
	
	
}
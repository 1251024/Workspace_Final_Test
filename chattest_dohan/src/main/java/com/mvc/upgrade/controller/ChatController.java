package com.mvc.upgrade.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mvc.upgrade.chat.biz.ChatRoomBiz;
import com.mvc.upgrade.chat.biz.MessageBiz;
import com.mvc.upgrade.chat.dto.ChatRoomDto;
import com.mvc.upgrade.chat.dto.MessageDto;
import com.mvc.upgrade.model.dto.UserDto;

@Controller
public class ChatController {

	
	@Autowired
	ChatRoomBiz cbiz;
	
	@Autowired
	MessageBiz mbiz;
	
	// 채팅방 입장
	@RequestMapping(value = "/chat.do", method = RequestMethod.GET)
	public String view_chat(HttpServletRequest request, HttpServletResponse response, Model model, @SessionAttribute("login") UserDto dto, @RequestParam("chatroom_seller") String chatroom_seller, @RequestParam("chatroom_title") String chatroom_title) throws Exception {
		String chatroom_buyer = dto.getUserid();

		System.out.println(chatroom_seller);
		System.out.println(chatroom_title);
		
		ChatRoomDto cdto = new ChatRoomDto();
		cdto.setChatroom_buyer(chatroom_buyer);
		cdto.setChatroom_seller(chatroom_seller);
		cdto.setChatroom_title(chatroom_title);
		
		ChatRoomDto result = cbiz.selectOne(cdto);
		MessageDto mdto = new MessageDto();
		
		List<MessageDto> mlist = new ArrayList<MessageDto>();
		
		int res = 0;
		int res2 = 0;
		
		
		if (result != null) {
			
			mlist = mbiz.selectList(result.getChatroom_id());
			model.addAttribute("messagelist", mlist);
			
		} else {
			 res = cbiz.createChatRoom(cdto);
			 result = cbiz.selectOne(cdto);
			 if(res > 0) {
				 mdto.setChatroom_id(result.getChatroom_id());
				 mdto.setMessage_receiver(chatroom_seller);
				 mdto.setMessage_sender(chatroom_buyer);
				 mdto.setMessage_content("대화를 시작해보세요. 욕설과 비속어 사용은 금지입니다.");
				 res2 = mbiz.insertMessage(mdto);
				  
				  if(res2 > 0) {
					  System.out.println("채팅방 개설이 되었습니다.");
					  mlist = mbiz.selectList(result.getChatroom_id());
					  model.addAttribute("messagelist", mlist);
					  
				  } 
			 }
		}
		 
		
		
		
		return "chat_test";
	}
	
	@RequestMapping("/chattest.do")
	public String chattest() {
		return "chat_test";
	}
}
package com.mvc.upgrade.chat.biz;

import java.util.List;

import com.mvc.upgrade.chat.dto.ChatRoomDto;



public interface ChatRoomBiz {

	
	public int createChatRoom(ChatRoomDto dto);
	
	public ChatRoomDto selectOne(ChatRoomDto dto);
	
	
	
}

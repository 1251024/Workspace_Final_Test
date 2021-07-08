package com.mvc.upgrade.chat.dao;

import java.util.List;

import com.mvc.upgrade.chat.dto.ChatRoomDto;

public interface ChatRoomDao {
	
	String NAMESPACE = "ChatRoom.";
	
	public int createChatRoom(ChatRoomDto dto);
	
	public ChatRoomDto selectOne(ChatRoomDto dto);
	
	
}

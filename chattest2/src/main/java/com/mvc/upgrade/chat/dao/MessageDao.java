package com.mvc.upgrade.chat.dao;

import java.util.List;

import com.mvc.upgrade.chat.dto.ChatRoomDto;
import com.mvc.upgrade.chat.dto.MessageDto;

public interface MessageDao {
	
	
	String NAMESPACE = "Messgae.";
	
	public List<MessageDto> selectList(int chatroom_id);
	
	public int insertMessage(MessageDto dto);

}

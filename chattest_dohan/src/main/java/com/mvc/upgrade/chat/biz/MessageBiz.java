package com.mvc.upgrade.chat.biz;

import java.util.List;

import com.mvc.upgrade.chat.dto.MessageDto;


public interface MessageBiz {
	
	public List<MessageDto> selectList(int chatroom_id);
	
	public int insertMessage(MessageDto dto);

}

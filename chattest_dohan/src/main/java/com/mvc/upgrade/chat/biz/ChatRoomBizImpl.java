package com.mvc.upgrade.chat.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.upgrade.chat.dao.ChatRoomDao;
import com.mvc.upgrade.chat.dto.ChatRoomDto;



@Service
public class ChatRoomBizImpl implements ChatRoomBiz {

	@Autowired
	private ChatRoomDao dao;
	
	
	@Override
	public int createChatRoom(ChatRoomDto dto) {
		// TODO Auto-generated method stub
		return dao.createChatRoom(dto);
	}


	@Override
	public ChatRoomDto selectOne(ChatRoomDto dto) {
		// TODO Auto-generated method stub
		return dao.selectOne(dto);
	}




}

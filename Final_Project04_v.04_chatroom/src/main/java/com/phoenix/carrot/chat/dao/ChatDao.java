package com.phoenix.carrot.chat.dao;

import java.util.List;

import com.phoenix.carrot.chat.dto.ChatMessageDto;
import com.phoenix.carrot.chat.dto.ChatRoomDto;

public interface ChatDao {

	String NAMESPACE = "chat.";
	
	
	public void createRoom(ChatRoomDto ctdto)throws Exception;
	public ChatRoomDto isRoom(ChatRoomDto ctdto)throws Exception;
	public void insertMessage(ChatMessageDto msdto)throws Exception;
	public String getPartner(ChatRoomDto ctdto)throws Exception;
	public String getProfile(String str)throws Exception;
	public String getName(String str)throws Exception;
	public List<ChatMessageDto> getMessageList(String str)throws Exception;
	public List<ChatMessageDto> getRoomList(String str)throws Exception;
	public List<ChatMessageDto> getRoomList2(String str)throws Exception;
	public ChatMessageDto getRecentMessage(String str)throws Exception;
	//public String isGetMessageList(String str)throws Exception;
	
	public String getTutorId(String str)throws Exception;
	public List<ChatRoomDto> getRoomListTutor(String str)throws Exception;
	public void updateReadTime(int class_id , String user_id , String TUTOR_USER_user_id)throws Exception;
	public void updateReadTimeTutor(int class_id , String user_id , String TUTOR_USER_user_id)throws Exception;
	
	public int getUnReadCount(String TUTOR_USER_user_id, int class_id, String user_id)throws Exception;
	public int getUnReadCountTutor(String TUTOR_USER_user_id, int class_id, String user_id)throws Exception;
	
	public int getAllCount(String str);
}

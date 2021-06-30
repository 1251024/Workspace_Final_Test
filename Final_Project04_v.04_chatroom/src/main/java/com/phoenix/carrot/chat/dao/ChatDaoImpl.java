package com.phoenix.carrot.chat.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.phoenix.carrot.chat.dto.ChatMessageDto;
import com.phoenix.carrot.chat.dto.ChatRoomDto;

@Repository
public class ChatDaoImpl implements ChatDao {

	@Autowired
	private SqlSession session;

	private static String namespace = "chat.";

	@Override
	public void createRoom(ChatRoomDto ctdto) throws Exception {
		System.out.println("chat_room");
		session.insert(namespace + ".createRoom", ctdto);
		System.out.println("CHAT_ROOM");

	}

	@Override
	public ChatRoomDto isRoom(ChatRoomDto ctdto) throws Exception {

		ChatRoomDto roomdto = null;
		roomdto = session.selectOne(namespace + ".isRoom", ctdto);
		System.out.println("ss");
		System.out.println(roomdto);

		return roomdto;
	}

	@Override
	public void insertMessage(ChatMessageDto msdto) throws Exception {

		session.insert(namespace + ".insertMessage", msdto);
	}

	@Override
	public String getPartner(ChatRoomDto ctdto) throws Exception {

		List<ChatMessageDto> mvo = session.selectList(namespace + ".getPartner", ctdto);

		return mvo.get(0).getUSER_user_id();
	}

	@Override
	public String getProfile(String str) throws Exception {
		return session.selectOne(namespace + ".getProfile", str);
	}

	@Override
	public String getName(String str) throws Exception {
		return session.selectOne(namespace + ".getName", str);
	}

	@Override
	public List<ChatMessageDto> getMessageList(String str) throws Exception {
		return session.selectList(namespace + ".getMessageList", str);
	}

	@Override
	public List<ChatMessageDto> getRoomList(String str) throws Exception {
		return session.selectList(namespace + ".getRoomList", str);
	}

	@Override
	public List<ChatMessageDto> getRoomList2(String str) throws Exception {
		return session.selectList(namespace + ".getRoomList2", str);
	}

	@Override
	public ChatMessageDto getRecentMessage(String str) throws Exception {
		return session.selectOne(namespace + ".getRecentMessage", str);
	}

	@Override
	public String getTutorId(String str) throws Exception {
		return session.selectOne(namespace + ".getTutorId", str);
	}

	@Override
	public List<ChatRoomDto> getRoomListTutor(String str) throws Exception {
		return session.selectList(namespace + ".getRoomListTutor", str);

	}

	@Override
	public void updateReadTime(int class_id, String user_id, String TUTOR_USER_user_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("TUTOR_USER_user_id", TUTOR_USER_user_id);
		map.put("USER_user_id", user_id);
		map.put("CLASS_class_id", class_id);
		session.update(namespace + ".updateReadTime", map);
	}

	@Override
	public void updateReadTimeTutor(int class_id, String user_id, String TUTOR_USER_user_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("TUTOR_USER_user_id", TUTOR_USER_user_id);
		map.put("USER_user_id", user_id);
		map.put("CLASS_class_id", class_id);
		session.update(namespace + ".updateReadTime", map);
	}

	@Override
	public int getUnReadCount(String TUTOR_USER_user_id, int class_id, String user_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("TUTOR_USER_user_id", TUTOR_USER_user_id);
		map.put("USER_user_id", user_id);
		map.put("CLASS_class_id", class_id);

		return session.selectOne(namespace + ".getUnReadCount", map);
	}

	@Override
	public int getUnReadCountTutor(String TUTOR_USER_user_id, int class_id, String user_id) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("TUTOR_USER_user_id", TUTOR_USER_user_id);
		map.put("USER_user_id", user_id);
		map.put("CLASS_class_id", class_id);

		return session.selectOne(namespace + ".getUnReadCount", map);
	}

	@Override
	public int getAllCount(String str) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("USER_user_id", str);
		map.put("TUTOR_USER_user_id", str);
		if (session.selectOne(namespace + ".getAllCount", map) == null) {
			return 0;
		} else {

			return session.selectOne(namespace + ".getAllCount", map);
		}
	}

}

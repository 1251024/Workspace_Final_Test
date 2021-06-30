package com.phoenix.carrot.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.phoenix.carrot.chat.dao.ChatDao;
import com.phoenix.carrot.chat.dto.ChatMessageDto;
import com.phoenix.carrot.chat.dto.ChatRoomDto;
import com.phoenix.carrot.chat.dto.ChatUserDto;

@RequestMapping("/chat")
public class ChatWebSocketHandler extends TextWebSocketHandler {

	@Inject
	private ChatDao dao;

	private List<WebSocketSession> connectedUsers;

	public ChatWebSocketHandler() {
	      connectedUsers = new ArrayList<WebSocketSession>();
	   }

	private Map<String, WebSocketSession> users = new ConcurrentHashMap<String, WebSocketSession>();

	@Override

	public void afterConnectionEstablished(

			WebSocketSession session) throws Exception {

		log(session.getId() + " 연결 됨!!");

		users.put(session.getId(), session);
		connectedUsers.add(session);
	}

	@Override

	public void afterConnectionClosed(

			WebSocketSession session, CloseStatus status) throws Exception {

		log(session.getId() + " 연결 종료됨");
		connectedUsers.remove(session);
		users.remove(session.getId());

	}


	@Override
	   protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {


		System.out.println(message.getPayload());

		  Map<String, Object> map = null;

	      ChatMessageDto messageDto = ChatMessageDto.convertMessage(message.getPayload());

	      System.out.println("1 : " + messageDto.toString());


	      ChatRoomDto roomDto  = new ChatRoomDto();
	      roomDto.setCLASS_class_id(messageDto.getCLASS_class_id()); //클래스
	      roomDto.setTUTOR_USER_user_id(messageDto.getTUTOR_USER_user_id()); //튜터
	      roomDto.setUSER_user_id(messageDto.getUSER_user_id()); //유저

	      ChatRoomDto croom =null;
	      if(!messageDto.getUSER_user_id().equals(messageDto.getTUTOR_USER_user_id())) {
	    	  System.out.println("a");



	    	  if(dao.isRoom(roomDto) == null ) {
	    		  System.out.println("b");
	    		  dao.createRoom(roomDto);
	    		  System.out.println("d");
	    		  croom = dao.isRoom(roomDto);

	    	  }else {
	    		  System.out.println("C");
	    		  croom = dao.isRoom(roomDto);
	    	  }
	      }else {

    		  croom = dao.isRoom(roomDto);
    	  }

	      messageDto.setCHATROOM_chatroom_id(croom.getChatroom_id());
	      if(croom.getUSER_user_id().equals(messageDto.getMessage_sender())) {

	    	  messageDto.setMessage_receiver(roomDto.getTUTOR_USER_user_id());
	      }else {
	    	  messageDto.setMessage_receiver(roomDto.getUSER_user_id());
	      }




	      for (WebSocketSession websocketSession : connectedUsers) {
	         map = websocketSession.getAttributes();
	         ChatUserDto login = (ChatUserDto) map.get("login");

	         //받는사람
	         if (login.getUser_id().equals(messageDto.getMessage_sender())) {

	            Gson gson = new Gson();
	            String msgJson = gson.toJson(messageDto);
	            websocketSession.sendMessage(new TextMessage(msgJson));
	         }


	      }
	   }

	@Override

	public void handleTransportError(

			WebSocketSession session, Throwable exception) throws Exception {

		log(session.getId() + " 익셉션 발생: " + exception.getMessage());

	}

	private void log(String logmsg) {

		System.out.println(new Date() + " : " + logmsg);

	}

}
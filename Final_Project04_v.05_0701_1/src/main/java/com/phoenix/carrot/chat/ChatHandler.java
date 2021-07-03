package com.phoenix.carrot.chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {
	//연결 요청 처리
		//메시지 받기, 메시지 전달
	
	
	//WebSocketSession 클라이언트 당 하나씩 생성, 해당 클라이언트와 연결된 웹소켓을 이용할 수 있는 객체
	//해당 객체를 통해 메세지를 주고 받음
	
	private List<WebSocketSession> users;
	private Map<String, Object> userMap;
	
	public ChatHandler() {
		users= new ArrayList<WebSocketSession>();
		userMap = new HashMap<String, Object>();
		
	}
	
	
	@Override
	//메시지 수신 후 실행 메서드
	protected void handleTextMessage(WebSocketSession session, TextMessage message)throws Exception {
		System.out.println("TextWebSocketHandler: 메시지 수신!");
		System.out.println("메시지 : "+message.getPayload());
		JSONObject object = new JSONObject(message.getPayload());
		String type = object.getString("type");
		
		if(type != null && type.equals("register")) {
			//등록 요청 메시지
			String user = object.getString("userid");
			//id랑 session이랑 매핑 >>> map
			userMap.put(user, session);
			
		}
		else {
			//채팅메세지 : 상대방 아이디를 포함해서 메세지를 보낼 것이기 때문에
			//Map에서 상대방 아이디에 해당하는 websocket 꺼내와서 메시지 전송
			String target = object.getString("target");
			WebSocketSession ws = (WebSocketSession)userMap.get(target);
			String msg = object.getString("message");
			if (ws != null) {
				ws.sendMessage(new TextMessage(msg));
			}
		}
	}
	
	@Override
	//연결 해제 후 실행 메서드
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status)throws Exception{
		System.out.println("TextWebSocketHandler: 연결종료!");
		users.remove(session);
	}
}

package com.sp.ex;

//import java.util.ArrayList;
import java.util.HashMap;
//import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

//@RequestMapping("/echo")
@Controller
@Component
public class EchoHandler extends TextWebSocketHandler {

	// 세션 리스트
//	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	HashMap<String,	WebSocketSession> sessionMap = new HashMap<>();//웹소켓 세션을 담아둘 맵
	
	
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);


	
	// 클라이언트가 연결 되었을 때 실행
	@SuppressWarnings("unchecked")//@SuppressWarnings: 컴파일러가 경고하는 내용중 제외시킬때 사용
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//소켓 연결
		super.afterConnectionEstablished(session);
		sessionMap.put(session.getId(), session);
		JSONObject obj = new JSONObject();
		obj.put("type", "getId");
		obj.put("sessionOd", session.getId());
		session.sendMessage(new TextMessage(obj.toJSONString()));
		
		
//		sessionList.add(session);
		logger.info("{} 연결됨", session.getId());

	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		
		String msg = message.getPayload();
		JSONObject obj = jsonToObjectParser(msg);
		for(String key : sessionMap.keySet()) {
			WebSocketSession wss = sessionMap.get(key);
			try {
				wss.sendMessage(new TextMessage(obj.toJSONString()));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		// 모든 유저에게 메세지 출력
//		for (WebSocketSession sess : sessionList) {
//			sess.sendMessage(new TextMessage(message.getPayload()));
//		}
		
		
		
		
		
	}

	// 클라이언트 연결을 끊었을 때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//소켓 종료
		sessionMap.remove(session.getId());
		super.afterConnectionClosed(session, status);
		
		
//		sessionList.remove(session);
		logger.info("{} 연결 끊김.", session.getId());
	}
	
	private static JSONObject jsonToObjectParser(String jsonStr) {
		JSONParser parser = new JSONParser();
		JSONObject obj = null;
		try {
			obj = (JSONObject) parser.parse(jsonStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return obj;
	}

}

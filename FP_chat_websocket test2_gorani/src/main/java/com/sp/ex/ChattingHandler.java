package com.sp.ex;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import jdk.internal.org.jline.utils.Log;
//@Log4j
@RequestMapping("/echo")

public class ChattingHandler extends TextWebSocketHandler {

	// 세션 리스트
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	private static Logger logger = LoggerFactory.getLogger(ChattingHandler.class);

	
	/*
	 * afterConnectionEstablished() : 
	 * 채팅을 위해 해당 페이지에 들어오면 (/chat)클라이언트가 연결된 후
	 * 해당 클라이언트의 세션을 sessionList에 add한다.
	 * 
	 * handleTextMessage() :
	 * 웹소켓 서버로 메세지를 전송했을 때 이 메서드가 호출됨
	 * 현재 웹소켓 서버에 접속한 session모두에게 메세지를 전달해야 하므로
	 * loop를 돌며 메세지를 전송한다.
	 * 
	 * 
	 * afterConnectionClosed() : 
	 * 클라이언트와 연결이 끊어진경우(채팅방을 나간 경우)
	 * remove로 해당 세션을 제거한다.
	 * 
	 * 
	 */
	
	
	
	
	
	// 클라이언트가 연결 되었을 때 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		logger.info("#ChattingHandler, afterConnectionEstablished");
		sessionList.add(session);
//		logger.info("{} 연결됨", session.getId());
		logger.info(session.getPrincipal().getName()+"님이 입장하셨습니다.");
		
		
	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//		logger.info("{}로 부터 {} 받음", session.getId(), message.getPayload());
		
		logger.info("#ChattingHandler, handleMessage");
		logger.info(session.getId()+": "+message);
		
		// 모든 유저에게 메세지 출력
		for (WebSocketSession sess : sessionList) {
			sess.sendMessage(new TextMessage(session.getPrincipal().getName()+":"+message.getPayload()));
		}
	}

	// 클라이언트 연결을 끊었을 때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessionList.remove(session);
//		logger.info("{} 연결 끊김.", session.getId());
		logger.info(session.getPrincipal().getName()+"님이 퇴장하셨습니다.");
		
	}

}

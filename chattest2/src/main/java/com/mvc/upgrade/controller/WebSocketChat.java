package com.mvc.upgrade.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.NotificationBroadcaster;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


//자바웹소켓

@Controller
@ServerEndpoint(value = "/echo.do")
public class WebSocketChat {

	private static final List<Session> sessionList = new ArrayList<Session>();
	private static final Logger logger = LoggerFactory.getLogger(WebSocketChat.class);

	public WebSocketChat() {
		System.out.println("웹소켓(서버) 객체생성");
	}

	//웹소켓 연결시 호출
	@OnOpen
	public void onOpen(Session session) {
		logger.info("Open session id : " + session.getId());
		System.out.println(session.getId()+" 웹 소켓 연결 ");

		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("대화방에 연결되었습니다.");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		sessionList.add(session);
	}

	/*
	 * 본인메시지를 모든 웹소켓에 전달(나 자신도 내 메시지를 받을 수 있게)
	 * @param self
	 * @param sender
	 * @param message
	 * 
	 */

	
	private void sendAllSessionToMessage(Session self, String receiver, String message) {
		try {
			for(Session session : WebSocketChat.sessionList) {
				if(!self.getId().equals(session.getId())) {
					
					//다른 사람으로부터 나에게 오는 메시지
					final Basic basic = session.getBasicRemote();
					basic.sendText(receiver + " : " + message);
					
					/*
					 * session.getBasicRemote().sendText(receiver);
					 * session.getBasicRemote().sendText(message);
					 * 
					 * System.out.println("id:" + receiver); System.out.println("message:" + message);
					 */
					
					/*
					 * 만약 id가 로그인 세션과 같고
					 * 
					 * product 테이블을 가져와서
					 * 만약 id가 product테이블의 userid와 같다면
					 * 
					 * 	  product 테이블의 userid / productname / sysdate를 가져와서
					 * 	  채팅룸을 생성한다.
					 *  	-userid가 product userid와 같다면
					 *  	 	-메시지 저장할건데
					 *  	 	messageid
					 *  	 	messagesender
					 *  	 	messagecontent
					 *   	 	messagedate
					 *   	 	저장
					 *  		-아니면
					 *  	 	messageid
					 *  	 	messagereceiver
					 *  	 	messagecontent
					 *   	 	messagedate
					 *   	 	저장
					 * 
					 * 만약 id가 != product테이블의 userid 라면
					 *    채팅룸에 진입한다.
					 * 	  	-메시지 저장할건데
					 *  	 messageid
					 *  	 messagereceiver
					 *  	 messagecontent
					 *   	 messagedate
					 *   	 저장
					 *  	-아니면
					 *  	 messageid
					 *  	 messagesender
					 *  	 messagecontent
					 *   	 messagedate
					 *   	 저장
					 * 
					 */
					
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	
	/*
	 * 내가 입력하는 메세지
	 * @param message
	 * @param session
	 */
	//웹소켓 메시지 수신시 호출
	@OnMessage
	public void onMessage(String message, Session session) {
		
		String sender = message.split(",")[1];
		message = message.split(",")[0];
		
		
		logger.info("Message Form " + sender + " : " + message);
		
		
		
		try {
			final Basic basic = session.getBasicRemote(); //리스트에 있는 유저면 아래 형식으로 basic 메시지를 보낸다.
			basic.sendText(sender+" : " + message);  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		sendAllSessionToMessage(session, sender, message);//리스트에 있는 모든 세션에 메시지를 보낸다.
		System.out.println("websocketchat_session:"+session);
		System.out.println("websocketchat_sender:"+sender);
		System.out.println("websocketchat_message:"+message);
	}
	
	@OnError
	public void onError(Throwable e, Session session) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		logger.info("Session " + session.getId() + "has ended");
		//세션에서리스트에서 세션 지워주기(안지워주면 다시 세션에 접속하지 못한다.)
		sessionList.remove(session);
		
	}
	
	
}

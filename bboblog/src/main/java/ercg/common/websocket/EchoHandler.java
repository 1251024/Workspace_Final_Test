package ercg.common.websocket;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.sun.tools.javac.util.StringUtils;

import ercg.sys.log.cmm.UserDetailsHelper;
import ercg.sys.log.vo.SysLog0101VO;

public class EchoHandler extends TextWebSocketHandler {
	//연결된 모든 sessions 저장
		List<WebSocketSession> sessions = new ArrayList<>();
		//userId의 webSession을 저장한다
		Map<String, WebSocketSession> userSessions = new HashMap<>();
		
		//클라이언트 접속 성공 시 연결 성공시
		@Override
		public void afterConnectionEstablished(WebSocketSession session) throws Exception{
			System.out.println("afterConnectionEstablished:" + session);
			
			//userId 알아내기
			Map<String, Object> sessionVal =  session.getAttributes();
			sysLog0101VO sysLog0101VO = (SysLog0101VO) sessionVal.get("sysLog0101VO"); 
			System.out.println(sysLog0101VO.getUserId());
			String userId = sysLog0101VO.getUserId();
			
			if(userSessions.get(userId) != null) {
				//userId에 원래 웹세션값이 저장되어 있다면 update
				userSessions.replace(userId, session);
			} else {
				//userId에 웹세션값이 없다면 put
				userSessions.put(userId, session);
			}
		}
		
		//소켓에 메시지를 보냈을때 js에서 on.message
		@Override
		protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
			System.out.println("handleTextmessage: " + session + " : " + message);
			
			//protocol: 내용, 보내는id, 받는id (content, requestId, responseId)
			String msg = message.getPayload();
			if(StringUtils.isNotEmpty(msg)) {
				String[] strs = msg.split(",");
				if (strs != null && strs.length == 3) {
					String sendId = strs[0];
					String receiveUserId = strs[1];
					String content = strs[2];
					
					//broadcasting
					if(receiveUserId.equals("")) {
						for (WebSocketSession sess: sessions) {
							//message를 TextMessage형태로 받음 (22번째줄, string x)
							sess.sendMessage(new TextMessage(receiveUserId + ":" + message.getPayload()));
						}
					} else {
						WebSocketSession responseIdSession = userSessions.get(receiveUserId);
						if (responseIdSession != null) {
							TextMessage tmpMsg = new TextMessage(sendId + "," + receiveUserId + "," + content);
							responseIdSession.sendMessage(tmpMsg);
						}
					}
					
				}
			}
			
		}

		
		//소켓이 close 됐을 때
		@Override
		public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
			sessions.remove(session);
			System.out.println("afterHandleTextmessage: " + session + " : " + status);
		}
}

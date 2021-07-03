<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>

<script type="text/javascript">
/* 1. websocket연결 요청 + 객체 생성 
 * 2. websocket 객체 생성 후 
 onopen : 연결이 생성되고 난 후 이벤트 처리
 onmessage: 생성된 소켓으로부터 메시지가 들어오는 이벤트 처리
 onclose: 연결이 끊겼을 경우 이벤트 처리하는 핸들러 각각 등록
 
 */
 var ws;
 var userid= '${param.id}';//파라미터로 넘겨서 설정할 아이디(my id)
 
 function connect(){
	 //웹소켓 객체 생성하는 부분
	 //핸들러 등록 (연결 생성, 메시지 수신, 연결 종료)
	 
	 //url 연결할 서버의 경로
	 ws = new WebSocket('ws://localhost:8787/chat');
 	
	 ws.onopen = function(){
		 console.log('연결 생성');
		 register();
	};
	ws.onmessage = function(){
		console.log('메시지 수신');
		var data = e.data;
		alert("받은 메시지:"+data);
		addMsg(data);
		
	};
	ws.onclose = function(){
		console.log('연결 끊김');
	};
	
 }
 
 function addMsg(msg){
	 //기존 수신된 채팅 메세지 + 방금 수신된 메세지 더해서 설정하기
	 var chat = $('#msgArea').val();
	 chat = chat + "\n상대방 : " + msg;
	 $('#msgArea').val(chat);
	 
 }
 function register(){
	 //메시지 수신을 위한 서버에 id 등록하기
	 var msg = {
			 //웹소켓과 아이디를 매핑시키기 위한 메세지
			 type : "register", //메세지 구분하는 구분자 - 상대 아이디와 메세지 포함해서 보냄
			 userid : '${param.id}'
	 };
	 ws.send(JSON.stringify(msg));
 }
 
 function sendMsg(){
	 //var msg = $("#chatMsg").val();
	 //ws.send(userid + " : " + msg);
	 var msg = {
			 //채팅을 위한 메세지
			 type : "chat", //메세지 구분하는 구분자 - 상대 아이디와 메세지 포함해서 보냄
			 target : $("#targetUser").val(),
			 message : $("#chatMsg").val()
	 };
	 ws.send(JSON.stringify(msg));
 };
 
 //페이지가 로딩되면 connect 실행
$(function({
	connect();
	 $('#btnSend').on("click", function(){
		 var chat = $("#msgArea").val();
		 chat = chat + "\n나 : " + $("#chatMsg").val();
		 $("#msgArea").val(chat);
		 sendMsg();
		 $("#chatMsg").val("");
	 })

}));
 
</script>
</head>
<body>
 	<!-- 채팅 내용 출력 화면 -->
 	<textarea rows="5" cols="30" id="msgArea">
 	</textarea>
 	<br>
 	<!-- 메시지 보내는 부분 -->
 	메시지: <input type="text" id="chatMsg"/>
 	<br>
 	<!-- 채팅 아이디 작성 부분 -->
 	상대 아이디: <input type="text" id="targetUser">
 	<br>
 	<input type="button" value="전송"	 id="btnSend">
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<!-- <script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.1.5/sockjs.min.js"></script>
 -->
 <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
	
</head>
<body>
	<!-- <input type="text" id="message" />
	<input type="button" id="sendBtn" value="submit"/>
	<div id="messageArea"></div> -->
	
	<div class="container">
		<div class="col-6">
			<label><b>채팅방</b></label>
		</div>
		<div>
			<div id="msgArea" class="col">
			
			
			</div>
			<div class="col-6">
				<div class="input-group mb-3">
					<input type = "text" id="msg" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2">
					<div class="input-group-append">
						<button class="btn btn-outline-secondart" type="button" id="button-send">전송</button>
					</div>
					
				</div>
			</div>
		</div>
		<div class="col-6"></div>
	
	</div>
	
</body>

<script type="text/javascript">

//전송 버튼 누르는 이벤트
$("#button-send").on("click", function(e){
	sendMessage();
	$('#msg').val('')
});

var sock = new SockJs("http://localhost:8787/chatting");
sock.onmessage = onMessage;
sock.onclose = onClose;
sock.onopen = onOpen;

function sendMessage(){
	sock.send($("msg").val());
}

//서버에서 메세지를 받았을때
function onMessage(msg){
	var data = msg.data;
	var sessionId = null;//데이터를 보낸사람
	var message = null;
	
	var arr = data.split(":");
	
	for(var i = 0; i<arr.length; i++ ){
		console.log('arr['+i+']'+arr[i]);
	}
	var cur_session= '${userid}';//현재 세션에 로그인 한 사람
	console.log("cur_session:"+cur_session);
	
	sessionId=arr[0];
	message = arr[1];
	
	//로그인 한 클라이언트와 타 클라리언트를 분류하기 위함
	if(sessionId==cur_session){
		
		var str = "<div class='col-6'>";
		str += "<div class='alert alsert-secondart'>";
		str += "<b>"+sessionId+":"+message+"</b>";
		str += "<div></div>";
		
		$("#msgArea").append(str);
		
		
	}
	else{
		
		var str = "<div class='col-6'>";
		str += "<div class='alert alsert-warning'>";
		str += "<b>"+sessionId+":"+message+"</b>";
		str += "<div></div>";
		
		$("#msgArea").append(str);
		
	}
	//채팅창에서 나갔을때
	function onClose(evt){
		var user = '${pr.username}';
		var str = user + "님이 퇴장하셨습니다.";
		
		$("#msgArea").append(str);
		
	}
	//채팅창에서 들어왔을때
	function onOpen(evt){
		var user = '${pr.username}';
		var str = user + "님이 입장하셨습니다.";
		
		$("#msgArea").append(str);
		
	}
}


</script>

<!-- <script type="text/javascript">
	$("#sendBtn").click(function() {
		sendMessage();
		$('#message').val('')
	});

	let sock = new SockJS("http://localhost:8787/ex/echo/");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	// 메시지 전송
	function sendMessage() {
		sock.send($("#message").val());
	}
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		var data = msg.data;
		$("#messageArea").append(data + "<br/>");
	}
	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		$("#messageArea").append("연결 끊김");

	}
</script> -->
</html>
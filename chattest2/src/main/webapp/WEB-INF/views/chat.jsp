<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방</title>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>


<body>

	<div>
		<button type="button" onclick="openSocket();">채팅하기(대화방참여)</button>
		<button type="button" onclick="closeSocket();">대화방 나가기</button>
		
		<br><br><br>
			메세지 입력 :
		<input type="text" id="sender" value="${login.userid }" style="display:none;">
		<input type="text" id="messageinput">
		<button type="button" onclick="send();">메세지 전송</button>
		<button type="button" onclick="javascript:clearText();">대화내용 지우기</button>
	</div>
	
	<div id="messages">
	</div>
	
	<script type="text/javascript">
	$(document).ready(function(){
		openSocket();
	});
	
	
	
		var ws;
		var messages = document.getElementById("messages");
		
		function openSocket() {
			if(ws !== undefined && ws.readyState !== WebSocket.CLOSED) {
				writeResponse("WebSocket is already opened");
				return;
			}
			
			// 웹소켓 객체 만드는 부분
			ws = new WebSocket("ws://localhost:8787/upgrade/echo.do");
			
			
			//웹소켓 연결 성공시
			ws.open = function(event) {
				if(event.data === undefined) {
					return;
				}
				writeResponse(event.data);
				consloe.log(ws)
			};
			
			//메시지 수신시
			ws.onmessage = function(event) {
				console.log("writeResponse");
				console.log(event.data);
				writeResponse(event.data);
			};
			
			//웹소켓 연결 끊겼을 시
			ws.onclose = function(event) {
				writeResponse("대화 종료");
			}
			
		}
		
		
		
		function send() {
			//var text = $("#massageinput").val+","+$("#sender").val
			var text = document.getElementById("messageinput").value + "," + document.getElementById("sender").value;
			ws.send(text);
			text = "";
		}
		
		function closeSocket() {
			ws.close();
		}
		
		function writeResponse(text) {
			messages.innerHTML += "<br/>" + text;
		}
		
		function clearText() {
			console.log(messages.parentNode);
			messages.parentNode.removeChild(messages);
		}
	</script>


</body>
</html>
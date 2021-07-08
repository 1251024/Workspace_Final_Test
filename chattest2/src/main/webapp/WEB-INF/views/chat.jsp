<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방</title>


<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v5.15.3/js/all.js"
	crossorigin="anonymous"></script>
<!-- Google fonts-->
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Tinos:ital,wght@0,400;0,700;1,400;1,700&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=DM+Sans:ital,wght@0,400;0,500;0,700;1,400;1,500;1,700&amp;display=swap"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />


<style>
.container {
	max-width: 1170px;
	margin: auto;
	height: 1000px;
}

.inbox_people {
	background: #f8f8f8 none repeat scroll 0 0;
	float: left;
	overflow: hidden;
	width: 40%;
	border-right: 1px solid #c4c4c4;
}

.inbox_msg {
	border: 1px solid #c4c4c4;
	clear: both;
	overflow: hidden;
}

.received_msg {
	display: inline-block;
	padding: 0 0 0 10px;
	vertical-align: top;
	width: 90%;
}

.received_withd_msg p {
	background: #fad7a9 none repeat scroll 0 0;
	border-radius: 3px;
	color: #646464;
	font-size: 14px;
	margin: 0;
	padding: 5px 10px 5px 12px;
	width: 100%;
}

.time_date {
	color: #747474;
	display: block;
	font-size: 12px;
	margin: 8px 0 0;
}

.received_withd_msg {
	width: 57%;
}

.mesgs {
	float: left;
	padding: 30px 15px 0 25px;
	width: 97%;
	height: 95%;
}

.sent_msg p {
	background: #f7a65b none repeat scroll 0 0;
	border-radius: 3px;
	font-size: 14px;
	margin: 0;
	color: #fff;
	padding: 5px 10px 5px 12px;
	width: 100%;
}

.outgoing_msg {
	overflow: hidden;
	margin: 26px 0 26px;
}

.sent_msg {
	float: right;
	width: 46%;
}

.input_msg_write input {
	background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	border: medium none;
	color: #4c4c4c;
	font-size: 15px;
	min-height: 48px;
	width: 100%;
}

.type_msg {
	border-top: 1px solid #c4c4c4;
	position: relative;
}

.msg_send_btn {
	background: #ff9100 none repeat scroll 0 0;
	border: medium none;
	border-radius: 50%;
	color: #fff;
	cursor: pointer;
	font-size: 17px;
	height: 33px;
	position: absolute;
	right: 0;
	top: 11px;
	width: 33px;
}

.messaging {
	padding: 0 0 50px 0;
	height: 95%;
}

.msg_history {
	height: 516px;
	width: 100%;
	overflow-y: auto;
}

#title {
	margin-bottom: 0px;
}

#title-id {
	margin-top: 0px;
	margin-bottom: 5px;
}

#receiver_id {
	padding: 0px 0px 0px 20px;
	margin-bottom: 5px;
}
</style>
</head>






<body>




	<div class="container">
		<input type="hidden" value='${login.userid}' id="loginuserid">
	
		<h3 class=" text-center" id="title">게시글 제목</h3>
		<p id="title-id">
			<small id="sessionid">채팅상대 id: 
				<b id="sessionid"> </b>
			</small>
		</p>
		<br>
		<button type="button" onclick="openSocket();">채팅하기(대화방참여)</button>
		<button type="button" onclick="closeSocket();">대화방 나가기</button>
		<div class="messaging">
			<div class="inbox_msg">
				<div class="mesgs">
					<div id="messages" class="msg_history" >
						<%-- <div class="incoming_msg">
							<p id="receive_id">
								<small>${login.userid }</small>
							</p>
							<div class="received_msg">
								<div class="received_withd_msg">
									<p>당근 구매 가능 ?</p>
									<span class="time_date"> 11:01 AM | June 9</span>
								</div>
							</div>
						</div>
						<div class="outgoing_msg">
						
							<div class="sent_msg">
								<p>네 가능합니다.</p>
								<span class="time_date"> 11:01 AM | June 9</span>
							</div>
						</div>
						<div class="incoming_msg">
							<p id="receiver_id">
								<small>상대방id</small>
							</p>
							<div class="received_msg">
								<div class="received_withd_msg">
									<p>구매 하겠습니다 ~</p>
									<span class="time_date"> 11:01 AM | Yesterday</span>
								</div>
							</div>
						</div>
						<div class="outgoing_msg">
							<div class="sent_msg">
								<p>네~</p>
								<span class="time_date"> 11:01 AM | Today</span>
							</div>
						</div>
						<div class="incoming_msg">
							<p id="receiver_id">
								<small>상대방id</small>
							</p>
							<div class="received_msg">
								<div class="received_withd_msg">
									<p>.,,.,.,</p>
									<span class="time_date"> 11:01 AM | Today</span>
								</div>
							</div>
						</div> --%>
					</div>
					<div class="type_msg">
						<div class="input_msg_write">
							
							<input type="text" id="sender" value="${login.userid }" style="display: none;"> 
							<input type="text" id="messageinput"  class="write_msg" placeholder="Type a message" >
							
							<button type="button" class="msg_send_btn" onclick="send();">							
								<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
									fill="currentColor" class="bi bi-chat-dots" viewBox="0 0 16 16">
                            <path d="M5 8a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm4 0a1 1 0 1 1-2 0 1 1 0 0 1 2 0zm3 1a1 1 0 1 0 0-2 1 1 0 0 0 0 2z" />
                            <path d="m2.165 15.803.02-.004c1.83-.363 2.948-.842 3.468-1.105A9.06 9.06 0 0 0 8 15c4.418 0 8-3.134 8-7s-3.582-7-8-7-8 3.134-8 7c0 1.76.743 3.37 1.97 4.6a10.437 10.437 0 0 1-.524 2.318l-.003.011a10.722 10.722 0 0 1-.244.637c-.079.186.074.394.273.362a21.673 21.673 0 0 0 .693-.125zm.8-3.108a1 1 0 0 0-.287-.801C1.618 10.83 1 9.468 1 8c0-3.192 3.004-6 7-6s7 2.808 7 6c0 3.193-3.004 6-7 6a8.06 8.06 0 0 1-2.088-.272 1 1 0 0 0-.711.074c-.387.196-1.24.57-2.634.893a10.97 10.97 0 0 0 .398-2z" />
                          </svg>
							</button>
							

						</div>
					</div>
				</div>
			</div>




		</div>
	</div>
	<!--https://bootsnipp.com/snippets/1ea0N : bootstrap template원본 -->


<%-- 	<div>
		<button type="button" onclick="openSocket();">채팅하기(대화방참여)</button>
		<button type="button" onclick="closeSocket();">대화방 나가기</button>

		<br>
		<br> 
		<br> 메세지 입력 : 
		<input type="text" id="sender" value="${login.userid }" style="display: none;"> 
		<input type="text" id="messageinput">
		<button type="button" onclick="send();">메세지 전송</button>
	</div>

	<div id="messages"></div> --%>


	<script type="text/javascript">
		$(document).ready(function() {
			openSocket();
		});

		var ws;
		var loginuserid = '${login.userid }';
		var messages = document.getElementById("messages");
		
			
			
		// 웹소켓 객체 만드는 부분
		//핸들러 등록(연결 생성, 메시지 수신, 연결 종료)
		function openSocket() {
			if (ws !== undefined && ws.readyState !== WebSocket.CLOSED) { 
				writeResponse("WebSocket is already opened");
				return;
			}

			
			//url 연결할 서버의 경로
			ws = new WebSocket("ws://localhost:8787/upgrade/echo.do");
			//경로는 톰캣 모듈의 path와 맞춰줘야 한다.

			//메시지 수신시 
			//event는 웹소켓이 보내준 데이터다.
			ws.onmessage = function(event) {
				console.log("메세지 받음");
				console.log(event.data);
				
				var data = event.data;
				var sessionid = null;
				var message = null;
				
				writeResponse(data); 
				//alert("받은 메시지:"+data);
				
				//문자열을 splite//
				var strArray = data.split(':')
				for (var i=0; i<strArray.length; i++){
					console.log('str['+i+']: '+strArray[i]);
				}
				
				sessionid = strArray[0];//현재 메시지를 보낸사람의 세션 등록
				message = strArray[1]; //현재 메시지를 저장
				
				console.log("세션아이디"+sessionid);
				console.log("로그인아이디"+loginuserid);
				
				//* /나와 상대방이 보낸 메세지를 구분하여 영역을 나눈다.//
				if(sessionid == loginuserid){
					
					console.log("세션아이디"+sessionid);
					console.log("로그인아이디"+loginuserid);
					
					writeResponse(data);
					/* const div = document.createElement('div');
					const p = document.createElement('p');
					const small = document.createElement('small');
					const span = document.createElement('span');
					
		            var msg_history = document.getElementById("msg_history");

					document.msg_history.append(div, p, small, div, div, p, span) */
					
					//동적 태그 요소 
					
					
					/* var obj = document.getElementById("sessionid");
		            obj.appendChild(sessionid);

					
		            var msg_history = document.getElementById("msg_history");

					var incomingDiv = document.createElement("div");
					incomingDiv.setAttribute("class","incoming_msg");
​
					var receivep = document.createElement("p");
					receivep.setAttribute("id","receive_id");
					
					var small = document.createElement("small");
					small.setAttribute("id","receive_id");
					small.innerHTML = sessionid;
					
					var receivemsgDiv = document.createElement("div");
					receivemsgDiv.setAttribute("class","received_msg");
					
					var receivewithDiv = document.createElement("div");
					receivemsgDiv.setAttribute("class","received_withd_msg");
​
					var receivemsg = document.createElement("p");
					receivemsg.innerHTML = sessionid;

					msg_history.appendChild(receivemsg); */
									
					
					
					
				}
				else{
					
				} */
					
			};
			
			//웹소켓 연결 성공시
			ws.open = function(event) {
				if (event.data === undefined) {
					return;
				}
				writeResponse(event.data);
				consloe.log('연결 성공'+ws)
				register();
			};

			

			//웹소켓 연결 끊겼을 시
			ws.onclose = function(event) {
				consloe.log('연결 끊김')
				
				writeResponse("대화 종료"); 
			}

		}
		
		
		
		//웹소켓으로 메시지를 보낸다.
		function send() {
			//var text = $("#massageinput").val+","+$("#sender").val
			/* var text = document.getElementById("messageinput").value 
					+ ","
					+ document.getElementById("sender").value;
			ws.send(text);
			text = "";
			 */
			
			var msg =  document.getElementById("messageinput").value;
			var user = document.getElementById("sender").value;
			var content = msg+","+user;
			
			ws.send(content);
			content = "";
			
			
			
			
		}

		function closeSocket() {
			ws.close();
		}

		function writeResponse(content) {
			messages.innerHTML += "<br/>" + content;
		}

	</script>


</body>
</html>
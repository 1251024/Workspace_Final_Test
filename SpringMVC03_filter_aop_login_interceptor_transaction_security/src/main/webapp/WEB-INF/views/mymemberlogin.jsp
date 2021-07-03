<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LoginForm</title>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">

	$(function(){
		$("#loginChk").hide();
	});

	function login() {
		var memberid = $("#memberid").val().trim();
		var memberpw = $("#memberpw").val().trim();
		var loginVal = {
				"memberid" : memberid,
				"memberpw" : memberpw
		}
		
		if (memberid == null || memberid == "" || memberpw == null ||memberpw ==""){
			alert("ID 및 PW를 다시 확인해 주세요!!");
		}else{
			//비동기통신
			$.ajax({
				type:"post",
				url:"ajaxlogin.do",//서버(컨트롤러와 연결)
				//dataType : 컨트롤러에서 오는거!  
				//contentType : 서버에 데이터를 보낼 때 사용
				data:JSON.stringify(loginVal),	// JSON.stringify() 메서드는 JavaScript 값이나 객체를 JSON 문자열로 변환합니다.
												
				contentType:"application/json",	// json 문자열을 json object(json객체) 형태로 바꿔줌 
												// 보내지는 타입:넘어가는게 string아니고 json이야 ! 
												// 없으면 415 에러 -> 리퀘스트 객체에 담아서 넘어오는 값이 잘못될 경우 발생
												
				dataType:"json", 				// controller에서 보내준거 json으로 받을거야
												
				//통신하고 나서 아래 실행
				success:function(msg){
					if(msg.check == true){
						location.href="list.do";
					}else{
						$("#loginChk").show();
						$("#loginChk").html("ID 혹은 PW가 잘못되었습니다.");
					}
				},
				error:function(){
					alert("통신 실패");
				}
			});
		}
		
	}
</script>

</head>
<body>


	<table>
		<tr>
			<th>ID</th>
			<td><input type="text" id="memberid"></td>
		</tr>

		<tr>
			<th>PW</th>
			<td><input type="password" id="memberpw"></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="login" onclick="login();">
				<input type="button" value="regist" onclick="location.href='registform.do'">
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" id="loginChk"></td>
		</tr>
	</table>

</body>
</html>
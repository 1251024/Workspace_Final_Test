<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#findidresult").hide();
	});
	
	function findid() {
		var useremail = $("#useremail").val().trim();
		
		var findVal = {
				"useremail" : useremail
		}
		
		
		if(useremail == null || useremail == "") {
			alert("email을 입력해 주세요");
			
		} else {
			$.ajax({
				type:"post",
				url:"ajaxfindid.do",
				data:JSON.stringify(findVal),
				contentType:"application/json",
				dataType:"json",
				success:function(msg) {
					if(msg.userid != null) {
						$("#findidresult").show();
						$("#findidresult").html(msg.userid);
						$("#cancelBtn").html("login하러가기")
					} else {
						$("#findidresult").show();
						$("#findidresult").html("일치하는 이메일이 존재하지 않습니다.").css("color", "red");
					}
				},
				error:function() {
					alert("통신 실패");
				}
			});
		}
		
	}
	

</script>
<body>
	
	<h2>아이디 찾기</h2>
	
		<label>Email</label>
		<input type="text" id="useremail" placeholder="이메일을 입력해 주세요 ex)1235@xxxx.xxx" style="width: 300px;">
		<div id="findidresult"></div>
		<br>
		<input type="button" value="찾기" onclick="findid();">
		<button type="button" id="cancelBtn" onclick="history.go(-1)">취소</button>
</body>
</html>














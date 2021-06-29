<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

*{
padding: 1px;
margin: 1px;
width: auto;
align-content: center;
}

header{
height: 30%
}

</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<script type="text/javascript">
	$(function(){
		$("#loginChk").hide();
	});
	
	function login() {
		var userid = $("#memberid").val().trim();
		var password = $("#memberpw").val().trim();
		
		// 자바스크립트 오브젝트 리터럴 형태 뭔지 찾아보자.
		var loginVal =  {
				"userid" : userid,
				"password" : password
		}
		if(userid == null || userid == "" || password == null || password == "") {
			alert("ID 및 PW를 다시 확인해 주세요!");
		} else {
			$.ajax({
				type:"post",
				url:"ajaxlogin.do",
				data:JSON.stringify(loginVal),  											   
				contentType:"application/json", 
				dataType:"json",
				success:function(msg) {
					if(msg.check == true) {
						alert("반갑습니다.");
						location.href="loginform.do";
					} else {
						$("#loginChk").show();
						$("#loginChk").html("ID 혹은 PW가 잘못되었습니다.");
					}
				},
				error:function() {
					alert("통신 실패");
				}
			});
		}
		
	}
	
</script>
</head>
<body>

<!-- 헤더  로고 넣고-->

<header><a><img src="resources/image/logo2.png" href="#"></a></header>


<!-- 메인 -->
<main>
<c:if test="${login == null}">
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
				<td colspan="2" align="center">
					<input type="button" value="login" onclick="login();">
					<a> / </a>
					<input type="button" value="sign in" onclick="location.href='registform.do'">
				
				</td>
				
			</tr>
			<tr>
				<td colspan="2" align="center" id="loginChk" style="color: red;"></td>
			</tr>
		</table>
		<a href="findid.do" style="color: black; text-decoration: none;" id="find_id_btn">아이디찾기</a>
		<a> / </a>
		<a href="findpw.do" style="color: black; text-decoration: none;">비밀번호찾기</a>
		<br>
		<a href="kakaologin.do">
		<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="200"/>
		</a>
		<br>
		<a href="${url}">
			<img width="200" src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png"/>
		</a>
</c:if>
<c:if test="${login != null}">

	<div>${login.userId}님 환영합니다.</div>

</c:if>

</main>

<!-- 푸터 -->	

<footer class=footer>
	  <div class="container">
            <small>Copyright &copy;
                Phoenix
                <a href="https://github.com/DOHAN25/Final_Project.git">@github</a>
            </small>
        </div>
        <p>
            <a href="#">Back to top</a>
        </p>
	
	</footer>
	
</body>
</html>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<a href="home.do">home...</a>
	<br/>
	<a href="main.do">대진이메인</a>
	<br/>
	<a href="pay.do">재희</a>
	<br/>
	<a href="weather.do">태린 날씨</a>
	<br/>
	<a href="header.do">보옥 헤더</a>
	<br/>
	<a href="loginform.do">로그인</a>
	
<c:if test="${login != null}">

	<div>${login.userId}님 환영합니다.</div>

</c:if>
</body>
</html>
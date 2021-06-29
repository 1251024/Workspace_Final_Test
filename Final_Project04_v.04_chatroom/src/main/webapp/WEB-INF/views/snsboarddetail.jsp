<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

	<h1>SnsBoardDetail</h1>
	
	<table border="1">
		<tr>
			<td>userId</td>
			<td>${dto.userId }</td>
		</tr>
		<tr>
			<td>boardDate</td>
			<td>${dto.boardDate }</td>
		</tr>
		<tr>
			<td>이미지</td>
			<td></td>
		</tr>
		<tr>
			<td>썸네일 이미지</td>
			<td></td>
			
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly">${dto.boardContents }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="수정" onclick="location.href='snsBoardUpdateForm.do?entireBoardSeq=${dto.entireBoardSeq}'"/>
				<input type="button" value="삭제" onclick="location.href='snsBoardDelete.do?entireBoardSeq=${dto.entireBoardSeq}'"/>
				<input type="button" value="목록" onclick="location.href='snsBoardList.do'"/>
			</td>
		</tr>
	</table>
	<img src="<c:url value ='C:\Git\Final_Project\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Final_Project04\resources${dto.boardThumbImg }' />">
	<img src="C:\Git\Final_Project\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\Final_Project04\resources${dto.boardImg }">

	


	
	

</body>
</html>
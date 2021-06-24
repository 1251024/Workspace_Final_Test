<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>snsboardupdate</h1>
	<form action="snsBoardUpdateRes.do" method="post">
	<input type="hidden" name="entireBoardSeq" value="${dto.entireBoardSeq }">
		<table border="1">
		<tr>
			<td>userId</td>
			<td><input type="text" name="userId" value="${dto.userId }"></td>
		</tr>
		<tr>
			<td>boardDate</td>
			<td>${dto.boardDate }</td>
		</tr>
		<tr>
			<td>snsImg</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" name="boardContents">${dto.boardContents }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="글수정" />
				<input type="button" value="취소" onclick="location.href='snsBoardDetail.do'"/>
			</td>
		</tr>
		</table>
		
	</form>

</body>
</html>
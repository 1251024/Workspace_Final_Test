<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>snsboarddetail</h1>
	
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
			<td>snsImg</td>
			<td>${dto.snsImg }</td>
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

</body>
</html>
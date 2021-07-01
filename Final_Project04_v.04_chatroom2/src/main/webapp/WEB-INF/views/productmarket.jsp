<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>당근마켓 상품 리스트</h1>

	<a href="productinsert.do">상품등록</a>
	
	<table border="1">
	<col width="200">
	<col width="100">
	<col width="200">
	<col width="100">
	<col width="300">
	
	<tr>
		<th>등록날짜</th>
		<th>유저ID</th>
		<th>상품명</th>
		<th>상품가격</th>
		<th>판매자주소</th>
	</tr>
	
		<c:choose>
			<c:when test="${empty userProductList }">
				<tr>
					<th colspan="4">------ 작성된 글이 없습니다 ------ </th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${userProductList }" var="dto">
					<tr>
						<td>${dto.productRegDate }</td>
						<td>${dto.userId }</td>
						<td><a href="productdetail.do?productSeq=${dto.productSeq }">${dto.productName }</a></td>
						<td>${dto.productPrice }</td>
						<td>${dto.sellerAddress }</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="4" align="right"><input type="button" value="글작성" onclick="location.href='productinsert.do'"></td>
		</tr>
	</table>

</body>
</html>





















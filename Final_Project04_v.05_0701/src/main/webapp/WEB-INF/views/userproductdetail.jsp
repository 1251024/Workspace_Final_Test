<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>상품정보</h1>
	
	<table border="1">
		<tr>
			<td>userId</td>
			<td>${dto.userId }</td>
		</tr>
		<tr>
			<td>등록날짜</td>
			<td>${dto.productRegDate }</td>
		</tr>
		<tr>
			<td>상품명</td>
			<td>${dto.productName }</td>
		</tr>
		<tr>
			<td>판매금액</td>
			<td>${dto.productPrice }</td>
		</tr>
		<tr>
			<td>판매가능여부</td>
			<td>${dto.saleStatus }</td>
		</tr>
		<tr>
			<td>상품정보</td>
			<td>${dto.productInfo }</td>
		</tr>
		<tr>
			<td>거래가능지역</td>
			<td>${dto.sellerAddress }</td>
		</tr>
	</table>

</body>
</html>
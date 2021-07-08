<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>씨앗 마켓</title>
<style type="text/css">
#test{
	height: 20px;
}

</style>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
	
$(document).ready(function(){	
	
	
	$("#searchBtn").click(function (){
		var selectBox = $("#selectBox option:selected").val();
		var searchVal = document.getElementById("searchVal").value;
		console.log(selectBox);
		
		if(selectBox == "" || selectBox == null) {
				alert("카테고리를 선택해 주세요.!");
				$("#searchVal").focus();
				
		} else if(selectBox === "addr") {
			
			var addrVal = {
					"sellerAddress": searchVal
			}
			
			$.ajax({
				type: "post",
				url: "searchProduct.do",
				data: JSON.stringify(addrVal),
				contentType: "application/json",
				dataType:"json",
				success:function(data) {
				//console.log(data.list.length);
				//console.log(data.list[0].productName);
			
				var html = "<tr>";
					for(var i = 0; i < data.list.length; i++) {
						html += "<td>"+data.list[i].productRegDate+"</td>";
						html += "<td>"+data.list[i].userId+"</td>";
						html += "<td><a href='userproductdetail.do?productSeq="+data.list[i].productSeq+"'>"+data.list[i].productName+"</a></td>";
						html += "<td>"+data.list[i].productPrice+"</td>";
						html += "<td>"+data.list[i].sellerAddress+"</td>";	
					}
					html += "</tr>";
					$("#dynamicTbody").empty();
					$("#dynamicTbody").append(html);
				}
				
			});
		} else if(selectBox === "productName") {
			
			var addrVal = {
					"productName": searchVal
			}
			
			$.ajax({
				type: "post",
				url: "searchProduct.do",
				data: JSON.stringify(addrVal),
				contentType: "application/json",
				dataType:"json",
				success:function(data) {
				//console.log(data.list.length);
				//console.log(data.list[0].productName);
			
				var html = "<tr>";
					for(var i = 0; i < data.list.length; i++) {
						html += "<td>"+data.list[i].productRegDate+"</td>";
						html += "<td>"+data.list[i].userId+"</td>";
						html += "<td><a href='userproductdetail.do?productSeq="+data.list[i].productSeq+"'>"+data.list[i].productName+"</a></td>";
						html += "<td>"+data.list[i].productPrice+"</td>";
						html += "<td>"+data.list[i].sellerAddress+"</td>";	
					}
					html += "</tr><br>";
					$("#dynamicTbody").empty();
					$("#dynamicTbody").append(html);
				}
				
			});
		}
			
		
	
		
	});


});

</script>
<body>
	<h1>씨앗마켓 상품 리스트</h1>
	<br>
	<p>당근팜 씨앗 마켓만의 텃밭가꾸기 상품들을 만나보세요!</p>

	<a href="adminproductinsert.do">상품등록</a>
	<br>
	<select id="selectBox">
		<option value="">선택</option>
		<option value="productName">상품명</option>
	</select>
	<input id="searchVal" type="text"/>
	<button id="searchBtn">검색</button>
	
	
	<table border="1">
	<col width="100">
	<col width="300">
	<col width="300">
	<col width="200">
	
	<tr>
		<th>상품번호</th>
		<th>이미지</th>
		<th>상품명</th>
		<th>상품가격</th>
		
	</tr>
	<tbody id="dynamicTbody">
		<c:choose>
			<c:when test="${empty adminProductList }">
				<tr>
					<th colspan="4">------ 작성된 글이 없습니다 ------ </th>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${adminProductList }" var="ProductDto">
				
					<tr id="test">
						<td>${ProductDto.productSeq }</td>
						<td>${ProductDto.productImg }</td>
						<td><a href="adminproductdetail.do?productSeq=${ProductDto.productSeq }">${ProductDto.productName }</a></td>
						<td> ${ProductDto.productPrice }</td>
					</tr>
				
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
		<tr>
			<td colspan="4" align="right"><input type="button" value="글작성" onclick="location.href='adminproductinsert.do'"></td>
		</tr>
	</table>

</body>
</html>





















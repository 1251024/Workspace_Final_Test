<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script type="text/javascript">
	//로그인 한 상태에서 하트를 클릭했을 때 (로그인한 상태인 하트의 <a></a> class명: heart-click)
	$(".heart-click").click(function(){
		
		//게시물 번호(entireBoardSeq)를 idx로 전달 받아 저장
		let entireBoardSeq = $(this).attr('idx');
		console.log("heart-click");
		
		//빈하트를 눌렀을때 
		if($(this).children('svg').attr('class') == "bi bi-suit-heart"){
			console.log("빈하트 클릭" + entireBoardSeq);
			
			$.ajax({
				url : 'saveHeart.do',
				type : 'get',
				data : {
					entireBoardSeq : entireBoardSeq
				},
				success : function(dto) {
					//페이지새로고침
					//document.location.reload(true);
					
					let heart = dto.likeCount;
					
					//페이지, 모달창에 하트수 갱신;
					$('#m_heart'+entireBoardSeq).text(heart);
					$('#heart'+entireBoardSeq).text(heart);
					
					console.log("하트추가 성공");
				},
				error : function() {
					alert('서버에러');
				}
			});
			console.log("꽉찬하트로 바껴라!");
			
			//꽉찬하트로 바꾸기!
			$(this).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");
	        $('.heart_icon'+entireBoardSeq).html("<svg xmlns='http://www.w3.org/2000/svg' width='16' height='16' fill='currentColor' class='bi bi-suit-heart-fill' viewBox='0 0 16 16'><path d='M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z'/></svg>");
		
		//꽉찬 하트를 눌렀을 때 
		}
		}
	});
</script>
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
			<td><img src="${pageContext.request.contextPath}/resources${dto.boardImg }"/></td>
		</tr>
		<tr>
			<td>썸네일 이미지</td>
			<td><img src="${pageContext.request.contextPath}/resources${dto.boardThumbImg }"/></td>
			
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly">${dto.boardContents }</textarea></td>
		</tr>
		<tr>
			<td>좋아요</td>
			<td>
			<c:choose>
				<c:when test="${empty likedto.likeSeq }">
					<!-- 빈하트일때 -->
    				<span> 
        				<a idx="${dto.entireBoardSeq }" href="javascript:" class="heart-click heart_icon${dto.entireBoardSeq }">
            				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart" viewBox="0 0 16 16">
            				<path d="M8 6.236l-.894-1.789c-.222-.443-.607-1.08-1.152-1.595C5.418 2.345 4.776 2 4 2 2.324 2 1 3.326 1 4.92c0 1.211.554 2.066 1.868 3.37.337.334.721.695 1.146 1.093C5.122 10.423 6.5 11.717 8 13.447c1.5-1.73 2.878-3.024 3.986-4.064.425-.398.81-.76 1.146-1.093C14.446 6.986 15 6.131 15 4.92 15 3.326 13.676 2 12 2c-.777 0-1.418.345-1.954.852-.545.515-.93 1.152-1.152 1.595L8 6.236zm.392 8.292a.513.513 0 0 1-.784 0c-1.601-1.902-3.05-3.262-4.243-4.381C1.3 8.208 0 6.989 0 4.92 0 2.755 1.79 1 4 1c1.6 0 2.719 1.05 3.404 2.008.26.365.458.716.596.992a7.55 7.55 0 0 1 .596-.992C9.281 2.049 10.4 1 12 1c2.21 0 4 1.755 4 3.92 0 2.069-1.3 3.288-3.365 5.227-1.193 1.12-2.642 2.48-4.243 4.38z" />
        				</a>
    				</span>
    			</c:when>
    			<c:otherwise>
    				<!-- 꽉찬하트일때 -->
    				<span> 
        				<a idx="${dto.entireBoardSeq }" href="javascript:" class="heart-click heart_icon${dto.entireBoardSeq }">
            				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-suit-heart-fill" viewBox="0 0 16 16">
            				<path d="M4 1c2.21 0 4 1.755 4 3.92C8 2.755 9.79 1 12 1s4 1.755 4 3.92c0 3.263-3.234 4.414-7.608 9.608a.513.513 0 0 1-.784 0C3.234 9.334 0 8.183 0 4.92 0 2.755 1.79 1 4 1z" /> </svg>
        				</a>
    				</span>
    			</c:otherwise>
    		</c:choose>
			</td>
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
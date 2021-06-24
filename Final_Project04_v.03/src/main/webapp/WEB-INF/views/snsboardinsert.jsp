<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- style -->
	<style>
	
	.uploadResult{
		width: 100%;
		background-color : gray;
	}
	
	.uploadResult ul{
		display:flex;
		flex-flow :row;
		justify-content : center;
		align-items : center;
	}
	
	.uploadResult ul li{
		list-style : none;
		padding : 10px;
	}
	
	.uploadResult ul li img{
		width : 100px;
	}
	
	.uploadResult ul li span{
		color : white;
	}
	
	#image_container img{
		width:400px;
		
	}
	
	</style>
	
</head>
<body>

	<h1>글 작성 Upload with Ajax</h1>
	
	<!-- 업로드 페이지 -->
	<form action="snsBoardInsertRes.do" method="post">
			<!-- 사진업로드 박스 -->
				<div>
					<p>사진 업로드</p>
				</div>
			<!-- 사진업로드 form -->
			<div class='uploadFileDiv'>
				<input type="file" name="uploadFile" accept="image/*1" id="input_img" onchange="setThumbnail(event);"><br>
			</div>
				<!-- 이미지 미리보기 출력 -->	
				<div id="image_container"></div>
				
				<!-- 여기서부터는 가져다 쓴것 -->
				<!-- 이건뭐지 -->
				<div class= 'uploadResult'>
					<ul>
					</ul>
				</div>
								
				<button id='uploadBtn'>Upload</button>
	
				<!-- end -->
				
				<!-- 게시글 + 업로드 버튼 -->
				<div class="upload-form-detail">
					<input type="text" name="boardContents" placeholder="게시글" /><br>
					<input type="text" name="tags" placeholder="태그"><br>
					<button type="submit">등록</button>
				</div>
											
	</form> 
	<!-- 해쉬태그 등록 -->
			<form class="hashtag-insert-form" action="" method="post">			
				<div class="insert-hash-tag">
					<input type="text" name="hashtags" placeholder="해쉬태그 입력"> 
				</div>
				<button type="submit">등록</button>
			</form>

		
	<script>
	//이미지 미리보기 함수 
		function setThumbnail(event) {
			var reader = new FileReader();
			
			//onload됐을시에 발생할 이벤트 작성 
			reader.onload = function(event) {
				//FileReader가 로드 됐을 때 img 엘리먼트 생성하고 src 속성을 설정 
				var img = document.createElement("img");
				img.setAttribute("src", event.target.result);
				//이미지가 들어갈 div에 넣어주기 
				document.querySelector("div#image_container").appendChild(img);
			};
			
			//리더에서 input 태그에서 선택한 파일을 읽어오도록 설정 
			reader.readAsDataURL(event.target.files[0]);
		} 
		//end setTumbnail 
	
		//첨부파일 영역 선택자 
		var uploadDiv = $("div.uploadFileDiv");
		
		//첨부파일 이벤트 처리 : 파일 업로드 처리 -> 파일 출력	
		uploadDiv.on("drop", function (event){
			//click 이벤트 발생시 전달된 파일 데이터 
			var files = event.originalEvent.dataTransfer.files;
			console.log("files : " , files);
			//단일 파일 데이터만을 처리하기 때문에 첫번째 파일만 저장
			var file = files[0];
			console.log("file : " , file);
			//formData 객체 생성 , 파일 데이터 저장 
			var formData = new FormData();
			formData.append("file", file);
			//파일 업로드 AJAX 통신 메서드 호출 
			uploadFile(formData);
		});
		
		function uploadFile(formData) {
			$.ajax({
				
			});
		}
		
		
	</script>


</body>
</html>



















<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.fileDrop {
		width: 100%;
		height: 200px;
		border: 2px dotted #0b58a2;
	}
</style>

<!-- 잘되면 js파일에 새로 만들어 주기  -->
<script type="text/javascript" src="${path}/resources/dist/js/upload.js">
<!-- 업로드 JS -->
<!-- 파일 정보 가공-->
function getFileInfo(boardFileName) {}
</script>
</head>
<body>

	<h1>글 작성</h1>
	<!-- 업로드 페이지 -->
	<form action="snsBoardInsertRes.do" method="post">
		<main class="snsContainer">
			<!-- 사진업로드 박스 -->
			<section class="upload">
				<div>
					<p>사진 업로드</p>
				</div>
			<!-- 사진업로드 form -->
			<form class="photo-upload-form" method="post" enctype="multipart/form-data" action="/file/upload">
				<input type="file" name="snsImg" accept="image/*1" id="input_img" onchange="setThumbnail(event);"><br>
				<div id="image_container"></div>
				<!-- 게시글 + 업로드 버튼 -->
				<div class="upload-form-detail">
					<input type="text" name="boardContents" placeholder="게시글" /><br>
					<input type="text" name="tags" placeholder="태그"><br>
					<button type="submit">등록</button>
				</div>
			</form>
			<!-- 첨부파일 영역 추가 -->
			<div class="form-group">
				<div class="fileDrop">
					<br/>
					<br/>
					<br/>
					<br/>
					<p class="text-center"><i class="fa fa-paperclip"></i>첨부파일을 드래그해주세요.</p>
				</div>
			</div>
			
			<!-- 첨부파일 영역 추가 -->
			<div class="card-footer">
				<ul class="mailbox-attachments clearfix uploadedList"></ul>
			</div>
			
			<!-- 첨부파일 하나의 영역 -->
			<!--이미지-->
			<script id="templatePhotoAttach" type="text/x-handlebars-template">
				<li>
    				<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
    				<div class="mailbox-attachment-info">
        				<a href="{{getLink}}" class="mailbox-attachment-name" data-lightbox="uploadImages"><i class="fas fa-camera"></i>{{boardFileName}}</a>
        				<a href="{{fullName}}" class="btn btn-default btn-xs float-right delBtn"><i class="far fa-trash-alt"></i></a> </div>
    				</div>
				<li>
			</script>
			<!-- 일반파일 -->
			<script id="templateFileAttach" type="text/x-handlebars-template">
			<li>
				<span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
				<div class="mailbox-attachment-info">
					<a href="{{getLink}}" class="mailbox-attachment-name"><i class="fas fa-paperclip"></i>{{boardFileName}}</a>
					<a href="{{fullName}}" class="btn btn-default btn-xs float-right delBtn"><i class="far fa-trash-alt"></i></a>
				</div>
			</li>
			</script>
					
	</form> 
	
			<form class="hashtag-insert-form" action="" method="post"/>
				<!-- 해쉬태그 등록 -->
				<div class="insert-hash-tag">
					<input type="text" name="hashtags" placeholder="해쉬태그 입력"> 
				</div>
			</form>
			</section>
		</main>
		
		
		<h2>INSERT</h2>
	<form action="snsBoardInsertRes.do" method="post">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="userId"/></td>
			</tr>

			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="boardContents"></textarea></td>
			</tr>
			
			<tr>
				<td colspan="2" align="right">
					<input type="submit" value="글작성" />
					<input type="button" value="취소" onclick="location.href='snsBoardInsertForm.do'" />
				</td>
			</tr>
		</table>
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
	
	//파일 정보 가공
	function getFile(fullName) {
		
		var boardFileName; //화면에 출력할 파일명
		var imgsrc;        //썸네일 or 파일 아이콘 이미지 파일 요청URL
		var getLink;       //원본파일 요청 URL
		
		var fileLink;     //날짜경로를 제외한 나머지 파일명 (UUID_파일명.확장자)
		
		//이미지 파일일 경우
		if (checkImageType(boardFileName)) {
			//썸네일 파일 이미지 URL
	        // 썸네일 파일 이미지 URL
	        imgsrc = "/mypage/file/display?boardFileName=" + boardFileName;
	        // UUID_파일명.확장자 (s_ 제외 : 원본이미지)
	        fileLink = boardFileName.substr(14);
	        // 원본파일 요청 URL
	        var front = boardFileName.substr(0, 12); // 날짜 경로
	        var end = boardFileName.substr(14);      // 파일명(s_ 제외)
	        getLink = "/mypage/file/display?boardFileName=" + front + end;
	        
	        //이미지 파일이 아닐경우
		} else {
			// 파일 아이콘 이미지 URL
	    	imgsrc = "/mypage/resources/upload/files/file-icon.png";
	        // UUID_파일명.확장자
	        fileLink = boardFileName.substr(12);
	        // 파일 요청 url
	        getLink = "/mypage/file/display?boardFileName=" + boardFileName;
		}
		
		// 화면에 출력할 파일명
	    boardFileName = fileLink.substr(fileLink.indexOf("_") + 1);

	    return {boardFileName: boardFileName, imgsrc: imgsrc, getLink: getLink, boardFileName: boardFileName};
	}
	
	// 이미지 파일 유무 확인
	function checkImageType(fileName) {
	    // 정규 표현식을 통해 이미지 파일 유무 확인
	    var pattern = /jpg$|gif$|png$|jpge$/i;
	    return fileName.match(pattern);
	    
	}
	
    /*====================================================게시판 첨부파일 업로드 관련======================================*/
    $(document).ready(function () {
        var fileDropDiv = $(".fileDrop");
        var templatePhotoAttach = Handlebars.compile($("#templatePhotoAttach").html());
        var templateFileAttach = Handlebars.compile($("#templateFileAttach").html());
        // 전체 페이지 파일 끌어 놓기 기본 이벤트 방지 : 지정된 영역외에 파일 드래그 드랍시 페이지 이동방지
        $(".content-wrapper").on("dragenter dragover drop", function (event) {
            event.preventDefault();
        });
        // 파일 끌어 놓기 기본 이벤트 방지
        fileDropDiv.on("dragenter dragover", function (event) {
            event.preventDefault();
        });
        // 파일 드랍 이벤트 : 파일 전송 처리, 파일 화면 출력
        fileDropDiv.on("drop", function (event) {
            event.preventDefault();
            var files = event.originalEvent.dataTransfer.files;
            var file = files[0];
            var formData = new FormData();
            formData.append("file", file);
            $.ajax({
                url: "/file/upload",
                data: formData,
                dataType: "text",
                processData: false,
                contentType: false,
                type: "POST",
                success: function (data) {
                    // 파일정보 가공
                    var fileInfo = getFileInfo(data);
                    // 이미지 파일일 경우
                    if (data.substr(12, 2) == "s_") {
                    	console.log("This is Image");
                        var html = templatePhotoAttach(fileInfo);
                    // 이미지 파일이 아닐 경우
                    } else {
                        html = templateFileAttach(fileInfo);
                    }
                    $(".uploadedList").append(html);
                }
            });
        });
        // 글 저장 버튼 클릭 이벤트 : 파일명 DB 저장 처리
        $("#writeForm").submit(function (event) {
            event.preventDefault();
            var that = $(this);
            var str = "";
            $(".uploadedList .delBtn").each(function (index) {
                str += "<input type='hidden' name='files["+index+"]' value='"+$(this).attr("href")+"'>"
            });
            that.append(str);
            that.get(0).submit();
        });
        // 파일 삭제 버튼 클릭 이벤트 : 파일삭제, 파일명 DB 삭제 처리
        $(document).on("click", ".delBtn", function (event) {
            event.preventDefault();
            var that = $(this);
            $.ajax({
                url: "/mypage/file/delete",
                type: "post",
                data: {fileName:$(this).attr("href")},
                dataType: "text",
                success: function (result) {
                    if (result == "DELETED") {
                        alert("삭제되었습니다.");
                        that.parents("li").remove();
                    }
                }
            });
        });
    });

	</script>

</body>
</html>



















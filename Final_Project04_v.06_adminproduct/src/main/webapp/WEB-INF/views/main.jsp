<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
</head>
<body>

	<h1>대진이 메인 작업창</h1>
	<h2>대진이 두번째 작업창 :<a href="weekendfarmmap.do"> 지도 </a></h2>
	<h2>대진이 세번째 작업창 : <a href="snsBoardInsertForm.do">글 작성</a></h2>
	<div id="page-content-wrapper">
		<div class="search-window">
			<input type="search" placeholder="검색어를 입력하세요."/>
			<input type="submit" value="검색" />
		</div>

        <!-- 글작성 -->
        
		<div class="content-container">
			<div class="timeline-insert-head">타임라인 글작성</div>
                <div class="timeline-insert-body">
                    <div class="insert-userId-form">
                    	유저아이디:<textarea type="text" class="user-id-form" name="userId" cols="30" rows="1"></textarea>
                    </div>
                    <div class="insert-content-form">
                        글내용:<br/><textarea type="text" class="sns-contents-form" name="boardContents" cols="30" rows="10"></textarea>
                    </div>
                    <div class="insert-btn-group" role="group">
                        <button type="button" class="insert-btn">미디어</button>
                        <button type="submit" class="insert-fin">등록</button>
                    </div>         
            </div>
		</div>
		
        <!-- 뉴스피드 -->
        <div class="newsfeed">
            <div class="newsfeed-one">
                <div class="newsfeen-one-contents">
                    <button type="button" class="btn btn-light float-right">...</button>
                    <img src="" class="rounded-circle profile photo" width="50">
                    <a class="feed-information">아이디</a>
                    <a class="feed-information" id="insert-time">작성시간</a><br>
                    <img src="" class="user-insert-image">회원이 올린 이미지<br/>
                    <a href="#" class="feed-content" style="margin-top: 10px;">글내용<br/>
                        블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창
                        블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창
                        블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창
                        블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창 블라블라피드에팔로우한사람글내용나오는창
                           
                    </a>
                </div>
                <div align="center">
                    <div class="btn-group-comment" role="group" aria-label="Button group with nested dropdown">
                        <button type="button" class="comment-btn-secondary" data-toggle="tooltip" data-placement="top" title="좋아요">
                            <img src="">좋아요
                        </button>
                        <button type="button" class="comment-btn-secondary" data-toggle="tooltip" data-placement="top" title="답글">답글</button>
                        <button type="button" class="comment-btn-secondary" data-toggle="tooltip" data-placement="top" title="공유하기">공유하기</button>
                    </div>

                </div>
            </div>

            </div>
        </div>
	</div>

  
	<table border="1">
	<col width="20">
	<col width="100">
	<col width="100">
	<col width="200">


	<tr>
		<th>번호</th>
		<th>작성자</th>
		<th>제목</th>
		<th>작성일</th>
	</tr>
	
	<c:choose>
		<c:when test="${empty snsBoardList }">
			<tr>
				<th colspan="4">------ 작성된 글이 없습니다-----</th>
			</tr>
		
		</c:when>
		<c:otherwise>
			<c:forEach items="${snsBoardList }" var= "dto">
				<tr>
					<td>${dto.entireBoardSeq }</td>
					<td>${dto.userId }</td>
					<td><a href="snsBoardOne.do?entireBoardSeq=${dto.entireBoardSeq }">${dto.boardTitle }</a></td>
					<td>${dto.boardDate }</td>
				</tr>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	<tr>
		<td colspan="4" align="right"><input type="button" value="글작성" onclick="location.href='snsBoardInsertForm.do'"></td>
	</tr>
	

</table>
</body>
</html>
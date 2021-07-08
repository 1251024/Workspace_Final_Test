<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당근팜</title>
<!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!--jquery (부트스트랩의 자바스트립트 플러그인을 위해 필요합니다.)-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <style>
        body { 
          
          padding-top: 70px;
          position: relative;
         }
        .navbar-brand img{
            width: 100px;  
        }
        
        .container-fluid .row p{
            font-size: 2em;
            
        }
        #ad{
          padding-bottom: 500px;
        }
        .navbar-default .navbar-nav>.active>a,
        .navbar-default .navbar-nav>.active>a:focus,
        .navbar-default .navbar-nav>.active>a:hover  {
          color:#166FA0;
          background-color: lightblue;
}
#text .text {
  position:absolute;
  top:100px;
  right:80px;
}
#img_title{
    font-size: 50px;
    color: blanchedalmond;
    
    
}
#img_content{
    font-size: 20px;
}

    </style>
</head>
<body>

<nav class="navbar navbar-default navbar-fixed-top " ><!--navbar-fixed-top":상단 고정 그냥하면 윗부분 덮어져서 바디에 패딩탑 설정하자  -->
        <!--navbar-inverse" : 네브바 색상 변경 반대 색상으로~-->
        <div class="container-fluid" >
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#myNavbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">
                <img src="img/logo.jpg" alt="당근팜">
            </a>
            <p class="navbar-text"><a href="#" class="navbar-link">당근팜</a>은 최고의 서비스를 제공합니다.</p>
          </div>
      
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse navbar-scrollspy" id="myNavbar">
            <ul class="nav navbar-nav">
              <li><a href="#section1">로컬마켓</a></li>
              <li><a href="#section2">씨앗,도구 구매</a></li>
              <li><a href="#section3">주말농장</a></li>
              <li><a href="#section4">꿀팁</a></li>
              <li><a href="#section5">날씨/미세먼지</a></li>
              <li><a href="#section6">게시판</a></li>
              <li><a href="#section7">로그인</a></li>
            </ul>
            <form class="navbar-form navbar-right" role="search">
              <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
              </div>
              <button type="submit" class="btn btn-default">Submit</button>
            </form>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
      </nav>
      
          <div class="container-fluid" id="section1">
            <a href="usermarketplace.do"><div class="col-md-12" id="text" ><img src="resources/image/로컬마켓_main.jpg" alt="당근팜" style="width: 100%; height: 100%;">
                <div class="text"><p id="img_title">로컬마켓으로 이동</p><p id="img_content">설명</p></div></div></a>
            </div>
              
          
          <div class="container-fluid" id="section2">
                <a href="adminproduct.do"><div class="col-md-12" id="text"><img src="resources/image/씨앗도구구매_main2.jpg" alt="당근팜" style="width: 100%; height: 100%;">
              <div class="text"><p id="img_title">씨앗,도구 구매</p><p id="img_content">설명</p></div></div></a>
          </div>
        
        
          <div class="container-fluid" id="section3">
              <a href="loginform.do"><div class="col-md-12" id="text"><img src="resources/image/주말농장_main1.jpg" alt="당근팜" style="width: 100%; height: 100%;">
              <div class="text"><p id="img_title">주말농장</p><p id="img_content">설명</p></div></div></a>
          </div>
        
          <div class="container-fluid" id="section4">
            <a href="main.do"><div class="col-md-12" id="text"><img src="resources/image/꿀팁얻기_main1.jpg" alt="당근팜" style="width: 100%;">
              <div class="text"><p id="img_title">꿀팁얻기</p><p id="img_content">설명</p></div></div></a>
          </div>
        
          <div class="container-fluid" id="section5">
            <a href="weather.do"><div class="col-md-12" id="text"><img src="resources/image/날씨확인_main1.jpg" alt="당근팜" style="width: 100%;">
            <div class="text"><p id="img_title">날씨확인</p><p id="img_content">설명</p></div></div></a>
        </div>
    
        <div class="container-fluid" id="section6">
            <a href="noticeboardlist.do"><div class="col-md-12" id="text"><img src="resources/image/게시판_main1.jpg" alt="당근팜" style="width: 100%;">
            <div class="text"><p id="img_title">게시판</p><p id="img_content">설명</p></div></div></a>
        </div>
        
        
        <div class="container-fluid" id="section7">
            <a href="loginform.do"><div class="col-md-12" id="text"><img src="resources/image/로그인_main1.jpg" alt="당근팜" style="width: 100%;">
            <div class="text"><p id="img_title">게시판</p><p id="img_content">설명</p></div></div></a>
        </div>
    
          
      
      <script>
        $(document).ready(function(){
    // Add scrollspy to <body>
    $('body').scrollspy({target: ".navbar", offset: 50});   
  
    // Add smooth scrolling on all links inside the navbar
    $("#myNavbar a").on('click', function(event) {
  
      // Prevent default anchor click behavior
      event.preventDefault();
  
      // Store hash
      var hash = this.hash;
  
      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 800, function(){
     
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    });
  });
      </script>

	

	<a href="home.do">home...</a>
	<br/>
	<a href="main.do">대진이메인</a>
	<br/>
	<a href="pay.do">재희</a>
	<br/>
	<a href="weather.do">태린 날씨</a>
	<br/>
	<a href="header.do">보옥 헤더</a>
	<br/>
	<a href="loginform.do">로그인</a>
	<br/>
	<a href="noticeboardlist.do">공지사항</a>
	<br/>
	<a href="usermarketplace.do">중고시장</a>
	<br/>
	



</body>
</html>
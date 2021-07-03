<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!--jquery (부트스트랩의 자바스트립트 플러그인을 위해 필요합니다.)-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<style>
#header {
	height: 200px;
}

#body {
	height: 500px;
}

#left {
	height: 500px;
}

#right {
	height: 500px;
}

.container nav li {
	width: 265px;
}
</style>
</head>
<body>

	<header>
		<div class="container " style="width: 100%">

			<div class="row">


				<div class="col-md-12 bg-danger " id="header">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="#" title="로그인 페이지 이동"
							style="color: rgb(116, 116, 116)"><span
								class="glyphicon glyphicon-log-in" aria-hidden="true"></span></span></a></li>
						<li><a href="#" title="메인 페이지 이동"
							style="color: rgb(116, 116, 116)"><span
								class="glyphicon glyphicon-home" aria-hidden="true"></span></a></li>
					</ul>
					<div class="col-md-2 col-md-offset-5">
						<a class="navbar-brand" href="#"><img
							src="https://via.placeholder.com/150" alt="" class="img-rounded"></a>
					</div>
				</div>
			</div>
		</div>
	</header>

	<div class="container text-center" style="width: 100%">

		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="active"><a href="#">로컬마켓<span class="sr-only">(current)</span></a></li>
						<li><a href="#">씨앗,도구 구매</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">주말농장
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">농장소개</a></li>
								<li><a href="#">예약하기</a></li>
								<li><a href="#">위치안내</a></li>
								<li class="divider"></li>
								<li><a href="#">Separated link</a></li>
								<li class="divider"></li>
								<li><a href="#">One more separated link</a></li>
							</ul></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a href="#">꿀팁</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">날씨/미세먼지
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">날씨</a></li>
								<li><a href="#">미세먼지</a></li>
								<li><a href="#">Something else here</a></li>
								<li class="divider"></li>
								<li><a href="#">Separated link</a></li>
							</ul></li>

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">게시판
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="#">공지사항</a></li>
								<li><a href="#">~~게시판</a></li>
								<li><a href="#">~~게시판</a></li>

							</ul></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
	</div>




	<div class="container" style="width: 100%">
		<div class="row">
			<div class="col-md-2 bg-success" id="left">f</div>
			<div class="col-md-8 bg-info " id="body">f</div>
			<div class="col-md-2 bg-warning" id="right">ffff</div>
		</div>
	</div>





	<footer class="footer text-center secondary">
		<div class="container" style="width: 100%">
			<div class="row">
				<hr>
				<div class="col-md-12  text-center">
					<div class="col-md-4 mb-5 mb-lg-0">
						<h4 class="text-uppercase mb-4">Project</h4>
						<p>
							kh정보교육원<br> 오픈소스 기반 머신러닝 플랫폼을 활용한 응용소프트웨어 개발자 양성과정<br>
							당근팜 프로젝트 - 불사조
						</p>
					</div>
					<div class="col-md-4 mb-5 mb-lg-0">
						<h4 class="text-uppercase mb-4">git</h4>
						<a href="https://github.com/DOHAN25/Final_Project.git"> 
						<img
							src="resources/image/git_logo.png" alt="git_logo" width="32" height="32">
						<svg id="i-github" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 64 64" width="32" height="32"></svg>
						</a>
					</div>
					<div class=" col-md-4">
						<h4 class="text-uppercase mb-4">Developer</h4>
						<div class="dropdown d-inline-block">
							<button
								class="btn mb-2 mr-2 dropdown-toggle btn btn-outline-secondary dropdown-toggle"
								type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
								aria-expanded="false">Developer</button>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
								<li><a class="dropdown-item"
									href="https://github.com/DOHAN25">김도한</a></li>
								<li><a class="dropdown-item"
									href="https://github.com/dd-jiny">김대진</a></li>
								<li><a class="dropdown-item"
									href="https://github.com/Taerin-Kim">김태린</a></li>
								<li><a class="dropdown-item" href="#">박찬종</a></li>
								<li><a class="dropdown-item"
									href="https://github.com/jaehee-9638">이재희</a></li>
								<li><a class="dropdown-item"
									href="https://github.com/1251024">장보옥</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="copyright py-4 text-center text-light bg-success">
					<div class="container">
						<small>Copyright &copy; Phoenix <a
							href="https://github.com/DOHAN25/Final_Project.git">@github</a>
						</small>
					</div>
					<p>
						<a href="#">Back to top</a>
					</p>

				</div>



				<!-- Optional tstrap Bundle with Popper -->
				<script
					src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
					integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
					crossorigin="anonymous"></script>
			</div>
		</div>
	</footer>



</body>
</html>
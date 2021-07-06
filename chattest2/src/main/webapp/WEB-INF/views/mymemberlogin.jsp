<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- 네이버 로그인 -->
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"
	charset="utf-8"></script>

<!-- 구글 로그인 api-->
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="526188882166-3g6n3ua4795hsb0p3889lhg7f90hcfta.apps.googleusercontent.com">

<!-- 카카오 로그인 api -->
<script type="text/javascript" src="https://developers.kakao.com/sdk/js/kakao.js"></script>


<script type="text/javascript">
	$(function() {
		$("#loginChk").hide();
	});

	function login() {
		var userid = $("#memberid").val().trim();
		var password = $("#memberpw").val().trim();

		// 자바스크립트 오브젝트 리터럴 형태 뭔지 찾아보자.
		var loginVal = {
			"userid" : userid,
			"password" : password
		}
		if (userid == null || userid == "" || password == null
				|| password == "") {
			alert("ID 및 PW를 다시 확인해 주세요!");
		} else {
			$.ajax({
				type : "post",
				url : "ajaxlogin.do",
				data : JSON.stringify(loginVal), // ajax로 보내준다. 자바에는 JSON이라는 타입이 없다. 
				// 그런데 requestbody가 넘어가는 json형태의 값을 자바 오브젝트 형태로 바꿔준다
				contentType : "application/json", // 이거 없으면 415 에러뜬다. unsupported media type : 넘어오는 값이 잘못되었다.
				// 원래 String 타입으로 넘어가는데 contentType을 쓰면 json 타입이라고 알려주는 것. requestBody랑 세트라고 생각해도 무방하다.
				dataType : "json",
				success : function(msg) {
					if (msg.check == true) {
						alert("반갑습니다.");
						location.href="index.jsp";
					} else {
						$("#loginChk").show();
						$("#loginChk").html("ID 혹은 PW가 잘못되었습니다.");
					}
				},
				error : function() {
					alert("통신 실패");
				}
			});
		}

	}
	function kakaoLogin() {
		Kakao.Auth.login({
			success : function(response) {
				Kakao.API.request({
					url : '/v2/user/me',
					success : function(response) {
						kakaoLoginPro(response)
					},
					fail : function(error) {
						console.log(error)
					},
				})
			},
			fail : function(error) {
				console.log(error)
			},
		})
	}

	function onSignIn(googleUser) {
		var profile = googleUser.getBasicProfile();
		console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
		console.log('Name: ' + profile.getName());
		console.log('Image URL: ' + profile.getImageUrl());
		console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
		var userid = profile.getId();
		var username = profile.getName();
		var useremail = profile.getEmail();

		var googleVal = {
			"userid" : userid,
			"username" : username,
			"useremail" : useremail
		}

		$.ajax({
			type : "post",
			url : "googlelogin.do",
			data : JSON.stringify(googleVal),
			contentType : "application/json",
			dataType : "json",
			success : function(msg) {
				alert(msg.username + "님 환영합니다.");
			},
			error : function() {
				alert("통신 실패");
			}
		});

	}

	function logOut() {
		var auth2 = gapi.auth2.getAuthInstance();
		if (auth2 != null) {
			auth2.signOut().then(function() {
				console.log('로그아웃 되었습니다.');
				alert("로그아웃 되었습니다.");
				location.href = "logOut.do";
			});
		}
		
		if (Kakao.Auth.getAccessToken()) {
		      Kakao.API.request({
		        url: '/v1/user/unlink',
		        success: function (response) {
		        	console.log(response)
		        	alert("로그아웃 되었습니다.");
		        	location.href = "logOut.do";
		        },
		        fail: function (error) {
		          console.log(error)
		        },
		      })
		      Kakao.Auth.setAccessToken(undefined)
		    }
	}
	
	
	
	Kakao.init('1e4c72d72b9614055f4d89bb5623539d');
	Kakao.isInitialized();
	
	//카카오 로그인 부분
	function kakaoLogin() {
		Kakao.Auth.login({
		      success: function (response) {
		        Kakao.API.request({
		          url: '/v2/user/me',
		          success: function (response) {
		        	  console.log(response);
		        	  var userid = response.id;
		        	  var useremail = response.kakao_account.email;
		        	  var username = response.properties.nickname;
		        	  
		        	  var kakaologinVal = {
		        			"userid" : userid,
		        			"useremail" : useremail,
		        			"username" : username
		        	  };
		        	  
		        	  if(userid == null || userid == "") {
		        		  alert("아이디 비밀번호를 확인해 주세요.");
		        	  } else {
		        	  $.ajax({
		        		type:"post",
		        		url:"kakaologincallback.do",
		        		data:JSON.stringify(kakaologinVal),
		        		contentType:"application/json",
		        		dataType:"json",
		        		success:function(msg) {
		        			if(msg.username != null) {
		        				alert(msg.username+"님 환영합니다.");
		        				location.href="index.jsp";
		        			} else {
		        				alert("로그인에 실패하였습니다.");
		        			}
		        		},
		        		error : function() {
		        			alert("통신 실패");
		        		}
		        	  });
		        	 }
		        	  
		          },
		          fail: function (error) {
		            console.log(error)
		          },
		        })
		      },
		      fail: function (error) {
		        console.log(error)
		      },
		    })
	}
	
</script>
</head>
<body>

	<c:if test="${login == null}">
		<table>
			<tr>
				<th>ID</th>					<!-- inputEmail3 --> 
				<td><input type="text" id="memberid"></td>
			</tr>
			<tr>
				<th>PW</th>					<!-- inputPassword3 -->
				<td><input type="password" id="memberpw"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="login"
					onclick="login();"> 
					<input type="button" value="regist"
					onclick="location.href='registform.do'"></td>

			</tr>
			<tr>
				<td colspan="2" align="center" id="loginChk" style="color: red;"></td>
			</tr>
		</table>
		<a href="findidform.do" style="color: black; text-decoration: none;"
			id="find_id_btn">아이디찾기</a>
		<a href="findpwform.do" style="color: black; text-decoration: none;">비밀번호찾기</a>
		<br>
		<!-- 카카오 로그인 -->
		<a href="javascript:kakaoLogin();">
		<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="223" />
		</a>
		<br>
		
		
		<form action="naverlogin.do">
			<!-- 네이버 로그인 창으로 이동 -->
			<div id="naver_id_login">
				<a href="${naverUrl}"> <img width="223"
					src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" /></a>
			</div>
		</form>
		<div id="googleBtn" class="g-signin2" data-onsuccess="onSignIn"></div>
		<br>

		<button onclick="logOut();">로그아웃</button>








	</c:if>
	<c:if test="${login != null}">

		<div>환영합니다</div>
		<div>${login.userid}님환영합니다.</div>

	</c:if>


</body>
</html>








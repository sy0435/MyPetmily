<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css?v="<%=new java.util.Date().getTime() %>">
<style type="text/css">

.login {
	width: 100%;
	margin: 0 auto;
	padding: 100px 0 125px 0;
}

#userid, #userpwd {
	height: 40px;
	width: 300px;
	outline-color: pink;
	box-sizing: border-box;
	border: 1px solid #969696;
	border-radius: 5px;
	margin-top: 10px;
	padding: 0 20px;
	font-size: 16px;
	vertical-align: middle;
}

#find {
	display: block;
	margin: 20px 0px;
}

.btn-login, .btn-signin {
	width: 300px;
	height: 40px;
	box-sizing: border-box;
	border: 1px solid #000000;
	border-radius: 5px;
	box-shadow: 2px 2px 2px #969696;
}

.btn-login {
	background-color: #FFB4BE;
	color: white;
}

.btn-signin {
	margin-top: 10px;
}

.btn-login a {
	font-size: 14px;
	font-weight: bold;
	color: #333333;
}

.btn-signin a {
	font-size: 14px;
	font-weight: bold;
	color: #333333;
}

/* 클릭시 placeholder 문구 사라짐 */
input:focus::-webkit-input-placeholder,
textarea:focus::-webkit-input-placeholder{
	/*webkit browser */
	color: transparent;
}
input:focus:-moz-input-placeholder,
textarea:focus:-moz-input-placeholder{
	/* Mozila Firefox 4 to 18 */
	color: transparent;
}
input:focus::-moz-input-placeholder,
textarea:focus::-moz-input-placeholder{
	/* Mozila Firefox 19+ */
	color: transparent;
}
input:focus:-ms-input-placeholder,
textarea:focus:-ms-input-placeholder{
	/* Internet Explorer 10+ */
	color: transparent;
}

</style>
</head>
<body>

<%-- 
	<br>
		<c:choose>
			<c:when test="${sessionId != null}">
				<h2>네이버 아이디 로그인 성공하셨습니다!!</h2>
				<h3>'${sessionId}' 님 환영합니다!</h3>
				<h3>
					<a href="logout">로그아웃</a>
				</h3>
			</c:when>
			<c:otherwise>
			 --%>
			 
				<div class="login">
				
					<h2 style='color:#333333'>로그인</h2>
					
						<input type="text" id="userid" placeholder="아이디"/><br/>
						<input onkeypress="if(event.keyCode==13){go_login()}" type="password" id="userpwd" placeholder="비밀번호"/><br/>
						
						<span id="find"><a href="idFind" style="font-size:15px;"> 아이디 & 비밀번호 찾기 </a></span>
							
						<button class='btn-login'><a onclick="go_login()">로그인</a></button><br/>
						<button class='btn-signin'><a href="email.do">회원가입</a></button>
<%-- 		
				<!-- 네이버 로그인 창으로 이동 -->
				<div id="naver_id_login" style="text-align: center">
					<a href="${url}"> <img width="223"
						src="https://developers.naver.com/doc/review_201802/CK_bEFnWMeEBjXpQ5o8N_20180202_7aot50.png" /></a>
				</div>
				<br>
				 --%>
			</div>	
<%-- 				
			</c:otherwise>
		</c:choose>

	 --%>

 

<!-- <script type="text/javascript">
 
         var naver_id_login = new naver_id_login("5KDJ2AowQdWU418eNmvz", "http://localhost:8089/cteam/");    // Client ID, CallBack URL 삽입
                                            // 단 'localhost'가 포함된 CallBack URL
         var state = naver_id_login.getUniqState();
        
         naver_id_login.setButton("white", 4, 40);
         naver_id_login.setDomain("http://localhost:8089/cteam/loginPage");    //  URL
         naver_id_login.setState(state);
         naver_id_login.setPopup();
         naver_id_login.init_naver_id_login();
 
</script> -->

<script type = "text/javascript" src = "https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
<script type="text/javascript">
function go_login(){
	if($("#userid").val()==''){
		alert('아이디를 입력하세요');
		$("#userid").focus();
		return;
	}else if($('#userpwd').val()==''){
		alert('비밀번호를 입력하세요');
		return;
	}

	$.ajax({
		url: 'login',
		data: {userid:$("#userid").val(), userpwd:$("#userpwd").val()},
		success:function(data){
				if(data){
				//	alert("로그인성공");
					location.href='/cteam/';
				}else{
					alert("아이디나 비밀번호가 일치하지않습니다");
				}
			},	
		error:function(req,text){
			alert(text+':'+req.status);
		}

	});
}
</script>
</body>
</html>
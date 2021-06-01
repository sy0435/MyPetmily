<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">

<header style='background-color:white;' >
	<div style='margin:0 auto; padding:25px 0px 25px 0px; overflow:hidden;'>
	
		<!-- 헤더 윗줄 : 로고 & 로그인 -->
		<div style='width:100%; height:100px; float:left; margin-bottom:20px; '>
			<a href='<c:url value="/" />'><img src='img/cteam_logo1.png' width='250px' height='100px' style="margin-right: -7.8%">
			<a href="cart.bs"><img src="img/cart.png" style="float: right; width: 50px; height: 50px; margin-top:20px; margin-right: 4.6%;"></a>
		</div>

		<!-- 헤더 아랫줄 : 메뉴버튼 -->
		<div id='header_menu' style='width:100%; float:left; overflow:hidden;'>
			<a href="intro.co" style="line-height:60px; font-size:20px; color:#222222;"><div class='header_menu'>회사소개</div></a>
			<a href="list.no"><div class='header_menu'>공지사항</div></a>
			<a href="list.sh" ${category eq 'sh' ? 'class="active"' : '' } ><div class='header_menu'>상품페이지</div></a>
			<a href="list.bo" ${category eq 'bo' ? 'class="active"' : '' } ><div class='header_menu'>커뮤니티</div></a>
			<a href="list.qn" ${category eq 'qn' ? 'class="active"' : '' } ><div class='header_menu'>Q&A</div></a>	
			<a href="list.mp"><div class='header_menu'>내펫정보</div></a>
			<div class='header_menu1'>
			

				<c:if test="${!empty login_info && login_info.member_id ne 'admin'}">
					<a href="list.my"  ${category eq 'my' ? 'class="active"' : '' }><div class='header_menu2'>마이페이지</div></a>
					<a onclick="go_logout()"><div class='header_menu2'>로그아웃</div></a>
				</c:if>
				<c:if test="${login_info.member_id eq 'admin' }">
					<a href="admin.ad"><div class='header_menu2'>관리자페이지</div></a>
					<a onclick="go_logout()"><div class='header_menu2'>로그아웃</div></a>
				</c:if>
				<c:if test="${empty login_info}">
					<a href="loginPage"><div class='header_menu2'>로그인</div></a>
					<a href="email.do"><div class='header_menu2'>회원가입</div></a>
				</c:if>
				
			</div>
		</div>
	
	</div>
</header>
	
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script>
		function go_logout(){
 
			var newURL = window.location.pathname;
			//alert(newURL);
			if(newURL=="/cteam/list.my"){
				location.href='logout_home';
// 				$.ajax({
// 					url:'logout_home',
// 					success: function(){
// 						location.reload();		
// 					}
					
// 				});
				
			}else{
 
				$.ajax({
					url:'logout',
					success:function(){
						alert("로그아웃되었습니다");
						location.reload();},
					error:function(req,text){
						alert(text+":"+req.status);
					}
					
				});
				

			}
				
		}
		
	</script>
	
	<script type="text/javascript">
        var naver_id_login = new naver_id_login("5KDJ2AowQdWU418eNmvz", "http://localhost:8089/cteam/callback"); // 역시 마찬가지로 'localhost'가 포함된 CallBack URL
        
        // 접근 토큰 값 출력
        alert(naver_id_login.oauthParams.access_token);
        
        // 네이버 사용자 프로필 조회
        naver_id_login.get_naver_userprofile("naverSignInCallback()");
        
        // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
        function naverSignInCallback() {
            alert(naver_id_login.getProfileData('email'));
            alert(naver_id_login.getProfileData('age'));
        }
    </script>
	
	<style type="text/css">
		.header_menu, .header_menu1 { 
			width:14.28%;
			height: 60px;
			float: left;
		}
		.header_menu2 {
			width: 100%;
			height: 30px;
			margin: 0 auto;
		}
		#header_menu a:not(:last-child),
		#header_menu a:not(:nth-child(1))
		 {
			line-height: 60px;
			font-size: 20px;
			color: #222222;
		}
		#header_menu a:last-child,
		#header_menu a:nth-child(1)  {
			line-height: 30px;
			font-size: 18px;
			color: #222222;
		}
		.header_menu:hover, .header_menu2:hover { 
			background-color: #666666;
			color: #FFFFFF;
		}
		
	/*
		header ul, header ul li { margin: 0; padding: 0; }
		header div.category{font-size:18px;}
		header div.category li:not(:first-child) {padding-left:30px;}
		header div.category li a:hover, header div.category li a.active{ font-weight:bold; color:pink; } 
	
		header li input{ display:block; }
		#userid, #userpwd{width:100px; height: 20px;}
	*/
	</style>

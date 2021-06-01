<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<header style="border-bottom: 1px solid navy; padding: 15px 0; text-align: left;" >
	<div style="margin-left: 100px" class='category' >
	<ul>
		<li><a href='<c:url value="/"/>'><img src='img/hanul.logo.png' alt='홈으로' /></a></li>	
		<li><a href="list.cu" ${category eq 'cu' ? 'class="active"' : '' }>고객관리</a></li>	
		<li><a href="list.no" ${category eq 'no' ? 'class="active"' : '' }>공지사항</a></li>	
		<li><a href="list.bo" ${category eq 'bo' ? 'class="active"' : '' }>방명록</a></li>	
		<li><a href="list.da" ${category eq 'da' ? 'class="active"' : '' }>공공데이터</a></li>
	</ul>
	</div>
	
	<div style="position: absolute; right: 0; top: 25px; margin-right: 100px">
	<c:if test="${!empty login_info }">
	<ul>
		<li>${login_info.name} [${login_info.id}]</li>
		<li><a class="btn-fill" onclick="go_logout()">로그아웃</a></li>
	</ul>
	</c:if>
	
	<c:if test="${empty login_info }">
	<ul>
		<li>
			<span style="position: absolute; top: -14px; left: -120px;">
			<input type="text" id="userid" placeholder="아이디" />
			<input onkeypress="if(event.keyCode==13){go_login()}" type="password" id="userpwd" placeholder="비밀번호" />
			</span>
		</li>
		<li>
			<li><a class="btn-fill" onclick="go_login()">로그인</a></li>
			<li><a class="btn-empty" href="member">회원가입</a></li>
		</li>
	</ul>
	</c:if>
	</div>
	</header>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script>
	function go_logout(){

		$.ajax({
			url : 'logout',
			success: function(){
				//	alert("로그아웃 성공");
					location.reload();},
			error: function(req, text){
					alert(text+':'+req.status);
			}
					
		});
		}
	
	function go_login(){
		if($("#userid").val() == ''){
				alert('아이디를 입력하세요');
				$("#userid").focus();
				return;
			}else if($('#userpwd').val() == ''){
				alert('비밀번호를 입력하세요');
				return;
			}

			$.ajax({
				url : 'login',
				data : {userid: $("#userid").val(), userpwd: $('#userpwd').val() },
				success: function(data){
					if(data){
					//alert("로그인성공");
					location.reload();
					
				}else{		
					alert("아이디나 비밀번호가 일치하지 않습니다.");
				}
				}, 
				error: function(req, text){
						alert(text+':'+req.status);
				}
						
			});

		 }
	</script>
	<style type="text/css">
	header ul, header ul li {   margin: 0;   padding: 0;   display: inline;}
	header div.category{   font-size: 18px;}
	header div.category li:not(:first-child){   padding-left: 30px;}
	header div.category li a:hover, header div.category li a.active { font-weight: bold; color: #ff9500;}
	header li input{display: block;}
	#userid, #userpwd {width: 100px; height: }
	</style>

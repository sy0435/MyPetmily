<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/common.css?v="<%=new java.util.Date().getTime()%>">

</head>
<body>

<div style="margin:100px auto; font-size: 24px; text-align:center; color: #333333; height:50px; line-height:25px;"> 입력한 이메일로 발송된 인증번호를 입력하세요. <br/>
(인증번호가 일치해야 회원가입으로 넘어가실 수 있습니다.) </div>

<form action="join_injeung.do${dice}" method="post">
<input type="hidden" name="tomail" value="${tomail }">

<div style="align:center; margin: 50px auto;">
<input style="width:30%; height: 35px; margin: 0 auto; padding: 0 5px; border: 1px solid #96969650; box-sizing: border-box; border-radius: 3px; 
	   outline-color:#FFB4BE;" type="number" name="email_injeung" placeholder="인증번호를 입력하세요.">
</div>

<div style="align:center; margin: 50px auto;">
<button style="border: 1px solid #333333; box-sizing: border-box; border-radius: 3px; background-color: #96969650; font-size: 15px;
		width: 30%; height: 35px; color: #333333; cursor: pointer; outline-color:#FFB4BE; font-weight: bold;"
		type="submit" name="submit">인증번호 전송</button>
</div>

<div style="height:100px;"></div>
								
</form>

</body>
</html>

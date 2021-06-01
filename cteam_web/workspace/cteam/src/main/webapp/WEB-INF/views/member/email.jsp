<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
-->
<link rel="stylesheet" type="text/css" href="css/common.css?v="<%=new java.util.Date().getTime() %>">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">

</script>

</head>
<body>

<div style="margin:100px auto; font-size: 24px; text-align:center; color: #333333; height:50px; line-height:50px;"> 이메일을 인증 받아야 회원가입 단계로 넘어갈 수 있습니다. </div>

<form action="auth.do" method="post">

<div style="align:center; margin: 50px auto;">
<input style="width:30%; height: 35px; margin: 0 auto; padding: 0 5px; border: 1px solid #96969650; box-sizing: border-box; border-radius: 3px; 
	   outline-color:#FFB4BE;" type="email" name="e_mail" placeholder="이메일주소를 입력하세요.">
</div>

<div style="align:center; margin: 50px auto;">
<button style="border: 1px solid #333333; box-sizing: border-box; border-radius: 3px; background-color: #96969650; font-size: 15px;
		width: 30%; height: 35px; color: #333333; cursor: pointer; outline-color:#FFB4BE; font-weight: bold;"
		type="submit" name="submit">인증번호 발송</button>
</div>

<div style="height:100px;"></div>

</form>

</body>
</html>
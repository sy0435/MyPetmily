<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.btn1{
	background-color: pink;	
	color:#333;
	font-weight: bold;
}

.btn2{
	background-color: white;	
	color: pink;
	font-weight: bold;
}
.btn1, .btn2{
	padding: 10px 30px;
	width:200px;
	border-radius: 5px;
	font-size: 20px;
	box-shadow: 2px 2px 3px #022d72;
}
#div1 {
	margin: 0 auto;
	width: 80%;
	height: 800px;
	padding-top: 50px;
}
#div1 table tr th {
	height: 50px;
}

#div1 {
	width: 60%;
}
input {
	width: 80%;
}
</style>
</head>
<body>
<h2>비밀번호 확인</h2>
	<div id="div1" align="center">
		<table style="margin-bottom: 30px; width: 80%;" >
			<tr>
				<th style="background-color: #FBD4EA; font-size: 1.2em; font-weight: bold;" colspan="2">비밀번호를 확인해주세요</th>
			</tr>
			<tr>
				<th style="background-color: #EBFFD3; color: #2C472B;">비밀번호 입력</th>
				<td style="background-color: #F6FFEC; height: 35px;">
					<input type="password" id="user_pw" style="border: 1px solid #C5C7CC; width: 90%; margin-left: 20px;"
					placeholder="영문, 숫자, 특수문자를 모두 포함 8-12자"/>
				</td>
			</tr>
			</table>
	<div>
		<a class="btn1" onclick="go_detail()">확인</a>
		<a class="btn2" href="javascript:history.go(-1)">취소</a>
	</div>
	</div>
</body>
<script type="text/javascript" src="js/need_check.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	var member_pw = '${login_info.member_pw}';

	 
	 function go_detail(){
		if($('#user_pw').val() == member_pw){
			location.href='${pageContext.request.contextPath}/mypageDetail.my';
			
		}else{
			alert('비밀번호를 다시 입력하세요!');
		}
	}  
</script>
</html>
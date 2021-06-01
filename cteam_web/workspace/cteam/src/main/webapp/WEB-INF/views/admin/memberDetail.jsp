<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 상세보기</title>
<style type="text/css">
 table {
	 border-collapse: collapse;
	 margin-bottom: 50px;
	 margin-top: 10px;
	 text-align: center;
	 border: 3px solid #FFB4BE;
	}
	
	table td {
	 border-bottom: 1px solid #FFB4BE;
	 padding: 10px;	 
	}
	
	table th {
	 background-color: #D7567F;
	 color: white;
	}
</style>
</head>
<body>
<div align="center">
<table style="width: 50%; margin-top: 50px;">
	<tr>
		<th id="member_id">아이디</th>
		<td>${list.member_id }</td>
	</tr>
	<tr>
		<th>이름</th>
		<td id="member_name">${list.member_name }</td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td>${list.member_pw }</td>
	</tr>
	<tr>
		<th>핸드폰번호</th>
		<td>${list.member_phonenum }</td>
	</tr>
	<tr>
		<th>비밀번호 찾기 질문</th>
		<td>${list.member_question }</td>
	</tr>
	<tr>
		<th>비밀번호 찾기 답변</th>
		<td>${list.member_answer }</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td>${list.member_email }</td>
	</tr>
	<tr>
		<th>주소</th>
		<td>${list.member_post }<br/>
		${list.member_address }<br/>${list.member_address2 }</td>
	</tr>
	</table>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<style type="text/css">
	div {
		margin: 0 auto;
		width: 90%;
	}
	
	table {
	 width: 100%;
	 border-collapse: collapse;
	 margin-bottom: 50px;
	 margin-top: 10px;
	 text-align: center;
	 border: 3px solid #FFB4BE;
	}
	
	table td {
	 border-bottom: 1px solid #FFB4BE;
	 padding-bottom: 5px;
	 padding-top: 5px;	 
	}
	
	table th {
	 padding: 10px;
	 background-color: #D7567F;
	 color: white;
	}
	a:hover {
		color: #D7567F;
		font-weight: bold;
	}
	
	td {
		padding-left: 5px;
	}
</style>
</head>
<body>
<h2>회원관리</h2>
<form action="memberList.ad" method="post">
<input type='hidden' name='curPage' value='1' />
<div align="center" id="data-list">
	<table>
		<tr>
			<th>아이디</th>
			<th>성명</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>주소</th>
			<th>우편번호</th>		
		</tr>
	<c:forEach items="${page.list }" var="list"> 
		
		<tr>
			<td style="width:100px; background-color:#FFF3EC;"><a href="memberDetail.ad?member_id=${list.member_id }">${list.member_id }</a></td>
			<td style="width:100px; background-color:#F7F9FF; ">${list.member_name }</td>
			<td>${list.member_phonenum }</td>
			<td>${list.member_email }</td>
			<td>${list.member_address }<br/>${list.member_address2 }</td>
			<td style="background-color: #FBFFEA;">${list.member_post }</td>

		</tr>
		
	</c:forEach>
	</table>
</div>
</form>
<!-- 페이징처리 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; height:30px; margin:0 auto; '>
			<jsp:include page="/WEB-INF/views/include/page2.jsp" />
		</div>
	</div>
</body>
</html>
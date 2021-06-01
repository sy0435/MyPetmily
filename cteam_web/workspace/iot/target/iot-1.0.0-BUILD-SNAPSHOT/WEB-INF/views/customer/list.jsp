<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
</head>
<body>
<div align="center">
<h3>[고객목록]</h3>
<table border="1" class='w-pct60'>
<tr>
	<th class ='w-px40'>번호</th>
	<th class ='w=px80'>성명</th>
	<th class ='w=px80'>이메일</th>
	<th class ='w=px80'>전화번호</th>
</tr>
<c:forEach items="${list }" var="vo">
<tr>
 <td>${vo.no }</td>
 <td><a href="detail.cu?id=${vo.id }">${vo.name }</a></td>
 <td>${vo.email }</td>
 <td>${vo.phone }</td>
</tr>
</c:forEach>
</table><br />
<a class='btn-fill' href="new.cu">신규고객</a>
</div>
<br />
</body>
</html>
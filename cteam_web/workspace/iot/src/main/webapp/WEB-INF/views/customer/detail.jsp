<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
</head>
<body>
<div class="content">
	<h3>[${vo.name} 고객상세정보]</h3>
	<table class="w-pct50">
		<tr>
			<th class ='w-px40'>성명</th>
			<td>${vo.name }</td>
		</tr>
		<tr>
			<th class ='w-px40'>성별</th>
			<td>${vo.gender }</td>
		</tr>
		<tr>
			<th class ='w-px40'>이메일</th>
			<td>${vo.email }</td>
		</tr>
		<tr>
			<th class ='w-px40'>전화번호</th>
			<td>${vo.phone }</td>
		</tr>
	</table>
	<div class='btnSet'>
		<a class = "btn-fill" href="list.cu">고객목록</a>
		<a class = "btn-fill" href="new.cu">신규등록</a>
		<a class = "btn-fill" href="modify.cu?id=${vo.id }">수정</a>
		<a class = "btn-fill" onclick ="javascript:if(confirm('정말삭제하시겠습니까?')){href='delete.cu?id=${vo.id }'}">삭제</a>
	</div><br/>
</div>
</body>
</html>
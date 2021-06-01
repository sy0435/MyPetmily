<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규고객</title>
</head>
<body>
<div>
<h3>[신규고객 등록]</h3>
<form action="insert.cu" method="post">
<table>
	<tr>
		<th>성명</th>
		<td><input type="text" name = "name" required="required"/></td>
	</tr>
		<tr>
		<th>성별</th>
		<td>
			<label><input type="radio" name = "gender" checked="checked" value="여"/>여자</label>	
			<label><input type="radio" name = "gender" value="남"/>남자</label>	
		</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" name = "email"  required="required"/></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td><input type="text" name = "phone"  required="required"/></td>
	</tr>
</table><br>
</form>
<div class="btnSet">
<a class="btn-fill" onclick="$('form').submit()">저장</a>
<a class="btn-empty" href='list.cu' />취소</a>
</div>
</div>
</body>
</html>
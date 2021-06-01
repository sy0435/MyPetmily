<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="content">
	<h3>[${vo.name} 고객상세정보]</h3>
	<form method="post" action='update.cu'>
	<input type="hidden" name="id" value="${vo.id }" />
			<table class='w-pct70'>
			<tr>
				<th>성명</th>
				<td><input type="text" name="name" value="${vo.name }" /></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><label><input ${vo.gender eq '여' ? 'checked' : '' } type="radio" name="gender" value="여"/>여자</label>
				<label><input ${vo.gender eq '남' ? 'checked' : '' } type="radio" name="gender" value="남"/>남자</label></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email" value="${vo.email }" /></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone" value="${vo.phone }" /></td>
			</tr>
		</table>
		<div class="btnSet">
			<a class="btn-fill" onclick="$('form').submit()">수정저장</a>
			<a class="btn-empty" href="detail.cu?id=${vo.id}">수정취소</a>
		</div>
	</form>
</div>
</body>
</html>
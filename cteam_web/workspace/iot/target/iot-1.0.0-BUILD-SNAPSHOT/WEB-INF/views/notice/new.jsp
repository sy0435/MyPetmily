<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>[공지글 쓰기]</h3>
<!-- 
파일전송시
1. form 의 전송방식은 반드시 post로 지정한다.
2. form enctype을 지정한다. enctype="multipart/form-data" 
-->
<form action="insert.no" method="post" enctype="multipart/form-data">
<table>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" title="제목" class="need"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" class="need" title="내용"></textarea></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class="left">
		<label>
		<input type="file" id="attach-file" name="file"/>
		<img alt="파일선택" class="file-img" src="img/select.png">
		</label>
		<span id="file-name"></span>
		<span id="delete-file" style="color: red;">
			<i class="far fa-calendar-times font-img"></i></span>
		</td>
	</tr>
</table>
<br/>
</form>
<div>
	<a class="btn-fill" onclick="if(necessary()){$('form').submit()}">저장</a>
	<a class="btn-empty">취소</a>
</div>
<script type="text/javascript" src="js/need_check.js"></script>
<script type="text/javascript" src="js/file_attach.js"></script>
<br/>
</body>
</html>
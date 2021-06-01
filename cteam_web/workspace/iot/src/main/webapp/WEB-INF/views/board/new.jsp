<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 글쓰기</title>
</head>
<body>
<h3>[방명록 글쓰기]</h3>
<form method="post" action="insert.bo" enctype="multipart/form-data">
<table>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" class="need" title="제목"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" class="need" title=" 내용"></textarea></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class="left">
			<label>
				<input type="file" name="file" id="attach-file"/>
				<img src="img/select.png" class="file-img">
			</label>
			<span id="file-name"></span>
			<span id="preview"></span>
			<span id="delete-file" style="color: red;"><i class="far fa-calendar-times font-img"></i></span>
		</td>
	</tr>
</table>
</form>
<div class="btnSet">
	<a class="btn-fill" onclick="if(necessary()){$('form').submit()}">저장</a>
	<a class="btn-empty" href="list.bo">취소</a>
</div>

<script type="text/javascript" src="js/iamge_preview.js"></script>
<script type="text/javascript" src="js/need_check.js"></script>
<script type="text/javascript" src="js/file_attach.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>[방명록 수정]</h3>
<form action="update.bo" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${vo.id }" />
<input type="hidden" name="attach" />

<table>
	<tr>
		<th>제목</th>
		<td><input type="text"	name="title" value="${vo.title }" class="need" title="제목"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="content" class="need" title="내용">${vo.content }</textarea></td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td class="left">
			<label>
				<input type="file" name="file" id="attach-file"/>
				<img src="img/select.png" class="file-img" />
			</label>
			<span id="file-name">${vo.filename }</span>
			<span id="preview"></span>
			<span id="delete-file" style="color:red">
				<i class="far fa-calendar-times font-img"></i></span>			
		</td>
	</tr>
</table>
</form>

<div class="btnSet">
<script type="text/javascript" src="js/need_check.js"></script>
<script type="text/javascript" src="js/file_attach.js"></script>
<script type="text/javascript" src="js/image_preview.js"></script>
<a class="btn-fill" onclick="if( necessary()){$('[name=attach]').val($('#file-name').text());  $('form').submit();}">저장</a>
<a class="btn-empty" href="javascript:history.go(-1)">취소</a>
</div>
<script type="text/javascript">
if( ${!empty vo.filename}){
	$('#delete-file').css('display','inline');
if(isImage('${vo.filename}')){
	//filepath : /upload/board/2020/09/25/abc.png
	var filepath = '${vo.filepath}'.substring(1);
	var img = "<img src='"+filepath+"' class='file-img' id='preview-img'/>";
	$('#preview').html(img)
	}	
}


</script>

</body>
</html>
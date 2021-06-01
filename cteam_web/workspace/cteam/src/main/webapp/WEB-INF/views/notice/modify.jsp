<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
textarea[name=content] { width:calc(100% - 6px); 
	resize:none; height:150px;  }
	
table {
	width: 80%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
	margin-bottom: 30px;
}

.left{text-align: left;}
.right {text-align: right; }

#list-top { padding:20px 10%; } 
#list-top div { width:100%; height:32px; }
#list-top div ul { margin:0; display:flex; padding:0  }
#list-top div ul:first-child { float:left; }
#list-top div ul:last-child { float:right; }
#list-top div ul li * { vertical-align:middle; } 
#list-top div ul li:not(:first-child) { margin-left:2px; } 

table th, table td {
	border: 1px solid black;
	padding: 5px 10px;
}

table th {
	background-color: #FFB4BE;
}

select { height:32px; }

button {
	height: 35px;
	background-color: #FFB4BE;
	border: 1px groove #333333;
 	box-shadow: 2px 2px 2px #969696;
}

button a {
	display: block;
	color: #111111;
	font-weight: bold;
}

.cancel, .submit {
	width: 75px;
	height: 30px;
	font-weight: bold;
	font-size: 15px;
	cursor: pointer;
}

</style>
</head>
<body>

<form action="update.no" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value="${vo.id }" />
<input type="hidden" name="attach" />
<table>
	<tr>
		<th>제목</th>
		<td><input type="text"	name="title" value="${vo.title }" class="need" title="제목" style="width: calc(100% - 14px);"/></td>
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
			<span id="delete-file" style="color:red">
				<i class="far fa-calendar-times font-img"></i></span>			
		</td>
	</tr>
</table>
</form>
<div class="btnSet">
<script type="text/javascript" src="js/need_check.js"></script>
<script type="text/javascript" src="js/file_attach.js"></script>
<button class='cancel'><a href="javascript:history.go(-1)">취소</a></button>
<button class='submit' onclick="if( necessary()){$('[name=attach]').val($('#file-name').text());  $('form').submit();}">저장</button>
</div>
<br/>
<script type="text/javascript">
if( ${!empty vo.filename}){
	$('#delete-file').css('display','inline');
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<form action="reply_insert.no" method="post" enctype="multipart/form-data">
<input type="hidden" name="root" value="${vo.root }"/>
<input type="hidden" name="indent" value="${vo.indent }"/>
<input type="hidden" name="step" value="${vo.step }"/>

<table>
	<tr>
		<th>제목</th>
		<td><input type="text" name="title" title="제목" class="need" style="width: calc(100% - 14px);"/></td>
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
	<button class='cancel'><a href="javascript:history.go(-1)">취소</a></button>
	<button class='submit'><a onclick="if(necessary()){$('form').submit()}">저장</a></button>
</div>
<script type="text/javascript" src="js/need_check.js"></script>
<script type="text/javascript" src="js/file_attach.js"></script>
<br/>
</body>
</html>
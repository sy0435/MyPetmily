<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 쓴 글 / 댓글</title>
<style type="text/css">
#board, #comment {
  margin-top: 30px;
}

#comment {
	display: none;
}

table {
	width: 60%;
	border: 1px solid pink;
	border-collapse: collapse;
}
table td {
text-align: center;
height: 30px;
border: 1px solid pink;

}

.btn {
 width: 200px;
 height: 50px;
 background-color: #FFF7BB;
 border-radius: 10px;
 border: 5px solid #E5A8CC;
 margin: 10px 15px;
 font-size: 1.2em;
 color: #B365B6;
 font-weight: bold;
}
.click{
 width: 200px;
 height: 50px;
 background-color: #C33488;
 border-radius: 10px;
 border: 5px solid #E5A8CC;
 margin: 10px 15px;
 font-size: 1.2em;
 color: #FFFFFF;
 font-weight: bold;
}

</style>
</head>
<body>
<div>
	<button type="button" class="btn" id="boardBtn">내가 쓴 글</button>
	<button type="button" class="btn" id="commentBtn">내가 쓴 댓글</button>
</div>
<div id="board" align="center"> 
<h2>내 글보기</h2>
	<table>
		<tr style="background-color: #F2B2D9; height: 40px;">
			<th style="width: 10%;">게시판</th>
			<th>글 제목</th>
			<th style="width: 20%;">작성일</th>
		</tr>
		<c:forEach items="${board }" var="board">
		<tr>
			<td>${board.board_subject }</td>
			<td>${board.board_title }</td>
			<td>${board.board_date }</td>
		</tr>
		</c:forEach>
	</table>
</div>

<div id="comment" align="center"> 
<h2>내 댓글 보기</h2>
	<table>
		<tr style="background-color: #F2B2D9; height: 40px;">
			<th>내용</th>
			<th>작성일</th>
		</tr>
		<c:forEach items="${comment }" var="comment">
		<tr>
			<td>${comment.content }</td>
			<td>${comment.writedate }</td>
		</tr>
		</c:forEach>
	</table>
</div>
<script type="text/javascript">
	$("#commentBtn").click(function(){
		$('#comment').show();
		$('#board').hide();

		$("#commentBtn").addClass("click");
		$("#boardBtn").removeClass("click");
		});
	$("#boardBtn").click(function(){
		$('#comment').hide();
		$('#board').show();
		$("#boardBtn").addClass("click");
		$("#commentBtn").removeClass("click");
		});
</script>
</body>
</html>
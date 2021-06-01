<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티<</title>

<style type="text/css">
/* button {
	height: 25px;
	background-color: #FFB4BE;
	border: 1px groove #333333;
 	box-shadow: 2px 2px 2px #969696;
}

button a {
	display: block;
	color: #111111;
	font-weight: bold;
} */

table {
	margin: 0 auto;
	border: 1px solid #000000;
}

table th, table td {
	margin: 0 auto;
	height: 25px;
	border: 1px solid #333333;
	color: #111111;
}

table th {
	background-color: #FFB4BE;
}

.modify, .delete {
	width: 75px;
	height: 30px;
}

#comment_writer {
	display: block;
	width: 10%;
	height: 30px;
	line-height: 30px;
	vertical-align: middle;
	float: left;
	font-weight: bold;
	font-size: 15px;
	color: #111111;
}

#comment_write {
	display: block;
	width: 80%;
	height: 30px;
	line-height: 28px;
	box-sizing: border-box;
	border: 1px solid #969696;
	border-radius: 5px;
	vertical-align: middle;
	float: left;
	color: #111111;
}

.comment_space {
	display: block;
	width: 5%;
	height: 30px;
	float: left;
}

.comment_space_1 {
	display: block;
	width: 1%;
	height: 30px;
	float: left;
}

#comment_enter {
	display: block;
	width: 8%;
	height: 30px;
	line-height: 28px;
	box-sizing: border-box;
	border: 1px solid #969696;
	border-radius: 5px;
	float: left;
	font-weight: bold;
	font-size: 15px;
	color: #333333;
	vertical-align: middle;
	background-color: white;
	cursor: pointer;
}

/* 클릭시 placeholder 문구 사라짐 */
input:focus::-webkit-input-placeholder,
textarea:focus::-webkit-input-placeholder{
	/*webkit browser */
	color: transparent;
}
input:focus:-moz-input-placeholder,
textarea:focus:-moz-input-placeholder{
	/* Mozila Firefox 4 to 18 */
	color: transparent;
}
input:focus::-moz-input-placeholder,
textarea:focus::-moz-input-placeholder{
	/* Mozila Firefox 19+ */
	color: transparent;
}
input:focus:-ms-input-placeholder,
textarea:focus:-ms-input-placeholder{
	/* Internet Explorer 10+ */
	color: transparent;
}

</style>

</head>
<body>

<form method="post" action="list.bo">
<input type="hidden" name="board_num" value="${vo.board_num}">
<input type="hidden" name="curPage" value="${page.curPage }">
<input type="hidden" name="search" value="${page.search }">
<input type="hidden" name="keyword" value="${page.keyword }">
<input type="hidden" name="pageList" value="${page.pageList }">

	<!-- 상단 메뉴 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; margin:0 auto;'>
		<!-- 글 작성자 : 수정, 삭제 가능, 관리자 : 삭제 가능 -->
		<c:choose>
			<c:when test='${login_info.member_id eq vo.member_id}'>
				<span style='display:block; background-color:#FFFFFF; height:35px;'>
					<span style='float:right; margin-top:2.5px; margin-right:2px;'>
						<button class='modify' style='margin-right:5px;'>
							<a style='color:#111111; font-size:15px;'
							   onclick='$("form").attr("action", "modify.bo"); $("form").submit()'>수정</a>
						</button>
						<button class='delete'>
							<a style='color:#111111; font-size:15px;' 
							   onclick='if(confirm("정말 삭제하시겠습니까?")){$("form").attr("action", "delete.bo"); $("form").submit()}'>삭제</a>
						</button>
					</span>
				</span>
			</c:when>
			<c:when test='${login_info.member_id ne vo.member_id && login_info.member_id eq "admin"}'>
				<span style='display:block; background-color:#FFFFFF; height:35px;'>
					<span style='float:right; margin-top:2.5px; margin-right:2px;'>
					<button class='delete'>
						<a style='color:#111111; font-size:15px;' 
						   onclick='if(confirm("정말 삭제하시겠습니까?")){$("form").attr("action", "delete.bo"); $("form").submit()}'>삭제</a>
					</button>
					</span>
				</span>
			</c:when>
			<c:otherwise>
				<span style='display:block; background-color:#FFB4BE; height:35px;'>
				</span>
			</c:otherwise>
		</c:choose>
		</div>
	</div>

	<!-- 게시글 상세보기 -->
	<div style='width:100%;'>
		<table style='width:90%;'>
			<tr>
				<th class='w-px100'>제목</th>
				<td colspan='7'>
					<span style='float:left; margin-left:10px'>${vo.board_title}</span>
				</td>
			</tr>
			<tr>
				<th class='w-px100'>작성자</th>
				<td class='w-px150'>
					<span style='float:left; margin-left:10px'>${vo.member_id}</span>
				</td>
				<th class='w-px100'>작성일자</th>
				<td class='w-px200'>${vo.board_date}</td>
				<th class='w-px100'>게시판</th>
				<td class='w-px100'>${vo.board_subject}</td>
				<th class='w-px100'>지역</th>
				<td class='w-px200'>${vo.board_city} ${vo.board_region}</td>
			</tr>
			<tr>
				<th class='w-px100' style='height:388px;'>내용</th>
				<td colspan='5'>
					<span style='float:left; margin:10px'>${vo.board_content}</span>
				</td>
				<th class='w-px100'>첨부사진</th>
				<td>
					<c:set var="imgpath" value="${vo.board_imagepath }"/> 
					<c:choose>
						<c:when test="${fn:contains(imgpath, 'http')}">
							<img src="${vo.board_imagepath}" width="90%" />
						</c:when>
						<c:when test="${imgpath eq null}"></c:when>
						<c:when test="${fn:contains(imgpath, 'null')}"></c:when>
						<c:otherwise>
							<img src="<c:url value='/' />${vo.board_imagepath}" width="90%"/>
						</c:otherwise>
					</c:choose>	
				</td>
			</tr>
		</table>
	</div>
	
</form>

	<div id='comment_list'></div>
	
	<!-- 댓글 작성 -->
	<div style='width:100%; height:30px; margin-top:10px;'>
		<div style='width:90%; height:100%; margin:0 auto;'>
			<span id='comment_writer'>${login_info.member_id}</span>
			<input type='text' id='comment_write' placeholder="이곳에 댓글을 입력하세요"/>
			<span class='comment_space_1'></span>
			<button id='comment_enter' onclick='comment_regist()'>확인</button>
			<span class='comment_space_1'></span>
		</div>
	</div>
	
	<!-- 버튼 : 목록으로 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; height:30px; margin:0 auto; background-color: #FFB4BE;'>
			<span style='display:block; background-color:#FFFFFF; width:200px; height:35px; margin:0 auto;'>
			<button style='height:30px; width:100px; font-size:15px;'><a onclick='$("form").submit()'>목록으로</a></button>
			</span>
		</div>
	</div>


<script type="text/javascript">
comment_list();
function comment_list(){
	$.ajax({
		url : 'community/comment/${vo.board_num}',
		success: function(data){
			$('#comment_list').html(data);
		},
		error: function(req, text){
			alert(text+':'+req.status);
		}
	});
}

function comment_regist(){
	if( ${empty login_info} ){
		alert('댓글을 등록하려면 로그인하세요');
		return;
	} else if( $('#comment_write').val() == '' ){
		alert('댓글을 입력하세요!');
		$('#comment_write').focus();
		return;
	}

	$.ajax({
		url: 'community/comment/regist',
		data: { content:$('#comment_write').val(), board_num:${vo.board_num} },
		success: function(data){
			if( data == 1 ){
				alert('댓글이 등록되었습니다');
				$('#comment_write').val('');
				comment_list();
			}
		},error: function(req, text){
			alert(text+':'+req.status);
		}
	});
}

</script>

</body>
</html>
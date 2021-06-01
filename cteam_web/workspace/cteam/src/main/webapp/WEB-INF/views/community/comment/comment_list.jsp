<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>

<style type="text/css">

button {
	height: 25px;
	background-color: #FFB4BE;
	border: 1px groove #333333;
 	box-shadow: 2px 2px 2px #969696;
}

button a {
	display: block;
	color: #111111;
	font-weight: bold;
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
	overflow:hidden;
}

#comment_content {
	display: block;
	width: 70%;
	height: 30px;
	line-height: 30px;
	vertical-align: middle;
	float: left;
	text-align: left;
	color: #111111;
	overflow:hidden;
}

#comment_content_modify {
	display: none;
	width: 70%;
	height: 30px;
	line-height: 28px;
	box-sizing: border-box;
	border: 1px solid #969696;
	border-radius: 5px;
	vertical-align: middle;
	float: left;
	text-align: left;
	color: #111111;
	overflow:hidden;
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

.comment_click {
	display: block;
	width: 4%;
	height: 30px;
	line-height: 30px;
	float: left;
	font-weight: bold;
	font-size: 13px;
	color: #333333;
	vertical-align: middle;
	overflow:hidden;
}

#comment_date {
	display: block;
	width: 10%;
	height: 30px;
	line-height: 30px;
	vertical-align: middle;
	float: right;
	font-size: 13px;
	color: #111111;
	overflow:hidden;
}

</style>

</head>
<body>

<c:forEach items="${list}" var="vo">
	<div style='width:100%; height:30px; margin-top:10px;'>
		<div style='width:90%; height:100%; margin:0 auto; background-color:#FFB4BE50' data-comment_num='${vo.comment_num}' >
		
			<span id='comment_writer'>${vo.member_id}</span>
			
			<div id='comment_content'>${fn:replace(fn:replace( vo.content, lf, '<br>' ), crlf, '<br>')}</div>
			<input type='text' id='comment_content_modify' value='${vo.content}'>
			
			<!-- 글 작성자 : 수정, 삭제 가능, 관리자 : 삭제 가능 -->
			<c:choose>
				<c:when test='${login_info.member_id eq vo.member_id}'>
					<span class='comment_space_1'></span>
					<a class='comment_click comment_save'>수정</a>
					<a class='comment_click comment_delete'>삭제</a>
					<span class='comment_space_1'></span>
				</c:when>
				<c:when test='${login_info.member_id ne vo.member_id && login_info.member_id eq "admin"}'>
					<span class='comment_space'></span>
					<a class='comment_click comment_delete'>삭제</a>
					<span class='comment_space_1'></span>
				</c:when>
				<c:otherwise>
					<span class='comment_space'></span>
					<span class='comment_space'></span>
				</c:otherwise>
			</c:choose>
			
			<span id='comment_date'>${vo.writedate}</span>
			
		</div>
	</div>
</c:forEach>

<script type="text/javascript">
$('.comment_save').on('click', function(){
	var $div = $(this).closest('div');
	$div.children('#comment_content_modify').css('height',$div.children('#comment_content'));
	
	if($(this).text() == '수정') {
		var tag = $div.children('#comment_content').html().replace(/<br>/g,'\n');
		$div.children('#comment_content_modify').html( tag );
		display( $div, 'm' );
	} else {
		var comment = {comment_num:$div.data('comment_num'), content:$div.children('#comment_content_modify').val()};
		$.ajax({
			url:'community/comment/update',
			data:JSON.stringify(comment),
			type: 'post',
			contentType:'application/json',
			success:function(data){
				comment_list();
				alert('댓글이 수정되었습니다');
			},
			error: function(req, text){
				alert(text+':'+req.status);
			}
		});
	}
});

$('.comment_delete').on('click', function() {
	var $div = $(this).closest('div');
	if($(this).text() == '취소'){
		display($div, 'd');
	} else {
		if(confirm('정말 삭제하시겠습니까?')) {
			$.ajax({
				url:'community/comment/delete/' + $div.data('comment_num'),
				success: function() {
					comment_list();
				},
				error: function(req, text) {
					alert(text+':'+req.status);
				}
			});
		}
	}
});

function display(div, mode){
	div.find('.comment_save').text(mode == 'm' ? '확인' : '수정' );
	div.find('.comment_delete').text(mode == 'm' ? '취소' : '삭제');
	div.children('#comment_content').css('display', mode == 'm' ? 'none' : 'block' );
	div.children('#comment_content_modify').css('display', mode == 'm' ? 'block' : 'none' );
}
</script>

</body>
</html>
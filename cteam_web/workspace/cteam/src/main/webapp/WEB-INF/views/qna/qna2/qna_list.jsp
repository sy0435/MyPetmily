<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA</title>

<style type="text/css">

.qna_title {
	width: 100%;
	height: 50px;
	line-height: 50px;
	box-sizing: border-box;
	border: 1px solid translate;
	border-radius: 10px 10px 0 0;
	font-size: 18px; 
	font-weight: bold;
	background: linear-gradient(to bottom, #FFB4BE80 50%, #FFFFFF 100%);
	/* background: linear-gradient(to bottom, #FFB4BE, #FFFFFF); */
	color: #333333;
	cursor: pointer;
}

.qna_content {
	width: 100%;
	margin: 10px auto;
	padding: 10px 20px;
	box-sizing: border-box;
	border: 1px solid translate;
	font-size: 18px;  
	color: #333333;
	display: none;
}

.qna_title_modify {
	display: none;
}

.qna_modify {
	display: block;
	width: 20%;
	height: 50px;
	line-height: 50px;
	float: right;
	box-sizing: border-box;
	border: 1px solid translate;
	border-radius: 0 10px 0 0;
	font-size: 12px; 
	background: linear-gradient(to bottom, #BEBEBE 50%, #FFFFFF 100%);
	cursor: default;
}

.qna_modify a {
	box-sizing: border-box;
	border: 1px solid #333333;
	border-radius: 5px;
	padding: 2px 5px;
	background-color: white;
	color: #111111;
}

.title_original {
	width: 80%;
	float: left;
}

.title_modify {
	width: 80%;
	height: 50px;
	float: left;
	line-height: 50px;
	box-sizing: border-box;
	border: 1px solid transparent;
	font-size: 18px; 
	font-weight: bold;
	text-align: center;
	background-color: transparent;
	color: #333333;
	display: none;
}

.content_modify {
	width: 100%;
	margin: 10px auto;
	padding: 10px 20px;
	box-sizing: border-box;
	border: 0px;
	font-size: 18px;
	text-align: center;
	color: #333333;
 	display: none;
}

</style>
<!-- 관리자 모드 : 수정, 삭제 가능 -->
<c:if test='${login_info.member_id eq "admin"}'>
	<c:forEach items="${list}" var="vo">
		<div class="qna_title" data-qna_num='${vo.qna_num}'>
			<div class="title_original title">${fn:replace(fn:replace( vo.qna_title, lf, '<br>' ), crlf, '<br>')}</div>
			<input type="text" class="title_modify" value="${vo.qna_title}">
				<span class="qna_modify" style="overflow:hidden;">
					<a class="qna_title_modify_button">질문수정</a>
					<a class="qna_content_modify_button">답변수정</a>
					<a class="qna_delete_button">삭제</a>
				</span>
			</div>
			<div class="qna_content content">${fn:replace(fn:replace( vo.qna_content, lf, '<br>' ), crlf, '<br>')}</div>
			<textarea class="content_modify"></textarea>
		</div>
	</c:forEach>
</c:if>

<!-- 회원 모드 : 수정, 삭제 버튼 보이지 않음 -->
<c:if test='${login_info.member_id ne "admin"}'>
	<c:forEach items="${list}" var="vo">
		<div class="qna_title title">${fn:replace(fn:replace( vo.qna_title, lf, '<br>' ), crlf, '<br>')}</div>
		<div class="qna_content content">${fn:replace(fn:replace( vo.qna_content, lf, '<br>' ), crlf, '<br>')}</div>
	</c:forEach>
</c:if>

</head>
<body>

<script type="text/javascript">

$('.qna_title_modify_button').on('click', function(){
	var $div = $(this).closest('div');
	$div.children('.title_modify').css('height', $div.children('.title_original'));

	if($(this).text() == '질문수정') {
		var tag = $div.children('.title_original').html().replace(/<br>/g,'\n');
		$div.children('.title_modify').html( tag );
		display_title( $div, 'tm' );
		$('.title_modify').focus();
	} else {
		var title = {qna_num:$div.data('qna_num'), qna_title:$div.children('.title_modify').val()};
		$.ajax({
			url:'qna/qna2/update_title',
			data:JSON.stringify(title),
			type: 'post',
			contentType:'application/json',
			success:function(data){
				qna_list();
				alert('질문이 수정되었습니다');
			},
			error: function(req, text){
				alert(text+':'+req.status);
			}
		});
	}
});

$('.qna_content_modify_button').on('click', function(){
	var $div = $(this).closest('div');
	$div.next('.qna_content').css('height', $div.next('.qna_content'));

	if($(this).text() == '답변수정') {
		var tag = $div.next('.qna_content').html().replace(/<br>/g,'\n');
		$div.next('.qna_content').next('.content_modify').html( tag );
		display_content( $div, 'cm' );
		$('.content_modify').focus();
	} else {
		var title = {qna_num:$div.data('qna_num'), qna_content:$div.next('.qna_content').next('.content_modify').val()};
		$.ajax({
			url:'qna/qna2/update_content',
			data:JSON.stringify(title),
			type: 'post',
			contentType:'application/json',
			success:function(data){
				qna_list();
				alert('답변이 수정되었습니다');
				//$('.qna_title[data-qna_num='+ $div.data('qna_num') +']').children('.title').trigger('click');
			},
			error: function(req, text){
				alert(text+':'+req.status);
			}
		});
	}
});

$('.qna_delete_button').on('click', function() {
	var $div = $(this).closest('div');
	if($(this).text() == '취소'){
		/* console.log( $div.find('.qna_title_modify_button').text(), $div.find('.qna_content_modify_button').text()) */
		if( $div.find('.qna_title_modify_button').text()=='수정완료' ) display_title($div, 'd');
		else if( $div.find('.qna_content_modify_button').text()=='수정완료' ) display_content($div, 'd');

		
	} else {
		if(confirm('질문과 답변이 모두 삭제됩니다. 정말 삭제하시겠습니까?')) {
			$.ajax({
				url:'qna/qna2/delete/' + $div.data('qna_num'),
				success: function() {
					qna_list();
				},
				error: function(req, text) {
					alert(text+':'+req.status);
				}
			});
		}
	}
});

function display_title(div, mode){
	div.find('.qna_title_modify_button').text(mode == 'tm' ? '수정완료' : '질문수정' ).css('background-color', mode == 'tm' ? '#FFB4BE' : '#fff');
	div.find('.qna_delete_button').text(mode == 'tm' ? '취소' : '삭제').css('background-color', mode == 'tm' ? '#FFB4BE' : '#fff');
	div.children('.title_original').css('display', mode == 'tm' ? 'none' : 'block' );
	div.children('.title_modify').css('display', mode == 'tm' ? 'block' : 'none' );
}

function display_content(div, mode){
	div.find('.qna_content_modify_button').text(mode == 'cm' ? '수정완료' : '답변수정' ).css('background-color',  mode == 'cm' ? '#FFB4BE' : '#fff');
	div.find('.qna_delete_button').text(mode == 'cm' ? '취소' : '삭제').css('background-color', mode == 'cm' ? '#FFB4BE' : '#fff' );
	div.next('.qna_content').css('display', mode == 'cm' ? 'none' : 'block' );
	div.next('.qna_content').next('.content_modify').css('display', mode == 'cm' ? 'block' : 'none' );
}
 
$(".title").on('click',function() {
	if(${login_info.member_id eq "admin"}) {
		$(this).parent(".qna_title").next(".content").slideToggle(100);
	  
		if($(".content_modify").css('display', 'block')) {
			$(".content_modify").css('display', 'none'); 
		}
	} else {
		$(this).next(".content").slideToggle(100);
	} 
});

</script>

</body>
</html>
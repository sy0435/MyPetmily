<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA</title>

<style type="text/css">
#qna {
	overflow-y: scroll;
}

#qna::-webkit-scrollbar-button:start:decrement, 
#qna::-webkit-scrollbar-button:end:increment {
	display: block;
	height: 15px;
	background-color: #969696;
}

#qna::-webkit-scrollbar {
	width: 15px;
}

#qna::-webkit-scrollbar-thumb {
	background-color: #FFB4BE;
}

#qna::-webkit-scrollbar-track {
	background-color: #96969650;
}

#qna::-webkit-scrollbar-button {
	background-color: #969696;
}

#qna_body {
	width: 60%;
	height: 500px;
	margin: 0 auto;
}

#qna_title_insert {
	width: 80%;
	height: 30px;
	line-height: 28px;
	box-sizing: border-box;
	border: 1px solid #969696;
	border-radius: 5px;
	vertical-align: middle;
	float: left;
	padding: 0 10px;
	font-size: 15px;
	color: #111111;
}

#qna_content_insert {
	width: 100%;
	height: 60px;
	line-height: 29px;
	box-sizing: border-box;
	border: 1px solid #969696;
	border-radius: 5px;
	float: left;
	padding: 0 10px;
	font-size: 15px;
	color: #111111;
}

#qna_button_insert {
	width: 15%;
	height: 30px;
	line-height: 28px;
	box-sizing: border-box;
	border: 2px solid #FFB4BE;
	border-radius: 5px;
	float: left;
	font-weight: bold;
	font-size: 15px;
	color: #333333;
	vertical-align: middle;
	background-color: #FFB4BE80;
	cursor: pointer;
}

.qna_space_insert_5 {
	display: block;
	width: 5%;
	height: 30px;
	float: left;
}

.qna_space_insert_20 {
	display: block;
	width: 100%;
	height: 20px;
	float: left;
}

.qna_space_insert_10 {
	display: block;
	width: 100%;
	height: 10px;
	float: left;
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

	<!-- 상단 그냥 괜히 놔둔 바 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; margin:0 auto;'>
			<span style='display:block; background-color:white; height:35px;'>
			</span>
		</div>
	</div>
	
	<!-- 중간 바디 -->
	<div id='qna' style='width:100%; height:500px; float:left;'>
		<div id='qna_body'>
			<div id='qna_list'></div>
		
			<!-- 관리자만 추가 가능 -->
			<c:if test='${login_info.member_id eq "admin"}'>
				<span class="qna_space_insert_20"></span>
					<input type="text" id="qna_title_insert" placeholder="이곳에 추가할 질문을 작성하세요"/>
				<span class="qna_space_insert_5"></span>
					<button id="qna_button_insert" onclick="qna_regist()">확인</button>
				<span class="qna_space_insert_10"></span>
					<textarea id="qna_content_insert" placeholder="이곳에 추가할 답변을 작성하세요"></textarea>
				<span class="qna_space_insert_20"></span>
			</c:if>
		</div>
	</div>
	
	<!-- 하단 그냥 괜히 놔둔 바 -->
	<div style='width:100%; float:left; margin:10px 0;'>
		<div style='width:90%; margin:0 auto;'>
			<span style='display:block; background-color:white; height:30px;'>
			</span>
		</div>
	</div>

<script type="text/javascript">
qna_list();
function qna_list(){
	$.ajax({
		url : 'qna/qna2',
		success: function(data) {
			$('#qna_list').html(data);
		},
		error: function(req, text) {
			alert(text+':'+req.status);
		}
	});
}

function qna_regist() {
	if ( $('#qna_title_insert').val() == '' ) {
		alert('질문을 입력하세요');
		$('#qna_title_insert').focus();
		return;
	} else if ( $('#qna_content_insert').val() == '' ) {
		alert('답변을 입력하세요');
		$('#qna_content_insert').focus();
		return;
	}

	$.ajax({
		url: 'qna/qna2/regist',
		data: { qna_title:$('#qna_title_insert').val(), qna_content:$('#qna_content_insert').val() },
		success: function(data) {
			if( data == 1 ){
				alert('등록되었습니다');
				$('#qna_title_insert').val('');
				$('#qna_content_insert').val('');
				qna_list();
			}
		},
		error: function(req, text) {
			alert(text+':'+req.status);
		}
	});
}
</script>

</body>
</html>
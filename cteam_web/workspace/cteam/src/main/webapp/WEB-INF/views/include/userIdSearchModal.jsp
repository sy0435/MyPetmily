<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
* {
    margin: 0;
    padding: 0;
}

body {
    font: 17px 'Nanum Gothic', sans-serif;

}

a {
    text-decoration: none;
    color: #404040;
}

li {
    list-style: none;
}

/*BODY*/

/*팝업창*/
#background_modal {
    display:none;
    position: fixed;
    width: 80%;
    height: 100%;
    background: rgba(0,0,0,0.9);
    margin-left: 0px;
}

.modal_contents {
    position: absolute;
    left: 50%;
    top: 30%;
    transform: translate(-50%,-50%);
    width: 400px;
    height: 200px;
    border-radius:20px;
    text-align: center;
    background: #fff;
}

#popmenu p {
    margin-top: 80px;
}

#pwSearch_btn {
    position: absolute;
    left: 50%;
    bottom: 10px;
    transform: translate(-50%,0);
    width: 200px;
    height: 40px;
    text-align: center;
    line-height: 20px;
    background: black;
    color:white;
    cursor: pointer;
    border: 1px solid black;
    padding: 10px;
}
span{
	cursor: pointer;
}



</style>
</head>
<body>
<div id="background_modal" class="background_modal">
	<div class="modal_contents">
		<br>
		<h4>
			<b>가입한 아이디는</b> <span class="close" style="color:red;">&times;</span>
		</h4><br>
		<br>

		
			<h2 id="id_value"></h2>
		<br>
		<button type="button" id="pwSearch_btn" class="btn peach-gradient btn-rounded waves-effect" onclick="search_check(3)">
		<i class="fa fa-envelope"></i>비밀번호 찾기</button>
	</div>
</div>

</body>
<script type="text/javascript">
$(document).ready(function() {
	/////////모///달///기///능///////////
	// 1. 모달창 히든 불러오기

	
	$('#searchBtn').click(function() {
		$('#background_modal').fadeIn();
	});
	// 2. 모달창 닫기 버튼
	$('.close').on('click', function() {
		$('#background_modal').fadeOut();
	});
	// 3. 모달창 위도우 클릭 시 닫기
	$(window).on('click', function() {
		if (event.target == $('#background_modal').get(0)) {
            $('#background_modal').hide();
         }
	});
});

</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	   <%@ include file="/WEB-INF/views/include/userIdSearchModal.jsp" %>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.btn {margin-top:20px; border: 1px solid #000000;  font-size:14px; background-color: #FFB4BE; width:329px; height: 40px; color: black;}
	a.btn-danger { margin:5px auto; font-size:14px;  border: 1px solid #000000; color:black; background-color: #96969650; width:329px; height: 40px; display: block; line-height: 40px;}
	div.custom-radio{ display: inline-block; }
	.label {font-size: 15px; text-align: left; display: inline-block; vertical-align:-1px; color:#777777;}
	input {width: 300px; outline-color: pink; height: 40px; padding-left: 20px; color: #565656; height: 40px; 
		}
	input[type=radio]{	width:13px; height:13px;vertical-align:text-top }
	label.custom-control-label {font-size: 15px; margin: 10 auto; color: #565656;}
	.space{margin: 10px auto;}
	#name, #phone, #id, #email {width: 329px; margin-bottom: 5px; }
	p { margin-bottom: 30px;}
	.custom-control { margin: 0px 10px 0px 10px; margin-bottom:20px;}
	h3 { margin-bottom: 5px; margin-top: 30px;}


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
	
<!-- 
	<table style="margin-bottom:150px; border-collapse: collapse;" width="300" height="300" align="center">
		<div style="margin-top:100px; text-align:center; color: black;">아이디 찾기</div> 
 
       
 
        
        <div style="text-align:center;">
            <tr>        
                <td>
                <center>
                    <form action="idFindDB" method="post">
                    
                    <center>
                        <br>
                        <div>
                            	<input style="outline-color:pink; width:250px; height: 40px; border: none;" maxlength="11"  type="text" name="member_name"
                                placeholder="이름을 입력하세요 ">
                        </div>    
                        <br/>
                        <div>
                            	<input style="outline-color:pink; width:250px; height: 40px; border: none;" maxlength="11"  type="number" name="member_phonenum"
                                placeholder="가입한 핸드폰번호를 입력하세요 ">
                        </div>         
                                                               
 
                        <br> <br>
                        
                        <button style="border: 1px solid #000000; background-color: #000000; width:300px; height: 30px; color: white;" type="submit" name="submit">아이디 찾기</button>
 
                        </div>
                    </td>
                </tr>
                    </center>
            </table>
        </form>
	</table> -->
	
		<div class="full">
		<div class="container">
			<div class="area_inputs wow fadeIn">
				<div class="sub_title font-weight-bold text-white">
					<h3 style="color:#565656">아이디/비밀번호 찾기</h3>
					<br>
					<p>인증된 이메일을 통해서만 정보 찾기가 가능합니다</p>
				</div>
				<div style="margin-bottom: 10px;"
					class="custom-control custom-radio custom-control-inline">
					<input type="radio" style="vertical-align: middle;" class="custom-control-input" id="search_1" name="search_total" onclick="search_check(1)" checked="checked">
					<label class="custom-control-label font-weight-bold text-white idF"	for="search_1">아이디 찾기</label>
				</div>
				<div class="custom-control custom-radio custom-control-inline">
					<input type="radio" style="vertical-align: middle" class="custom-control-input" id="search_2" name="search_total" onclick="search_check(2)"> 
					<label class="custom-control-label font-weight-bold text-white pwF" for="search_2">비밀번호 찾기</label>
				</div>
				<div id="searchI">
					<div class="form-group space">
						<label class="font-weight-bold text-white label" id="name" for="inputName_1">이름</label>
						<div>
							<input type="text" class="form-control" id="inputName_1" name="member_name" placeholder="ex) 정수안">
						</div>
					</div>
					<div class="form-group space">
						<label class="font-weight-bold text-white label" id="phone" for="inputPhone_1">휴대폰번호</label>
						<div>
							<input type="text" class="form-control" id="inputPhone_1" name="member_phonenum" placeholder="ex) 01077779999">
						</div>
					</div>
					<div class="form-group">
						<button id="searchBtn" type="button" onclick="idSearch_click()" class="btn btn-primary btn-block">확인</button>
					<a class="btn btn-danger btn-block" style="margin-bottom: 100px;" href="${pageContext.request.contextPath}">취소</a>
					</div>
				</div>
				<div id="searchP" style="display: none;">
					<div class="form-group space">
						<label id="id" class="font-weight-bold text-white label" for="inputId">아이디</label>
						<div>
							<input type="text" class="form-control" id="inputId" name="member_id" placeholder="ex) godmisu">
						</div>
					</div>
					<div class="form-group space">
						<label id="email" class="font-weight-bold text-white label" for="inputEmail_2">이메일</label>
						<div>
							<input type="email" class="form-control" id="inputEmail_2"	name="member_email" placeholder="ex) E-mail@gmail.com">
						</div>
					</div>
					<div class="form-group">
						<button id="searchBtn2" type="button" class="btn btn-primary btn-block" onclick="pwSearch_click()">확인</button>
					<a class="btn btn-danger btn-block" style="margin-bottom: 100px;" href="${pageContext.request.contextPath}">취소</a>
				</div>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	function search_check(num){
		if(num == '1'){
			document.getElementById("searchP").style.display = "none";
			document.getElementById("searchI").style.display = "";
			$('.idF').css("color","pink").css("font-weight","bold");
			$('.pwF').css("color","#565656").css("font-weight","normal");
			
		}else if(num=='2'){

			document.getElementById("searchI").style.display = "none";
			document.getElementById("searchP").style.display = "";

			$('.pwF').css("color","pink").css("font-weight","bold");
			$('.idF').css("color","#565656").css("font-weight","normal");
			
		}else if(num =='3'){
			$('#background_modal').fadeOut();

			document.getElementById("searchI").style.display = "none";
			document.getElementById("searchP").style.display = "";
			$("#search_2").prop("checked",true);


			$('.pwF').css("color","pink").css("font-weight","bold");
			$('.idF').css("color","#565656").css("font-weight","normal");
			
			$("#inputId").val(idV);
			
		}
	}

	//비번찾기
	function pwSearch_click(){

		$.ajax({
			type:"GET",
			url:"${pageContext.request.contextPath}/searchPassword?member_id="
					+$('#inputId').val()+"&member_email="+$('#inputEmail_2').val(),
			success:function(data){
				if(data == "ok"){
					alert("이메일로 발송된 임시비밀번호로 로그인해주세요");
					
				} else {
					alert("등록된 회원정보가 없습니다");
				}
			}
		});

	}

	// 아이디 & 스토어 값 저장하기 위한 변수
	var idV = "";
	// 아이디 값 받고 출력하는 ajax
	var idSearch_click = function(){
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/idFindDB?member_name="
					+$('#inputName_1').val()+"&member_phonenum="+$('#inputPhone_1').val(),
			success:function(data){
				if(data == ""){
					$('#id_value').text("회원 정보를 확인해주세요!");	
				} else {
					$('#id_value').text(data);
					// 아이디값 별도로 저장
					idV = data;
				}
			}
		});
	}

	

</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회사</title>

<style type="text/css">

#company_menu, #company_menu_body, #company_employee_body, #company_map_body {
	float: left;
	height: 500px;
	overflow: auto;
}
#company_menu ul li a {
	display: block;
	border: 2px dashed #FFB4BE; 
	font-size: large;
	font-weight: bold;
	color: #666666;
	padding-top: 10px;
	padding-bottom: 10px;
}
#company_menu ul { 
	margin: 0;
	padding: 0;
}
#company_menu ul li {
	margin: 10px;
}
#company_menu_body h1, #company_menu_body h2, #company_menu_body h3 {
	margin: 0;
	padding: 10px;
}
.employee_cardview {
	width: 300px;
	height: 155px;
	box-sizing: border-box;
	margin: 10px;
	float: left;
	border: 2px solid #FFB4BE;
}
.employee_cardview_picture {
	width: 135px;
	height: 135px;
	margin: 7px;
	float: left;
	border: 1px dashed #FFB4BE;
}
.employee_cardview_text {
	width: 135px;
	height: 135px;
	margin: 7px 7px 7px 0px;
	float: right;
	border: 1px dashed transferans;
}
.employee_cardview_name {
	display: block;
	margin: 10px 0px;
	font-weight: bold;
	color: #333333;
	font-size: 17px;
}
.employee_cardview_note {
	display: block;
	margin: 0px;
	color: #333333;
	font-size: 16px;
}
</style>

<script type="text/javascript">
//메뉴 회사정보 클릭
function company_intro() {
	$('#company_menu_body').css('display', 'block');
	$('#company_employee_body').css('display', 'none');
	$('#company_map_body').css('display', 'none');
}
//메뉴 직원정보 클릭
function company_employee() {
	$('#company_menu_body').css('display', 'none');
	$('#company_employee_body').css('display', 'block');
	$('#company_map_body').css('display', 'none');
}
//메뉴 오시는길 클릭
function company_map() {
	$('#company_menu_body').css('display', 'none');
	$('#company_employee_body').css('display', 'none');
	$('#company_map_body').css('display', 'block');
}
</script>

</head>
<body>

	<!-- 상단 그냥 괜히 놔둔 바 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; margin:0 auto;'>
			<span style='display:block; background-color:#FFB4BE; height:35px;'>
			</span>
		</div>
	</div>
	
	<!-- 중간 바디 -->
	<div style='width:100%; float:left;'>
		<div style='width:90%; margin:0 auto;'>

			<!-- 왼쪽 메뉴 -->
			<div id='company_menu' style='width:20%;'>
				<ul>
					<li><a href='javascript:company_intro()'>회사 소개</a></li>
					<li><a href='javascript:company_employee()'>직원 정보</a></li>
					<li><a href='javascript:company_map()'>오시는 길</a></li>
				</ul>
			</div>
			
			<!-- 오른쪽 내용 : 회사 소개 -->
			<div id='company_menu_body' style='width:80%;'>
				<h1>
					<font style='color:#111111;'>신뢰와 믿음을 목표로 한단계씩 성장하는 팀 펫넥트</font>
				</h1>
				<h2>
					<font style='color:#111111;'>다양한 지식, 편리한 접근성, 양질의 소스!</font>
				</h2>
				<h3>
					<font style='color:#333333;'>
					팀 펫넥트는 반려 동물의 체계적인 관리, 실천적인 관리, 동물 존중의 삶이라는 3대 이념을 바탕으로 
					전문적 급여과정, 실무적 훈련과정, 능동적 놀이과정을 통한 반려 동물의 행복한 삶을 위해 설립되었습니다.<br/>
					집사와 반려동물에게 필요한 산책 메이트 구하기 시스템과 
					타 집사와의 차별화된 편리한 반려 동물 관리 프로그램 제공으로 
					반려 동물 복지 발전에 기여해 온 자타가 공인하는 최고의 팀입니다.<br/>
					더불어 팀 펫넥트는 강형욱 동물훈련사 유튜브 구독을 통해 얻어진 지식들을 
					더 효과적으로 활용할 수 있는 방법을 개발하기 위해 최선을 다해 실시하고 있습니다.<br/> 
					앞으로도 팀 펫넥트의 웹/앱 개발은 물론 
					반려 동물 스케줄 관리의 명가로써 여러분의 기대를 저버리지 않도록 최선을 다할 것이며 
					더불어 여러분의 지속적인 관심과 격려를 부탁드립니다.
					</font>
				</h3>
			</div>
			
			<!-- 오른쪽 내용 : 직원 정보 -->
			<div id='company_employee_body' style='width:80%; display:none;' >
				<div class='employee_cardview'>
					<div class='employee_cardview_picture'>
						<img src='img/구루미.jpg' width='135px' height='135px'/>
					</div>
					<div class='employee_cardview_text'>
						<span class='employee_cardview_name'>해일뷘</span>
						<span class='employee_cardview_note'>
						최고의 조장!<br/>
						다양한 지식, 편리한 접근성, 양질의 소스!
						</span>
					</div>
				</div>
				<div class='employee_cardview'>
					<div class='employee_cardview_picture'>
						<img src='img/뚱이.png' width='135px' height='135px'/>
					</div>
					<div class='employee_cardview_text'>
						<span class='employee_cardview_name'> 제리누나</span>
						<span class='employee_cardview_note'>
						특 : 목포에 있어서 못봄
						</span>
					</div>
				</div>
				<div class='employee_cardview'>
					<div class='employee_cardview_picture'>
						<img src='img/피카츄.png' width='135px' height='135px'/>
					</div>
					<div class='employee_cardview_text'>
						<span class='employee_cardview_name'> 자몽언니</span>
						<span class='employee_cardview_note'>
						특 : 가끔 자몽이 데려옴 자몽이 견생 부러움
						</span>
					</div>
				</div>
				<div class='employee_cardview'>
					<div class='employee_cardview_picture'>
						<img src='img/아따맘마 아빠.png' width='135px' height='135px'/>
					</div>
					<div class='employee_cardview_text'>
						<span class='employee_cardview_name'> 경석이</span>
						<span class='employee_cardview_note'>
						불쌍하게도 이분은 반려동물이 없습니다...1
						</span>
					</div>
				</div>
				<div class='employee_cardview'>
					<div class='employee_cardview_picture'>
						<img src='img/아따맘마 엄마.png' width='135px' height='135px'/>
					</div>
					<div class='employee_cardview_text'>
						<span class='employee_cardview_name'> 성현이</span>
						<span class='employee_cardview_note'>
						불쌍하게도 이분은 반려동물이 없습니다...2
						</span>
					</div>
				</div>
			</div>
			
			<!-- 오른쪽 내용 : 오시는 길 -->
			<div id='company_map_body' style='width:80%; display:none;'>
				<div style='padding:10px 12px 10px 12px;'>
					<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d13047.805951622882!2d126.8840455!3d35.1578306!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xb8207167890e32bb!2z7ZWc7Jq47KeB7JeF7KCE66y47ZWZ6rWQ!5e0!3m2!1sko!2skr!4v1602308233338!5m2!1sko!2skr" 
							width="100%" height="350px" frameborder="2px" style="border:2px dashed #FFB4BE" allowfullscreen="" aria-hidden="false" tabindex="0">
					</iframe>
				</div>
  				<div style='padding:10px;'>
		 			<div><font style='color:#222222;'>
					주소 : (619-28) 광주광역시 서구 경열로 3 ( 농성동 271-4 )<br/>
					버스 : 금호36, 문흥39, 봉선37, 송정19,송암68, 송암72, 나주160, 함평500, 지선1187, 마을763<br/>
					지하철 : 농성역1번 출구, (도보 5분여 소요)<br/>
					전화번호 : 062)362-7797<br/>
					FAX : 062)362-7798
		 			</font></div>
				</div>
			</div>
			
		</div>
	</div>
			
	<!-- 하단 그냥 괜히 놔둔 바 -->
	<div style='width:100%; float:left; margin:10px 0;'>
		<div style='width:90%; margin:0 auto;'>
			<span style='display:block; background-color:#FFB4BE; height:30px;'>
			</span>
		</div>
	</div>

</body>
</html>
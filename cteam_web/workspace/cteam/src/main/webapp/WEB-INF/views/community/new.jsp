<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>커뮤니티</title>

<style type="text/css">
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

.cancel, .submit {
	width: 75px;
	height: 30px;
}

.file-pre { width:90%; }
</style>

</head>
<body>

	<!-- 상단 메뉴 -->
	<div style='width:100%; float:left; margin:10px 0;'>
		<div style='width:90%; margin:0 auto;'>
			<span style='display:block; background-color:#FFB4BE; height:35px;'>
			</span>
		</div>
	</div>

	<!-- 게시글 작성 -->
	<form method="post" action="insert.bo" enctype="multipart/form-data">
	<div style='width:100%; float:left;' >
		<table style='width:90%;'>
			<tr>
				<th class='w-px100'>제목</th>
				<td class='w-px400'>
					<input class='need' type='text' name='board_title' title='제목' 
					style='height:25px; width:100%; margin:0 auto; padding:0px 10px; 
					vertical-align:middel; border:0px; box-sizing:border-box;'/>
				</td>
				<th class='w-px100'>게시판</th>
				<td class='w-px100'>
					<select name='board_subject' class='need' title='게시판 카테고리' style='width:90%; height:25px; border:none;'>
						<option selected='selected' disabled='disabled'>== 카테고리 ==</option>
						<option value='산책'>산책</option>
						<option value='나눔'>나눔</option>
					</select>
				</td>
				
				<!-- 지역 선택 -->
				<th class='w-px100'>지역</th>
				<td class='w-px250'>		
				
				<select name='board_city' class='need' title='지역' style='width:50%; height:25px; border:none;' onchange='select()'>
					<option value='' selected disabled='disabled'>== 지역 선택 ==</option>
					<option title='seoul' value='서울특별시'>서울특별시</option>
					<option title='busan' value='부산광역시'>부산광역시</option>
					<option title='daegu' value='대구광역시'>대구광역시</option>
					<option title='incheon' value='인천광역시'>인천광역시</option>
					<option title='gwangju' value='광주광역시'>광주광역시</option>
					<option title='daejeon' value='대전광역시'>대전광역시</option>
					<option title='ulsan' value='울산광역시'>울산광역시</option>
					<option title='sejong' value='세종특별자치시'>세종특별자치시</option>
					<option title='gyeonggi' value='경기도'>경기도</option>
					<option title='gangwon' value='강원도'>강원도</option>
					<option title='chungbuk' value='충청북도'>충청북도</option>
					<option title='chungnam' value='충청남도'>충청남도</option>
					<option title='jeonbuk' value='전라북도'>전라북도</option>
					<option title='jeonnam' value='전라남도'>전라남도</option>
					<option title='gyeongbuk' value='경상북도'>경상북도</option>
					<option title='gyeongnam' value='경상남도'>경상남도</option>
					<option title='jeju' value='제주특별자치도'>제주특별자치도</option>
				</select>
				
				<!-- 지역 상세-->
				<select name='board_region' style='width:30%; height:25px; margin-left:10%; border:none;'>
					<option value=''>== 전체 ==</option>
				</select>

				<span id='seoul' class='area'>
					<option value='강남구'>강남구</option>
					<option value='강동구'>강동구</option>
					<option value='강북구'>강북구</option>
					<option value='강서구'>강서구</option>
					<option value='관악구'>관악구</option>
					<option value='광진구'>광진구</option>
					<option value='구로구'>구로구</option>
					<option value='금천구'>금천구</option>
					<option value='노원구'>노원구</option>
					<option value='도봉구'>도봉구</option>
					<option value='동대문구'>동대문구</option>
					<option value='동작구'>동작구</option>
					<option value='마포구'>마포구</option>
					<option value='서대문구'>서대문구</option>
					<option value='서초구'>서초구</option>
					<option value='성동구'>성동구</option>
					<option value='성북구'>성북구</option>
					<option value='송파구'>송파구</option>
					<option value='양천구'>양천구</option>
					<option value='영등포구'>영등포구</option>
					<option value='용산구'>용산구</option>
					<option value='은평구'>은평구</option>
					<option value='종로구'>종로구</option>
					<option value='중구'>중구</option>
					<option value='중랑구'>중랑구</option>
				</span>

				<span id='busan' class='area'>
					<option value='강서구'>강서구</option>
					<option value='금정구'>금정구</option>
					<option value='기장군'>기장군</option>
					<option value='남구'>남구</option>
					<option value='동구'>동구</option>
					<option value='동래구'>동래구</option>
					<option value='부산진구'>부산진구</option>
					<option value='북구'>북구</option>
					<option value='사상구'>사상구</option>
					<option value='사하구'>사하구</option>
					<option value='서구'>서구</option>
					<option value='수영구'>수영구</option>
					<option value='연제구'>연제구</option>
					<option value='영도구'>영도구</option>
					<option value='중구'>중구</option>
					<option value='해운대구'>해운대구</option>
				</span>

				<span id='daegu' class='area'>
					<option value='중구'>중구</option>
					<option value='동구'>동구</option>
					<option value='서구'>서구</option>
					<option value='남구'>남구</option>
					<option value='북구'>북구</option>
					<option value='수성구'>수성구</option>
					<option value='달서구'>달서구</option>
					<option value='달성군'>달성군</option>
				</span>

				<span id='incheon' class='area'>
					<option value='강화군'>강화군</option>
					<option value='계양구'>계양구</option>
					<option value='남구'>남구</option>
					<option value='남동구'>남동구</option>
					<option value='동구'>동구</option>
					<option value='부평구'>부평구</option>
					<option value='서구'>서구</option>
					<option value='연수구'>연수구</option>
					<option value='옹진군'>옹진군</option>
					<option value='중구'>중구</option>
				</span>					

				<span id='gwangju' class='area'>
					<option value='동구'>동구</option>
					<option value='서구'>서구</option>
					<option value='남구'>남구</option>
					<option value='북구'>북구</option>
					<option value='광산구'>광산구</option>
				</span>

				<span id='daejeon' class='area'>
					<option value='대덕구'>대덕구</option>
					<option value='동구'>동구</option>
					<option value='서구'>서구</option>
					<option value='유성구'>유성구</option>
					<option value='중구'>중구</option>
				</span>

				<span id='ulsan' class='area'>
					<option value='동구'>동구</option>
					<option value='남구'>남구</option>
					<option value='북구'>북구</option>
					<option value='울주군'>울주군</option>
					<option value='중구'>중구</option>
				</span>

				<span id='gyeonggi' class='area'>
					<option value='가평군'>가평군</option>
					<option value='고양시'>고양시</option>
					<option value='과천시'>과천시</option>
					<option value='광명시'>광명시</option>
					<option value='광주시'>광주시</option>
					<option value='구리시'>구리시</option>
					<option value='군포시'>군포시</option>
					<option value='김포시'>김포시</option>
					<option value='남양주시'>남양주시</option>
					<option value='동두천시'>동두천시</option>
					<option value='부천시'>부천시</option>
					<option value='성남시'>성남시</option>
					<option value='수원시'>수원시</option>
					<option value='시흥시'>시흥시</option>
					<option value='안산시'>안산시</option>
					<option value='안성시'>안성시</option>
					<option value='안양시'>안양시</option>
					<option value='양주시'>양주시</option>
					<option value='양평군'>양평군</option>
					<option value='여주시'>여주시</option>
					<option value='연천군'>연천군</option>
					<option value='오산시'>오산시</option>
					<option value='용인시'>용인시</option>
					<option value='의왕시'>의왕시</option>
					<option value='의정부시'>의정부시</option>
					<option value='이천시'>이천시</option>
					<option value='파주시'>파주시</option>
					<option value='평택시'>평택시</option>
					<option value='포천시'>포천시</option>
					<option value='하남시'>하남시</option>
					<option value='화성시'>화성시</option>
				</span>

				<span id='gangwon' class='area'>
					<option value='강릉시'>강릉시</option>
					<option value='고성군'>고성군</option>
					<option value='동해시'>동해시</option>
					<option value='삼척시'>삼척시</option>
					<option value='속초시'>속초시</option>
					<option value='양구군'>양구군</option>
					<option value='양양군'>양양군</option>
					<option value='영월군'>영월군</option>
					<option value='원주시'>원주시</option>
					<option value='인제군'>인제군</option>
					<option value='정선군'>정선군</option>
					<option value='철원군'>철원군</option>
					<option value='춘천시'>춘천시</option>
					<option value='태백시'>태백시</option>
					<option value='평창군'>평창군</option>
					<option value='홍천군'>홍천군</option>
					<option value='화천군'>화천군</option>
					<option value='횡성군'>횡성군</option>
				</span>

				<span id='chungbuk' class='area'>
					<option value='괴산군'>괴산군</option>
					<option value='단양군'>단양군</option>
					<option value='보은군'>보은군</option>
					<option value='영동군'>영동군</option>
					<option value='옥천군'>옥천군</option>
					<option value='음성군'>음성군</option>
					<option value='제천시'>제천시</option>
					<option value='증평군'>증평군</option>
					<option value='진천군'>진천군</option>
					<option value='청주시'>청주시</option>
					<option value='충주시'>충주시</option>
				</span>

				<span id='chungnam' class='area'>
					<option value='계룡시'>계룡시</option>
					<option value='공주시'>공주시</option>
					<option value='금산군'>금산군</option>
					<option value='논산시'>논산시</option>
					<option value='당진시'>당진시</option>
					<option value='보령시'>보령시</option>
					<option value='부여군'>부여군</option>
					<option value='서산시'>서산시</option>
					<option value='서천군'>서천군</option>
					<option value='아산시'>아산시</option>
					<option value='예산군'>예산군</option>
					<option value='천안시'>천안시</option>
					<option value='청양군'>청양군</option>
					<option value='태안군'>태안군</option>
					<option value='홍성군'>홍성군</option>
				</span>

				<span id='jeonbuk' class='area'>
					<option value='고창군'>고창군</option>
					<option value='군산시'>군산시</option>
					<option value='김제시'>김제시</option>
					<option value='남원시'>남원시</option>
					<option value='무주군'>무주군</option>
					<option value='부안군'>부안군</option>
					<option value='순창군'>순창군</option>
					<option value='완주군'>완주군</option>
					<option value='익산시'>익산시</option>
					<option value='임실군'>임실군</option>
					<option value='장수군'>장수군</option>
					<option value='전주시'>전주시</option>
					<option value='정읍시'>정읍시</option>
					<option value='진안군'>진안군</option>
				</span>

				<span id='jeonnam' class='area'>
					<option value='강진군'>강진군</option>
					<option value='고흥군'>고흥군</option>
					<option value='곡성군'>곡성군</option>
					<option value='광양시'>광양시</option>
					<option value='구례군'>구례군</option>
					<option value='나주시'>나주시</option>
					<option value='담양군'>담양군</option>
					<option value='목포시'>목포시</option>
					<option value='무안군'>무안군</option>
					<option value='보성군'>보성군</option>
					<option value='순천시'>순천시</option>
					<option value='신안군'>신안군</option>
					<option value='여수시'>여수시</option>
					<option value='영광군'>영광군</option>
					<option value='영암군'>영암군</option>
					<option value='완도군'>완도군</option>
					<option value='장성군'>장성군</option>
					<option value='장흥군'>장흥군</option>
					<option value='진도군'>진도군</option>
					<option value='함평군'>함평군</option>
					<option value='해남군'>해남군</option>
					<option value='화순군'>화순군</option>
				</span>

				<span id='gyeongbuk' class='area'>
					<option value='경산시'>경산시</option>
					<option value='경주시'>경주시</option>
					<option value='고령군'>고령군</option>
					<option value='구미시'>구미시</option>
					<option value='군위군'>군위군</option>
					<option value='김천시'>김천시</option>
					<option value='문경시'>문경시</option>
					<option value='봉화군'>봉화군</option>
					<option value='상주시'>상주시</option>
					<option value='성주군'>성주군</option>
					<option value='안동시'>안동시</option>
					<option value='영덕군'>영덕군</option>
					<option value='영양군'>영양군</option>
					<option value='영주시'>영주시</option>
					<option value='영천시'>영천시</option>
					<option value='예천군'>예천군</option>
					<option value='울릉군'>울릉군</option>
					<option value='울진군'>울진군</option>
					<option value='의성군'>의성군</option>
					<option value='청도군'>청도군</option>
					<option value='청송군'>청송군</option>
					<option value='칠곡군'>칠곡군</option>
					<option value='포항시'>포항시</option>
				</span>

				<span id='gyeongnam' class='area'>
					<option value='거제시'>거제시</option>
					<option value='거창군'>거창군</option>
					<option value='고성군'>고성군</option>
					<option value='김해시'>김해시</option>
					<option value='남해군'>남해군</option>
					<option value='밀양시'>밀양시</option>
					<option value='사천시'>사천시</option>
					<option value='산청군'>산청군</option>
					<option value='양산시'>양산시</option>
					<option value='의령군'>의령군</option>
					<option value='진주시'>진주시</option>
					<option value='청녕군'>청녕군</option>
					<option value='창원시'>창원시</option>
					<option value='통영시'>통영시</option>
					<option value='하동군'>하동군</option>
					<option value='함안군'>함안군</option>
					<option value='함양군'>함양군</option>
					<option value='합천군'>합천군</option>
				</span>

				<span id='jeju' class='area'>
					<option value='서귀포시'>서귀포시</option>
					<option value='제주시'>제주시</option>
				</span>

				</td>
			</tr>
			<tr>
				<th rowspan='2' class='w-px100' style='height:459px;'>내용</th>
				<td rowspan='2' colspan='3'>
					<textarea name='board_content' title='내용' class='need' 
					style='width:100%; height:99%; margin:0; padding:10px; border:none; box-sizing:border-box; font-size:15px;'></textarea>
				</td>
				<th class='w-px100' style='height:400px;'>첨부사진</th>
				<td>
					<div id="preview"></div>
				</td>
			</tr>
			<tr>
				<th><label style='cursor:pointer;'><input type="file" name="file" id="attach-file" accept="image/gif, image/jpeg, image/png, image/jpg, image/bmp"/>첨부하기</label></th>
				<td>
					<span id="file-name"></span>
					<span id="delete-file" style='cursor:pointer;'><img src='img/delete.png' height='75%'/></span>
					<!-- <span id="delete-file" style="color: red;"><i class="far fa-calendar-times font-img"></i></span> -->
				</td>
			</tr>
		</table>
	</div>
	</form>
	
	<!-- 글쓰기 버튼 -->
	<div style='width:100%; float:left; margin:10px 0;'>
		<div style='width:90%; height:30px; margin:0 auto; background-color: #FFB4BE;'>
			<span style='display:block; background-color:#FFFFFF; width:200px; height:35px; margin:0 auto;'>
				<button class='cancel'><a href='list.bo' style='color:#111111; font-size:15px;'>취소</a></button>
				<button class='submit' onclick='if(necessary()){$("form").submit()}' 
						style='color:#111111; font-size:15px; font-weight:bold; cursor:pointer;'>글쓰기</button>
			</span>
		</div>
	</div>

<script type="text/javascript" src="js/image_preview.js?v=<%=new java.util.Date().getTime()%>"></script>
<script type="text/javascript" src="js/need_check.js"></script>
<script type="text/javascript" src="js/file_attach.js"></script>
<script type="text/javascript">
$('.area').css('display', 'none');
function select() {
	var board_city = $('[name=board_city]').val();

	/* 지역select 태그에 지역option을 변경하는 경우 */
	$('[name=board_region] option:gt(0)').remove();
	$('[name=board_region]').append( $('#'+ $('[name=board_city] option:selected').attr('title')).html() );
	
	/* 지역별 select 태그를 사용하는 경우 */
/*	$('[name=board_region]').css('display', 'none');
	$('#'+ $('[name=board_city] option:selected').attr('title')).css('display', 'block'); */
}
</script>
</body>
</html>
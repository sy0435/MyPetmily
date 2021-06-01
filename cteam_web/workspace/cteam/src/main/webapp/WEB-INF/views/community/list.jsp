<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

/* 제목이 너무 길면 말줄임 처리 */
table td { overflow: hidden; text-overflow:ellipsis; white-space: nowrap; }

input[type=text] {
	width: 200px;
	height: 25px;
	box-sizing: border-box;
	border: 1px solid #333333;
	vertical-align: middle;
}

select {
	height: 25px;
	border: 1px solid #333333;
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
</style>

</head>
<body>

<form method='post' action='list.bo'>
<input type='hidden' name='curPage' value='1'/>
<input type='hidden' name='board_num'/>
	
	<!-- 상단 메뉴 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; margin:0 auto;'>
			<!-- 게시판 분류 -->
			<select name='search' onchange='$("form").submit()' style='float:left; margin:5px 5px 0px 0px;'>
				<option value='allboard' ${page.search eq 'allboard' ? 'selected' : ''}>전체 게시판</option>
				<option value='walk' ${page.search eq 'walk' ? 'selected' : ''}>산책</option>
				<option value='sharing' ${page.search eq 'sharing' ? 'selected' : ''}>나눔</option>
			</select>
			<!-- 지역 선택 -->
			<select name='search2' onchange='$("form").submit()' style='float:left; margin:5px 5px 0px 0px;'>
				<option value='allcity' ${page.search2 eq 'allcity' ? 'selected' : ''}>전체 지역</option>
				<option value='seoul' ${page.search2 eq 'seoul' ? 'selected' : ''}>서울특별시</option>
				<option value='busan' ${page.search2 eq 'busan' ? 'selected' : ''}>부산광역시</option>
				<option value='daegu' ${page.search2 eq 'daegu' ? 'selected' : ''}>대구광역시</option>
				<option value='incheon' ${page.search2 eq 'incheon' ? 'selected' : ''}>인천광역시</option>
				<option value='gwangju' ${page.search2 eq 'gwangju' ? 'selected' : ''}>광주광역시</option>
				<option value='daejeon' ${page.search2 eq 'daejeon' ? 'selected' : ''}>대전광역시</option>
				<option value='ulsan' ${page.search2 eq 'ulsan' ? 'selected' : ''}>울산광역시</option>
				<option value='sejong' ${page.search2 eq 'sejong' ? 'selected' : ''}>세종특별자치시</option>
				<option value='gyeonggi' ${page.search2 eq 'gyeonggi' ? 'selected' : ''}>경기도</option>
				<option value='gangwon' ${page.search2 eq 'gangwon' ? 'selected' : ''}>강원도</option>
				<option value='chungbuk' ${page.search2 eq 'chungbuk' ? 'selected' : ''}>충청북도</option>
				<option value='chungnam' ${page.search2 eq 'chungnam' ? 'selected' : ''}>충청남도</option>
				<option value='jeonbuk' ${page.search2 eq 'jeonbuk' ? 'selected' : ''}>전라북도</option>
				<option value='jeonnam' ${page.search2 eq 'jeonnam' ? 'selected' : ''}>전라남도</option>
				<option value='gyeongbuk'> ${page.search2 eq 'gyeongbuk' ? 'selected' : ''}경상북도</option>
				<option value='gyeongnam' ${page.search2 eq 'gyeongnam' ? 'selected' : ''}>경상남도</option>
				<option value='jeju' ${page.search2 eq 'jeju' ? 'selected' : ''}>제주특별자치도</option>
			</select>
			<!-- 지역 상세 -->
			<select name='search3' onchange='$("form").submit()' style='float:left; width:75px; margin:5px 5px 0px 0px;'>
				<option value='allregion' ${page.search3 eq 'allregion' ? 'selected' : ''}>전체</option>
				<c:if test='${page.search2 eq "seoul"}'>
					<option value='seoul1' ${page.search3 eq 'seoul1' ? 'selected' : ''}>강남구</option>
					<option value='seoul2' ${page.search3 eq 'seoul2' ? 'selected' : ''}>강동구</option>
					<option value='seoul3' ${page.search3 eq 'seoul3' ? 'selected' : ''}>강북구</option>
					<option value='seoul4' ${page.search3 eq 'seoul4' ? 'selected' : ''}>강서구</option>
					<option value='seoul5' ${page.search3 eq 'seoul5' ? 'selected' : ''}>관악구</option>
					<option value='seoul6' ${page.search3 eq 'seoul6' ? 'selected' : ''}>광진구</option>
					<option value='seoul7' ${page.search3 eq 'seoul7' ? 'selected' : ''}>구로구</option>
					<option value='seoul8' ${page.search3 eq 'seoul8' ? 'selected' : ''}>금천구</option>
					<option value='seoul9' ${page.search3 eq 'seoul9' ? 'selected' : ''}>노원구</option>
					<option value='seoul10' ${page.search3 eq 'seoul10' ? 'selected' : ''}>도봉구</option>
					<option value='seoul11' ${page.search3 eq 'seoul11' ? 'selected' : ''}>동대문구</option>
					<option value='seoul12' ${page.search3 eq 'seoul12' ? 'selected' : ''}>동작구</option>
					<option value='seoul13' ${page.search3 eq 'seoul13' ? 'selected' : ''}>마포구</option>
					<option value='seoul14' ${page.search3 eq 'seoul14' ? 'selected' : ''}>서대문구</option>
					<option value='seoul15' ${page.search3 eq 'seoul15' ? 'selected' : ''}>서초구</option>
					<option value='seoul16' ${page.search3 eq 'seoul16' ? 'selected' : ''}>성동구</option>
					<option value='seoul17' ${page.search3 eq 'seoul17' ? 'selected' : ''}>성북구</option>
					<option value='seoul18' ${page.search3 eq 'seoul18' ? 'selected' : ''}>송파구</option>
					<option value='seoul19' ${page.search3 eq 'seoul19' ? 'selected' : ''}>양천구</option>
					<option value='seoul20' ${page.search3 eq 'seoul20' ? 'selected' : ''}>영등포구</option>
					<option value='seoul21' ${page.search3 eq 'seoul21' ? 'selected' : ''}>용산구</option>
					<option value='seoul22' ${page.search3 eq 'seoul22' ? 'selected' : ''}>은평구</option>
					<option value='seoul23' ${page.search3 eq 'seoul23' ? 'selected' : ''}>종로구</option>
					<option value='seoul24' ${page.search3 eq 'seoul24' ? 'selected' : ''}>중구</option>
					<option value='seoul25' ${page.search3 eq 'seoul25' ? 'selected' : ''}>중랑구</option>
				</c:if>
				<c:if test='${page.search2 eq "busan"}'>
					<option value='busan1' ${page.search3 eq 'busan1' ? 'selected' : ''}>강서구</option>
					<option value='busan2' ${page.search3 eq 'busan2' ? 'selected' : ''}>금정구</option>
					<option value='busan3' ${page.search3 eq 'busan3' ? 'selected' : ''}>기장군</option>
					<option value='busan4' ${page.search3 eq 'busan4' ? 'selected' : ''}>남구</option>
					<option value='busan5' ${page.search3 eq 'busan5' ? 'selected' : ''}>동구</option>
					<option value='busan6' ${page.search3 eq 'busan6' ? 'selected' : ''}>동래구</option>
					<option value='busan7' ${page.search3 eq 'busan7' ? 'selected' : ''}>부산진구</option>
					<option value='busan8' ${page.search3 eq 'busan8' ? 'selected' : ''}>북구</option>
					<option value='busan9' ${page.search3 eq 'busan9' ? 'selected' : ''}>사상구</option>
					<option value='busan10' ${page.search3 eq 'busan10' ? 'selected' : ''}>사하구</option>
					<option value='busan11' ${page.search3 eq 'busan11' ? 'selected' : ''}>서구</option>
					<option value='busan12' ${page.search3 eq 'busan12' ? 'selected' : ''}>수영구</option>
					<option value='busan13' ${page.search3 eq 'busan13' ? 'selected' : ''}>연제구</option>
					<option value='busan14' ${page.search3 eq 'busan14' ? 'selected' : ''}>영도구</option>
					<option value='busan15' ${page.search3 eq 'busan15' ? 'selected' : ''}>중구</option>
					<option value='busan16' ${page.search3 eq 'busan16' ? 'selected' : ''}>해운대구</option>
				</c:if>
				<c:if test='${page.search2 eq "daegu"}'>
					<option value='daegu1' ${page.search3 eq 'daegu1' ? 'selected' : ''}>중구</option>
					<option value='daegu2' ${page.search3 eq 'daegu2' ? 'selected' : ''}>동구</option>
					<option value='daegu3' ${page.search3 eq 'daegu3' ? 'selected' : ''}>서구</option>
					<option value='daegu4' ${page.search3 eq 'daegu4' ? 'selected' : ''}>남구</option>
					<option value='daegu5' ${page.search3 eq 'daegu5' ? 'selected' : ''}>북구</option>
					<option value='daegu6' ${page.search3 eq 'daegu6' ? 'selected' : ''}>수성구</option>
					<option value='daegu7' ${page.search3 eq 'daegu7' ? 'selected' : ''}>달서구</option>
					<option value='daegu8' ${page.search3 eq 'daegu8' ? 'selected' : ''}>달성군</option>
				</c:if>
				<c:if test='${page.search2 eq "incheon"}'>
					<option value='incheon1' ${page.search3 eq 'incheon1' ? 'selected' : ''}>강화군</option>
					<option value='incheon2' ${page.search3 eq 'incheon2' ? 'selected' : ''}>계양구</option>
					<option value='incheon3' ${page.search3 eq 'incheon3' ? 'selected' : ''}>남구</option>
					<option value='incheon4' ${page.search3 eq 'incheon4' ? 'selected' : ''}>남동구</option>
					<option value='incheon5' ${page.search3 eq 'incheon5' ? 'selected' : ''}>동구</option>
					<option value='incheon6' ${page.search3 eq 'incheon6' ? 'selected' : ''}>부평구</option>
					<option value='incheon7' ${page.search3 eq 'incheon7' ? 'selected' : ''}>서구</option>
					<option value='incheon8' ${page.search3 eq 'incheon8' ? 'selected' : ''}>연수구</option>
					<option value='incheon9' ${page.search3 eq 'incheon9' ? 'selected' : ''}>옹진군</option>
					<option value='incheon10' ${page.search3 eq 'incheon10' ? 'selected' : ''}>중구</option>
				</c:if>
				<c:if test='${page.search2 eq "gwangju"}'>
					<option value='gwangju1' ${page.search3 eq 'gwangju1' ? 'selected' : ''}>동구</option>
					<option value='gwangju2' ${page.search3 eq 'gwangju2' ? 'selected' : ''}>서구</option>
					<option value='gwangju3' ${page.search3 eq 'gwangju3' ? 'selected' : ''}>남구</option>
					<option value='gwangju4' ${page.search3 eq 'gwangju4' ? 'selected' : ''}>북구</option>
					<option value='gwangju5' ${page.search3 eq 'gwangju5' ? 'selected' : ''}>광산구</option>
				</c:if>
				<c:if test='${page.search2 eq "daejeon"}'>
					<option value='daejeon1' ${page.search3 eq 'daejeon1' ? 'selected' : ''}>대덕구</option>
					<option value='daejeon2' ${page.search3 eq 'daejeon2' ? 'selected' : ''}>동구</option>
					<option value='daejeon3' ${page.search3 eq 'daejeon3' ? 'selected' : ''}>서구</option>
					<option value='daejeon4' ${page.search3 eq 'daejeon4' ? 'selected' : ''}>유성구</option>
					<option value='daejeon5' ${page.search3 eq 'daejeon5' ? 'selected' : ''}>중구</option>
				</c:if>
				<c:if test='${page.search2 eq "ulsan"}'>
					<option value='ulsan1' ${page.search3 eq 'ulsan1' ? 'selected' : ''}>동구</option>
					<option value='ulsan2' ${page.search3 eq 'ulsan2' ? 'selected' : ''}>남구</option>
					<option value='ulsan3' ${page.search3 eq 'ulsan3' ? 'selected' : ''}>북구</option>
					<option value='ulsan4' ${page.search3 eq 'ulsan4' ? 'selected' : ''}>울주군</option>
					<option value='ulsan5' ${page.search3 eq 'ulsan5' ? 'selected' : ''}>중구</option>
				</c:if>
				<c:if test='${page.search2 eq "gyeonggi"}'>
					<option value='gyeonggi1' ${page.search3 eq 'gyeonggi1' ? 'selected' : ''}>가평군</option>
					<option value='gyeonggi2' ${page.search3 eq 'gyeonggi2' ? 'selected' : ''}>고양시</option>
					<option value='gyeonggi3' ${page.search3 eq 'gyeonggi3' ? 'selected' : ''}>과천시</option>
					<option value='gyeonggi4' ${page.search3 eq 'gyeonggi4' ? 'selected' : ''}>광명시</option>
					<option value='gyeonggi5' ${page.search3 eq 'gyeonggi5' ? 'selected' : ''}>광주시</option>
					<option value='gyeonggi6' ${page.search3 eq 'gyeonggi6' ? 'selected' : ''}>구리시</option>
					<option value='gyeonggi7' ${page.search3 eq 'gyeonggi7' ? 'selected' : ''}>군포시</option>
					<option value='gyeonggi8' ${page.search3 eq 'gyeonggi8' ? 'selected' : ''}>김포시</option>
					<option value='gyeonggi9' ${page.search3 eq 'gyeonggi9' ? 'selected' : ''}>남양주시</option>
					<option value='gyeonggi10' ${page.search3 eq 'gyeonggi10' ? 'selected' : ''}>동두천시</option>
					<option value='gyeonggi11' ${page.search3 eq 'gyeonggi11' ? 'selected' : ''}>부천시</option>
					<option value='gyeonggi12' ${page.search3 eq 'gyeonggi12' ? 'selected' : ''}>성남시</option>
					<option value='gyeonggi13' ${page.search3 eq 'gyeonggi13' ? 'selected' : ''}>수원시</option>
					<option value='gyeonggi14' ${page.search3 eq 'gyeonggi14' ? 'selected' : ''}>시흥시</option>
					<option value='gyeonggi15' ${page.search3 eq 'gyeonggi15' ? 'selected' : ''}>안산시</option>
					<option value='gyeonggi16' ${page.search3 eq 'gyeonggi16' ? 'selected' : ''}>안성시</option>
					<option value='gyeonggi17' ${page.search3 eq 'gyeonggi17' ? 'selected' : ''}>안양시</option>
					<option value='gyeonggi18' ${page.search3 eq 'gyeonggi18' ? 'selected' : ''}>양주시</option>
					<option value='gyeonggi19' ${page.search3 eq 'gyeonggi19' ? 'selected' : ''}>양평군</option>
					<option value='gyeonggi20' ${page.search3 eq 'gyeonggi20' ? 'selected' : ''}>여주시</option>
					<option value='gyeonggi21' ${page.search3 eq 'gyeonggi21' ? 'selected' : ''}>연천군</option>
					<option value='gyeonggi22' ${page.search3 eq 'gyeonggi22' ? 'selected' : ''}>오산시</option>
					<option value='gyeonggi23' ${page.search3 eq 'gyeonggi23' ? 'selected' : ''}>용인시</option>
					<option value='gyeonggi24' ${page.search3 eq 'gyeonggi24' ? 'selected' : ''}>의왕시</option>
					<option value='gyeonggi25' ${page.search3 eq 'gyeonggi25' ? 'selected' : ''}>의정부시</option>
					<option value='gyeonggi26' ${page.search3 eq 'gyeonggi26' ? 'selected' : ''}>이천시</option>
					<option value='gyeonggi27' ${page.search3 eq 'gyeonggi27' ? 'selected' : ''}>파주시</option>
					<option value='gyeonggi28' ${page.search3 eq 'gyeonggi28' ? 'selected' : ''}>평택시</option>
					<option value='gyeonggi29' ${page.search3 eq 'gyeonggi29' ? 'selected' : ''}>포천시</option>
					<option value='gyeonggi30' ${page.search3 eq 'gyeonggi30' ? 'selected' : ''}>하남시</option>
					<option value='gyeonggi31' ${page.search3 eq 'gyeonggi31' ? 'selected' : ''}>화성시</option>
				</c:if>
				<c:if test='${page.search2 eq "gangwon"}'>
					<option value='gangwon1' ${page.search3 eq 'gangwon1' ? 'selected' : ''}>강릉시</option>
					<option value='gangwon2' ${page.search3 eq 'gangwon2' ? 'selected' : ''}>고성군</option>
					<option value='gangwon3' ${page.search3 eq 'gangwon3' ? 'selected' : ''}>동해시</option>
					<option value='gangwon4' ${page.search3 eq 'gangwon4' ? 'selected' : ''}>삼척시</option>
					<option value='gangwon5' ${page.search3 eq 'gangwon5' ? 'selected' : ''}>속초시</option>
					<option value='gangwon6' ${page.search3 eq 'gangwon6' ? 'selected' : ''}>양구군</option>
					<option value='gangwon7' ${page.search3 eq 'gangwon7' ? 'selected' : ''}>양양군</option>
					<option value='gangwon8' ${page.search3 eq 'gangwon8' ? 'selected' : ''}>영월군</option>
					<option value='gangwon9' ${page.search3 eq 'gangwon9' ? 'selected' : ''}>원주시</option>
					<option value='gangwon10' ${page.search3 eq 'gangwon10' ? 'selected' : ''}>인제군</option>
					<option value='gangwon11' ${page.search3 eq 'gangwon11' ? 'selected' : ''}>정선군</option>
					<option value='gangwon12' ${page.search3 eq 'gangwon12' ? 'selected' : ''}>철원군</option>
					<option value='gangwon13' ${page.search3 eq 'gangwon13' ? 'selected' : ''}>춘천시</option>
					<option value='gangwon14' ${page.search3 eq 'gangwon14' ? 'selected' : ''}>태백시</option>
					<option value='gangwon15' ${page.search3 eq 'gangwon15' ? 'selected' : ''}>평창군</option>
					<option value='gangwon16' ${page.search3 eq 'gangwon16' ? 'selected' : ''}>홍천군</option>
					<option value='gangwon17' ${page.search3 eq 'gangwon17' ? 'selected' : ''}>화천군</option>
					<option value='gangwon18' ${page.search3 eq 'gangwon18' ? 'selected' : ''}>횡성군</option>
				</c:if>
				<c:if test='${page.search2 eq "chungbuk"}'>
					<option value='chungbuk1' ${page.search3 eq 'chungbuk1' ? 'selected' : ''}>괴산군</option>
					<option value='chungbuk2' ${page.search3 eq 'chungbuk2' ? 'selected' : ''}>단양군</option>
					<option value='chungbuk3' ${page.search3 eq 'chungbuk3' ? 'selected' : ''}>보은군</option>
					<option value='chungbuk4' ${page.search3 eq 'chungbuk4' ? 'selected' : ''}>영동군</option>
					<option value='chungbuk5' ${page.search3 eq 'chungbuk5' ? 'selected' : ''}>옥천군</option>
					<option value='chungbuk6' ${page.search3 eq 'chungbuk6' ? 'selected' : ''}>음성군</option>
					<option value='chungbuk7' ${page.search3 eq 'chungbuk7' ? 'selected' : ''}>제천시</option>
					<option value='chungbuk8' ${page.search3 eq 'chungbuk8' ? 'selected' : ''}>증평군</option>
					<option value='chungbuk9' ${page.search3 eq 'chungbuk9' ? 'selected' : ''}>진천군</option>
					<option value='chungbuk10' ${page.search3 eq 'chungbuk10' ? 'selected' : ''}>청주시</option>
					<option value='chungbuk11' ${page.search3 eq 'chungbuk11' ? 'selected' : ''}>충주시</option>
				</c:if>
				<c:if test='${page.search2 eq "chungnam"}'>
					<option value='chungnam1' ${page.search3 eq 'chungnam1' ? 'selected' : ''}>계룡시</option>
					<option value='chungnam2' ${page.search3 eq 'chungnam2' ? 'selected' : ''}>공주시</option>
					<option value='chungnam3' ${page.search3 eq 'chungnam3' ? 'selected' : ''}>금산군</option>
					<option value='chungnam4' ${page.search3 eq 'chungnam4' ? 'selected' : ''}>논산시</option>
					<option value='chungnam5' ${page.search3 eq 'chungnam5' ? 'selected' : ''}>당진시</option>
					<option value='chungnam6' ${page.search3 eq 'chungnam6' ? 'selected' : ''}>보령시</option>
					<option value='chungnam7' ${page.search3 eq 'chungnam7' ? 'selected' : ''}>부여군</option>
					<option value='chungnam8' ${page.search3 eq 'chungnam8' ? 'selected' : ''}>서산시</option>
					<option value='chungnam9' ${page.search3 eq 'chungnam9' ? 'selected' : ''}>서천군</option>
					<option value='chungnam10' ${page.search3 eq 'chungnam10' ? 'selected' : ''}>아산시</option>
					<option value='chungnam11' ${page.search3 eq 'chungnam11' ? 'selected' : ''}>예산군</option>
					<option value='chungnam12' ${page.search3 eq 'chungnam12' ? 'selected' : ''}>천안시</option>
					<option value='chungnam13' ${page.search3 eq 'chungnam13' ? 'selected' : ''}>청양군</option>
					<option value='chungnam14' ${page.search3 eq 'chungnam14' ? 'selected' : ''}>태안군</option>
					<option value='chungnam15' ${page.search3 eq 'chungnam15' ? 'selected' : ''}>홍성군</option>
				</c:if>
				<c:if test='${page.search2 eq "jeonbuk"}'>
					<option value='jeonbuk1' ${page.search3 eq 'jeonbuk1' ? 'selected' : ''}>고창군</option>
					<option value='jeonbuk2' ${page.search3 eq 'jeonbuk2' ? 'selected' : ''}>군산시</option>
					<option value='jeonbuk3' ${page.search3 eq 'jeonbuk3' ? 'selected' : ''}>김제시</option>
					<option value='jeonbuk4' ${page.search3 eq 'jeonbuk4' ? 'selected' : ''}>남원시</option>
					<option value='jeonbuk5' ${page.search3 eq 'jeonbuk5' ? 'selected' : ''}>무주군</option>
					<option value='jeonbuk6' ${page.search3 eq 'jeonbuk6' ? 'selected' : ''}>부안군</option>
					<option value='jeonbuk7' ${page.search3 eq 'jeonbuk7' ? 'selected' : ''}>순창군</option>
					<option value='jeonbuk8' ${page.search3 eq 'jeonbuk8' ? 'selected' : ''}>완주군</option>
					<option value='jeonbuk9' ${page.search3 eq 'jeonbuk9' ? 'selected' : ''}>익산시</option>
					<option value='jeonbuk10' ${page.search3 eq 'jeonbuk10' ? 'selected' : ''}>임실군</option>
					<option value='jeonbuk11' ${page.search3 eq 'jeonbuk11' ? 'selected' : ''}>장수군</option>
					<option value='jeonbuk12' ${page.search3 eq 'jeonbuk12' ? 'selected' : ''}>전주시</option>
					<option value='jeonbuk13' ${page.search3 eq 'jeonbuk13' ? 'selected' : ''}>정읍시</option>
					<option value='jeonbuk14' ${page.search3 eq 'jeonbuk14' ? 'selected' : ''}>진안군</option>
				</c:if>
				<c:if test='${page.search2 eq "jeonnam"}'>
					<option value='jeonnam1' ${page.search3 eq 'jeonnam1' ? 'selected' : ''}>강진군</option>
					<option value='jeonnam2' ${page.search3 eq 'jeonnam2' ? 'selected' : ''}>고흥군</option>
					<option value='jeonnam3' ${page.search3 eq 'jeonnam3' ? 'selected' : ''}>곡성군</option>
					<option value='jeonnam4' ${page.search3 eq 'jeonnam4' ? 'selected' : ''}>광양시</option>
					<option value='jeonnam5' ${page.search3 eq 'jeonnam5' ? 'selected' : ''}>구례군</option>
					<option value='jeonnam6' ${page.search3 eq 'jeonnam6' ? 'selected' : ''}>나주시</option>
					<option value='jeonnam7' ${page.search3 eq 'jeonnam7' ? 'selected' : ''}>담양군</option>
					<option value='jeonnam8' ${page.search3 eq 'jeonnam8' ? 'selected' : ''}>목포시</option>
					<option value='jeonnam9' ${page.search3 eq 'jeonnam9' ? 'selected' : ''}>무안군</option>
					<option value='jeonnam10' ${page.search3 eq 'jeonnam10' ? 'selected' : ''}>보성군</option>
					<option value='jeonnam11' ${page.search3 eq 'jeonnam11' ? 'selected' : ''}>순천시</option>
					<option value='jeonnam12' ${page.search3 eq 'jeonnam12' ? 'selected' : ''}>신안군</option>
					<option value='jeonnam13' ${page.search3 eq 'jeonnam13' ? 'selected' : ''}>여수시</option>
					<option value='jeonnam14' ${page.search3 eq 'jeonnam14' ? 'selected' : ''}>영광군</option>
					<option value='jeonnam15' ${page.search3 eq 'jeonnam15' ? 'selected' : ''}>영암군</option>
					<option value='jeonnam16' ${page.search3 eq 'jeonnam16' ? 'selected' : ''}>완도군</option>
					<option value='jeonnam17' ${page.search3 eq 'jeonnam17' ? 'selected' : ''}>장성군</option>
					<option value='jeonnam18' ${page.search3 eq 'jeonnam18' ? 'selected' : ''}>장흥군</option>
					<option value='jeonnam19' ${page.search3 eq 'jeonnam19' ? 'selected' : ''}>진도군</option>
					<option value='jeonnam20' ${page.search3 eq 'jeonnam20' ? 'selected' : ''}>함평군</option>
					<option value='jeonnam21' ${page.search3 eq 'jeonnam21' ? 'selected' : ''}>해남군</option>
					<option value='jeonnam22' ${page.search3 eq 'jeonnam22' ? 'selected' : ''}>화순군</option>
				</c:if>
				<c:if test='${page.search2 eq "gyeongbuk"}'>
					<option value='gyeongbuk1' ${page.search3 eq 'gyeongbuk1' ? 'selected' : ''}>경산시</option>
					<option value='gyeongbuk2' ${page.search3 eq 'gyeongbuk2' ? 'selected' : ''}>경주시</option>
					<option value='gyeongbuk3' ${page.search3 eq 'gyeongbuk3' ? 'selected' : ''}>고령군</option>
					<option value='gyeongbuk4' ${page.search3 eq 'gyeongbuk4' ? 'selected' : ''}>구미시</option>
					<option value='gyeongbuk5' ${page.search3 eq 'gyeongbuk5' ? 'selected' : ''}>군위군</option>
					<option value='gyeongbuk6' ${page.search3 eq 'gyeongbuk6' ? 'selected' : ''}>김천시</option>
					<option value='gyeongbuk7' ${page.search3 eq 'gyeongbuk7' ? 'selected' : ''}>문경시</option>
					<option value='gyeongbuk8' ${page.search3 eq 'gyeongbuk8' ? 'selected' : ''}>봉화군</option>
					<option value='gyeongbuk9' ${page.search3 eq 'gyeongbuk9' ? 'selected' : ''}>상주시</option>
					<option value='gyeongbuk10' ${page.search3 eq 'gyeongbuk10' ? 'selected' : ''}>성주군</option>
					<option value='gyeongbuk11' ${page.search3 eq 'gyeongbuk11' ? 'selected' : ''}>안동시</option>
					<option value='gyeongbuk12' ${page.search3 eq 'gyeongbuk12' ? 'selected' : ''}>영덕군</option>
					<option value='gyeongbuk13' ${page.search3 eq 'gyeongbuk13' ? 'selected' : ''}>영양군</option>
					<option value='gyeongbuk14' ${page.search3 eq 'gyeongbuk14' ? 'selected' : ''}>영주시</option>
					<option value='gyeongbuk15' ${page.search3 eq 'gyeongbuk15' ? 'selected' : ''}>영천시</option>
					<option value='gyeongbuk16' ${page.search3 eq 'gyeongbuk16' ? 'selected' : ''}>예천군</option>
					<option value='gyeongbuk17' ${page.search3 eq 'gyeongbuk17' ? 'selected' : ''}>울릉군</option>
					<option value='gyeongbuk18' ${page.search3 eq 'gyeongbuk18' ? 'selected' : ''}>울진군</option>
					<option value='gyeongbuk19' ${page.search3 eq 'gyeongbuk19' ? 'selected' : ''}>의성군</option>
					<option value='gyeongbuk20' ${page.search3 eq 'gyeongbuk20' ? 'selected' : ''}>청도군</option>
					<option value='gyeongbuk21' ${page.search3 eq 'gyeongbuk21' ? 'selected' : ''}>청송군</option>
					<option value='gyeongbuk22' ${page.search3 eq 'gyeongbuk22' ? 'selected' : ''}>칠곡군</option>
					<option value='gyeongbuk23' ${page.search3 eq 'gyeongbuk23' ? 'selected' : ''}>포항시</option>
				</c:if>
				<c:if test='${page.search2 eq "gyeongnam"}'>
					<option value='gyeongnam1' ${page.search3 eq 'gyeongnam1' ? 'selected' : ''}>거제시</option>
					<option value='gyeongnam2' ${page.search3 eq 'gyeongnam2' ? 'selected' : ''}>거창군</option>
					<option value='gyeongnam3' ${page.search3 eq 'gyeongnam3' ? 'selected' : ''}>고성군</option>
					<option value='gyeongnam4' ${page.search3 eq 'gyeongnam4' ? 'selected' : ''}>김해시</option>
					<option value='gyeongnam5' ${page.search3 eq 'gyeongnam5' ? 'selected' : ''}>남해군</option>
					<option value='gyeongnam6' ${page.search3 eq 'gyeongnam6' ? 'selected' : ''}>밀양시</option>
					<option value='gyeongnam7' ${page.search3 eq 'gyeongnam7' ? 'selected' : ''}>사천시</option>
					<option value='gyeongnam8' ${page.search3 eq 'gyeongnam8' ? 'selected' : ''}>산청군</option>
					<option value='gyeongnam9' ${page.search3 eq 'gyeongnam9' ? 'selected' : ''}>양산시</option>
					<option value='gyeongnam10' ${page.search3 eq 'gyeongnam10' ? 'selected' : ''}>의령군</option>
					<option value='gyeongnam11' ${page.search3 eq 'gyeongnam11' ? 'selected' : ''}>진주시</option>
					<option value='gyeongnam12' ${page.search3 eq 'gyeongnam12' ? 'selected' : ''}>청녕군</option>
					<option value='gyeongnam13' ${page.search3 eq 'gyeongnam13' ? 'selected' : ''}>창원시</option>
					<option value='gyeongnam14' ${page.search3 eq 'gyeongnam14' ? 'selected' : ''}>통영시</option>
					<option value='gyeongnam15' ${page.search3 eq 'gyeongnam15' ? 'selected' : ''}>하동군</option>
					<option value='gyeongnam16' ${page.search3 eq 'gyeongnam16' ? 'selected' : ''}>함안군</option>
					<option value='gyeongnam17' ${page.search3 eq 'gyeongnam17' ? 'selected' : ''}>함양군</option>
					<option value='gyeongnam18' ${page.search3 eq 'gyeongnam18' ? 'selected' : ''}>합천군</option>
				</c:if>
				<c:if test='${page.search2 eq "jeju"}'>
					<option value='jeju1' ${page.search3 eq 'jeju1' ? 'selected' : ''}>서귀포시</option>
					<option value='jeju2' ${page.search3 eq 'jeju2' ? 'selected' : ''}>제주시</option>
				</c:if>
			</select>
			<!-- 직접 입력하여 내용 검색 -->
			<select name='search4' onchange='$("form").submit()' style='float:left; margin:5px 5px 0px 0px;'>
				<option value='all' ${page.search4 eq 'all' ? 'selected' : ''}>전체 검색</option>
				<option value='title' ${page.search4 eq 'title' ? 'selected' : ''}>제목</option>
				<option value='content' ${page.search4 eq 'content' ? 'selected' : ''}>내용</option>
			</select>
			<input type='text' name='keyword' value='${page.keyword}' style='float:left; margin:5px 5px 0px 0px;'/>
			<button style='float:left; margin-right:5px; width:60px; margin-top:5px;'>
				<a>검색</a>
			</button>
			<!-- 로그인 되어 있는 경우에만 글쓰기 가능 -->
			<c:if test='${not empty login_info}'>
			<button style='float:right; margin-right:2px; width:75px; height:35px;'>
				<a href='new.bo' style='color:#111111; font-size:17px;'>글쓰기</a>
			</button>
			</c:if>
			<!-- 로그인 되어 있지 않은 경우에는 로그인 페이지로 이동 -->
			<c:if test='${empty login_info}'>
			<button style='float:right; margin-right:2px; width:75px; height:35px;'>
				<a style='color:#111111; font-size:17px;' href='loginPage'>글쓰기</a>
			</button>
			</c:if>
		</div>
	</div>
	
	<!-- 테이블 : 게시글 목록 -->
	<div style='width:100%; float:left;'>	
		<table style='width:90%'>
			<tr>
				<th class='w-px70'>게시판</th>
				<th class='w-px180'>지역</th>
				<th class='w-px500'>제목</th>
				<th class='w-px100'>작성자</th>
				<th class='w-px150'>작성일자</th>
				<th class='w-px50'>사진</th>
			</tr>
			<c:forEach items='${page.list}' var='vo'>
			<tr>
				<td>${vo.board_subject}</td>
				<td>${vo.board_city} ${vo.board_region}</td>
				<td>
					<span style='color:#FFB4BE; font-weight:bold; float:left; margin-left:5px;'>♡&nbsp;</span>
						<c:if test='${vo.member_id eq "admin"}'>
							<a href='detail.bo?board_num=${vo.board_num}' style='color:#000000; font-weight:bold;'>${vo.board_title}</a>
						</c:if>
						<c:if test='${vo.member_id ne "admin"}'>
							<a href='detail.bo?board_num=${vo.board_num}' style='color:#000000;'>${vo.board_title}</a>
						</c:if>
					<span style='color:#FFB4BE; font-weight:bold; float:right; margin-right:5px;'>&nbsp;♡</span>
				</td>
				<td>
					<c:if test='${vo.member_id eq "admin"}'>
						<font style='font-weight:bold;'>${vo.member_id}</font>
					</c:if>
					<c:if test='${vo.member_id ne "admin"}'>
						${vo.member_id}
					</c:if>
				</td>
				<td>${vo.board_date}</td>
				<td>${vo.board_imagepath == null || vo.board_imagepath == 'null' ? '' : '<img src="img/attach.png" width="15px" height="15px"/>'}</td>
			</tr>
			</c:forEach>
		</table>
	</div>
	
	<!-- 페이징처리 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; height:30px; margin:0 auto; background-color:#FFB4BE;'>
			<jsp:include page="/WEB-INF/views/include/page.jsp" />
		</div>
	</div>

</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.grid li div span {float: right;}
#top { margin-bottom: 10px; }
/* 제목이 너무 길면 말줄임 처리 */
table { table-layout: fixed;}
table td { overflow: hidden; text-overflow:ellipsis; white-space: nowrap; } 
/*그리드에서 처리*/
.grid li div:nth-child(1) {
 overflow: hidden;
 display: -webkit-box; 
 -webkit-line-clamp: 3; /*보이게 할 줄수 */
 -webkit-box-orient:vertical;
 word-wrap : break-word;/*영문*/
 }
</style>
</head>
<body>
<h3>방명록 목록</h3>
<div id='list-top'>
<form method='post' action='list.bo'>
<input type='hidden' name='curPage' value='1' />
<input type='hidden' name='id'/>
<div id="top">
	<ul>
		<li>
			<select name='search' class='w-px80'>
			<option value='all' ${page.search eq 'all' ? 'selected' : ''}>전체</option>
			<option value='title' ${page.search eq 'title' ? 'selected' : ''}>제목</option>
			<option value='content' ${page.search eq 'content' ? 'selected' : ''}>내용</option>
			<option value='writer' ${page.search eq 'writer' ? 'selected' : ''}>작성자</option>
			</select>
		</li>
		<li><input type='text' name='keyword' value='${page.keyword}' class='w-px300' /></li>
		<li><a class='btn-fill'>검색</a></li>
	</ul>
	<ul>
		<li>
			<select name='pageList' class='w-px80' 
				onchange='$("[name=curPage]").val(1); $("form").submit()'>
			<option value='10' ${page.pageList eq 10 ? 'selected' : ''}>10개씩</option>
			<option value='20' ${page.pageList eq 20 ? 'selected' : ''}>20개씩</option>
			<option value='30' ${page.pageList eq 30 ? 'selected' : ''}>30개씩</option>
			</select>
		</li>
		<li>
			<select name='viewType' class='w-px100' onchange='$("form").submit()'>
			<option value='list' ${page.viewType eq 'list' ? 'selected' : ''}>리스트형태</option>
			<option value='grid' ${page.viewType eq 'grid' ? 'selected' : ''}>그리드형태</option>
			</select>
		</li>
	
		<!-- 로그인 되어 있는 경우 글쓰기 가능 -->
		<c:if test='${not empty login_info}'>
		<li>
			<a href='new.bo' class='btn-fill'>글쓰기</a>
		</li>
		</c:if>
	</ul>
</div>
</form>
</div>

<div id='data-list'>
<c:if test='${page.viewType eq "list"}'>
<table>
<tr><th class='w-px80'>번호</th>
	<th>제목</th>
	<th class='w-px120'>작성자</th>
	<th class='w-px120'>작성일자</th>
	<th class='w-px80'>첨부파일</th>
</tr>
<c:forEach items="${page.list}" var="vo">
<tr><td>${vo.no}</td>
	<td class='left'><a href="javascript:go_detail(${vo.id })">${vo.title}</a></td>
	<td>${vo.name}</td>
	<td>${vo.writedate}</td>
   	<td>${ empty vo.filename ? '' : '<img class="file-img" src="img/attach.png"/>'}</td>
</tr>
</c:forEach>
</table>
</c:if>

<c:if test="${page.viewType eq 'grid'}">
<ul class='grid' style="padding: 0">
<c:forEach items="${page.list}" var="vo">
	<li><div><a href="javascript:go_detail(${vo.id })">${vo.title}</a></div>
		<div>${vo.name}</div>
		<div>${vo.writedate}<span>${empty vo.filename ? '' : '<img class="file-img" src="img/attach.png"/>'}</span></div>
	</li>
</c:forEach>
</ul>
</c:if>
</div>

<div class='btnSet'>
<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>
<script type="text/javascript">
$(function(){
	$('#data-list ul').css('height',
		(($('.grid li').length%5 > 0 ? 1 : 0) + Math.floor($('.grid li').length/5) )*$('.grid li').outerHeight(true)-20
	);
});

function go_detail(id){
	$('[name=id]').val(id);
	$('form').attr('action', 'detail.bo');
	$('form').submit();
}
</script>
</body>
</html>
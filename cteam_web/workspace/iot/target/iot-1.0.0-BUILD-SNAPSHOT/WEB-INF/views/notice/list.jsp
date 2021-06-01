<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>[공지글 목록]</h3>
<form method="post" action="list.no">
<input type="hidden" name="curPage" value="1">
<div id="list-top">
	<div>
	<ul>
		<li>
			<select name="search" class="w-px80">
				<option value="all" ${page.search eq 'all' ? 'selected' : '' }>전체</option>
				<option value="title" ${page.search eq 'title' ? 'selected' : '' }>제목</option>
				<option value="content" ${page.search eq 'content' ? 'selected' : '' }>내용</option>
				<option value="writer" ${page.search eq 'writer' ? 'selected' : '' }>작성자</option>
			</select>
		</li>
		<li><input type="text" name="keyword" class="w-px300" value="${page.keyword }"/></li>
		<li><a class="btn-fill" onclick="$('form').submit()">검색</a></li>
	</ul>
	<ul>
		<li>
		<!-- 관리자만 글쓰기 가능 -->
		<c:if test="${login_info.admin eq 'Y' }">
			<a class="btn-fill" href="new.no">글쓰기</a>
		</c:if>
		</li>
	</ul>
	</div>
</div>
</form>

<table>
<tr>
	<th class="w-px60">번호</th>
	<th>제목</th>
	<th class="w-px100">작성자</th>
	<th class="w-px120">작성일자</th>
	<th class="w-px100">파일첨부</th>
</tr>
<c:forEach items="${page.list }" var="vo">
<tr>
	<td>${vo.no }</td>
	<td class="left">
	<c:forEach var="i" begin="1" end="${vo.indent }">
		&nbsp;&nbsp;
		<c:if test="${i eq vo.indent }">
		<img src="img/re.gif" />
		<%-- ${ vo.indent gt 0 ? '<img src="img/re.gif"/>' : '' } --%>
	</c:if>
	</c:forEach>
	<a href="detail.no?id=${vo.id }">${vo.title }</a></td>
	<td>${vo.name }</td>
	<td>${vo.writedate }</td>
	<td>
		<c:if test="${! empty vo.filename }">
			<img src="img/attach.png" class="file-img">
		</c:if>
	</td>
</tr>
</c:forEach>
</table>
<br/>
<div>
<jsp:include page="/WEB-INF/views/include/page.jsp" />
</div>
<br/>
</body>
</html>
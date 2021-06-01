<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

/* input {
	height: 12px;	
}
 */

table th {
	background-color: #FFB4BE;
}
textarea[name=content] { width:calc(100% - 6px); 
	resize:none; height:150px;  }
	
table {
	width: 80%;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	text-align: center;
	margin-bottom: 30px;
}

.left{text-align: left;}
.right {text-align: right; }

#list-top { padding:20px 10%; } 
#list-top div { width:100%; height:32px; }
#list-top div ul { margin:0; display:flex; padding:0  }
#list-top div ul:first-child { float:left; }
#list-top div ul:last-child { float:right; }
#list-top div ul li * { vertical-align:middle; } 
#list-top div ul li:not(:first-child) { margin-left:2px; } 

table th, table td {
	border: 1px solid black;
	padding: 5px 10px;
}

table th {
	background-color: #FFB4BE;
}

select { height:32px; }
</style>
</head>
<body>

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
		<li><a class="btn-fill-s" onclick="$('form').submit()" style="padding:6px 13px; margin-top: 10px;">검색</a></li>
	</ul>
	<ul>
		<li>
		<!-- 관리자만 글쓰기 가능 -->
		<c:if test="${login_info.admin eq 'Y' }">
			<a class="btn-fill-s" href="new.no" style="padding: 10px 15px;">글쓰기</a>
		</c:if>
		</li>
	</ul>
	</div>
</div>
</form>

<table>
<tr>
	<th class="w-px100">번호</th>
	<th>제목</th>
	<th class="w-px120">작성자</th>
	<th class="w-px140">작성일자</th>
	<th class="w-px120">파일첨부</th>
</tr>
<c:forEach items="${page.list }" var="vo">
<tr>
	<td>${vo.no }</td>
	<td class="left">
	<c:forEach var="i" begin="1" end="${vo.indent }">
		&nbsp;&nbsp;
		<c:if test="${i eq vo.indent }">
		<img src="img/re.gif" />
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
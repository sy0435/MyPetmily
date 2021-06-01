<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>[공지글 안내]</h3>

<table>
	<tr>
		<th class="w-px160">제목</th>
		<td colspan="5" class="left">${vo.title }</td>
	</tr>
		<tr>
		<th class="w-px120">작성자</th>
		<td class="w-px120">${vo.name }</td>
		<th class="w-px120">작성일자</th>
		<td class="w-px120">${vo.writedate }</td>
		<th class="w-px120">조회수</th>
		<td class="w-px120">${vo.readcnt }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td colspan="5" class="left">${fn:replace(vo.content, crlf, '<br/>') }</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td colspan="5" class="left">${vo.filename }
		<c:if test="${! empty vo.filename }">
		<a href='download.no?id=${vo.id }'><i class="fas fa-save font-img"></i></a>
		</c:if>
		<c:if test="${ empty vo.filename }">
			<span>첨부된 파일이 없습니다</span>
		</c:if>
		</td>
	</tr>
</table>
	<div class="btnSet">
		<!-- <a class='btn-fill' href='list.no'>목록으로</a> -->
		<a class='btn-fill' href='javascript:$("form").submit()'>목록으로</a>
		<c:if test="${login_info.admin eq 'Y' }">
		<a class="btn-fill" href='modify.no?id=${vo.id }'>수정</a>
		<a class="btn-fill" onclick="if(confirm('정말 삭제하시겠습니까?')){ href='delete.no?id=${vo.id }'}" >삭제</a>
		</c:if>
		<!-- 로그인한 경우 무조건 답글작성 가능하다고 가정 -->
		<c:if test="${!empty login_info }">
		<a class="btn-fill" href="reply.no?id=${vo.id }">답글쓰기</a>
		</c:if>
	</div>
<br/>

<form method='post' action='list.no'>
<input type='hidden' name='curPage' value='${page.curPage}'/>
<input type='hidden' name='search' value='${page.search}'/>
<input type='hidden' name='keyword' value='${page.keyword}'/>
</form>

</body>
</html>
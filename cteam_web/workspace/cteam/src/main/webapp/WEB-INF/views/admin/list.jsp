<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#item_imgpath {
		width: 80px;
		height: 80px;
	}
	

	
	table {
	 width: 60%;
	 border-collapse: collapse;
	 margin-bottom: 50px;
	 margin-top: 10px;
	 text-align: center;
	 border: 3px solid #FFB4BE;
	}
	
	table td {
	 border-bottom: 1px solid #FFB4BE;
	}
	
	table th {
	 padding: 10px;
	 background-color: #D7567F;
	 color: white;
	}
	
	#go_add{
		margin-bottom: 30px;
		margin-left: 50%;
	}

</style>
</head>
<body>
<h2>상품 관리</h2>
	<a id="go_add" class="btn-fill-s" href="itemNew.ad">제품등록하기</a>

<form method='post' action='list.ad'>
<input type='hidden' name='curPage' value='1' />
	<div align="center" id="data-list">
	<table>
		<tr>
			<th>상품 사진</th>
			<th>상품 코드</th>
			<th>상품 이름</th>
			<th>상품 가격</th>
			<th></th>
		</tr>
	<c:forEach items="${page.list}" var="list"> 
		<tr>
			<td><img id="item_imgpath" src="<c:url value='/' />${list.item_imgpath }"/></td>
			<td>${list.item_code }</td>
			<td>${list.item_name }</td>
			<td>${list.item_price } 원</td>
			<td><a href="itemModify.ad?item_num=${list.item_num }" class="btn-fill-s" style="background-color: pink; color: #666;">수정</a>
<%-- 			<a href="item_delete.ad?item_num=${list.item_num } onclick="if( confirm('정말 삭제?') ) { $('form').attr('action','item_delete.ad?item_num=${list.item_num } ');  --%>
<!-- 			$('form').submit() }" class="btn-empty-s" style="color: white; background-color: #666;">삭제</a> </td> -->
		</tr>
	</c:forEach>
	</table>
</div>
</form>

<!-- 페이징처리 -->
	<div style='width:100%; float:left; margin:10px 0;' >
		<div style='width:90%; height:30px; margin:0 auto; '>
			<jsp:include page="/WEB-INF/views/include/page2.jsp" />
		</div>
	</div>

</body>
</html>
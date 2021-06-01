<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
 <%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>상품페이지</title>
<link rel="stylesheet" type="text/css" href="css/common.css?v="<%=new java.util.Date().getTime() %>">

<style type="text/css">
img.shop-img { width: 306px; height: 396px;}
span{display:block;  margin-bottom: 10px; text-align: left;}
span.item-title{ font-weight: bold; color: gray; font-size: 20px; margin-top: 5px; }
span.item-price{ color: purple; font-size: 15px; font-weight: bold;}
ul li{ float: left; margin-right: 20px;}
ul{width:100%; padding: 0px; margin: 0 auto; margin-left: 100px;}
input.search-input{ border-radius: 20px; background-color: #f7f7f6; width:200px; border: #f7f7f6; outline: none; font-size: 13px; padding-left: 10px;}
#top	{margin-bottom:10px;}
#search	{border:none; padding:5px; outline: none; width:20px; }
</style>

</head>
<body>
	
	
	<form method="post" action="list.sh">
	<input type="hidden" name='curPage' value='1'/>
	<input type="hidden" name='item_num' />
		
				
		<div id="top" style="margin: 50px auto">
		
			
			
		<div style="float:right;">
		
		
			<ul>
				<li >
					<select id="search" name="search" class='w-px80'>
					<option value='all' ${page.search eq 'all' ? 'selected' : '' } >전체</option>
					<option value='item_content' ${page.search eq 'item_content' ? 'selected' : '' } >내용</option>
					<option value='item_name' ${page.search eq 'item_name' ? 'selected' : '' } >상품이름</option>
					</select>
				</li>
				<li><input type="text" class="search-input" name="keyword" value="${page.keyword }" class='w-px300'></li>
				<li><a class='search-btn' onclick="$('form').submit()"><i class='fas fa-search'></i></a></li>
			</ul>
			<br/><br/><br/>
		</div>

			
			
			<ul style="overflow: hidden;">
			
				<c:set var="fir" value="0"/>
				
				<c:forEach items="${page.list }" var="vo" varStatus="status">
							<li>
							<div style="width: 306px; box-sizing: border-box;" >
							<c:if test="${!empty vo.item_num }">
									<a href="javascript:go_detail(${vo.item_num })"><img src="<c:url value='/' />${vo.item_imgpath }" class="shop-img"/></a>	
							</c:if>	
								<a href="javascript:go_detail(${vo.item_num })">
									<span class="item-name" style="font-weight: bold">${vo.item_name}</span>
									<span class="item-price"> <fmt:formatNumber value="${vo.item_price}" /></span>
								</a>
							</div>
						</li>
					
				</c:forEach>
				
				
			</ul> 
		</div>
		
		<div class='btnSet'>
			<%-- <jsp:include page="/WEB-INF/views/include/page.jsp/" /> --%>
		</div>
	</form>	
	<script type="text/javascript">

	function go_detail( item_num ){
		$('[name=item_num]').val(item_num);
		$('form').attr('action','item.detail');
		$('form').submit();
	}

	</script>	
</body>
</html>
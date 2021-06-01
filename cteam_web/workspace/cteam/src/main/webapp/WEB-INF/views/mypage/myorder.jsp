<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<style type="text/css">
table {
	border-collapse: collapse;
	margin-bottom: 50px;
	border: 1px solid #969696
	;
}
table td {
	padding: 5px;
}
table th {
 border-bottom: 1px solid #FFB4BE;	
 width: 300px;
 padding: 5px;
}
#ordernum {
	border-bottom: 1px solid #FFB4BE;
	text-align: left;
}
label {
	margin-right: 100px;
	margin-left: 10px;
}
#last {
	 background-color: #FFB4BE;
}
#title {
	background-color: #D7567F;
	color: white;
	height: 30px;
	}

#item_content_imgpath {
	width: 80px;
	height: 80px;
}
</style>
</head>
<body>
<div align="center">
<h2>주문정보 조회</h2>
<c:forEach items="${orderlist }" var="vo"> 
<table style="width: 80%">
	<tr id="title">
		<th colspan="5" id="ordernum"> <label>주문날짜 ${vo.order_date }</label> 주문번호 》 <a style="color: white;" href="orderdetail.my?order_num=${vo.order_num }" >${vo.order_num }</a></th>
	<tr/>
	<tr style="text-align: center;">
		<th>상품</th>
		<th>상품명/옵션</th>
		<th>상품금액/수량</th>
		<th>주문상태</th>
		<th>금액</th>
		<!-- <th>리뷰</th> -->
	</tr>
		
	<c:set var = "total" value = "0" />
 	<c:forEach items="${vo.order_item}" var="item"> 
		<c:set var="options" value="${fn:split(item.option_info, '/')}" />
			<c:forEach begin="0" end="${fn:length(options)-1}" varStatus="optionsStatus">
			<c:set var="option" value="${options[optionsStatus.index]}" />
			<c:set var="detail" />
				<c:forEach begin="0" end="${fn:length(option)-1}" varStatus="optionStatus">
					<c:set var="detail" value="${fn:split(option, '@')}" />
				</c:forEach>
					
		<tr style="text-align: center;">
			<td><img id="item_content_imgpath" src="<c:url value='/' />${item.item_imgpath }"/></td>
			
			<td>${item.item_name } / ${detail[0]}</td>
			<td><fmt:formatNumber value="${item.item_price  }" />￦ / ${detail[1]}개</td>
			<td>${vo.order_state }</td>
			<td class="price"><fmt:formatNumber value="${item.item_price * detail[1]}" /></td>
			<!-- <td><button value="리뷰작성" class="test">리뷰작성</button></td> -->
		</tr>
		<c:set var= "total" value="${total + item.item_price * detail[1]}"/>
	
	</c:forEach>
	
	
	</c:forEach>			
			
		<c:if test="${total lt 50000 }">
		<tr align="right"  style="border-top: 1px solid #FFB4BE;">
			<td colspan="4">(5만원 이상 무료배송) 배송비</td><td>＋ 2,500￦</td>
		</tr>
		<tr align="right" id="last">
<%-- 			<td colspan="4">총액</td><td><c:out value="${total + 2500}"/>￦</td> --%>
			<td colspan="4">총액</td><td><fmt:formatNumber value="${total + 2500}" />￦</td>
		</tr>
		</c:if>
		
		<c:if test="${total gt 50000 }">
		<tr align="right" style="border-top: 1px solid #FFB4BE;">
			<td colspan="4">(5만원 이상 무료배송) 배송비</td><td>＋ 0￦</td>
		</tr>
		<tr align="right" id="last">
<%-- 			<td colspan="4">총액</td><td><c:out value="${total}"/>￦</td> --%>
			<td colspan="4">총액</td><td><fmt:formatNumber value="${total}" />￦</td>
		</tr>
		</c:if> 
		
		</table>		
		
	</c:forEach>


</div>
	
<script type="text/javascript">

</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문상세</title>
<style type="text/css">
.detail {
	border-collapse: collapse;
	margin-bottom: 50px;
	border: 1px solid #969696;
	line-height: 1.5;
}
.detail td {
	padding: 5px;
}
.detail th {
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



.head {
	background-color: #999999;
}
table.type07 {
    /* text-align: left; */
    border: 1px solid #ccc;
    /*margin: 20px 10px; */
    width: 100%;
    margin-bottom: 20px;
}
table.type07 thead {
    border-right: 1px solid #ccc;
    border-left: 1px solid #ccc;
    background: #D8EBA3;
}
table.type07 thead th {
    padding: 5px;
    font-weight: bold;
    vertical-align: top;
    color: #3C422C;
}
table.type07 tbody th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #F9F6D9;
}
table.type07 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
</style>
</head>
<body>
<div align="center">
<h1>주문번호 [${list.order_num }] 상세내역</h1>
		

<table class="detail" style="width: 80%">
	<tr id="title">
		<th colspan="3" id="ordernum"> <label>주문날짜 ${list.order_date }</label> 주문번호 》${list.order_num }</th>
		<th>${list.order_state}</th>
	<tr>
	<tr>
		<th>주문자</th>
		<td colspan="3" style="border-bottom: 1px solid #FFB4BE; font-weight: bold;">${list.member_id}</td>
	</tr>
	<tr style="text-align: center;">
		<th>상품</th>
		<th>상품명/옵션</th>
		<th>상품금액/수량</th>
		<th>금액</th>
	</tr>
	
	<c:forEach items="${list.order_item}" var="item"> 
	</c:forEach>
	<c:set var = "total" value = "0" />
 	<c:forEach items="${list.order_item}" var="item"> 
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
			<td class="price"><fmt:formatNumber value="${item.item_price * detail[1]}" /> ￦</td>
		</tr>
		<c:set var= "total" value="${total + item.item_price * detail[1]}"/>
	
	</c:forEach>
	
	
	</c:forEach>			
			
		<c:if test="${total lt 50000 }">
		<tr align="right"  style="border-top: 1px solid #FFB4BE;">
			<td colspan="3">(5만원 이상 무료배송) 배송비</td><td>＋ 2,500￦</td>
		</tr>
		<tr align="right" id="last">
			<td colspan="3">총액</td><td><fmt:formatNumber value="${total+2500}" />￦</td>
		</tr>
		</c:if>

			<c:if test="${total gt 50000 }">
		<tr align="right" style="border-top: 1px solid #FFB4BE;">
			<td colspan="3">(5만원 이상 무료배송) 배송비</td><td>＋ 0￦</td>
		</tr>
		<tr align="right" id="last">
			<td colspan="3">총액</td><td><fmt:formatNumber value="${total}" />￦</td>
		</tr>
		</c:if> 
		</table>		
	<div  class="ship" style="width: 80%" >
	<table class="type07">
    <thead>
    <tr>
        <th colspan="2" style="text-align: center;font-size: 1.2em" >주문자 정보</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">성명</th>
        <td>${list.member_name }</td>
    </tr>
    <tr>
        <th scope="row">전화번호</th>
        <td>${list.member_phonenum }</td>
    </tr>
    <tr>
        <th scope="row">이메일</th>
        <td>${list.member_email }</td>
    </tr>
    </tbody>
</table>
	<table class="type07">
    <thead>
    <tr>
        <th colspan="2" style="text-align: center;font-size: 1.2em" >배송지 정보</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">성명</th>
        <td>${list.shipping_name }</td>
    </tr>
    <tr>
        <th scope="row">전화번호</th>
        <td>${list.shipping_phonenum }</td>
    </tr>
    <tr>
        <th scope="row">항목명</th>
        <td>내용이 들어갑니다.</td>
    </tr>
    <tr>
        <th scope="row">배송 메세지</th>
        <td>${list.shipping_message }</td>
    </tr>
    <tr>
        <th scope="row">배송지 주소</th>
        <td>${list.shipping_address }<br/>${list.shipping_address2 }</td>
    </tr>
    </tbody>
</table>
</div>
</div>
</html>
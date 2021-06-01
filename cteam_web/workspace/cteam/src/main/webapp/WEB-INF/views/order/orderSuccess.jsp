<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
th.th-top {
	padding-bottom: 10px;
	border-bottom: 1px solid #E1E1E1;
}

td.th-middle-top {
	padding-bottom: 10px;
	border-bottom: 1px solid #E1E1E1;
	text-align: left;
	color: #000000;
	font-size: 20px;
	font-weight: bold;
}

td.th-middle {
	color: #777777;
	padding: 10px;
	border-bottom: 1px solid #E1E1E1;
}

table {
	margin: 50px auto;
}

input {
	border: 1px solid #dedede;
	color: #565656;
	height: 16px;
	outline: none;
}

h3{
	color:#666666;
	margin-bottom: 50px;
}

a.listBtn{
	background-color: black;
	color:white;
	font-size: 13px;
	padding: 10px;
	display:inline-block;
	width: 200px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
		<div>
			
			<c:set var = "total" value = "0" />
			<c:forEach var="vo" items="${goods }">
				<h3> ${vo.member_name }님의 주문 [ ${vo.order_num } ] 가 접수되었습니다 </h3>
			
		
		
			<table style="margin: 0 auto;">
				<tr style="color: #777; font-size: 12px;">
					<th class="w-px120 th-top">사진</th>
					<th class="w-px350 th-top">상품명</th>
					<th class="w-px160 th-top">가격</th>
					<th class="w-px120 th-top">수량</th>
					<th class="w-px200 th-top">합계</th>
				</tr>
			
				<c:set value="${fn:split(vo.option_info,'/') }" var="options"/>
					<c:forEach begin="0" end="${fn:length(options) -1 }" varStatus="optionsStatus">
					 <c:set value="${options[optionsStatus.index]}" var="option"/>
					 <c:set var="detail"/>
						<c:forEach begin="0" end="${fn:length(option)-1 }" varStatus="optionStatus">
							<c:set var="detail" value="${fn:split(option, '@') }"/>
						</c:forEach>
					
					<tr>
						<td><img class="imgpath"
							src="<c:url value='/' />${shopVO.item_imgpath }"
							style="width: 80; height: 113px;"></td>
						<td>${shopVO.item_name } / ${detail[0]}</td>
						<td><fmt:formatNumber value="${shopVO.item_price }" /> 원</td>
						<td>${detail[1] }</td>
						<td><fmt:formatNumber value="${shopVO.item_price * detail[1] }" /> ￦</td>
						
						<c:set var="total" value="${total + shopVO.item_price * detail[1] }"/>
					</tr>
		
					
	
					
				</c:forEach>
					
				<c:choose>
				<c:when test="${total lt 50000 }">
					<tr
					style="background-color: #F4F4F4; color: #6d6c71; font-size: 12dp; text-align: right;">
					<td colspan="6" style="padding: 10px;" class="right">total cost: <fmt:formatNumber value="${total}" /> + 배송비 2,500 = <fmt:formatNumber value="${total+2500}" /> ￦
					</tr>
				</c:when>
				<c:otherwise>
					<tr
						style="background-color: #F4F4F4; color: #6d6c71; font-size: 12dp; text-align: right;">
						<td colspan="6" style="padding: 10px;" class="right">total cost: <fmt:formatNumber value="${total}" /> + 배송비 0 = <fmt:formatNumber value="${total}" /> ￦
					</tr>
				
				</c:otherwise>
				</c:choose>
					
					</table>
					
					<table>
				<tr>
					<td class="th-middle-top" colspan="2">주문정보</td>
				</tr>
				
				<tr>
					<td class="w-px120 th-middle" style="color:black;">주문일시:</td>
					<td class="w-px500 th-middle" style="color:black;">${vo.order_date }</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">주문자명:</td>
					<td class="w-px500 th-middle">${vo.member_name }</td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">E-mail:</td>
					<td class="w-px500 th-middle">${vo.member_email }</td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">연락처:</td>
					<td class="w-px500 th-middle">${vo.member_phonenum }</td>
				</tr>

				</table>
				
				<table>
				<tr>
					<td class="th-middle-top" colspan="2">받으시는 분</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">수취인 성함:</td>
					<td class="w-px500 th-middle">${vo.shipping_name }</td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">수취인연락처:</td>
					<td class="w-px500 th-middle">${vo.shipping_phonenum }</td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">배송지:</td>
					<td class="w-px500 th-middle">[${vo.shipping_post }] ${vo.shipping_address }${vo.shipping_address2 }</td>

				</tr>
				<tr>
					<td class="w-px120 th-middle">배송메세지</td>
					<td class="w-px120 th-middle">${vo.shipping_message }</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">결제수단</td>
					<td class="w-px120 th-middle">${vo.pay }</label>
					</td>
				</tr>
			</table>
					
				</c:forEach>
		
			<a href="javascript:history.go(-3)" class="listBtn"> 목록으로</a>
		
		</div>
		
		
		
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
        <%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<style>

td{ border-bottom:1px solid #E1E1E1;}


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

a.orderbtn{

	background-color: black;
	color:white;
	font-size: 13px;
	padding: 10px;
	display:inline-block;
	width: 200px;
	margin: 50px auto;
}
</style>
</head>

<body>

		<h1>Cart</h1>

	 <form action="cart_order" method="post"> 
			<table style="margin: 0 auto;">
				<tr style="color: #777; font-size: 12px;">
					<th class="w-px120 th-top">사진</th>
					<th class="w-px250 th-top">상품명</th>
					<th class="w-px160 th-top">가격</th>
					<th class="w-px120 th-top">수량</th>
					<th class="w-px200 th-top">합계</th>
					<th class="w-px100 th-top"></th>
				</tr>
		
			
					
					<c:set var="total" value="0"/>
					
					<c:forEach items="${list }" var="vo">
					
					
					
					<c:set var="options" value="${fn:split(vo.option_info, '/')}" />
					<c:forEach begin="0" end="${fn:length(options)-1}"
						varStatus="optionsStatus">
						<c:set var="option" value="${options[optionsStatus.index]}" />
						<c:set var="detail" />
						<c:forEach begin="0" end="${fn:length(option)-1}"
							varStatus="optionStatus">
							<c:set var="detail" value="${fn:split(option, '@')}" />
						</c:forEach>
						<tr>
							<td style="border-bottom:1px solid #E1E1E1;"><img class="imgpath"
								src="<c:url value='/' />${vo.order_item.item_imgpath }"
								style="width: 80; height: 113px; margin:10px auto; "></td>
					
				
	
							<td style="color:#666666">${vo.order_item.item_name } / ${detail[0]}</td>
							<td><fmt:formatNumber value="${vo.order_item.item_price}" />원</td>
							<td>${detail[1]}</td>
							<td><fmt:formatNumber value="${vo.order_item.item_price * detail[1]}" />원</td>
							<td><a class="deletebtn" onclick="delete_cart(${vo.cart_num})">delete</a></td>
							
							
								<%-- <td><input type="checkbox" name="cart_num" value="${vo.cart_num }"/></td> --%>
					
							
							<c:set var="total" value="${total+vo.order_item.item_price * detail[1] }"/>
							
						</tr>
					</c:forEach>
					
					
				
				</c:forEach>
				
				<tr style="background-color: #F4F4F4; color: #6d6c71; font-size: 12dp; text-align: right;">
						<td colspan="7" style="padding: 10px;" class="right">total
							cost : <fmt:formatNumber value="${total}" />￦</td>
<%-- 							cost : <c:out value= "${total}"/>￦</td> --%>
				</tr>
					
					
			</table>
			
		</form>

			<a onclick="submit()" class="orderbtn" >order</a>

<script type="text/javascript">
	function delete_cart(cart_num){
			$.ajax({
				url:"cart_delete",
				data:{cart_num:cart_num},
				success:function(data){
					if(data!=""){
						alert("삭제되었습니다");
						location.reload();
					}
				}
			});
	}

	function submit(){

		if(${fn:length(list)} < 1){
			alert("장바구니에 상품이 없습니다");
				return false;
		}
		
		$('form').submit();

	}

</script>
</body>

</html>
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

a.btn-pay{
	background-color: black;
	color:white;
	font-size: 13px;
	padding: 10px;
	display:inline-block;
	width: 200px;
	margin: 50px auto;

}
 a.btn-back{
	background-color: white;
	color:black;
	border:1px solid black;
	font-size: 13px;
	padding: 10px;
	display:inline-block;
	width: 200px;
	margin: 50px auto;
}
</style>
</head>

<body>
	<div>
		<h1>ORDER LIST</h1>
		<form action="orderSuccess" method="post">
			<table style="margin: 0 auto;">
				<tr style="color: #777; font-size: 12px;">
					<th class="w-px120 th-top">사진</th>
					<th class="w-px350 th-top">상품명</th>
					<th class="w-px150 th-top">옵션</th>
					<th class="w-px160 th-top">가격</th>
					<th class="w-px120 th-top">수량</th>
					<th class="w-px200 th-top">합계</th>
				</tr>
				<c:set var="options" value="${fn:split(vo.option_name, '/')}" />
				<c:forEach begin="0" end="${fn:length(options)-1}"
					varStatus="optionsStatus">
					<c:set var="option" value="${options[optionsStatus.index]}" />
					<c:set var="detail" />
					<c:forEach begin="0" end="${fn:length(option)-1}"
						varStatus="optionStatus">
						<c:set var="detail" value="${fn:split(option, '@')}" />
					</c:forEach>
					<tr>
						<td><img class="imgpath"
							src="<c:url value='/' />${vo.item_imgpath }"
							style="width: 80; height: 113px;"></td>

						<td>${vo.item_name }</td>
						<td>${detail[0]}</td>
						<td><fmt:formatNumber value="${vo.item_price}" />원</td>
						<td>${detail[1]}</td>
						<td><fmt:formatNumber value="${ vo.item_price * detail[1]}" />원</td>

					</tr>
				</c:forEach>
				<tr
					style="background-color: #F4F4F4; color: #6d6c71; font-size: 12dp; text-align: right;">
					<td colspan="6" style="padding: 10px;" class="right">total
						cost : ${vo.totalPrice } 원</td>
				</tr>
			</table>



			<table>
				<tr>
					<td class="th-middle-top" colspan="2">주문자</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">주문자명:</td>
					<td class="w-px500 th-middle"><input name="member_name"
						readonly="readonly" value="${member.member_name }"></td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">E-mail:</td>
					<td class="w-px500 th-middle"><input name="member_email"
						value="${member.member_email }" /></td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">연락처:</td>
					<td class="w-px500 th-middle"><input name="member_phonenum"
						maxlength="11" onKeyup="SetNum(this);"
						value="${member.member_phonenum }" /></td>
				</tr>

			</table>



			<table>
				<tr>
					<td class="th-middle-top" colspan="2">받으시는 분</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">수취인 성함:</td>
					<td class="w-px500 th-middle"><input name="shipping_name"></td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">수취인연락처:</td>
					<td class="w-px500 th-middle"><input name="shipping_phonenum"
						maxlength="11" onKeyup="SetNum(this);" /></td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">배송지:</td>
					<td class="w-px500 th-middle"><input type="text"
						name="shipping_post" maxlength="5"
						style="width: 70px; margin-bottom: 10px;" readonly> <a
						class="btn-fill-s" onclick="daum_post()">우편번호찾기</a> <br /> <input
						type="text" name="shipping_address" readonly /> <input
						type="text" name="shipping_address2" value="상세주소를 입력하세요"
						onfocus="this.value=''" /></td>

				</tr>
				<tr>
					<td class="w-px120 th-middle">배송메세지</td>
					<td class="w-px120 th-middle"><input type="text"
						style="width: 200px;" name="shipping_message" /></td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">결제수단</td>
					<td class="w-px120 th-middle"><label>무통장입금
						<input type="radio" name="pay" value="무통장입금" checked="checked"></label>
						<label>신용카드<input type="radio" name="pay" value="신용카드"></label>
					</td>
				</tr>
			</table>
			<input type="hidden" name="member_id" value="${login_info.member_id }" />
			<input type="hidden" name="item_num" value="${vo.item_num }" />
			<input type="hidden" name="option_info" value="${vo.option_name }" />
			
		</form>

		<div class="btnSet">
			 <a class="btn-back" onclick="history.go(-1);">돌아가기</a>
			 <a onclick="go_pay()" class="btn-pay">결제하기</a>
		</div>
	</div>

	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script type="text/javascript"
		src="js/join_check.js?v=<%=new java.util.Date().getTime()%>"></script>


	<script type="text/javascript">

		function go_pay() {
			if( $("[name=shipping_address2]").val() =="상세주소를 입력하세요"){
				alert("상세주소를 입력하세요");
				return false;
			}
			$('form').submit();
		}

		//전화번호 유효성
		function SetNum(obj) {

			if ((event.keyCode <= 27)
					|| (event.keyCode >= 33 && event.keyCode <= 46)
					|| (event.keyCode >= 91 && event.keyCode <= 93)
					|| (event.keyCode >= 112 && event.keyCode <= 145)) {
				return false;
			}

			val = obj.value;
			re = /[^0-9]/gi;
			obj.value = val.replace(re, "");

		}

		var j = 0;
		var option = '${vo.option_name}'.split('/');

		function daum_post() {
			new daum.Postcode(
					{
						oncomplete : function(data) {
							$('[name=shipping_post]').val(data.zonecode);//우편번호
							var member_address = data.userSelectedType == 'J' ? data.jibunAddress
									: data.roadAddress;
							if (data.buildingName != '')
								member_address += '(' + data.buildingName + ')';
							$('[name=shipping_address]').eq(0).val(
									member_address);

						}
					}).open();

		}
	</script>
</body>
</html>
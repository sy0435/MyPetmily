<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<h3>${orderVO.member_name }님의 주문 [${orderVO.order_num }]이 접수되었습니다</h3>
		<table>
				<tr>
					<td class="th-middle-top" colspan="2">주문자</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">주문자명:</td>
					<td class="w-px500 th-middle">${orderVO.member_name }</td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">E-mail:</td>
					<td class="w-px500 th-middle">${orderVO.member_email }</td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">연락처:</td>
					<td class="w-px500 th-middle">${orderVO.member_phonenum }</td>
				</tr>

			</table>



			<table>
				<tr>
					<td class="th-middle-top" colspan="2">수취자</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">수취인:</td>
					<td class="w-px500 th-middle">${orderVO.shipping_name }</td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">수취인연락처:</td>
					<td class="w-px500 th-middle">${orderVO.shipping_phonenum } </td>
				</tr>
				<tr style="border-top: 1px solid gray">
					<td class="w-px120 th-middle">배송지:</td>
					<td class="w-px500 th-middle">
						[ ${orderVO.shipping_post } ]
						${orderVO.shipping_address } 
						${orderVO.shipping_address2 }
						</td>

				</tr>
				<tr>
					<td class="w-px120 th-middle">배송메세지</td>
					<td class="w-px120 th-middle">${orderVO.shipping_message }</td>
				</tr>
				<tr>
					<td class="w-px120 th-middle">결제수단</td>
					<td class="w-px120 th-middle">${orderVO.pay }
					</td>
				</tr>
			</table>
			
			<a href="javascript:history.go(-4)" class="listBtn"> 목록으로</a>
			

</body>
</html>
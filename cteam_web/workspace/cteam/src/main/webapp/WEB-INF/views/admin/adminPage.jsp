<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<style type="text/css">
ul {
	width: 50%;
	height: 250px;
	margin: 0 auto;
	margin-bottom: 50px;
	margin-top: 30px;
}

ul li{
	width: 33%;
	float: left;
	height: 250px;
	font-weight: bold;
	font-size:1.4em;
}

ul li:first-child {
	background-color: #FFE6EB;
	border-top: 3px solid #969696;
	border-left: 3px solid #969696;
	border-bottom: 3px solid #969696;
}
ul li:nth-child(2){
	background-color: #ffffef;
	border-top: 3px solid #969696;
	border-bottom: 3px solid #969696;
}
ul li:last-child {
	background-color: #f8efff;
	border-top: 3px solid #969696;
	border-bottom: 3px solid #969696;
	border-right: 3px solid #969696;
}

a:hover{
	color: #E60081;
}

#product {
	width: 45%;
	height: 130px;
	margin-top: 30px;
	margin-bottom: 10px;
}

#member {
	width: 55%;
	height: 150px;
	margin-top: 20px;

}

#order {
	width: 40%;
	height: 110px;
	margin-top: 40px;
	margin-bottom: 20px;

}
</style>
</head>
<body>
<h2 align="center">관리자 페이지</h2>
<ul>
	<li>
		<a id="p.m" href="list.ad"><img id="product" src="img/product.png" /></a>
		<p><a id="p.m" href="list.ad">상품관리</a></p>
	</li>
	<li>
		<a href="memberList.ad"><img id="member" src="img/member.png" /></a>
		<p><a href="memberList.ad">회원관리</a></p>
	</li>
	<li>
		<a href="orderList.ad"><img id="order" src="img/orderlist.png" /></a>
		<p><a href="orderList.ad">주문관리</a></p>
	</li>
</ul>

</body>
</html>
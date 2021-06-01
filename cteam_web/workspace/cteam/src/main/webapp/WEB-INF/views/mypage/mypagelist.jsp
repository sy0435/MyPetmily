<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table {
		width: 99%;
		height: 100%;
		border-collapse: collapse;
	}
	img {
	
	}
	table tr{
	 border: none;
	}
	#info {
		width: 750px;
		border: 3px solid #FFB4BE;
		height: 250px;
		margin-bottom: 20px;
	}
	
	#profile{
		width: 30%;
		height: 100%;
		border-right: 1px solid #FFB4BE;
		float: left;
		box-sizing: content-box;
	}
	#box {
		width: 69.8%;
		float: left;
		height:49.8%;
		border-bottom: 1px solid #FFB4BE;
		box-sizing: border-box;
	}
	#state {
		width: 69.8%;
		height:50%;
		float: right;
	}
	#paw {
		width: 70%;
		height: 60%;
	}
	#imgpaw {
		width: 100%;
		height: 100%
	}
	p{
		margin:0px;
		background-color: pink;
		height: 20.1%;
		text-align: center;
		
		box-sizing: border-box;
	}
	#welcome{
		font-weight: bold;
		padding: 20px 5px 0px 5px;
	}
	.next {
		margin-right: 20px;
		width: 15px;
		height:15px;
		float: right;
	}
li {
	list-style: none;
	float: left;
	margin: 20px 50px;
}
ol {
    display: block;
    list-style-type: decimal;
    margin-block-start: 5px;
    margin-block-end: 5px;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 15px;

}
.mypage_order_info_cont ol li b {
    display: block;
    padding: 0 0 15px 0;
}
.mypage_order_info_cont ol li strong {
    display: inline-block;
    width: 50px;
    height: 50px;
    
    color: #ffffff;
    font-size: 18px;
    background-image: linear-gradient(to top, #f77062 0%, #fe5196 100%);
    text-align: center;
    line-height:50px;
    vertical-align: middle;
    border-radius: 50%;
    box-sizing: border-box;
}

#order {
	 border-bottom: 1px solid #FFB4BE;
	 border-top: 1px solid #FFB4BE;
}
</style>

</head>
<body>
<c:if test="${empty login_info }">
<c:out value="${login_info.member_id eq '로그인후 이용해주세요'}"/>
</c:if>
<div style="margin: 50px;">

<h2>마이페이지</h2>

<div align="center">
<div id="info" align="center" >
	<div id="profile">
	<div id="paw">
		<img id="imgpaw" src="img/paw.png"/>
	</div>
	<p id="welcome">WELCOME!</p><p>${login_info.member_name }님</p>
	</div>
	<div id="box">
	<table>
<!-- 		<tr><td align="left"><a href="mypageDetail.my">회원정보수정<img class="next" src="img/next.png"></a></td></tr> -->
		<tr><td align="left"><a href="pwConfirm.my">회원정보수정<img class="next" src="img/next.png"></a></td></tr>
		<tr><td id="order" align="left"><a href="myorder.my">주문조회<img class="next" src="img/next.png"></a></td></tr>
		<tr><td align="left"><a href="myWrite.my">내가 쓴 글 / 댓글보기<img class="next" src="img/next.png"></a></td></tr>
	</table>
	</div>
	<div id="state">

	    <div class="mypage_order_info_cont">
            <ol>
              <li class="">
                <b>상품준비중</b>
                <strong>
                <c:if test="${orderlist.pre eq null }">
                	0
                </c:if>
                <c:out value="${orderlist.pre }"/>
                
                </strong>
              </li>
              <li class="">
                 <b>배송중</b>
                 <strong>
				<c:if test="${orderlist.ship eq null }">
                	0
                </c:if>                 
                 <c:out value="${orderlist.ship }"/>
                 </strong>
   	          </li>
              <li class="">
                  <b>배송완료</b>
                  <strong>
                  <c:if test="${orderlist.ship eq null }">
                	0
              	  </c:if> 
                  <c:out value="${orderlist.finish }"/>
                  </strong>
              </li>
            </ol>
        </div>
	</div>
	</div>	
</div>
</div>

</body>
</html>
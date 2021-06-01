<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 <%@ taglib prefix="fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품상세페이지</title>
<style type="text/css">
 img.imgpath{ height: 500px; width: 400px; }
 #all{ margin: 70px auto; text-align: center; box-sizing: border-box; }
 div.name-div{ font-size: 17px; font-weight: bold; text-align:left;}
 div.content-div{ font-size: 13px; margin-top:40px; text-align:left; color: #333333;}
 div.price-div, div.order-div, div.option-div, div.ship-div, div.list-div {text-align:left; margin-top:20px; font-size: 15px;}
 div.desc-div{ margin-left: 50px; float:left;}
 div.buy-div {float:left; width:300px; margin-top: 20px;}
 div.center-div{margin: 0 auto; width:800px;}
 div.modify-div{float:left; margin-top: 100px;}
 span { font-size: 13px; color:#333333; }
 span.color-span, span.price-span {color:#000000;}
 strong.price-strong{color:#000000;}
 
 a.buy-btn, a.cart-btn{ float:left; padding: 5px; width: 100px; display: block; color:white;}
 a.buy-btn {background-color: black; margin-right: 10px;}
 a.cart-btn {background-color: #EAEAEA; color: #747474;}
 a.modify-btn, a.delete-btn {padding:5px;}
 a.modify-btn{background-color: #EAEAEA; color: #747474;}
 a.delete-btn{background-color: black; color: white;}
 input[type=number] {width:25px; height: 15px; background-color: #F2F2F2; border: none; font-size: 15px; outline: none; }
 a.go_list{

	background-color: black;
	color:white;
	font-size: 13px;
	padding: 10px;
	display:inline-block;
	width: 200px;
	margin: 20px auto;

 }
</style> 
</head>
<body>
	<div id="all" style="overflow: hidden; ">
		<div class='center-div'>	
		
		<c:set var="option" value=""/>
		
		
		<c:forEach items="${list }" var="vo" varStatus="status">
	
			
			<c:if test="${  status.index == ( fn:length( list ) ) - 1 }">
			
			
			
			<c:if test="${!empty vo.item_num }">
				<div class="img-div">
					<img class="imgpath" src="<c:url value='/' />${vo.item_imgpath }" style="float:left;">
				</div>
				
		
			</c:if>	
			
		
					
					<div class="desc-div name-div" style="float:left; width:300px;">
						${vo.item_name}
					</div>
					
					<div class="desc-div content-div" style="width:300px;">
						${vo.item_content}
					</div>
					<%-- <c:if test="${!vo.option }">
						<select></select>
					</c:if> --%>
					
					
		 </c:if>
		</c:forEach>	
		
		<div class="desc-div option-div">	
		
		
		<c:set var="option_name" value="" />
		
		<select id="select">
		<option value="default" selected disabled hidden>옵션선택</option>
		<c:forEach items="${list }" var="vo" varStatus="status">
								
				
				<c:if test="${!empty vo.option_name }">
					
					
					<option value="${vo.option_name }">${vo.option_name }</option>
					
					
				</c:if>	
		
		</c:forEach>	
		</select>	
		</div>
			
		<c:forEach items="${list }" var="vo" varStatus="status">
	
			
			<c:if test="${  status.index == ( fn:length( list ) ) - 1 }">
					
								
					<span class="y" style="display: none;">${vo.item_price}</span>
				<%-- 	<div class="desc-div list-div invisible" style="border-bottom: 1px solid #ddd; padding-bottom: 10px;">
			
					
						
							<span class="option-span">option:</span>

							<span class="option_name-sapn" style="padding-right: 100px;">${option_name }</span>
						
							<a class="option_plus"> + </a>
							
							
							<input class="item_su" name="item_su" type="number" readonly="readonly" value="1" style="text-align: center;"/>
							
							
							
							<a class="option_minus"> - </a>
							
							<span>${vo.item_price } 원</span>
							<a class="list-delete" width="12px" height="13px"><i class="fas fa-times"></i></a>
					</div>	 --%>
						
				<%-- 	<div class="desc-div ship-div" style="float:left; width:300px;">
						<span class="ship">배송비:</span>	
						<c:choose>
						<c:when test="${vo.item_price gt 50000 }"><span id="ship">무료</span></c:when>
						<c:otherwise><span id="shipPrice">2,500원</span></c:otherwise>
						</c:choose>
					</div> --%>
					
					<div class="desc-div price-div" style="float:left; width:300px; color: #333333;" >
						<span class="price">총 상품금액:</span>	
						<Strong class="price-strong"> <fmt:formatNumber value="${vo.item_price}" /></Strong>
						<span class="won">원</span>
					</div>
					
					<div class='desc-div buy-div'>
						<a href="javascript:submit()" class="buy-btn">buy it now</a>
						<a class="cart-btn" onclick="go_cart()">go cart</a>
					</div>
					
					
					
					<c:if test="${login_info.member_id == 'admin' }">
						<div class="desc-div modify-div">
							<a class='modify-btn' onclick="$('form').attr('action','itemModify.ad'); $('form').submit()">수정</a>
							<a class='delete-btn' onclick="if(confirm('정말 삭제하시겠습니까?') ){$('form').attr('action','delete.sh'); $('form').submit() }">삭제</a>
						</div>
					</c:if>
					
					<form method="post" action="order.sh">
					
						<input type="hidden" name="item_num" value="${vo.item_num }">
						<input type="hidden" name="curPage" value="${page.curPage }">
						<input type="hidden" name="search" value="${page.search }">
						<input type="hidden" name="pageList" value="${page.pageList }">
						<input type="hidden" name="option_name" value="">
						<input type="hidden" name="item_price" value="${vo.item_price }">
						<input type="hidden" name="item_name" value="${vo.item_name }">
						<input type="hidden" name="item_imgpath" value="${vo.item_imgpath }">
						<input type="hidden" name="totalPrice" value="">
					</form>
						<div class="contentImg-div" style="margin-top:50px;">
		
							<img class="imgpath" src="<c:url value='/' />${vo.item_content_imgpath }" style="width:800px; height: inherit;" >
						</div>
			
				</c:if>
			</c:forEach>
				
		</div>
		
	
		<a class="go_list" href="javascript:history.go(-1);">목록보기</a>
	</div>


	<script type="text/javascript">	

		var state;

	
		var length = $("[id=select] option").length;

		var price= parseInt($('.y').html());


		var su= 1;

		var eachPrice;

		var totalPrice=0;


		
	
		$('#select').change(function() {

			state = $('#select option:selected').val();
			
			
			if ( state ) {
	
 				
				
				if( $('span[data-option=' + state + ']').length == 0 ){
				
 				
/*
				//만들어진 옵션창 개수
 				 var divLength=0;
 				 //div state 저장 
				var tempArray = new Array();
					
				if(tempArray.indexOf(state)>-1){
						alert("이미 선택된 옵션입니다");
				}
*/

				var addDiv = 
			
					'<div id="div1" class="desc-div list-div invisible" style="border-bottom: 1px solid #ddd; padding-bottom: 10px;">'+
				
				
				
					'<span class="option-span" data-option='+ state +'>'+state +'</span>'+

					'<a class="option_plus" onclick="plus(this);" style=" margin-left:170px;"> + </a>'+
				
				
					'<input class="item_su" name="item_su2" type="number" readonly="readonly" value="1" style="text-align: center;"/>'+
					
					'<a class="option_minus" onclick="minus(this)"> - </a>'+
				
					'<span class="won" style="padding-right:5px;" data-option='+ price +'>'+ price.toLocaleString() +  '원 </span>'+
					
					'<a class="list-delete" width="12px" height="13px" onclick="wrap(this);"><i class="fas fa-times"></i></a>'+
					'</div>';			
				
				var divHtml = $('.y');
				divHtml.after(addDiv);
// 				eachPrice=parseInt( $(".won").text() );
				eachPrice=parseInt( $(".won").data('option') );
					totalPrice += eachPrice; //옵션추가시 토탈금액 늘어남

					$(".price-strong").text(totalPrice.toLocaleString());
					//토탈금액 세팅
					
					
				}


				
				
				
			
					
// 					var addDiv = 
// 						'';			
					
// 					var divHtml = $('.y');
// 					divHtml.after(addDiv);
					
/* 	
					divLength =$('.list-div').length;

					$(".list-div").each(function(){

					    tempArray.push($(this).children('span:eq(0)').html());

							});

					var optionChk;
					//이미 선택한 옵션값을 배열로 받는다!@ㅃ!
					for(var i = 0; i<divLength ;i++){
						optionChk=tempArray[i];
						
						}
*/
				
					
			
				/* 	var optionChk=
						$('.y').next().children('span:eq(0)').html(); */
					

				/*   if(divLength == length-1){	//옵션이 이미 2개 선택된 상태이고
					if( state == optionChk)
							
					}else{

					}
				
				} */


				
				
				
			}

		});

	
		/* var optionChk=$('.y').next().filter(':first').val();
		alert(optionChk); */
	
	/* 	if(divLength == length-1){
			if( state == )
			
		} */
		



		function submit(){


			if(state){
			var option_name="";
			
				//선택한 옵션이름들 저장 /로 구분
				 $('.option-span').each(function (i) {
					 if(i==0){
		             option_name += $('.option-span').eq(i).text()+'@'+ $("input[name='item_su2']").eq(i).val() ;
					 }else{
			          option_name += '/'+$('.option-span').eq(i).text()+'@'+ $("input[name='item_su2']").eq(i).val() ;
					}
					 	
		      });
	
				 $('[name=option_name]').val(  option_name  ); //선택한 옵션이름들 세팅
				 
					
					
	
	        	    
				//옵션만큼 수량 저장
		/* 		 $("input[name='item_su2']").each(function (i) {
			 
			             item_su += $("input[name='item_su2']").eq(i).val()+'@' ;
			 				
			      }); */
			    		      
	
			      $('[name=totalPrice]').val( $(".price-strong").text() );
				
				$('form').submit(); 
			}else{
				alert("옵션을 선택하세요");
			}
		}

		
		function plus(p){
			var su=$(p).next().val();
			++su;
			//var plus=parseInt($(p).siblings('#won').html());
			var value= price * parseInt(su);

			$(p).next().siblings('.won').html(value.toLocaleString());

			totalPrice += price; 
			
			$(p).next().val(su);

			
			$(".price-strong").text(totalPrice.toLocaleString());
			
		};

		function minus(m){
				
			var su=$(m).prev().val();
			if(su>1){
				--su;
				var minus=parseInt($(m).next().html().replace(/,/g, ''));
				var value= minus - price;
				
				$(m).next().html(value.toLocaleString());

				totalPrice -= price;
				$(".price-strong").text(totalPrice.toLocaleString());
				
			}
			$(m).prev().val(su);
		
		};
		function go_cart(){

			if(state){
				var option_name="";
			
				//선택한 옵션이름들 저장 /로 구분
				 $('.option-span').each(function (i) {
					 if(i==0){
		             option_name += $('.option-span').eq(i).text()+'@'+ $("input[name='item_su2']").eq(i).val() ;
					 }else{
			          option_name += '/'+$('.option-span').eq(i).text()+'@'+ $("input[name='item_su2']").eq(i).val() ;
					}
					 	
		      });
	
				 
	
			      var cart_totalPrice= $(".price-strong").text();
				  var item_num = $('[name=item_num]').val();

				$.ajax({
					url:"${pageContext.request.contextPath}/cartAdd?item_num="+item_num+"&option_info="+option_name+"&totalPrice="+cart_totalPrice,
					success:function(data){ 
						if(data){
							alert("장바구니에 담겼습니다");
						}else{
							alert("실패했습니다");
						}
					}
				});
					



			}else{
				alert("옵션을 선택하세요");
			}

		}

		function wrap(o){
			//옵션창 지우면 그만큼 토탈금액이 마이너스
			var deletePrice= (parseInt($(o).prev().text()))*1000; 

			totalPrice-=deletePrice;

			$(".price-strong").text(totalPrice);
			
			
			$(o).parent().remove();

			$("#select option:eq(0)").prop("selected", true);


			su=1;




		}



		
	</script>
</body>
</html>
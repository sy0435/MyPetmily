<%@ page pageEncoding="utf-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="css/common.css?v="<%=new java.util.Date().getTime() %>>


        <style>
        
        img.shop-img { width: 306px; height: 396px;}
		span{display:block;  margin-bottom: 10px; text-align: left;}
		span.item-title{ font-weight: bold; color: gray; font-size: 20px; margin-top: 5px; }
		span.item-price{ color: purple; font-size: 15px; font-weight: bold;}
		ul li{ float: left; margin-right: 20px; }
		ul{  padding:0px; margin: 0 auto; }
		input.search-input{ border-radius: 20px; background-color: #f7f7f6; width:200px; border: #f7f7f6; outline: none; font-size: 13px; padding-left: 10px;}
		#top	{margin-bottom:10px;}
		#search	{border:none; padding:5px; outline: none; width:20px; }

         /* Animation Canvas */
		
		
         .container {
                border-top: 8px solid white;
                border-bottom: 8px solid white;
                margin: 50px 0 100px 0;            
                overflow: hidden;
                min-width: 1280px;
          }
          
		  #slide {
                width: 500%;     
            }

            #slide::after { content: ""; display: block; clear: both; }

            #slide > li {
                float: left;
                padding: 0px;
                margin : 0px auto;
                width: 20%;
                position: relative;
                height: 600px;
                overflow: hidden;
          		
            }


		     #slide > li > img {
                display: block;
                position: absolute;
                top: 50%; left: 50%;
                transform: translate(-50%, -50%);
                width: 1280px;
            }


	

            
            
        </style>
        <script src="https://code.jquery.com/jquery.min.js"></script>
 
    
        <script>
            $(function () {
                //--------------------------------------------------------------------------------------
                // 1. 일정한 시간마다
                // 1.1. #slide 요소를 뷰포트 너비만큼 왼쪽으로 이동하게 한다.
                // 1.2. #slide 요소의 움직임이 끝나면
                // 1.3. #slide 요소에 설정했던 스타일 속성을 제거하고
                // 1.4. #slide 요소의 첫 번째 자식 요소를 #slide 요소의 마지막으로 옮긴다.

                // 2. #container 요소의 영역에 마우스 포인터가 들어가면
                // 2.1. #slide 요소의 움직임을 멈추게 한다.
                // 3. #container 요소의 영역에서 마우스 포인터가 빠져나오면
                // 3.1. #slide 요소가 다시 움직이도록 한다.
                
                //--------------------------------------------------------------------------------------
                // 프로그램에서 참조하는 요소 탐색
                var $slide = $("#slide");
                
                // 나중에 타이머를 해제하기 위해 setInterval 메서드가 반환하는 타이머 ID를 변수에 저장해놓는다.
                var timerId;
                
                // 1. 일정한 시간마다
                timerId = window.setInterval(slideBanner, 2000);
                
                //---------------------------------------------------------------------------------------
                // 2. #container 요소의 영역에 마우스 포인터가 들어가면
                //    → #container 요소에 mouseenter 이벤트 핸들러를 연결해서 구현
                // 3. #container 요소의 영역에서 마우스 포인터가 빠져나오면
                //    → #container 요소에 mouseleave 이벤트 핸들러를 연결해서 구현
                $(".container").hover(
                    function () {
                        // 이벤트 핸들러: #container 요소에 mouseenter 이벤트가 발생하면 실행할 기능
                        
                        // 2.1. #slide 요소의 움직임을 멈추게 한다.
                        //      → 위에서 setInterval 메서드로 등록했던 타이머를 해제
                        window.clearInterval(timerId);
                    }, 
                    function () {
                        // 이벤트 핸들러: #container 요소에 mouseleave 이벤트가 발생하면 실행할 기능
                        
                        // 3.1. #slide 요소가 다시 움직이도록 한다.
                        //      → 위에서 setInterval 메서드로 등록했던 타이머와 똑같은 타이머를 다시 등록
                        timerId = window.setInterval(slideBanner, 2000);
                    }
                );
                
                //---------------------------------------------------------------------------------------
                // setInerval 메서드로 등록한 타이머에서 실행할 함수 선언
                function slideBanner() {
                    // 이 함수는 setInterval 메서드로 등록한 타이머에 의해 2000ms마다 실행된다.

                    // 1.1. #slide 요소를 뷰포트 너비만큼 왼쪽으로 이동하게 한다.
                    $slide.css({ "margin-left": "-100%", "transition": "0.4s" });
                    
                    // 1.2. #slide 요소의 움직임이 끝나면
                    window.setTimeout(function () {
                        // 이 함수는 setTimeout 메서드로 등록한 타이머에 의해 400ms 마다 실행된다.

                        // 1.3. #slide 요소에 설정했던 스타일 속성을 제거하고
                        // 1.4. #slide 요소의 첫 번째 자식 요소를 #slide 요소의 마지막으로 옮긴다.
                        $slide.removeAttr("style").children(":first").appendTo($slide);
                        
                    }, 2000);
                }
            });  // document.onready
            </script>

    </head>
   

<body>
	
	
	<div class="container">
		<div style="margin-bottom:30px;" id="top-img" style="width:100%; height: 400px;">
    		 <ul id="slide" >
    		<li><img src="img/banner-pets-dog-cat-boarding (1).png" ></li>  
    		<li><img src="img/dog1.jpg" class="slider_image"></li>
    		<li><img src="img/cat4.jpg" class="slider_image"></li>     
    		<li><img src="img/dog2.jpg" class="slider_image"></li>     
    		<li><img src="img/dog5.jpg" class="slider_image"></li>
            </ul>
    	</div>
    	
	</div>
				
		<div id="top" style="margin: 50px auto; text-align: center;">
			<h1 style="text-align: left;">Best 상품</h1>
			<form method="post" action="list.sh">
			
			<input type="hidden" name='curPage' value='1'/>
			<input type="hidden" name='item_num' />
				
			<div style="width:100% ">	
				<ul style="float: right;">
					<li>
						
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
			</form>	


			<div>
			
			<ul style="overflow: hidden; text-align: center;">
			
				<c:set var="fir" value="0"/>
				
				<c:forEach items="${page.list }" var="vo" varStatus="status">
						
					<!-- 첫째로 돌때  -->
					<c:if test="${status.index==0 }">
						<li>
							<div style="width: 306px; box-sizing: border-box;" >
							<c:if test="${!empty vo.item_num }">
									<a href="javascript:go_detail(${vo.item_num })"><img src="<c:url value='/' />${vo.item_imgpath }" class="shop-img"/></a>	
							</c:if>	
								<a href="javascript:go_detail(${vo.item_num })">
									<span class="item-name" style="font-weight: bold">${vo.item_name}</span>
									<span class="item-price">${vo.item_price}</span>
								</a>
							</div>
						</li>
						<c:set var="fir" value="${vo.item_num }"/>
					</c:if>
							<!-- 두번재부터 돌때 전값이랑 비교해서 동일하면 출력x -->
					<c:if test="${status.index!=0}">
						<c:set var="item_num" value="${vo.item_num }"></c:set>
					
						<c:if test="${ item_num != fir }">
						
							<li>	
								<div style="width: 306px; box-sizing: border-box;" >
									
									<c:if test="${!empty vo.item_num }">
									<a href="javascript:go_detail(${vo.item_num })"><img src="<c:url value='/' />${vo.item_imgpath }" class="shop-img"/></a>	
									</c:if>
									<a href="javascript:go_detail(${vo.item_num })">
										<span class="item-name" style="font-weight: bold">${vo.item_name}</span>
										<span class="item-price">${vo.item_price}</span>
									</a>
								</div>
							</li>	
							
							<c:set var="fir" value="${vo.item_num }"/>
						</c:if>
							
						
					</c:if>
					
					
					
				</c:forEach>
				
					<%-- <li>
						<div>
							<a href="item.detail"><img class="shop-img" src="<spring:url value='/image/cat.jpg'/>" /></a>	
							<a href="item.detail">
								<span class="item-title">iot 개방석</span>
								<span class="item-price">24000원</span>
							</a>
						</div>
					</li> --%>
			</ul>
			</div>
		</div>
		
		<div class='btnSet' >
			<%-- <jsp:include page="/WEB-INF/views/include/page.jsp/" /> --%>
		</div>
	

    
        <div id="container">
           
        </div>
 
    
    <script type="text/javascript">

	function go_detail( item_num ){
		$('[name=item_num]').val(item_num);
		$('form').attr('action','item.detail');
		$('form').submit();
	}

	</script>	
</body>

</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#map { 
	position:absolute; width:800px; height:600px;
	left:50%; top:50%;	transform:translate(-50%, -50%);
	border:2px solid #666;		display:none;      
}
.page_on, .page_off, .page_first, .page_prev, .page_next, .page_last {
	display: inline-block; line-height:30px;  margin:0;
}
.page_on{  border:1px solid gray; background-color:gray; 
			color:#fff; font-weight:bold   }
.page_on, .page_off { min-width:22px; padding:0 5px 2px; }
.page_first, .page_prev, .page_next, .page_last {
	width:30px; border:1px solid #d0d0d0;  text-indent:-999999999px   
}		
.page_first { background:url('img/page_first.jpg') center no-repeat; }	
.page_prev { background:url('img/page_prev.jpg') center no-repeat; }	
.page_next { background:url('img/page_next.jpg') center no-repeat; }	
.page_last { background:url('img/page_last.jpg') center no-repeat; }	

.list-view, .grid-view { font-size:25px; color:#3367d6; padding-top:3px; }

#list-top ul li:not(:last-child) { float:left; margin-right:10px;  }
#data-list ul li div:first-child { height: 25px; }
#data-list ul li div:last-child { font-size: 14px; }
</style>
</head>
<body>
<h3>공공데이터</h3>
<div class='btnSet dataOption'>
	<a class='btn-fill'>약국조회</a>
	<a class='btn-empty'>유기동물조회</a>
	<a class='btn-empty'>A</a>
	<a class='btn-empty'>B</a>
	<a class='btn-empty'>C</a>
</div>

<div id='list-top'>
<div>
	<ul>
		<li><select id='pageList' class='w-px80'>
			<option value=10>10개씩</option>
			<option value=20>20개씩</option>
			<option value=30>30개씩</option>
			</select>
		</li>
		<li class='list-view'><i class='fas fa-list font-img' style='vertical-align:top'></i></li>
		<li class='grid-view'><i class='fas fa-th font-img' style='vertical-align:top'></i></li>
	</ul>
</div>
</div>

<div id='data-list' ></div>
<div class='btnSet'><div class='page_list'></div></div>

<div id='popup-background' onclick="$('#popup-background, #map').css('display', 'none');"></div>
<div id='map'></div>
<script type="text/javascript" 
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCsrerDHJrp9Wu09Ij7MUELxCTPiYfxfBI" ></script>
<script type="text/javascript">
$(document).on('click', '.show', function(){
	$('#popup-background, #map').css('display', 'block');

	var xy = { lat: Number($(this).data('y')) , lng: Number($(this).data('x')) };
	var map = new google.maps.Map(document.getElementById("map"), {
		center: xy,
		zoom: 15,
	});

// 	new google.maps.Marker({
// 		map:map,  position:xy,	 title:$(this).text()
// 	});
	var info = new google.maps.InfoWindow();
	info.setOptions({
		content: '<div>'+ $(this).text() +'</div>'
	});	
	info.open(map, new google.maps.Marker({
		map:map, position:xy 
	}) );

}).on('click', '.page_list a', function(){
	pharmacy_list( $(this).data('page'), 0 );
	
}).on('change', '#pageList', function(){
	pageList = $(this).val();
	pharmacy_list( 1 );
	
}).on('click', '.grid-view', function(){
	if( viewType == 'list' ){
		viewType = 'grid';
		pharmarcy_grid_data( $('table tr'), 1 );
	}
}).on('click', '.list-view', function(){
	if(viewType == 'grid'){
		viewType ='list';
		pharmarcy_list_data($('.grid li'), 1);

	}
})
;

function pharmarcy_list_data(data, type ){
	//그리드뷰 → 테이블 목록뷰로 바꾸기
	var tag = '<table><tr>'
	+'<th class="w-px200">약국명</th>'
	+'<th class="w-px140">전화번호</th>'
	+'<th>주소</th>'
	+'</tr>';

	if(type == 0){
	data.each(function(){
		tag += '<tr>'
			+ '<td><a class="show" data-x="'+ this.XPos +'" data-y="'+ this.YPos +'" >'+ this.yadmNm +'</a></td>'
			+ '<td>'+ this.telno +'</td>'
			+ '<td class="left">'+ this.addr +'</td>'
			+ '</tr>';
	});
	}else{
	
	data.each(function(){
		var $a = $(this).find('.show');
		tag += '<tr>'
			+'<td><a class="show" data-x='+ $a.data('x')
			+' data-y='+ $a.data('y')+'>'+$(this).children('div:eq(0)').text()+'</a></td>'
			+'<td>'+$(this).children('div:eq(1)').text()+'</td>'
			+'<td class="left">'+$(this).children('div:eq(2)').text()+'</td>'
			+'</tr>';
		});
	}
		tag += '</table>';
		$('#data-list').html(tag);
}

function pharmarcy_grid_data( data, type ){
	//테이블 목록뷰 --> 그리드뷰로 바꾸기
	var tag = '<ul class="grid">';

	if(type == 0){
	data.each(function(){
		tag += '<li>'
			+ '<div><a class="show" data-x='+ this.XPos
			+' data-y='+ this.YPos+'>'+ this.yadmNm +'</a></div>'
			+ '<div>'+ this.telno +'</div>'
			+ '<div>'+ this.addr +'</div>'
			+ '</li>';
		});
	}else{
	data.each(function(){
		if( $(this).index()>0 ){
			var $a = $(this).find('.show');
			tag += '<li>'
				+ '<div><a class="show" data-x='+ $a.data('x')
				+' data-y='+ $a.data('y')+'>'+ $(this).children('td:eq(0)').text() +'</a></div>'
				+ '<div>'+ $(this).children('td:eq(1)').text() +'</div>'
				+ '<div>'+ $(this).children('td:eq(2)').text() +'</div>'
				+ '</li>';
			}
		});
	}
	tag += '</ul>';
	$('#data-list').html(tag);
	$('#data-list ul').css('height', 
		(($('.grid li').length%5>0? 1 : 0) + Math.floor($('.grid li').length/5)) * $('.grid li').outerHeight(true) - 20 
		
				);
	
}

pharmacy_list(1);

$('.dataOption a').click(function(){
	if( $(this).hasClass('btn-empty') ){
		$('.dataOption a').removeClass();
		$(this).addClass('btn-fill');
		var idx = $(this).index();
		$('.dataOption a:not(:eq('+ idx +'))').addClass('btn-empty');
		if( idx==0 ) pharmacy_list( 1 );
		else if( idx==1 ) animal_list();
	}
});

function pharmacy_list( page ){
	$.ajax({
		url: 'data/pharmacy',
		data: { pageNo:page, rows:$('#pageList').val() },
		success: function( data ){
			if( viewType == 'list') pharmarcy_list_data( $(data.item), 0);
			else					pharmarcy_grid_data( $(data.item), 0);
			/* var tag = '<table>'
				+'<tr><th class="w-px200">약국명</th><th class="w-px140">전화번호</th><th>주소</th></tr>';
			$(data.item).each(function(){
				tag += '<tr>'
					+ '<td><a class="show" data-x="'+ this.XPos +'" data-y="'+ this.YPos +'" >'+ this.yadmNm +'</a></td>'
					+ '<td>'+ this.telno +'</td>'
					+ '<td class="left">'+ this.addr +'</td>'
					+ '</tr>';
			});
			tag += '</table>';
			$('#data-list').html( tag );*/
			makePage(data.count, page);
			
		},error: function(req, text){
			alert(text+':'+req.status);
		}
	});
}

var pageList = 10, blockPage = 10, viewType = 'list';

function makePage(totalList, curPage){
	var pageVO = new Object();
	
	pageVO.totalPage 
	= parseInt(totalList / pageList) + (totalList % pageList>0 ? 1 : 0);
	pageVO.totalBlock
	= parseInt(pageVO.totalPage / blockPage) 
		+ (pageVO.totalPage % blockPage==0 ? 0 : 1);
	pageVO.curBlock
	= parseInt(curPage / blockPage)	+ (curPage % blockPage==0 ? 0 : 1); 

	pageVO.endPage = pageVO.curBlock*blockPage;
	pageVO.beginPage = pageVO.endPage - (blockPage-1);
	if( pageVO.endPage > pageVO.totalPage ) pageVO.endPage = pageVO.totalPage; 

	var tag = '';

	if(pageVO.curBlock > 1 ){
		tag += '<a class="page_first" data-page=1 >처음</a>'
			+ '<a class="page_prev" data-page='+ (pageVO.beginPage-blockPage) +'>이전</a>';
	}

	for(var no=pageVO.beginPage; no<=pageVO.endPage; no++ ){
		if( no==curPage )
			tag += '<span class="page_on">'+ no +'</span>';
		else
			tag += '<a class="page_off" data-page='+ no +'>'+ no +'</a>';
	}

	if( pageVO.curBlock < pageVO.totalBlock ){
		tag += '<a class="page_next" data-page='+ (pageVO.endPage+1) +'>다음</a>'
			+ '<a class="page_last" data-page='+ pageVO.totalPage +'>마지막</a>';
	}
	$('.page_list').html( tag );
}

function animal_list(){
	
}
</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#popup {
	position:absolute;	 left:50%;  top:50%;  transform: translate(-50%, -50%);
	width:350px;   height:350px;
	border:2px solid #666;   border-radius:100%;    display:none;  
}
table tr td {text-align: left; word-break:break-all}

#comment_regist span {width:50%; float: left;}

</style>
</head>
<body>
<h3>방명록 상세</h3>
<table>
<tr><th class='w-px120'>제목</th>
	<td colspan='5' class='left'>${vo.title}</td>
</tr>
<tr><th>작성자</th>
	<td>${vo.name}</td>
	<th class='w-px120'>작성일자</th>
	<td class='w-px120'>${vo.writedate}</td>
	<th class='w-px80'>조회수</th>
	<td class='w-px80'>${vo.readcnt}</td>
</tr>
<tr><th>내용</th>
	<td colspan='5' class='left'>${fn:replace( fn:replace( vo.content, lf, '<br>' ), crlf, '<br>')}</td>
</tr>
<tr><th>첨부파일</th>
	<td colspan='5' class='left'>${vo.filename}
	<c:if test='${!empty vo.filename}'>
		<span id='preview'></span>
		<a href='download.bo?id=${vo.id}'><i class='fas fa-download'></i>
		</a>
	</c:if>
	</td>
</tr>
</table>
<div class="btn-fill">
	<a onclick="$('form').submit()" class="btn-fill">목록으로</a>
	<!-- 글쓰기한 사용자로 로그인 된 경우만 수정/삭제 가능 -->
	<c:if test="${login_info.id eq vo.writer }">
		<a class="btn-fill" onclick="$('form').attr('action','modify.bo'); $('form').submit()">수정</a>
		<a class='btn-fill' 
	onclick="if( confirm('정말 삭제?') ) { $('form').attr('action','delete.bo'); $('form').submit() }">삭제</a>
	</c:if>
</div>
<form method="post" action="list.bo">
<input type="hidden" name="id" value="${vo.id }">
<input type="hidden" name="curPage" value="${page.curPage }">
<input type="hidden" name="search" value="${page.search }">
<input type="hidden" name="keyword" value="${page.keyword }">
<input type="hidden" name="pageList" value="${page.pageList }">
<input type="hidden" name="viewType" value="${page.viewType }">
</form>

<div id='popup-background' onclick="$('#popup, #popup-background').css('display', 'none');"></div>
<div id='popup'></div>

<div style='margin:0 auto; padding-top:20px; width:500px; '>
	<div id='comment_regist'>
	<span class='left'><strong>댓글작성</strong></span>
	<span class='right'><a onclick='comment_regist()' class='btn-fill-s'>등록</a></span>
	<textarea id='comment' style='width:99%; height:60px; margin-top:5px; resize:none '></textarea> 
	</div>
<div id="comment_list" class="left">
	
</div>
</div>
<script type="text/javascript">
if( ${!empty vo.filename} ){
	showAttachedImage( '#preview' );
}
comment_list();
function comment_list(){
	$.ajax({
		url : 'board/comment/${vo.id}',
		success: function(data){
			$('#comment_list').html(data);
			
		},error: function(req, text){
			alert(text+':'+req.status);
		}
	});
}

function comment_regist(){
	if( ${empty login_info} ){
		alert('댓글을 등록하려면 로그인하세요!');
		return;
	}else if( $('#comment').val()=='' ){
		alert('댓글을 입력하세요!');
		$('#comment').focus();
		return;
	}

	$.ajax({
		url: 'board/comment/regist',
		data: { content:$('#comment').val(), bid:${vo.id} },
		success: function(data){
			if( data==1 ){
				alert('댓글이 등록되었습니다');
				$('#comment').val('');
				comment_list();
			}
			
		},error: function(req, text){
			alert(text+':'+req.status);
		}
	});
	
}

function showAttachedImage( id ){
	var filename = '${vo.filename}';
	var ext = filename.substring( filename.lastIndexOf('.')+1 ).toLowerCase();
	var imgs = [ 'jpg', 'gif', 'bmp', 'png', 'jpeg' ];
	if( imgs.indexOf(ext) > -1 ){
		// /upload/board/2020/09/24/abc.png
		var tag = '<img src="' + '${vo.filepath}'.substring(1)  
					 	+ '" class="' + (id=='#popup' ? 'popup' : 'file-img')  +  '" ' 
					 	+ ' id="preview-img" />';
	 	$(id).html(tag);
	}
} 
$('#preview-img').on('click', function(){
	$('#popup, #popup-background').css('display', 'block');
	showAttachedImage( '#popup' );
	
});


</script>
</body>
</html>
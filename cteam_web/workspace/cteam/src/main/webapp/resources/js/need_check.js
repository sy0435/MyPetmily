/**
 *  필수입력항목에 입력여부를 반환하는 처리
 */

function necessary(){
	var need = true;
	$('.need').each(function(){
		console.log( $(this).prop("tagName") );
		if( $(this).prop("tagName")=='SELECT' && $(this).val()==null ) {
			alert($(this).attr("title")+' 선택하세요');
			$(this).focus();
			need=false;
			return need;
				
		}else{
			if($(this).val()=='') {
				alert($(this).attr("title")+'을 입력하세요');
				$(this).focus();
				need=false;
				return need;
			}
		}
	});
	return need;
}
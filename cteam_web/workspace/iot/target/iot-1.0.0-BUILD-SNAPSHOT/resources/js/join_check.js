/**
 *  회원가입시 유효성체크
 */
 
 var space = /\s/g;
 
 var join = {
 	common : { 
 		empty : {code: 'invalid', desc:'필수항목을 모두 입력하세요'},
 		space : {code: 'invalid', desc:'공백없이 입력하세요'},
 		min: {code: 'invalid', desc:'최소 5자이상 입력하세요'},
 		max: {code: 'invalid', desc:'최대 10자이하 입력하세요'},
 	},
 	
 userid : {
 	valid : {code:'valid', desc:'유효한 형식의 아이디입니다. 아이디 중복확인하세요'},
 	invalid : { code:'invalid', desc:'아이디는 영문 소문자 , 숫자만 입력하세요.' },
 	usable : { code:'valid', desc : '사용가능한 아이디입니다.'},
 	unUsable : { code:'invalid', desc : '사용중인 아이디입니다.'},
 },
 	
 userid_status: function(id){
 	var reg = /[^a-z0-9]/g;
 	
 	if( id == '' )				return this.common.empty;
 	else if( id.match(space) ) 	return this.common.space;
	else if( reg.test(id) )		return this.userid.invalid;
 	else if( id.length < 5 )	return this.common.min;
 	else if( id.length > 10 )	return this.common.max;
 	else 						return this.userid.valid;
 },
 
 userid_usable : function( data ){
  if( data )		return this.userid.usable;
  else				return this.userid.unUsable;
 },
 
 userpwd : {
 	valid : {code:'valid', desc:'사용가능한 비밀번호 입니다.'},
 	invalid : { code: 'invalid', desc:'비밀번호는 영문 소문자 , 숫자만 입력하세요.' },
 	lack :{ code:'invalid', desc :'영문 대/소문자, 숫자를 모두 포함해야합니다.'},
 	equal : {code:'valid', desc :'비밀번호가 일치합니다.' },
 	notEqual : {code:'invalid', desc :'비밀번호가 일치하지 않습니다.' }
 },
 
 userpwd_status : function( pwd ){
 	var reg = /[^a-zA-Z0-9]/g;
 	var upper = /[A-Z]/g, lower = /[a-z]/g, digit = /[0-9]/g;
 	if ( pwd == '' )			 return this.common.empty;
 	else if( pwd.match(space) )  return this.common.space;
 	else if( reg.test(pwd) )	 return this.userpwd.invalid;
 	else if( pwd.length < 5 ) 	 return this.common.min;
 	else if( pwd.length > 10 ) 	 return this.common.max;
 	else if ( !upper.test(pwd) || !lower.test(pwd) || !digit.test(pwd)) 
 								 return this.userpwd.lack;
 	else 						 return this.userpwd.valid;
 },
 
 userpwd_ck_status : function(pwd_ck){
 	if(pwd_ck == $('[name=pwd]').val())		return this.userpwd.equal;
 	else									return this.userpwd.notEqual;
 },
 
 useremail : {
 	valid: {code:'valid', desc:'유효한 이메일입니다.'},
 	invalid: {code:'valid', desc:'유효하지 않은 이메일입니다.'}
 },
 
 useremail_status : function( email ) {
 	var reg= /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
 	if(email == '' )				return this.common.empty;
 	else if( email.match(space) )	return this.common.space;
 	else if( reg.test(email) )		return this.useremail.valid;
 	else							return this.useremail.invalid;
 },
 
 tag_status : function( tag ){
 	var data = tag.val();
 	tag = tag.attr('name');
 	if(tag == 'id')		data = this.userid_status(data);
 	else if(tag == 'pwd')		data = this.userpwd_status(data);
 	else if(tag == 'pwd_ck')	data = this.userpwd_ck_status(data);
 	else if(tag == 'email')		data = this.useremail_status(data);
 	
 	return data;
 }
 
 
 
 }
/**
 * 
 */
  var space = /\s/g;
 
 var join={
 
 	common:{
 		empty:{code:'invalid', desc:'필수항목을 모두 입력하세요'},
 		space:{code:'invalid', desc:'공백없이 입력하세요'},
 		min:{code:'invalid', desc:'최소 5자이상 입력하세요'},
 		max:{code:'invalid', desc:'최대 10자이하 입력하세요'},
 	
 	},
 	
 	userid : {
 		valid: {code:'valid' , desc:'아이디 중복확인하세요'},
 		invalid:{ code:'invalid', desc:'아이디는 영문,소문자,숫자만 입력하세요'},
 		usable:{ code:'valid', desc:'사용가능한 아이디입니다'},
 		unUsable:{ code:'invalid', desc:'사용중인 아이디입니다'},
 	
 	},
 	
 	userid_status: function(id){
 		var reg=/[^a-z0-9]/g;
 		
 		if( id =='')					return this.common.empty;
 		else if( id.match( space ) )	return this.common.space;
 		else if( reg.test(id) )			return this.userid.invalid;
 		else if (id.length < 5 )		return this.common.min;
 		else if (id.length > 10 ) 		return this.common.max;
 		else							return this.userid.valid;
 		
 	},
 	userid_usable : function( data ){
 		if( data ) 		return this.userid.usable;
 		else			return this.userid.unUsable;
 	},
 	
 	userpwd : {
 		valid : {code: 'valid', desc:'사용가능한 비밀번호입니다'},
 		invalid:{code:'invalid', desc:'비밀번호는 소문자, 숫자, 특수문자만 입력하세요'},
 		lack:{code:'invalid', desc:'영문, 숫자, 특수문자를 포함해야 합니다'},
 		equal: {code:'valid', desc:'비밀번호가 일치합니다'},
 		notEqual:{code:'invalid', desc:'비밀번호가 일치하지 않습니다'}
 	},
 	
 	
 	
 	userpw_status:function( pwd ){
 		//var reg=/(?=.*\d{1,10})(?=.*[~`!@#$%\^&*()-+=]{1,10})(?=.*[a-zA-Z]{1,10}).{8,10}$/i;
 		reg = /[^a-z0-9~`!@#$%\^&*()-+=]/g;
 		var lower=/[a-z]/g, digit=/[0-9]/g, special=/[~!@#$%^&*()_+|<>?:{}]/g
 		if( pwd=='') return this.common.empty;
 		else if(pwd.match(space)) return this.common.space;
 		else if(reg.test(pwd)) return this.userpwd.invalid;
 		else if(pwd.length < 5 ) return this.common.min;
 		else if(pwd.length > 10 ) return this.common.max;
 		else if(!lower.test(pwd) || !digit.test(pwd) || !special.test(pwd) ) return this.userpwd.lack;
 		else	return this.userpwd.valid;
 		
 	},
 	userpw_ck_status: function( pwd_ck ){
 		if( pwd_ck==$('[name=member_pw]').val() ) return this.userpwd.equal;
 		else		return this.userpwd.notEqual;
 		
 	},
 	
 	
 	
 	useremail : {
 	 	valid: {code:'valid',desc:'유효한 이메일입니다.'},
 	 	invalid:{code:'invalid',desc:'유효하지않은 이메일입니다'}
 		
 	},
 	
 	useremail_status:function( email ){
 		var reg=/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
 		if( email.match(space) )	return this.common.space;
 		else if( reg.test(email) ) 		return this.useremail.valid;
 		else							return this.useremail.invalid;
 	},	
 	
 	answer:{
 		valid:{code:'valid',desc:'비밀번호 찾기 답이 입력되었습니다'}

 	},
 	
 	useranswer_status:function( answer ) {
 		if(answer=='')					return this.common.empty;
 		 else if( answer.match(space) )	return this.common.space;
 		 else							return this.answer.valid;
 		
 	},
 	
 	phonenum:{
 		valid:{code:'valid',desc: '전화번호가 입력되었습니다'}
 	},
 	
 	userphonenum_status( phonenum ) {
 		if(phonenum =='') return this.common.empty;
 		else		return this.phonenum.valid;
 		
 	},
 
 	
 	tag_status : function( tag ) {
 		var data= tag.val();
 		tag = tag.attr('name');
 		if( tag =='member_id' ) 		data = this.userid_status(data);
 		else if(tag == 'member_pw') 	data = this.userpw_status(data);
 		else if(tag == 'member_pw_ck') 	data = this.userpw_ck_status(data);
 		else if(tag == 'member_email') 	data = this.useremail_status(data);
 		else if(tag == 'member_answer') data = this.useranswer_status(data);
 	 	else if(tag == 'member_phonenum') data = this.userphonenum_status(data);
 	
 		return data;
 	}
 	
 
 }
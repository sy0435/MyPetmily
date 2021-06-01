package com.hanul.iot;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;

@Controller
public class MemberController {
	@Autowired private MemberServiceImpl service;
	@Autowired private CommonService common;
	
	//회원가입처리
	@ResponseBody @RequestMapping(value="/join", produces = "text/html; charset=utf-8")
	public String join(MemberVO vo, HttpServletRequest req, HttpSession session) {
		//화면에서 입력한 회원정보를 DB에 저장한다.
		String msg = "<script type='text/javascript'>";
		if(service.member_insert(vo)) {
			common.sendEmail(vo.getEmail(), vo.getName(),session);
			msg += "alert('회원가입이 완료되었습니다.'); location='"
					+req.getContextPath()+"'";
		}else {
			msg += "alert('회원가입에 실패하였습니다.); history.go(-1)";
		}
			msg += "</script>";
		
		return msg;
	}
	
	//아이디 중복화면 요청
	@ResponseBody @RequestMapping("/id_check")
	public boolean id_check(String id) {
		//화면에서 입력한 아이디가 DB에 존재하는지 여부를 판닪나다.
		return service.member_id_check(id);
	}
	
	//회원가입화면 요청
	@RequestMapping("/member")
	public String member(HttpSession session) {
		session.removeAttribute("category");
		return "member/join";
	}
	
	//로그아웃처리	
	@ResponseBody @RequestMapping("/logout")
	public void logout(HttpSession session) {
		//세션의 로그인 정보를 삭제한다
		session.removeAttribute("login_info");
	}
	
	//로그인처리
	@ResponseBody @RequestMapping("/login")
	public Boolean login(String userid, String userpwd, HttpSession session) {
		//화면에서 입력한 이이디, 비번이 일치하는 회원정보를
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("pwd", userpwd);
		service.member_login(map);
		MemberVO vo = service.member_login(map);
		//DB에서 조회해와 세션에 담는다.
		session.setAttribute("login_info", vo);
		return vo == null? false: true;
	}//login
	

	
}//MemberController

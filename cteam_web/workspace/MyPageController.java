package com.hanul.cteam;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import member.MemberVO;
import mypage.MypageServiceImpl;

@Controller
public class MyPageController {
	@Autowired private MypageServiceImpl service;
	
	//마이페이지 선택항목불러오기
	@RequestMapping("/list.my")
	public String mypage(Model model, HttpSession session) {
		String member_id = ((MemberVO)session.getAttribute("login_info")).getMember_id();
		model.addAttribute("orderlist", service.my_state(member_id));
		return "mypage/mypagelist";
	} 
	
	//회원정보 상세페이지
	@RequestMapping("/mypageDetail.my")
	public String mypage_detail(Model model, HttpSession session) {
		//session.setAttribute("category", "my");
		return "mypage/mypageDetail";
	}
	
	//내주문조회
	@RequestMapping("/myorder.my")
	public String mypage_list(Model model, HttpSession session) {
		if( session.getAttribute("login_info")==null ) 
			return "redirect:/";
		else	{
			String member_id = ((MemberVO)session.getAttribute("login_info")).getMember_id(); 
			//session.setAttribute("category", "my");
			model.addAttribute("orderlist", service.order_list(member_id));
			return "mypage/myorder";
		}
	}
	
	//내 주문 상세조회
	@RequestMapping("/orderdetail.my")
	public String orderdetail(Model model, HttpSession session) {
		
		session.setAttribute("category", "my");
		if( session.getAttribute("login_info")==null ) 
				return "redirect:/";
		else	return "mypage/orderdetail";
	}
	
	//내가 쓴 글, 댓글 보기
	@RequestMapping("/myWrite.my")
	public String my_write(Model model, HttpSession session) {
		String member_id = ((MemberVO)session.getAttribute("login_info")).getMember_id();
		session.setAttribute("category", "my");
		
		model.addAttribute("board", service.my_board(member_id));
		model.addAttribute("comment", service.my_comment(member_id));
		return "mypage/myWrite";
	}
	
	//회원정보 수정
	@RequestMapping("/update.my")
	public String my_update(MemberVO vo,HttpSession session, Model model) {
		if(service.update(vo)==1) {
			session.setAttribute("login_info", vo);
			
		}
		return "mypage/mypagelist";
	}
	
	
}

package com.hanul.cteam;

import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import calendar.CalendarServiceImpl;
import member.MemberVO;

@Controller
public class CalController {
	
	@Autowired private CalendarServiceImpl service;
	
	//내 펫 선택창 요청
	@RequestMapping("/list.mp")
	public String list(HttpSession session, Model model) {
		
		MemberVO member = (MemberVO)session.getAttribute("login_info");
		
		if(member != null) {
			model.addAttribute("petList", service.calendar_petList(member.getMember_id()));
			model.addAttribute("vo", service.calendar_list(member.getMember_id()) );
		}else {
			model.addAttribute("msg", "로그인 후 이용가능합니다.");
			model.addAttribute("url", "loginPage");
			
			/*
			 * response.setContentType("text/html; charset=UTF-8"); PrintWriter out =
			 * response.getWriter();
			 * out.println("<script>alert('로그인 후 이용 가능합니다.'); history.go(-1);</script>");
			 * out.flush();
			 */

		}
		
		
//		CalendarVO calendar = (CalendarVO)session.getAttribute("login_info");
		return "calendar/list";

	}
}
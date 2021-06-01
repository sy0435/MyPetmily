package com.hanul.cteam;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CompanyController {
	
	//회사소개 화면 이동
	@RequestMapping("/intro.co")
	public String company(HttpSession session) {
		session.setAttribute("category", "co");
		return "company/intro";
	}
	
}
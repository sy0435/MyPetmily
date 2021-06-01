package com.hanul.iot;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import customer.CustomerServiceImpl;
import customer.CustomerVO;

@Controller
public class CostomerController {
	@Autowired private CustomerServiceImpl service;
	
	//고객정보삭제처리요청
	@RequestMapping("/delete.cu")
	public String delete(int id) {
		//해당고객정보를 DB에서 삭제한 후 목록화면으로 연결
		service.customer_delete(id);
		return "redirect:list.cu";
	}
	
	//고객정보수정 저장처리 요청
	@RequestMapping("/update.cu")
	public String update(CustomerVO vo) {
		//선택한 고객의 정보를 DB에서 저장한 후 상세화면으로 연결
		service.customer_update(vo);
		return "redirect:detail.cu?id="+ vo.getId() ;
	}
	
	//고객정보수정화면 요청
	@RequestMapping("/modify.cu")
	public String modify(Model model, int id) {
		//선택한 고객의 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.customer_detail(id) );
		return "customer/modify";
	}
	
	//고객상세화면 요청
	@RequestMapping("/detail.cu")
	public String detail(Model model, int id) {
		//선택한 고객의 정보를 DB에서 조회해와 상세화면에 출력
		
		model.addAttribute("vo", service.customer_detail(id) );
		return "customer/detail";
	}
	//신규고객 등록요청
	@RequestMapping("/insert.cu")
	public String insert(CustomerVO vo) {
		//화면에서 입력한 정보들을 DB에 저장한 후 목록화면으로 연결
		service.customer_insert(vo);
		
		return "redirect:list.cu";
	}
	
	//신규고객 등록화면 요청
	@RequestMapping("/new.cu")
	private String customer() {
		
		return "customer/new";
	}
	
	//고객목록화면 요청
	@RequestMapping("/list.cu")
	public String list(Model model, HttpSession session) {
		session.setAttribute("category", "cu");
		//DB에서 고객목록을 조회해와서 목록화면에 출력할 수 있도록한다.
		List<CustomerVO> list = service.customer_list();
		model.addAttribute("list", list);
		
		return "customer/list";
	}
}

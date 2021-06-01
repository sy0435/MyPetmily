package com.hanul.cteam;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import common.CommonService;
import member.MemberVO;
import order.OrderGoodsVO;
import shop.ShopPage;
import shop.ShopServiceImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ShopCotroller {
	
	@Autowired private ShopServiceImpl service;
	@Autowired private ShopPage page;
	@Autowired private CommonService common;
	
	
	@RequestMapping("/order.sh")
	public String order( HttpSession session, Model model, String search, String keyword,@RequestParam(defaultValue ="1") int curPage,
		 String option_name, String item_price, String item_name, String item_imgpath, String totalPrice, int item_num, @RequestParam(defaultValue="10") int pageList ) {
		
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		page.setPageList(pageList);
		
		OrderGoodsVO vo = new OrderGoodsVO();
		vo.setItem_num(item_num);
		vo.setOption_name(option_name);
		vo.setItem_imgpath(item_imgpath);
		vo.setItem_name(item_name);
		vo.setItem_price(item_price);
		vo.setTotalPrice(totalPrice);
		
		if((MemberVO)session.getAttribute("login_info") != null) {
		String member_id= ((MemberVO)session.getAttribute("login_info")).getMember_id();
		
		
		model.addAttribute("member", service.member(member_id));
			
		
		
		}
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		
		return "order/order";
	}
	
	
	
	@RequestMapping("/delete.sh")
	public String delete(int item_num, Model model) {
		
		service.shop_delete(item_num);
		model.addAttribute("page",page);
		model.addAttribute("url","list.sh");
		
		return "shop/redirect";
	}

	
	
	@RequestMapping("/modify.sh")
	public String modify(int item_num, Model model) {
		
		return "shop/itemModify";
	}

	
	//상세보기
	@RequestMapping("/item.detail")
	public String itemDetail(Model model, int item_num) {
		
		model.addAttribute("list",service.shop_detail(item_num));
		model.addAttribute("crlf","\r\n");
		model.addAttribute("lf","\n");
		model.addAttribute("page",page);
		
		return "shop/itemDetail1";
	}
	
	

	
	
	@RequestMapping("/list.sh")
	public String list(Model model, HttpSession session, @RequestParam(defaultValue ="1") int curPage,
			 String search, String keyword ) {
		
		session.setAttribute("category", "sh");
		
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page", service.shop_list(page));
		
	
		
		return "shop/shopPage";
	}
	
	
	
	 
}

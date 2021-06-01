package com.hanul.cteam;

import java.text.DateFormat;
import java.util.Date;
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
import shop.ShopPage;
import shop.ShopServiceImpl;




/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired private ShopServiceImpl service;
	@Autowired private ShopPage page;
	@Autowired private CommonService common;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, HttpSession session, Model model,@RequestParam(defaultValue ="1") int curPage,
			 String search, String keyword ) {
		logger.info("Welcome home! The client locale is {}.", locale);
		session.removeAttribute("category");
		
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page", service.shop_list(page));
		
		return "home/home";
	}
	
}

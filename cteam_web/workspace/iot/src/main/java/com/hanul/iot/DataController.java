package com.hanul.iot;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.CommonService;

@Controller
public class DataController {
	private String key 
	 = "FPgj2NXbJw46TcGkmAfZEiYFDbxilys7KLjk3KaB7AfeJE00ZhPNM0M8unwbsI69fSmT8SNfVEimE6ZZ2U14hA%3D%3D"; 
	@Autowired private CommonService common;
	
	//약국정보조회 요청
	@ResponseBody @RequestMapping(value = "/data/pharmacy"
			, produces = "application/json; charset=utf-8")
	public String pharmacy_list(int pageNo, int rows) {
		StringBuilder url =	
			new StringBuilder("http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList");
		url.append("?ServiceKey="+key);
		url.append("&_type=json");
		url.append("&pageNo="+pageNo);
		url.append("&numOfRows="+rows);
		
		return common.json_list(url);
	}
	
	//공공데이터화면 요청
	@RequestMapping("/list.da")
	public String data(HttpSession session) {
		session.setAttribute("category", "da");
		return "data/list";
	}
}

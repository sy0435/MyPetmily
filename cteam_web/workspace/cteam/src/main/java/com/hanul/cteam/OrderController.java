package com.hanul.cteam;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import order.OrderGoodsVO;
import order.OrderServiceImpl;
import order.OrderVO;
import shop.ShopVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class OrderController {
	
	@Autowired
	private OrderServiceImpl service;
	
	
	  @RequestMapping("/orderPage")
	  public String orderPage(Model model, @RequestParam int item_num, @RequestParam String order_num) {
		  model.addAttribute("shopVO", service.order_show(item_num));
		  model.addAttribute("goods", service.order_select(order_num));
		  
		  System.out.println(((List<OrderVO>)service.order_select(order_num)).get(0).getOption_info());
		  
	  return "order/orderSuccess"; 
	  }
	 
	
	
	@ResponseBody 
	@RequestMapping(value = "/orderSuccess", produces = "text/html; charset=utf-8")
	public String orderSuccess(Model model,OrderVO orderVo, OrderGoodsVO vo, HttpServletRequest req) {
		
		String success="<script type='text/javascript'>";
		
		if(service.order_insert(orderVo)) {
			
			model.addAttribute("orderVo",orderVo);
			
			System.out.println(orderVo.getOrder_num());
			
			success += "alert('주문이 완료되었습니다'); location='"
					+ req.getContextPath()+"/orderPage?item_num="+orderVo.getItem_num()+"&order_num="+orderVo.getOrder_num()+"'";
			

		}else {
			success+="alert('주문이 실패했습니다'); histroy.go(-1)";
		}
		
		
		success+="</script>";
		
		return success;
	}	
	
	
}

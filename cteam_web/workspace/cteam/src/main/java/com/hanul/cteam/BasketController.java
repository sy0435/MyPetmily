package com.hanul.cteam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Basket.BasketServiceImpl;
import Basket.CartVO;
import member.MemberServiceImpl;
import member.MemberVO;
import order.OrderVO;

@Controller
public class BasketController {
	
	@Autowired BasketServiceImpl service;

	
	
	//주문넣기
	@RequestMapping("/orderInsert")
	public String orderInsert(HttpSession session,Model model, OrderVO orderVo) {
		
		String member_id=((MemberVO)session.getAttribute("login_info")).getMember_id();
		List<CartVO> carts=service.cart_select(member_id);
        int dice = new Random().nextInt(4589362) + 49311;
		orderVo.setOrder_num( new SimpleDateFormat("yyyyMMdd").format(new Date().getTime())  +dice);
		orderVo = service.cartOrder_insert(orderVo,carts);
		
		//System.out.println(carts.get(0).getCart_num());
		
		model.addAttribute("orderVO", orderVo);
		
//		model.addAttribute("vo", orderVo);
		
		

		return "order/cartOrderSuccess";
	}
	
	

	
	
	
	//주문하기
	
	@RequestMapping("/cart_order")
	public String cart_order(HttpSession session,Model model) {

		
		String member_id=((MemberVO)session.getAttribute("login_info")).getMember_id();
		List<CartVO> carts=service.cart_select(member_id);
		
		model.addAttribute("carts", carts);
		
		model.addAttribute("member", service.cart_member(member_id));
		
		return "order/cartOrder";
	}
	
	
	
	
	//장바구니 삭제
	
	
	@RequestMapping("/cart_delete")
	@ResponseBody
	public String go_cart(Model model,@RequestParam int cart_num) {
		
		String success="";
		if( service.cart_delete(cart_num) )
			{
				success="성공";
			}
		System.out.println(success);
		
		return success;
	}
	
	//장바구니로 가기
	@RequestMapping("/cart.bs")
	public String go_cart(HttpSession session,Model model) {
		session.setAttribute("category", "bs");
		
		if((session.getAttribute("login_info"))!=null) {
			

		MemberVO login = (MemberVO)session.getAttribute("login_info");
		
		String member_id=login.getMember_id();
		
		model.addAttribute("list",service.cart_select(member_id));
		
		}
		return "basket/cart";
	}
	
	@RequestMapping("/cartAdd")
	@ResponseBody
	public boolean CartAdd(@RequestParam("option_info") String option_info, @RequestParam("totalPrice") String totalPrice,
							@RequestParam("item_num") String item_num,HttpSession session){
		
	
		MemberVO login = (MemberVO)session.getAttribute("login_info");
		
		String member_id=login.getMember_id();
		
		CartVO vo= new CartVO();
		
		vo.setItem_num(item_num);
		vo.setOption_info(option_info);
		vo.setTotalPrice(totalPrice);
		vo.setMember_id(member_id);
			
		
		return service.cart_insert(vo);
		
	}
	
}

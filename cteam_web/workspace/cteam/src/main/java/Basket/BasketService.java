package Basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import member.MemberVO;
import order.OrderVO;

@Service
public interface BasketService {

	boolean cart_insert(CartVO vo);
	
	List<CartVO> cart_select(String member_id);
	
	MemberVO cart_member(String member_id);
	
	OrderVO cartOrder_insert(OrderVO orderVo,List<CartVO> carts);
	/* List<CartVO> cartOrder_select(String member_id); */
	
	boolean cart_delete(int cart_num);

}

package Basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.MemberVO;
import order.OrderVO;

@Service
public class BasketServiceImpl implements BasketService {
	@Autowired private BasketDAO dao;
	
	


	@Override
	public boolean cart_insert(CartVO vo) {
	
		return dao.cart_insert(vo);
	}

	@Override
	public List<CartVO> cart_select(String member_id) {
		// TODO Auto-generated method stub
		return dao.cart_select(member_id);
	}

	@Override
	public MemberVO cart_member(String member_id) {
		// TODO Auto-generated method stub
		return dao.cart_member(member_id);
	}

	@Override
	public OrderVO cartOrder_insert(OrderVO orderVo, List<CartVO> carts) {
		
		return dao.cartOrder_insert(orderVo, carts);
	}

	@Override
	public boolean cart_delete(int cart_num) {

		return dao.cart_delete(cart_num);
	}


	/*
	 * @Override public List<CartVO> cartOrder_select(String member_id) { // TODO
	 * Auto-generated method stub return dao.cartOrder_select(member_id); }
	 */


}

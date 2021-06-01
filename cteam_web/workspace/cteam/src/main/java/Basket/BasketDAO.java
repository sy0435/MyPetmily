package Basket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.MemberVO;
import order.ItemVO;
import order.OrderVO;

@Repository
public class BasketDAO implements BasketService {

	@Autowired
	private SqlSession sql;

	@Override
	public boolean cart_insert(CartVO vo) {

		return sql.insert("cart.mapper.cart_insert", vo) > 0 ? true : false;
	}

	@Override
	public List<CartVO> cart_select(String member_id) {

		List<CartVO> cartlist = sql.selectList("cart.mapper.cart_select", member_id);

		for (CartVO cart : cartlist) {
			ItemVO item = sql.selectOne("cart.mapper.cartlist_item", cart.getItem_num());
			cart.setOrder_item(item);
		}

		return cartlist;
	}
	/*
	 * @Override public List<CartVO> cartOrder_select(String member_id) {
	 * 
	 * 
	 * List<CartVO> carts =
	 * sql.selectList("cart.mapper.cartOrder_select",member_id);
	 * 
	 * for(CartVO cart : carts) { ItemVO item =
	 * sql.selectOne("cart.mapper.cartItem_select",cart.getItem_num());
	 * cart.setOrder_item(item);
	 * 
	 * }
	 * 
	 * 
	 * return carts;
	 * 
	 * 
	 * }
	 */

	@Override
	public MemberVO cart_member(String member_id) {

		return sql.selectOne("shop.mapper.member_info",member_id);
	}

	@Override
	public OrderVO cartOrder_insert(OrderVO buyer, List<CartVO> carts) {
			sql.insert("order.mapper.order_insert", buyer);
			int order = 0; 
			HashMap<String, Object> map = new HashMap<String, Object>();
			for(CartVO vo : carts ) {
				map.put("order_num", buyer.getOrder_num());
				map.put("item_num", vo.getItem_num());
				map.put("option_info", vo.getOption_info());
				order = sql.insert( "order.mapper.order_goods_insert", map); 
			}
			
			System.out.println(buyer.getMember_id());
		
			sql.delete("cart.mapper.delete_cart",buyer.getMember_id());
			
			
			
		return order > 0? buyer : null;
	}

	@Override
	public boolean cart_delete(int cart_num) {
		
		return sql.delete("cart.mapper.cart_item_delete",cart_num) > 0 ? true : false;
	}

}

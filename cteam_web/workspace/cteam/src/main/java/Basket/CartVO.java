package Basket;

import java.util.List;

import order.ItemVO;

public class CartVO {
	private String item_num, option_info, totalPrice, member_id;
	private int cart_num;
	private ItemVO order_item;
	

	
	public ItemVO getOrder_item() {
		return order_item;
	}
	

	public void setOrder_item(ItemVO order_item) {
		this.order_item = order_item;
	}

	public int getCart_num() {
		return cart_num;
	}

	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}


	public String getItem_num() {
		return item_num;
	}

	public void setItem_num(String item_num) {
		this.item_num = item_num;
	}

	public String getOption_info() {
		return option_info;
	}

	public void setOption_info(String option_info) {
		this.option_info = option_info;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
	
	
}

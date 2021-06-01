package order;

import java.util.List;

public class OrderVO {
	
	private int item_num;
	private String order_num, option_name, member_id,member_phonenum,member_name,order_date,order_seq,member_email,
	shipping_message,shipping_address,shipping_phonenum,shipping_name,shipping_address2,
	shipping_post,pay,option_info;
	
	private List<OrderGoodsVO> goods_list;
	
	
	private String order_state="상품준비중";
	
	
	

	public List<OrderGoodsVO> getGoods_list() {
		return goods_list;
	}

	public void setGoods_list(List<OrderGoodsVO> goods_list) {
		this.goods_list = goods_list;
	}

	public String getOrder_seq() {
		return order_seq;
	}

	public void setOrder_seq(String order_seq) {
		this.order_seq = order_seq;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public String getOption_info() {
		return option_info;
	}

	public void setOption_info(String option_info) {
		this.option_info = option_info;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public int getItem_num() {
		return item_num;
	}

	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}

	public String getOrder_num() {
		return order_num;
	}

	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}

	public String getOption_name() {
		return option_name;
	}

	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_phonenum() {
		return member_phonenum;
	}

	public void setMember_phonenum(String member_phonenum) {
		this.member_phonenum = member_phonenum;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getShipping_message() {
		return shipping_message;
	}

	public void setShipping_message(String shipping_message) {
		this.shipping_message = shipping_message;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}

	public String getShipping_phonenum() {
		return shipping_phonenum;
	}

	public void setShipping_phonenum(String shipping_phonenum) {
		this.shipping_phonenum = shipping_phonenum;
	}

	public String getShipping_name() {
		return shipping_name;
	}

	public void setShipping_name(String shipping_name) {
		this.shipping_name = shipping_name;
	}

	public String getShipping_address2() {
		return shipping_address2;
	}

	public void setShipping_address2(String shipping_address2) {
		this.shipping_address2 = shipping_address2;
	}

	public String getShipping_post() {
		return shipping_post;
	}

	public void setShipping_post(String shipping_post) {
		this.shipping_post = shipping_post;
	}

	public String getOrder_state() {
		return order_state;
	}

	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	
}

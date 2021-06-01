package order;

public class OrderGoodsVO {
	private int item_num;
	private String option_name, item_price, item_name, item_imgpath,totalPrice;
	
	private String option_info,order_num;
	int orderlist_goods_num;
	
	
	
	
	public String getOption_info() {
		return option_info;
	}
	public void setOption_info(String option_info) {
		this.option_info = option_info;
	}
	public String getOrder_num() {
		return order_num;
	}
	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}
	public int getOrderlist_goods_num() {
		return orderlist_goods_num;
	}
	public void setOrderlist_goods_num(int orderlist_goods_num) {
		this.orderlist_goods_num = orderlist_goods_num;
	}
	public String getItem_price() {
		return item_price;
	}
	public void setItem_price(String item_price) {
		this.item_price = item_price;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_imgpath() {
		return item_imgpath;
	}
	public void setItem_imgpath(String item_imgpath) {
		this.item_imgpath = item_imgpath;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	
	public String getOption_name() {
		return option_name;
	}
	public void setOption_name(String option_name) {
		this.option_name = option_name;
	}
	
	
	
}

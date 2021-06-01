package admin;

import java.util.List;

public class SellItemVO {
	private int item_price, item_num;
	private String item_name, item_imgpath, item_content, item_content_imgpath, item_code;
	
	
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
		this.item_price = item_price;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
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
	public String getItem_content() {
		return item_content;
	}
	public void setItem_content(String item_content) {
		this.item_content = item_content;
	}
	public String getItem_content_imgpath() {
		return item_content_imgpath;
	}
	public void setItem_content_imgpath(String item_content_imgpath) {
		this.item_content_imgpath = item_content_imgpath;
	}

}

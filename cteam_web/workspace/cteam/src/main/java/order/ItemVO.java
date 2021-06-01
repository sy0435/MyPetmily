package order;

public class ItemVO {
	private int item_num, item_price;
	private String item_name, item_imgpath, item_content_imgpath, content, option_info;
	
	
	public String getOption_info() {
		return option_info;
	}
	public void setOption_info(String option_info) {
		this.option_info = option_info;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public int getItem_price() {
		return item_price;
	}
	public void setItem_price(int item_price) {
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
	public String getItem_content_imgpath() {
		return item_content_imgpath;
	}
	public void setItem_content_imgpath(String item_content_imgpath) {
		this.item_content_imgpath = item_content_imgpath;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}

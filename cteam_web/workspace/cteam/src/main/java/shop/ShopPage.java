package shop;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class ShopPage extends PageVO{
	private List<ShopVO> list;

	
	public List<ShopVO> getList() {
		return list;
	}

	public void setList(List<ShopVO> list) {
		this.list = list;
	}
	
		

}

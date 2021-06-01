package admin;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;

@Component
public class ListPage extends PageVO {
	private List<ItemListVO> list;

	public List<ItemListVO> getList() {
		return list;
	}

	public void setList(List<ItemListVO> list) {
		this.list = list;
	}
	

}

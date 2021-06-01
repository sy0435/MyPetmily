package order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.ShopVO;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired OrderDAO dao;

	@Override
	public boolean order_insert(OrderVO orderVo) {
		// TODO Auto-generated method stub
		return dao.order_insert(orderVo);
	}

	@Override
	public ShopVO order_show(int item_num) {
		// TODO Auto-generated method stub
		return dao.order_show(item_num);
	}

	@Override
	public List<OrderVO> order_select(String order_num) {
		// TODO Auto-generated method stub
		return dao.order_select(order_num);
	}
	
	


}

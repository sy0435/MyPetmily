package order;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import shop.ShopVO;

@Service
public interface OrderService {
	
	boolean order_insert(OrderVO orderVo);
	
	ShopVO order_show(int item_num);
	
	List<OrderVO> order_select(String order_num);
	
}

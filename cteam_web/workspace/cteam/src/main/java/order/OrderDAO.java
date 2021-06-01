package order;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shop.ShopVO;

@Repository
public class OrderDAO implements OrderService{

	@Autowired private SqlSession sql;

	@Override
	public boolean order_insert(OrderVO orderVo) {
			
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar c1 = Calendar.getInstance();

        String strToday = sdf.format(c1.getTime());


        		
        Random r = new Random();
        int dice = r.nextInt(4589362) + 49311;
        
        

		orderVo.setOrder_num(strToday+dice);
	
		
		sql.insert("order.mapper.order_insert", orderVo);//이걸타고 또 위에서부터 다시 탐?!
		

		
		return sql.insert("order.mapper.order_goods_insert", orderVo) > 0 ? true : false;
	}

	@Override
	public ShopVO order_show(int item_num) {

		return sql.selectOne("order.mapper.order_show", item_num);
	}

	@Override
	public List<OrderVO> order_select(String order_num) {
		
		List<OrderVO> orderVo= sql.selectList("order.mapper.orderlist",order_num);
		
		return orderVo;
	}

	
}

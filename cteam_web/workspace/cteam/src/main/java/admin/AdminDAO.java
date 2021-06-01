package admin;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import member.MemberVO;
import order.ItemVO;
import order.OrderListVO;

@Repository
	public class AdminDAO implements AdminService {
	@Autowired private SqlSession sql;
	@Override
	public int item_insert(SellVO vo) {
		// TODO Auto-generated method stub
		sql.insert("admin.mapper.item_insert", vo);
		System.out.println(vo.getItem_num());
		return sql.insert("admin.mapper.item_option_insert", vo);
	}
	
	@Override
	public ListPage list(ListPage page) {
		page.setTotalList(
			(Integer) sql.selectOne("admin.mapper.total",page));	
		page.setList(sql.selectList("admin.mapper.list",page));
		return page;
	}
	
	@Override
	public MemberListPage member_list(MemberListPage page) {
		page.setTotalList(
				(Integer) sql.selectOne("admin.mapper.membertotal",page));	
			page.setList(sql.selectList("admin.mapper.memberlist",page));
			return page;
	}
	
	@Override
	public MemberVO member_detail(String member_id) {
		
		return sql.selectOne("admin.mapper.detail",member_id);
}

	@Override
	public List<OrderListVO> order_list() {
	List<OrderListVO>  orderlist = sql.selectList("admin.mapper.orderlist");
		
		for(OrderListVO order : orderlist ) {
			List<ItemVO> items = sql.selectList("admin.mapper.orderlist_item", order.getOrder_num());
			order.setOrder_item(items);
		}
		
		return orderlist;
	}

	@Override
	public SellItemVO item_select(int item_num) {
		
		return sql.selectOne("admin.mapper.itemmodify", item_num);
	}

	@Override
	public List<OptionVO> option_select(int item_num) {
		return sql.selectList("admin.mapper.optionlist", item_num);
	}

	@Override
	public int option_delete(int item_num) {
		// TODO Auto-generated method stub
		return sql.delete("admin.mapper.optionDelete",item_num);
	}

	@Override
	public int item_update(SellModifyVO vo) {
		sql.insert("admin.mapper.option_Re_insert", vo);
		return sql.update("admin.mapper.update", vo);
	}

	@Override
	public DetailVO order_detail(String order_num) {
		
		DetailVO item = sql.selectOne("admin.mapper.orderDetail", order_num);
		item.setOrder_item(sql.selectList("admin.mapper.detailList_item",order_num));	
			
		return item;
	}
	@Override
	public int item_delete(int item_num) {
		// TODO Auto-generated method stub
		return sql.delete("admin.mapper.item_delete",item_num);
	}

	@Override
	public boolean code_check(String code) {
		// TODO Auto-generated method stub
		return (Integer)sql.selectOne("admin.mapper.code_check", code) >0? false: true;
	}

	@Override
	public void state_update(HashMap<String, String> map) {
		int result = sql.update("admin.mapper.state",map);
		System.out.println(result);
	}

	
	
	
}

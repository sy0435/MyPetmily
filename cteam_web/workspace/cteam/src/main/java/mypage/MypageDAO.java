package mypage;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Repository;

import member.MemberVO;
import order.ItemVO;
import order.OrderListVO;
import order.OrderVO;
import order.StateVO;

@Repository
public class MypageDAO implements MypageService{
	@Autowired private SqlSession sql;
	@Override
	public List<OrderListVO> order_list(String member_id) {
		 List<OrderListVO> orderlist = sql.selectList("mypage.mapper.orderlist", member_id);

		 for(OrderListVO order : orderlist ) { 
			 List<ItemVO> items =
			 sql.selectList("mypage.mapper.orderlist_item", order.getOrder_num());
			 order.setOrder_item(items); 
		 }
		
		 return orderlist; 
//				 sql.selectList("mypage.mapper.orderlist", member_id);
	}

	@Override
	public void mypage_update(MemberVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public StateVO my_state(String member_id) {
		// TODO Auto-generated method stub
		return sql.selectOne("mypage.mapper.orderstate", member_id);
	}

	@Override
	public List<WrtieVO> my_board(String member_id) {
		// TODO Auto-generated method stub
		return sql.selectList("mypage.mapper.myboard", member_id);
	}

	@Override
	public List<CommentVO> my_comment(String member_id) {
		return sql.selectList("mypage.mapper.mycomment", member_id);
	}

	@Override
	public int update(MemberVO vo) {
		String member_id = vo.getMember_id();
		return sql.update("mypage.mapper.update", vo);
	}



}

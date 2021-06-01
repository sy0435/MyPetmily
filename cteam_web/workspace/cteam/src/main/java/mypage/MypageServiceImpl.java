package mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import member.MemberVO;
import order.OrderListVO;
import order.OrderVO;
import order.StateVO;

@Service
public class MypageServiceImpl implements MypageService {
	
	@Autowired private MypageDAO dao;
	@Override
	public List<OrderListVO> order_list(String member_id) {

		return dao.order_list(member_id);
	}


	@Override
	public void mypage_update(MemberVO vo) {
		// TODO Auto-generated method stub

	}


	@Override
	public StateVO my_state(String member_id) {
		// TODO Auto-generated method stub
		return (StateVO) dao.my_state(member_id);
	}

	@Override
	public List<WrtieVO> my_board(String member_id) {
		// TODO Auto-generated method stub
		return dao.my_board(member_id);
	}


	@Override
	public List<CommentVO> my_comment(String member_id) {
		// TODO Auto-generated method stub
		return dao.my_comment(member_id);
	}


	@Override
	public int update(MemberVO vo) {
		// TODO Auto-generated method stub
		return dao.update(vo);
	}


}

package admin;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import member.MemberVO;
import order.ItemVO;
import order.OrderListVO;

@Service
public interface AdminService {
	int item_insert(SellVO vo);							//상품등록	
	ListPage list(ListPage vo);							//모든상품 가져오기
	MemberListPage member_list (MemberListPage vo);		//회원정보조회(10/16)
	MemberVO member_detail(String member_id);			//회원정보 상세조회
	List<OrderListVO> order_list();						//주문내역 조회
	void state_update(HashMap<String, String> map);				//주문상태 수정		
	SellItemVO item_select(int item_num);				//수정할 상품 불러오기
	List<OptionVO> option_select(int item_num);			//수정할 상품의 옵션 불러오기
	int option_delete(int item_num);					//옵션삭제
	int item_update(SellModifyVO vo);					//옵션수정기능
	DetailVO order_detail(String order_num);			//상세화면 수정
	int item_delete(int item_num);
	boolean code_check(String code);
}

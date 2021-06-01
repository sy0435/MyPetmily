package shop;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ShopService {
	int shop_insert(ShopVO vo);	//글스기 저장
	ShopPage shop_list(ShopPage page);	//목록조회
	List<ShopVO> shop_detail(int item_num);	//상세조회
	int shop_update(ShopVO vo); //글 변경저장
	int shop_delete(int item_num);//글삭제
	
	
	
	
	
}

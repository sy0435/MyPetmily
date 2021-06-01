package customer;

import java.util.List;

public interface CustomerService {
	//CRUD(Create, Read, Update, Delete)
	void customer_insert(CustomerVO vo);
	List<CustomerVO> customer_list();		//고객목록 조회
	CustomerVO customer_detail(int id);		//고객상세조회
	void customer_update(CustomerVO vo);	//고객정보 변견
	void customer_delete(int id);			//고객정보 삭제

}

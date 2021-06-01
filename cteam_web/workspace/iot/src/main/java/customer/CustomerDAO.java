package customer;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO implements CustomerService {
	@Autowired private SqlSession sql;
	//어디서든 SqlSession 에 접근가능하도록 메모리에 올려둔다
		
	@Override
	public void customer_insert(CustomerVO vo) {
		// TODO Auto-generated method stub
		sql.insert("customer.mapper.insert",vo);
	}

	@Override
	public List<CustomerVO> customer_list() {
		
		return sql.selectList("customer.mapper.list");		//mapper에서 쿼리문을 가져와서 적용
	}

	//어디서든 CustomerDAO 에 접근가능하도록 메모리에 올려둔다
	
	@Override
	public CustomerVO customer_detail(int id) {
		// TODO Auto-generated method stub
		return sql.selectOne("customer.mapper.detail",id);
	}

	@Override
	public void customer_update(CustomerVO vo) {
		
		sql.update("customer.mapper.update",vo);
	}

	@Override
	public void customer_delete(int id) {	
		sql.delete("customer.mapper.delete",id);
	}

}

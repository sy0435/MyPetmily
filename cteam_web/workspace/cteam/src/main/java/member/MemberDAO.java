package member;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO implements MemberService {

	@Autowired private SqlSession sql;

	@Override
	public boolean member_insert(MemberVO vo) {

		return sql.insert("member.mapper.join",vo) > 0? true : false;
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		
		
		
		return sql.selectOne("member.mapper.login",map);
	}

	@Override
	public boolean member_update(MemberVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_delete(String userid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean member_id_check(String userid) {

		return (Integer)sql.selectOne("member.mapper.id_check", userid) > 0 ? false: true;
	}

	public String member_idFind(HashMap<String, String> map) {
		return sql.selectOne("member.mapper.idFind",map);
	}

	@Override
	public boolean member_emailFind(String member_email) {

		return (Integer)sql.selectOne("member.mapper.emailFind",member_email) > 0 ? true : false;
	}

	@Override
	public boolean member_pwChange(String key, String member_id) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key", key);
		map.put("member_id", member_id);
		
		return sql.insert("member.mapper.pwChange", map) > 0? true : false;
	}


	
	
	
}

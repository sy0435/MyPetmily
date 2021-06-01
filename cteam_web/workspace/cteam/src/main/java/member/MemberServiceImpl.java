package member;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired private MemberDAO dao;
	
	@Override
	public boolean member_insert(MemberVO vo) {

		return dao.member_insert(vo);
	}

	@Override
	public MemberVO member_login(HashMap<String, String> map) {
		System.out.println(map.get("pwd"));
		return dao.member_login(map);
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
		return dao.member_id_check(userid);
	}

	public String member_idFind(HashMap<String, String> map) {
		
		return dao.member_idFind(map);
	}

	@Override
	public boolean member_emailFind(String member_email) {
		
		return dao.member_emailFind(member_email);
	}

	@Override
	public boolean member_pwChange(String key, String member_id) {

		return dao.member_pwChange(key, member_id);
	}




	
}

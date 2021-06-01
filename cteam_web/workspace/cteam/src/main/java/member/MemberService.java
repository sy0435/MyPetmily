package member;

import java.util.HashMap;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {
	boolean member_insert(MemberVO vo);//회원가입시 회원정보 저장
	MemberVO member_login(HashMap<String, String>map);//로그인
	boolean member_update(MemberVO vo); //회원정보 수정
	boolean member_delete(String userid); //회원탈퇴처리
	boolean member_id_check(String userid); //아이디 중복확인
	String member_idFind(HashMap<String, String> map);
	boolean member_emailFind(String member_email);
	boolean member_pwChange(String key,String member_id);
}

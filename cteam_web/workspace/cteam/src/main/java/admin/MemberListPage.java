package admin;

import java.util.List;

import org.springframework.stereotype.Component;

import common.PageVO;
import member.MemberVO;

@Component
public class MemberListPage extends PageVO {
	private List<MemberVO> list;

	public List<MemberVO> getList() {
		return list;
	}

	public void setList(List<MemberVO> list) {
		this.list = list;
	}
	
}

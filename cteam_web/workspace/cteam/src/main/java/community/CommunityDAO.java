package community;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommunityDAO implements CommunityService {

	@Autowired private SqlSession sql;
	
	//글 목록
	@Override
	public CommunityPage community_list(CommunityPage page) {
		page.setTotalList(sql.selectOne("community.mapper.list_count", page));
		page.setList(sql.selectList("community.mapper.list", page));
		return page;
		//return sql.selectList("community.mapper.list", page);
	}

	//글 상세보기
	@Override
	public CommunityVO community_detail(int board_num) {
		return sql.selectOne("community.mapper.detail", board_num);
	}
	
	//글 쓰기
	@Override
	public int community_insert(CommunityVO vo) {
		return sql.insert("community.mapper.insert", vo);
	}
	
	//글 수정하기
	@Override
	public int community_update(CommunityVO vo) {
		return sql.update("community.mapper.update", vo);
	}
	
	//글 삭제하기
	@Override
	public int community_delete(int board_num) {
		return sql.delete("community.mapper.delete", board_num);
	}

	//댓글 목록
	@Override
	public List<CommunityCommentVO> community_comment_list(int board_num) {
		return sql.selectList("community.mapper.comment_list", board_num);
	}

	//댓글 작성
	@Override
	public int community_comment_regist(CommunityCommentVO vo) {
		return sql.insert("community.mapper.comment_regist", vo);
	}

	//댓글 수정
	@Override
	public int community_comment_update(CommunityCommentVO vo) {
		return sql.update("community.mapper.comment_update", vo);
	}
	
	//댓글 삭제
	@Override
	public int community_comment_delete(int comment_num) {
		return sql.delete("community.mapper.comment_delete", comment_num);
	}

}

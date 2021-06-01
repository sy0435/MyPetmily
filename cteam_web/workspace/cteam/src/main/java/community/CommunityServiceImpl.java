package community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {
	
	@Autowired private CommunityDAO dao;

	//글 목록
	@Override
	public CommunityPage community_list(CommunityPage page) {
		return dao.community_list(page);
	}
	
	//글 상세보기
	@Override
	public CommunityVO community_detail(int board_num) {
		return dao.community_detail(board_num);
	}

	//글 쓰기
	@Override
	public int community_insert(CommunityVO vo) {
		return dao.community_insert(vo);
	}
	
	//글 수정하기
	@Override
	public int community_update(CommunityVO vo) {
		return dao.community_update(vo);
	}

	//글 삭제하기
	@Override
	public int community_delete(int board_num) {
		return dao.community_delete(board_num);
	}

	//댓글 목록
	public List<CommunityCommentVO> community_comment_list(int board_num) {
		return dao.community_comment_list(board_num);
	}

	//댓글 작성
	public int community_comment_regist(CommunityCommentVO vo) {
		return dao.community_comment_regist(vo);
	}

	//댓글 수정
	@Override
	public int community_comment_update(CommunityCommentVO vo) {
		return dao.community_comment_update(vo);
	}
	
	//댓글 삭제
	@Override
	public int community_comment_delete(int comment_num) {
		return dao.community_comment_delete(comment_num);
	}

}

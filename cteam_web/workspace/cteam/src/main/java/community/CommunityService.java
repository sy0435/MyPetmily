package community;

import java.util.List;

public interface CommunityService {
	
	CommunityPage community_list(CommunityPage page);		//게시판 글 목록
	CommunityVO community_detail(int board_num);			//글상세
	
	int community_insert(CommunityVO vo);					//글쓰기
	int community_update(CommunityVO vo);					//글수정
	int community_delete(int board_num);					//글삭제
	
	List<CommunityCommentVO> community_comment_list(int board_num);	//댓글 목록 조회
	int community_comment_regist(CommunityCommentVO vo);			//댓글 작성
	int community_comment_update(CommunityCommentVO vo);			//댓글 변경
	int community_comment_delete(int comment_num);					//댓글 삭제
	
}

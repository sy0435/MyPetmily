package board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired private BoardDAO dao;
	
	@Override
	public int board_insert(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.board_insert(vo);
	}

	@Override
	public BoardPage board_list(BoardPage page) {
		return dao.board_list(page);
	}

	@Override
	public BoardVO board_detail(int id) {
		// TODO Auto-generated method stub
		return dao.board_detail(id);
	}

	@Override
	public int board_read(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int board_update(BoardVO vo) {
		// TODO Auto-generated method stub
		return dao.board_update(vo);
	}

	@Override
	public int board_delete(int id) {
		// TODO Auto-generated method stub
		return dao.board_delete(id);
	}

	@Override
	public int board_comment_regist(BoardCommentVO vo) {
		// TODO Auto-generated method stub
		return dao.board_comment_regist(vo);
	}

	@Override
	public List<BoardCommentVO> board_comment_list(int bid) {
		// TODO Auto-generated method stub
		return dao.board_comment_list(bid);
	}

	@Override
	public int board_comment_update(BoardCommentVO vo) {
		return dao.board_comment_update(vo);
	}

	@Override
	public int board_comment_delete(int id) {
		return dao.board_comment_delete(id);
	}

}

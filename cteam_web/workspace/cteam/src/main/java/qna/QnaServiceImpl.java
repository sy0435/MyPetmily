package qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired private QnaDAO dao;

	//QnA 목록
	@Override
	public List<QnaVO> qna_list() {
		return dao.qna_list();
	}

	//QnA 작성
	@Override
	public int qna_regist(QnaVO vo) {
		return dao.qna_regist(vo);
	}

	//QnA 삭제
	@Override
	public int qna_delete(int qna_num) {
		return dao.qna_delete(qna_num);
	}

	//QnA 질문수정
	@Override
	public int qna_update_title(QnaVO vo) {
		return dao.qna_update_title(vo);
	}
	
	//QnA 답변수정
	@Override
	public int qna_update_content(QnaVO vo) {
		return dao.qna_update_content(vo);
	}

}

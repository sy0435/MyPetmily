package qna;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QnaDAO implements QnaService {
	
	@Autowired private SqlSession sql;

	//QnA 목록
	@Override
	public List<QnaVO> qna_list() {
		return sql.selectList("qna.mapper.qna_list");
	}

	//QnA 작성
	@Override
	public int qna_regist(QnaVO vo) {
		return sql.insert("qna.mapper.qna_regist", vo);
	}

	//QnA 질문수정
	@Override
	public int qna_update_title(QnaVO vo) {
		return sql.update("qna.mapper.qna_update_title", vo);
	}
	
	//QnA 답변수정
	@Override
	public int qna_update_content(QnaVO vo) {
		return sql.update("qna.mapper.qna_update_content", vo);
	}
	
	//QnA 삭제
	public int qna_delete(int qna_num) {
		return sql.delete("qna.mapper.qna_delete", qna_num);
	}
	
}
package qna;

import java.util.List;

public interface QnaService {
	
	List<QnaVO> qna_list();				//QnA 목록
	int qna_regist(QnaVO vo);			//QnA 작성
	int qna_update_title(QnaVO vo);		//QnA 질문수정
	int qna_update_content(QnaVO vo);	//QnA 답변수정
	int qna_delete(int qna_num);		//QnA 삭제
	
}
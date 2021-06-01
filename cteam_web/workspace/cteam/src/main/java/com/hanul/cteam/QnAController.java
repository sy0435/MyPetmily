package com.hanul.cteam;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import community.CommunityCommentVO;
import member.MemberVO;
import qna.QnaServiceImpl;
import qna.QnaVO;

@Controller
public class QnAController {
	
	@Autowired private QnaServiceImpl service;
	
	//QnA 화면
	@RequestMapping("/list.qn")
	public String list(HttpSession session) {
		session.setAttribute("category", "qn");
		
		return "qna/list";
	}
	
	//QnA 목록
	@RequestMapping("/qna/qna2")
	public String qna_list(Model model) {
	
		model.addAttribute("list", service.qna_list());
		model.addAttribute("crlf","\r\n");
		model.addAttribute("lf","\n");
	
		return "qna/qna2/qna_list";
	}
	
	//QnA 작성
	@ResponseBody @RequestMapping("/qna/qna2/regist")
	public int qna_regist(QnaVO vo, HttpSession session) {
		
		MemberVO member = (MemberVO) session.getAttribute("login_info");
		vo.setMember_id(member.getMember_id());
		
		return service.qna_regist(vo);
	}
	
	//QnA 질문수정
	@ResponseBody @RequestMapping(value="/qna/qna2/update_title",
								  produces = "application/text; charset=utf-8")
	private String qna_update_title(@RequestBody QnaVO vo) {
		return service.qna_update_title(vo) > 0 ? "수정 성공" : "수정 실패";
	}
	
	//QnA 답변수정
	@ResponseBody @RequestMapping(value="/qna/qna2/update_content",
			produces = "application/text; charset=utf-8")
	private String qna_update_content(@RequestBody QnaVO vo) {
		return service.qna_update_content(vo) > 0 ? "수정 성공" : "수정 실패";
	}
	
	//QnA 삭제
	@ResponseBody @RequestMapping("/qna/qna2/delete/{qna_num}")
	public void qna_delete(@PathVariable int qna_num) {
		service.qna_delete(qna_num);
	}
	
}

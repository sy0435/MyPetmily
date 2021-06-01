package com.hanul.iot;

import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.BoardCommentVO;
import board.BoardPage;
import board.BoardServiceImpl;
import board.BoardVO;
import common.CommonService;
import member.MemberVO;
import oracle.net.aso.m;

@Controller
public class BoardController {
	@Autowired private BoardServiceImpl service;
	@Autowired private BoardPage page;
	@Autowired private CommonService common;
	
	//댓글 삭제처리요청
	@ResponseBody @RequestMapping("/board/comment/delete/{id}")
	public void comment_delete(@PathVariable int id) {
		service.board_comment_delete(id);
	}
	
	//방명록 댓글 변경저장처리 요청
	@ResponseBody @RequestMapping(value="/board/comment/update"
			,produces = "application/text; charset=utf-8")
	private String comment_update(@RequestBody BoardCommentVO vo) {
		
		return service.board_comment_update(vo) > 0 ? "수정 성공" : "수정실패";
	}
	
	//댓글 목록화면 조회
	@RequestMapping("/board/comment/{bid}")
	public String comment_list(@PathVariable int bid, Model model) {
		model.addAttribute("list",service.board_comment_list(bid));
		model.addAttribute("crlf","\r\n");
		model.addAttribute("lf","\n");
		
		return "board/comment/comment_list";
	}
	
	//방명록에 대한 댓글을 저장하는 처리 요청
	@ResponseBody @RequestMapping("/board/comment/regist")
	public int comment_regist(BoardCommentVO vo, HttpSession session) {
		//화면에서 입력한 정보를 DB에 저장한 후 ajax쪽으로 돌아간다.
		MemberVO member = (MemberVO) session.getAttribute("login_info");
		vo.setWriter(member.getId());
		
		return service.board_comment_regist(vo);
	}
	
	//방명록 삭제처리
	@RequestMapping("/delete.bo")
	public String delete(int id, Model model) {
		service.board_delete(id);
		model.addAttribute("page",page);
		model.addAttribute("url","list.bo");
		return "board/redirect";
	}

	//방명록 수정화면 저장처리 요청
	@RequestMapping("/update.bo")
		public String update(BoardVO vo,String attach, MultipartFile file, HttpSession session, Model model) {
		//public String update(BoardVO vo,String attach, MultipartFile file, HttpSession session, RedirectAttributes redirect) {
		
		BoardVO board = service.board_detail(vo.getId());
		String uuid = session.getServletContext().getRealPath("resources")+board.getFilename();
		//화면에서 입력한 정보를 DB에 저장 후 상세화면으로연결
		//첨부파일이 있는 경우
		if(! file.isEmpty()) {
			//원래 없었는데 추가 / 원래 있었는데 변경
			vo.setFilename(file.getOriginalFilename());
			vo.setFilepath(common.upload("board", file, session));
			
			//원래 있던 파일 삭제
			if(board.getFilename()!=null) {
				File f = new File(uuid);
				if(f.exists()) f.delete();
			}
		}else {
			//원래부터 없던 경우 → 아무 처리 필요 없음
			
			// 원래 있었는데 삭제한 경우
			if( attach.isEmpty() ) {
				if( board.getFilename()!= null) {
					File f = new File(uuid);
					if( f.exists() ) f.delete();
				}
			}else {
			//원래 있던걸 그대로 사용하는 경우
				vo.setFilename( board.getFilename() );
				vo.setFilepath( board.getFilepath() );
			}
		}	
		service.board_update(vo);
		//return "redirect:detail.bo?id="+vo.getId();
		//redirect.addFlashAttribute("id", vo.getId());
		//return "redirect:detail.bo";
		model.addAttribute("url","detail.bo");
		model.addAttribute("id",vo.getId());
		return "board/redirect";
	}
	
	//방명록 수정화면 요청
	@RequestMapping("/modify.bo")
	public String modify(int id, Model model) {
		//선택한 방명록 정보를 DB에서 조회해와 수정화면에 출력
		model.addAttribute("vo", service.board_detail(id));
		
		return "board/modify";
	}
	
	//첨부파일 다운로드 요청
	@ResponseBody @RequestMapping("/download.bo")
	//서버의 물리적인 곳에서 복사하려면 세션필요
	//클라이언트 찾아가려면 response필요
	public void download(int id, HttpSession session, HttpServletResponse response) {
		BoardVO vo = service.board_detail(id);
		common.download(vo.getFilename(), vo.getFilepath(), session, response);
		
	}
	
	//방명록 상세화면 요청
	@RequestMapping("/detail.bo")
	//public String detail(@ModelAttribute("id")int id, Model model) {
	public String detail(int id, Model model) {
		//선택한 방명록 글 정보를 DB에서 조회해와 상세화면에 출력
		model.addAttribute("vo",  service.board_detail(id) );
		model.addAttribute("crlf","\r\n");
		model.addAttribute("lf","\n");
		model.addAttribute("page", page);
		return "board/detail";
	}
	
	//방명록 신규글저장 처리 요청
	@RequestMapping("/insert.bo")
	public String insert(BoardVO vo, MultipartFile file, HttpSession session ) {
			//첨부파일정보
			if( ! file.isEmpty() ) {
				vo.setFilename( file.getOriginalFilename() );
				vo.setFilepath( common.upload("board", file, session) );
			}
			
			//화면에서 입력한 정보를 DB에 저장한 후
			vo.setWriter(((MemberVO)session.getAttribute("login_info")).getId() );
			service.board_insert(vo);
			//목록화면으로 연결
			return "redirect:list.bo";
		}
		
	
	@RequestMapping("/new.bo")
	//방명록 글쓰기 화면 요청
	public String board() {
		return "board/new";
	}
	
	//방명록 목록화면 요청
	@RequestMapping("/list.bo")
	public String list(Model model, HttpSession session,@RequestParam(defaultValue="1") int curPage,
			@RequestParam(defaultValue="10") int pageList,@RequestParam(defaultValue = "list") String viewType,  String search, String keyword ) {
		session.setAttribute("category", "bo");
		
		//DB에서 방명록 정보를 조회하여 목록화면에 출력
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		page.setPageList(pageList);
		page.setViewType(viewType);
		model.addAttribute("page", service.board_list(page));
		return "board/list";
	}
}

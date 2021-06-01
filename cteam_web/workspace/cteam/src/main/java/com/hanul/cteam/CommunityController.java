package com.hanul.cteam;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonService;
import community.CommunityCommentVO;
import community.CommunityPage;
import community.CommunityServiceImpl;
import community.CommunityVO;
import member.MemberVO;

@Controller
public class CommunityController {
	
	@Autowired private CommunityServiceImpl service;
	@Autowired private CommunityPage page;
	@Autowired private CommonService common;
	
	//글목록 화면
	@RequestMapping("/list.bo")
	public String list(HttpSession session, Model model, 
					   String search, String search2, String search3, 
					   String search4, String keyword, 
					   @RequestParam(defaultValue="1") int curPage, 
					   @RequestParam(defaultValue="15") int pageList) {
		
		session.setAttribute("category", "bo");
		
		page.setCurPage(curPage);
		page.setPageList(pageList);
		page.setSearch(search);
		page.setSearch2(search2);
		page.setSearch3(search3);
		page.setSearch4(search4);
		page.setKeyword(keyword);
		model.addAttribute("page", service.community_list(page));
		
		return "community/list";
	}
	
	//글 상세보기 화면
	@RequestMapping("/detail.bo")
	public String detail(Model model, int board_num) {
		
		model.addAttribute("vo", service.community_detail(board_num));
		model.addAttribute("crlf","\r\n");
		model.addAttribute("lf","\n");
		model.addAttribute("page", page);
		
		return "community/detail";
	}
	
	//새글쓰기 화면
	@RequestMapping("/new.bo")
	public String newBoard(Model model, String board_subject, String board_city, String board_region) {
		
		page.setBoard_subject(board_subject);
		page.setBoard_city(board_city);
		page.setBoard_region(board_region);
		model.addAttribute("page", page);
		
		return "community/new";
	}
	
	//새글쓰기 처리
	@RequestMapping("/insert.bo")
	public String insert(HttpSession session, CommunityVO vo, MultipartFile file) {
		
		if(!file.isEmpty()) {
			vo.setBoard_imagepath( common.upload("community", file, session) );
		}
		
		vo.setMember_id( ((MemberVO)session.getAttribute("login_info")).getMember_id() );
		service.community_insert(vo);	
		
		return "redirect:list.bo";
	}
	
	//글 수정하기 화면
	@RequestMapping("/modify.bo")
	public String modify(Model model, int board_num) {
		
		model.addAttribute("vo", service.community_detail(board_num));
		
		return "community/modify";
	}
	
	//글 수정하기 처리
	@RequestMapping("/update.bo")
	public String update(CommunityVO vo, Model model, HttpSession session, MultipartFile file, String attach) {
		
		CommunityVO community = service.community_detail(vo.getBoard_num());
		String uuid = session.getServletContext().getRealPath("resources") + community.getBoard_imagepath();
		
		if(! file.isEmpty()) {
			vo.setBoard_imagepath(common.upload("community", file, session));
			
			if(community.getBoard_imagepath() != null) {
				File f = new File(uuid);
				if(f.exists()) f.delete();
			}
		}
		else {
			if( attach.isEmpty() ) {
				if( community.getBoard_imagepath() != null) {
					File f = new File(uuid);
					if( f.exists() ) f.delete();
				}
			} else {
				vo.setBoard_imagepath( community.getBoard_imagepath() );
			}
		}
		
		service.community_update(vo);
		
		model.addAttribute("url", "detail.bo");
		model.addAttribute("board_num", vo.getBoard_num());

		return "community/redirect";
	}
	
	//글 삭제하기 처리
	@RequestMapping("/delete.bo")
	public String delete(Model model, int board_num) {
		
		service.community_delete(board_num);
		model.addAttribute("page", page);
		model.addAttribute("url", "list.bo");
		
		return "community/redirect";
	}
	
	//댓글목록 화면
	@RequestMapping("/community/comment/{board_num}")
	public String comment_list(Model model, @PathVariable int board_num) {
		model.addAttribute("list",service.community_comment_list(board_num));
		model.addAttribute("crlf","\r\n");
		model.addAttribute("lf","\n");
		
		return "community/comment/comment_list";
	}
	
	//댓글쓰기 처리
	@ResponseBody @RequestMapping("/community/comment/regist")
	public int comment_regist(CommunityCommentVO vo, HttpSession session) {
		
		MemberVO member = (MemberVO) session.getAttribute("login_info");
		vo.setMember_id(member.getMember_id());
		
		return service.community_comment_regist(vo);
	}
	
	//댓글수정 처리
	@ResponseBody @RequestMapping(value="/community/comment/update",
								  produces = "application/text; charset=utf-8")
	private String comment_update(@RequestBody CommunityCommentVO vo) {
		return service.community_comment_update(vo) > 0 ? "수정 성공" : "수정 실패";
	}
	
	//댓글삭제 처리
	@ResponseBody @RequestMapping("/community/comment/delete/{comment_num}")
	public void comment_delete(@PathVariable int comment_num) {
		service.community_comment_delete(comment_num);
	}
	
}
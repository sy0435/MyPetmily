package com.hanul.cteam;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import admin.AdminServiceImpl;
import admin.ListPage;
import admin.MemberListPage;
import admin.SellItemVO;
import admin.SellModifyVO;
import admin.SellVO;
import common.CommonService;

@Controller
public class AdminController {
	@Autowired private AdminServiceImpl service;
	@Autowired private CommonService common;
	@Autowired private ListPage page;
	@Autowired private MemberListPage memberpage;
	
	//관리자페이지 불러오기
	@RequestMapping("/admin.ad")
	public String admin_page(Model model, HttpSession session) {
		session.setAttribute("category", "ad");
		return "admin/adminPage";
	} 
	
	//상품리스트페이지 불러오기
	@RequestMapping("/list.ad")
	public String item_list(Model model, HttpSession session,@RequestParam(defaultValue="1") int curPage,
			@RequestParam(defaultValue="10") int pageList) {
		session.setAttribute("category", "ad");
		
		//DB에서 방명록 정보를 조회하여 목록화면에 출력
			page.setCurPage(curPage);
			page.setPageList(pageList);
			model.addAttribute("page",service.list(page));
		return "admin/list";
	} 

	//상품등록페이지
	@RequestMapping("/itemNew.ad")
	public String admin_up( HttpSession session) {
		
		
		return "admin/itemNew";
	}
	
	//상품등록
	@RequestMapping("/insert.ad")
	public String admin_insert(HttpSession session, MultipartFile file1,MultipartFile file2, SellVO vo) {
	
		if( ! file1.isEmpty() ) {
			vo.setItem_imgpath( common.upload("Item", file1, session) );
		}
		if( ! file2.isEmpty() ) {
			vo.setItem_content_imgpath( common.upload("content", file2, session) );
		}
		
		service.item_insert(vo);
		return "redirect:list.ad";
	}
	//회원관리
		@RequestMapping("/memberList.ad")
		public String member_list(Model model, HttpSession session,@RequestParam(defaultValue="1") int curPage,
				@RequestParam(defaultValue="15") int pageList) {
			
			//session.setAttribute("category", "ad");
			
			//DB에서 방명록 정보를 조회하여 목록화면에 출력
			memberpage.setCurPage(curPage);
			memberpage.setPageList(pageList);
			model.addAttribute("page", service.member_list(memberpage));
			return "admin/memberList";
		}
		
		//회원정보 상세보기
		@RequestMapping("memberDetail.ad")
		public String member_detail(Model model, HttpSession session, String member_id) {
			
			model.addAttribute("list", service.member_detail(member_id));
			return "admin/memberDetail";
		}
		
		//주문관리
		@RequestMapping("/orderList.ad")
		public String order_list(Model model, HttpSession session) {
			
			model.addAttribute("orderlist", service.order_list());
			return "admin/orderList";
		}
		
		//상품 수정화면 불러오기
	 	@RequestMapping("/itemModify.ad")
	 	public String item_modify(Model model, int item_num ) {
	 		
	 		model.addAttribute("option", service.option_select(item_num));
	 		model.addAttribute("item", service.item_select(item_num));
	 		return "admin/itemModify";
	 	}
	 	
	 	//상품 수정 저장처리
	 	@RequestMapping("/update.ad")
	 	public String item_update(SellModifyVO vo ,int item_num, String attach, MultipartFile file1, MultipartFile file2, HttpSession session, Model model) {

			String uuid = session.getServletContext().getRealPath("resources");
	 		System.out.println("vo.getItem_num" + vo.getItem_num());
	 		System.out.println("vo.getItem_code" + vo.getItem_code());
	 		System.out.println("vo.getItem_name" + vo.getItem_name() );
	 		System.out.println("vo.getItem_price" + vo.getItem_price());
	 		System.out.println("vo.getItem_content" + vo.getItem_content());
	 		System.out.println("vo.getItem_content_imgpath" + vo.getItem_content_imgpath());
	 		System.out.println("vo.getItem_imgpath" + vo.getItem_imgpath());
	 	
	 		System.out.println("item_num"+item_num);
			if(! file1.isEmpty()) {
				//원래 없었는데 추가 / 원래 있었는데 변경
				vo.setItem_imgpath(common.upload("Item", file1, session));
	 		
			//원래 있던 파일 삭제
			if(vo.getItem_imgpath()!=null) {
				File f = new File(uuid);
				if(f.exists()) f.delete();
			}
			
		}else {
			//원래부터 없던 경우 → 아무 처리 필요 없음
			
			// 원래 있었는데 삭제한 경우
			if( attach.isEmpty() ) {
				if( vo.getItem_imgpath()!= null) {
					File f = new File(uuid);
					if( f.exists() ) f.delete();
				}
				
			}else {
			//원래 있던걸 그대로 사용하는 경우
				vo.setItem_imgpath(vo.getItem_imgpath());
			}
		}	
			
			//file2
			if(! file2.isEmpty()) {
				//원래 없었는데 추가 / 원래 있었는데 변경
				vo.setItem_content_imgpath(common.upload("content", file2, session));
	 		
			//원래 있던 파일 삭제
			if(vo.getItem_content_imgpath()!=null) {
				File f = new File(uuid);
				if(f.exists()) f.delete();
			}
			
		}else {
			//원래부터 없던 경우 → 아무 처리 필요 없음
			
			// 원래 있었는데 삭제한 경우
			if( attach.isEmpty() ) {
				if( vo.getItem_content_imgpath()!= null) {
					File f = new File(uuid);
					if( f.exists() ) f.delete();
				}
				
			}else {
			//원래 있던걸 그대로 사용하는 경우
				vo.setItem_content_imgpath(vo.getItem_content_imgpath());
			}
		}	
		
			service.option_delete(item_num);
			service.item_update(vo);
			
	 		model.addAttribute("url", "list.ad");
	 		model.addAttribute("item", service.item_select(item_num));
	 		return "admin/redirect";
	 	}
	 	
	 	@RequestMapping("/adOrderdetail.my")
	 	public String order_detail(Model model, HttpSession session, String order_num) {
	 		
	 		model.addAttribute("list", service.order_detail(order_num));
	 		return "admin/orderDetail";
	 	}
		
		@RequestMapping("/stateUpdate.ad")
		public String state(String order_num,String order_state, HttpSession session) {
			System.out.println(order_num + ":" +order_state);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("order_num", order_num);
			map.put("order_state",order_state);
			service.state_update(map);
			return "redirect:orderList.ad";
		}
		
}

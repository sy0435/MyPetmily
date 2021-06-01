package com.hanul.cteam;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.scribejava.core.model.OAuth2AccessToken;

import common.CommonService;
import member.MemberServiceImpl;
import member.MemberVO;
import member.UserMailSendService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	@Autowired
	private MemberServiceImpl service;
	@Autowired
	private CommonService common;
	@Autowired
	private UserMailSendService userMailSendService;

	@Inject // 서비스를 호출하기 위해서 의존성을 주입
	JavaMailSender mailSender; // 메일 서비스를 사용하기 위해 의존성을 주입함.

	// 로깅을 위한 변수
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String String = null;

	/* NaverLoginBO */
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}

	@RequestMapping("/idCheck")
	public String idCheck() {

		return "member/idCheck";
	}

	// 아이디찾아오기
	@ResponseBody
	@RequestMapping(value = "/idFindDB", method = RequestMethod.POST)
	public String id_findDB(@RequestParam("member_name") String member_name,
			@RequestParam("member_phonenum") String member_phonenum) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("member_name", member_name);
		map.put("member_phonenum", member_phonenum);
		String result = service.member_idFind(map);

		System.out.println(result);
		return result;

	}

//비밀번호찾기

	// 비밀번호 찾기
	@RequestMapping(value = "searchPassword", method = RequestMethod.GET)
	@ResponseBody
	public String passwordSearch(@RequestParam("member_id") String member_id,
			@RequestParam("member_email") String member_email, HttpServletRequest request) {

		String success= "success";
		
		if(service.member_emailFind(member_email)) {
		
			// 비밀번호는 6자리로 보내고 데이터베이스 비밀번호를 바꿔준다
			String key = userMailSendService.getKey(false, 6);
			
			
			String setfrom = "runningwithcry@gmail.com";
			String tomail = member_email; // 받는 사람 이메일
			String title = "[마이펫밀리] 임시비밀번호가 발급되었습니다."; // 제목
			String content =
	
					System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해 작성
	
							System.getProperty("line.separator") +
	
							"안녕하세요 회원님 마이펫밀리입니다" 
	
							+ System.getProperty("line.separator") +
	
							System.getProperty("line.separator") +
							
							 "비밀번호 찾기를 신청해주셔서 임시 비밀번호를 발급해드렸습니다." +
							
							 System.getProperty("line.separator") +
	
							 "임시 비밀번호는  "+ key + "이며 로그인 후 마이페이지에서 비밀번호를 변경해주시면 됩니다." +
	
							 System.getProperty("line.separator") +
	
							System.getProperty("line.separator");
	
	
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	
				messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
				messageHelper.setTo(tomail); // 받는사람 이메일
				messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
				messageHelper.setText(content); // 메일 내용
	
				mailSender.send(message);
			} catch (Exception e) {
				System.out.println(e);
			}
		
				success="ok";
				service.member_pwChange(key,member_id);	
				
		}else {
			
			success="fail";
		}
		//service.passwordChange();

		  return success;

	}

	// 아이디찾기
	@RequestMapping("/idFind")
	public String id_find() {

		return "member/idFind";
	}

	// 회원가입
	@ResponseBody
	@RequestMapping(value = "/join", produces = "text/html; charset=utf-8")
	public String join(MemberVO vo, HttpServletRequest req, HttpSession session) {

		String msg = "<script type='text/javascript'>";
		System.out.println(vo.getMember_answer() + vo.getMember_question());

		if (service.member_insert(vo)) {
			common.sendEmail(vo.getMember_email(), vo.getMember_name(), session);
			msg += "alert('회원가입이 완료되었습니다'); location='http://192.168.0.141:8080/cteam/loginPage'";
		} else {
			msg += "alert('회원가입에 실패했습니다') history.go(-1)";

		}

		msg += "</script>";

		return msg;
	}

	// 네이버 로그인 성공시 callback호출 메소드
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException, ParseException {
		System.out.println("여기는 callback시 세션 " + session);
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		// 1. 로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken); // String형식의 json데이터
		/**
		 * apiResult json 구조 {"resultcode":"00", "message":"success",
		 * "response":{"id":"33666449","nickname":"shinn****","age":"20-29","gender":"M","email":"sh@naver.com","name":"\uc2e0\ubc94\ud638"}}
		 **/
		// 2. String형식인 apiResult를 json형태로 바꿈
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		// 3. 데이터 파싱
		// Top레벨 단계 _response 파싱
		JSONObject response_obj = (JSONObject) jsonObj.get("response");
		// response의 nickname값 파싱
		String nickname = (String) response_obj.get("nickname");
		System.out.println(nickname);
		// 4.파싱 닉네임 세션으로 저장
		session.setAttribute("sessionId", nickname); // 세션 생성
		model.addAttribute("result", apiResult);
		return "home/home";
	}

	// 아이디 중복화면

	@ResponseBody
	@RequestMapping("/id_check")
	public boolean id_check(String id) {

		return service.member_id_check(id);
	}

	// 회원가입 요청
	@RequestMapping("/member")
	public String member(HttpSession session) {
		session.removeAttribute("category");
		return "member/join";
	}

	@RequestMapping(value = "/logout_home", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout_home(HttpSession session) {

		session.removeAttribute("login_info");

		return "redirect:/";
	}

	// 로그아웃처리

	@ResponseBody
	@RequestMapping("/logout")
	public void logout(HttpSession session) {
		session.removeAttribute("login_info");
	}

	// 로그인처리
	@ResponseBody
	@RequestMapping("/login")
	public Boolean login(String userid, String userpwd, HttpSession session) {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", userid);
		map.put("pwd", userpwd);
		service.member_login(map);

		System.out.println(userid + userpwd);

		MemberVO vo = service.member_login(map);

		session.setAttribute("login_info", vo);

		return vo == null ? false : true;

	}

	// 로그인 화면이동
	@RequestMapping(value = "/loginPage", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginPage(Model model, HttpSession session) {
		/* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
		System.out.println("login 시 세션:" + session);
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		// https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
		// redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
		System.out.println("네이버:" + naverAuthUrl);
		// 네이버
		model.addAttribute("url", naverAuthUrl);
		return "member/login";
	}

	// mailSending 코드
	@RequestMapping(value = "/auth.do", method = RequestMethod.POST)
	public ModelAndView mailSending(HttpServletRequest request, String e_mail, HttpServletResponse response_email)
			throws IOException {

		Random r = new Random();
		int dice = r.nextInt(4589362) + 49311; // 이메일로 받는 인증코드 부분 (난수)

		String setfrom = "runningwithcry@gmail.com";
		String tomail = request.getParameter("e_mail"); // 받는 사람 이메일
		String title = "회원가입 인증 이메일 입니다."; // 제목
		String content =

				System.getProperty("line.separator") + // 한줄씩 줄간격을 두기위해 작성

						System.getProperty("line.separator") +

						"안녕하세요 회원님 마이 펫밀리를 찾아주셔서 감사합니다"

						+ System.getProperty("line.separator") +

						System.getProperty("line.separator") +

						" 인증번호는 " + dice + " 입니다. "

						+ System.getProperty("line.separator") +

						System.getProperty("line.separator") +

						"받으신 인증번호를 홈페이지에 입력해 주시면 다음으로 넘어갑니다."; // 내용

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

			messageHelper.setFrom(setfrom); // 보내는사람 생략하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
			messageHelper.setText(content); // 메일 내용

			mailSender.send(message);
		} catch (Exception e) {
			System.out.println(e);
		}

		ModelAndView mv = new ModelAndView(); // ModelAndView로 보낼 페이지를 지정하고, 보낼 값을 지정한다.
		mv.setViewName("member/email_injeung"); // 뷰의이름
		mv.addObject("dice", dice);
		mv.addObject("tomail", tomail);

		System.out.println("mv : " + mv);

		response_email.setContentType("text/html; charset=UTF-8");
		PrintWriter out_email = response_email.getWriter();
		out_email.println("<script>alert('이메일이 발송되었습니다. 인증번호를 입력해주세요.');</script>");
		out_email.flush();

		return mv;

	}

	// 이메일 인증 페이지 맵핑 메소드
	@RequestMapping("/email.do")
	public String email() {
		return "member/email";
	}

	// 이메일로 받은 인증번호를 입력하고 전송 버튼을 누르면 맵핑되는 메소드.
	// 내가 입력한 인증번호와 메일로 입력한 인증번호가 맞는지 확인해서 맞으면 회원가입 페이지로 넘어가고,
	// 틀리면 다시 원래 페이지로 돌아오는 메소드
	@RequestMapping(value = "/join_injeung.do{dice}", method = RequestMethod.POST)
	public ModelAndView join_injeung(String email_injeung, @PathVariable String dice, String tomail,
			HttpServletResponse response_equals) throws IOException {

		System.out.println("마지막 : email_injeung : " + email_injeung);

		System.out.println("마지막 : dice : " + dice);

		// 페이지이동과 자료를 동시에 하기위해 ModelAndView를 사용해서 이동할 페이지와 자료를 담음

		ModelAndView mv = new ModelAndView();

		mv.setViewName("member/join");

		mv.addObject("tomail", tomail);

		if (email_injeung.equals(dice)) {

			// 인증번호가 일치할 경우 인증번호가 맞다는 창을 출력하고 회원가입창으로 이동함

			mv.setViewName("member/join");

			// 만약 인증번호가 같다면 이메일을 회원가입 페이지로 같이 넘겨서 이메일을
			// 한번더 입력할 필요가 없게 한다.

			response_equals.setContentType("text/html; charset=UTF-8");
			PrintWriter out_equals = response_equals.getWriter();
			// out_equals.println("<script>alert('인증번호가 일치하였습니다. 회원가입창으로
			// 이동합니다.');</script>");
			out_equals.flush();

			return mv;

		} else if (email_injeung != dice) {

			ModelAndView mv2 = new ModelAndView();

			mv2.setViewName("member/email_injeung");

			response_equals.setContentType("text/html; charset=UTF-8");
			PrintWriter out_equals = response_equals.getWriter();
			out_equals.println("<script>alert('인증번호가 일치하지않습니다. 인증번호를 다시 입력해주세요.'); history.go(-1);</script>");
			out_equals.flush();

			return mv2;

		}

		return mv;

	}

}

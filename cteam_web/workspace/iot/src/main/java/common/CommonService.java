package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {
	
	//REST API 요청시 응답결과
	public String rest_api(StringBuilder url) {
		String result = url.toString();
		try {
			HttpURLConnection conn = (HttpURLConnection)new URL(result).openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			
			BufferedReader reader;
			if(conn.getResponseCode()>= 200 && conn.getResponseCode() <=300 ) {
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			
			}else {
				reader = new BufferedReader(new InputStreamReader(conn.getErrorStream(),"utf-8"));
				
			}
			url = new StringBuilder();
			while((result = reader.readLine()) != null) {
				url.append(result);
			}
			reader.close();
			conn.disconnect();
			result = url.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	public String json_list(StringBuilder url) {
		JSONObject json = null;
		try {
			json = (JSONObject)new JSONParser().parse(rest_api(url));
			json = (JSONObject)json.get("response");
			json = (JSONObject)json.get("body");
			
			int count = json.get("totalCount") == null? 0 :
			 Integer.parseInt(json.get("totalCount").toString());
			
			json = (JSONObject)json.get("items");

			json.put("count", count);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json.toJSONString();
	}
	
	//파일 다운로드
	public File download(String filename, String filepath, HttpSession session, HttpServletResponse response) {
		//다운로드할 파일 객체를 생성
		File file = new File(session.getServletContext().getRealPath("resources") + filepath);
		String mime = session.getServletContext().getMimeType(filename);
		response.setContentType(mime);
		
		try {
			filename = URLEncoder.encode(filename, "utf-8");
			response.setHeader("content-disposition", "attachment; filename="+filename);
			ServletOutputStream out = response.getOutputStream();
			FileCopyUtils.copy(new FileInputStream(file), out);
			out.flush();
			
		} catch (Exception e) {
			System.out.println("★파일다운로드 에러 : "+e.getMessage());
		}
		
		return file;
	}
	
	//파일 업로드
	public String upload (String category, MultipartFile file, HttpSession session) {
		//업로드해둘 서버의 물리적위치
		String resources = session.getServletContext().getRealPath("resources");
		
		//업로드해 둘 폴더지정
		String upload = resources + "/upload";
		//upload/notice/2020/09/18/1234_abc.txt
		
		//폴더만들기
		String folder = upload + "/" + category + "/" + new SimpleDateFormat("yyyy/MM/dd").format(new Date());
		
		File f = new File(folder);
		if( !f.exists() ) f.mkdirs();
		String uuid = UUID.randomUUID().toString() + "_"+ file.getOriginalFilename();
		
		try {
			file.transferTo(new File(folder, uuid));
		} catch (Exception e) {
			System.out.println("★FileException★ "+e.getMessage());
		}
		//folder:D:\Study_Spring\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\iot\resources
		return folder.substring(resources.length() )+ "/" + uuid;

	}
	
	//이메일전송
	public void sendEmail(String email, String name, HttpSession session ) {
		//1.일반 메일
		// simpleSend(email, name);
		
		//2. 첨부파일이 있는 메일
	//	attachSend(email, name, session);
		htmlSend(email, name, session);
	}
	
	//3.Html 형식의 내용을 갖는 메일 : 파일첨부가능
	private void htmlSend(String email, String name, HttpSession session) {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		mail.setAuthentication("jinyi0604", "naverwlsdl6134");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("jinyi0604@naver.com","관리자임");		//송신자정보
			mail.addTo(email, name);		//수신자정보
			
			mail.setSubject("회원가입을 축하드립니다 ^0^! - HTML문서내용");
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<a><img src='https://blogfiles.pstatic.net/MjAyMDA1MDFfMjE1/MDAxNTg4MzA5NjU3NDE4.E7VR-jHLIPaFVsWDcknAer_oLnZnQLe8wrvljZC32fQg.Ivu4AQAwiSlXlsbewyDptVjZc4uHoPYhHe-Q98enTCsg.JPEG.giveanapple/%EC%95%84%EC%9D%B4%EB%A6%B0.jpg'/></a>");
			msg.append("<hr>");
			msg.append("<h1>아이린 조녜ㅜ 알럽</h3>");
			msg.append("<h3>회원가입 왜 했나요? 탈퇴못해 노빠꾸</h3>");		
			msg.append("</body>");
			msg.append("</html>");
			
			mail.setHtmlMsg(msg.toString());
			
			EmailAttachment file = new EmailAttachment();
			file.setPath(session.getServletContext().getRealPath("resources")+"/images/hanul.ico");
			mail.attach(file);
			
			file = new EmailAttachment();
			file.setPath("C:\\Users\\user\\Desktop\\Mydoggy.jpg");
			mail.attach(file);
			
			mail.send();
			
		} catch (Exception e) {
			System.out.println("EmailException☆"+e.getMessage());
		}
	}
	
	private void attachSend(String email, String name, HttpSession session) {
		MultiPartEmail mail = new MultiPartEmail();
		
		//메일을 작성할 서버지정
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		//로그인하기 위한 아이디 / 비번지정
		mail.setAuthentication("jinyi0604", "naverwlsdl6134");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("jinyi0604@naver.com","관리자임");		//송신자정보
			mail.addTo(email, name);		//수신자정보
			
			mail.setSubject("회원가입완료메일 - 첨부파일있음");		//메일제목
			mail.setMsg(name+"님! 회원가입을 축하드립니다\n 되도록 빨리 탈퇴하세요");
			
			EmailAttachment file = new EmailAttachment();
			file.setPath("D:\\JINI\\첨부파일입니다.txt");
			mail.attach(file);
			
			file = new EmailAttachment();
			file.setPath(session.getServletContext().getRealPath("resources")+"/images/hanul.ico");
			mail.attach(file);
			
			file = new EmailAttachment();
			file.setURL(new URL("https://pbs.twimg.com/media/EE0R8XcU0AAlbth?format=jpg&name=medium"));
			mail.attach(file);
			
			
			mail.send();		//메일보내기
		} catch (Exception e) {
			System.out.println("EmailException☆"+e.getMessage());
		}
		
		
	}
	//일반적인 메일전송
	private void simpleSend(String email, String name) {
		SimpleEmail mail = new SimpleEmail();
		
		//메일을 작성할 서버지정
		mail.setHostName("smtp.naver.com");
		mail.setCharset("utf-8");
		mail.setDebug(true);
		
		//로그인하기 위한 아이디 / 비번지정
		mail.setAuthentication("jinyi0604", "naverwlsdl6134");
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("jinyi0604@naver.com","관리자임");		//송신자정보
			mail.addTo(email, name);		//수신자정보
			
			mail.setSubject("회원가입완료메일");		//메일제목
			mail.setMsg(name+"님! 회원가입을 축하드립니다 빨리 탈퇴하세요");
			
			mail.send();		//메일보내기
		} catch (EmailException e) {
			System.out.println("EmailException☆"+e.getMessage());
		}
		
	}
}

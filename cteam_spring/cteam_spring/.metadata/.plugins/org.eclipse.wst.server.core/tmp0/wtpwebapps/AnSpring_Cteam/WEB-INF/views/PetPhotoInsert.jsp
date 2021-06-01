<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%		
	String state = (String)request.getAttribute("state");	//다시 스트링으로 변환

	out.println(state);//클라이언트한테 다시 보내줌  삽입성공시 1이 뜬다	
	
	
%>
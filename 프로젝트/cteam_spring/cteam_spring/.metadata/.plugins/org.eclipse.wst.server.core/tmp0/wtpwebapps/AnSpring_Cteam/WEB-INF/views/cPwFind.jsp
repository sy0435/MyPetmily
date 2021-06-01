<%@page import="com.cteam.app.dto.MemberDTO1"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%@page import="org.springframework.ui.Model"%>
<%@page import="java.sql.*, java.sql.Date, javax.sql.*, javax.naming.*, 
					java.util.*, java.io.PrintWriter" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   


<%		
	String pw_return = (String)request.getAttribute("cPwFind");	//다시 스트링으로 변환

	out.println(pw_return);//클라이언트한테 다시 보내줌 	
	
	
	%>
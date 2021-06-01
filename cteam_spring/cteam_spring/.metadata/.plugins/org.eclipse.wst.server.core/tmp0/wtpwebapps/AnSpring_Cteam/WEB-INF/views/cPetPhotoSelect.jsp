
<%@page import="com.cteam.app.dto.petPhotoDTO"%>
<%@page import="com.cteam.app.dto.PetSelectDTO"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.google.gson.JsonObject"%>

<%@page import="org.springframework.ui.Model"%>
<%@page import="java.sql.*, java.sql.Date, javax.sql.*, javax.naming.*, 
					java.util.*, java.io.PrintWriter" %>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   


<%		
	Gson gson = new Gson();
	String json = gson.toJson((ArrayList<petPhotoDTO>)request.getAttribute("cPhotoSelect"));
	
	out.println(json);	
	


%>


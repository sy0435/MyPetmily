<%@page import="com.cteam.app.dto.BoarddetailDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%		
Gson gson = new Gson();
String json = gson.toJson((BoarddetailDTO)request.getAttribute("boarddetail"));

out.println(json);

%>
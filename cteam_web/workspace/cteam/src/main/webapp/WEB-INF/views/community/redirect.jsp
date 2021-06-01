<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<form action="${url}" method="post">
<input type="text" name="board_num" value="${board_num}">
<input type="text" name="curPage" value="${page.curPage}">
<input type="text" name="search" value="${page.search}">
<input type="text" name="keyword" value="${page.keyword}">
<input type="text" name="pageList" value="${page.pageList}">
<input type="text" name="viewType" value="${page.viewType}">
</form>
<script>$('form').submit()</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name = "admin";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 헤더</title>
</head>
<body>
	<h2>헤더페이지입니다.</h2>
	<h3>현재 출력내용은 <%=request.getParameter("title") %>입니다.</h3>
	<h4><%=request.getParameter("content") %></h4>
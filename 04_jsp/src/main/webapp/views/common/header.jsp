<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String headerData = "헤더에 선언된 변수";	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
<style type="text/css">
	header{
		border:1px solid red;
		width:100%;
		height:150px;
	}
	nav>ul{
		display:flex;
		list-style:none;
	}
	section{
		border:1px solid green;
		width:100%;
		height:500px;
	}
	footer{
		border:1px solid blue;
		width:100%;
		height:150px;
	}
</style>
</head>
<body>
	<header>
		<h1>sample header</h1>
		<nav>
			<ul>
				<li><a href="main.jsp">메인화면</a></li>
				<li><a href="notice.jsp">공지사항</a></li>
				<li>게시판</li>
				<li>자료실</li>
				<li>추가메뉴</li>
			</ul>
		</nav>
	</header>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.web.member.model.vo.Member" %>    
<%
	Member m=(Member)request.getAttribute("member");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복확인하기</title>
<style>
	div#checkId-container{
		text-align: center;
		padding-top:50px;
	}
	span#duplicated{
		color : red;
		font-weight:bolder; 
	}
</style>
</head>
<body>
	<div id="checkId-container">
		<%if(m==null){ %>
			[<span><%=request.getParameter("userId") %></span>]는 사용가능합니다.	
			<br><br>
			<button type="button" >닫기</button>
		<%} else{%>
			[<span id="duplicated"><%=request.getParameter("userId") %></span>]는 사용중입니다.
			<br><br>
			<!-- 아이디 재입력창 구성 -->
			<form action="<%=request.getContextPath() %>/member/idDuplicate.do" method="get">
				<input type="text" name="userId" id="userId">
				<input type="submit" value="중복검사" >
			</form>
		<%} %>
	</div>
	<script>
		const btn=document.querySelector("button[type=button]");
		/* console.log(btn); */
		btn.addEventListener("click",e=>{
			console.log(opener.enrollMemberFrm.userId);
			console.log(opener.enrollMemberFrm.password);
			opener.document.querySelector("input#userId_")
			.value="<%=request.getParameter("userId")%>";
			opener.document.querySelector("input#password_").focus();
			close();
		});
	
	</script>
</body>
</html>
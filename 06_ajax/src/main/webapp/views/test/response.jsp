<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%
	List<String> names = (List<String>)request.getAttribute("data");
%>
<!-- 응답할 html 코드만 작성하는 것 -->
<ul>
  <%for(String n : names) {%>
  		<li><%=n %></li>
  <%} %>
</ul>



















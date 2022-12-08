<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.web.member.model.vo.Member"%>
<%
	List<Member> members = (List<Member>)request.getAttribute("members");
%>


<table>
  <tr>
    <th>아이디</th>
    <th>이름</th>
    <th>이메일</th>
    <th>전화번호</th>
  </tr>
  <%if(!members.isEmpty()){ %>
  		<%for(Member m : members){ %>
  			<tr>
  				<td><%=m.getUserId() %></td>
  				<td><%=m.getUserName() %></td>
  				<td><%=m.getEmail() %></td>
  				<td><%=m.getPhone() %></td>
  			</tr>
	  <%} 
  	}%>
</table>

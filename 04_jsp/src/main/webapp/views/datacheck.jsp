<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ page import = "java.util.List,com.jsp.model.vo.Animal" %>
<%
	String requestData = (String)request.getAttribute("msg");
	String sessionData = (String)session.getAttribute("sessionMsg");
	String applicationData = (String)application.getAttribute("applicationMsg");
	//이러면 계속 임포트가 많아진다.. 그래서 페이지를 따로 만들기도 함
	List<Animal> animals = (List<Animal>)request.getAttribute("animals");
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>requestdata : <%=requestData %></h2>
	<h2>sessionData : <%=sessionData %></h2>
	<h2>applicationData : <%=applicationData %></h2>
	<h2>현재 키우고 있는 동물</h2>
	<table>
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>성별</th>
			<th>키</th>
			<th>몸무게</th>
		</tr>
		<%for(Animal a : animals){ %>
			<% if(a.getWeight()>5){ %>
				<tr>
					<td><%=a.getName()%></td>
					<td><%=a.getAge() %>살</td>
					<td><%=a.getGender() %>성</td>
					<td><%=a.getHeight() %>cm</td>
					<td><%=a.getWeight() %>kg</td>
				</tr>
			<%} %>
		<%} %>
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
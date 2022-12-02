<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>에러발생</h1>
	<h3 style="color:red;"><%=exception.getMessage() %></h3>
	<script>
		setTimeout(()=>{
			location.replace("<%=request.getContextPath()%>");
		},5000)
	</script>
</body>
</html>
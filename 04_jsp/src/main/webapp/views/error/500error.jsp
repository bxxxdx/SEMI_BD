<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!-- isErrorPage를 지우면 exception 내부객체를 사용할 수 없다~~ default값이 false 인듯!! -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500에러 페이지</title>
</head>
<body>
	<h1 style="color:red">에러발발발발생생생생</h1>
	<p>3초 후 메인페이지로 이동합니다.</p>
	<p><%=exception.getMessage() %></p>
	<script>
		setTimeout(()=>{
			location.replace('<%=request.getContextPath()%>');
		}, 3000)
	</script>
</body>
</html>
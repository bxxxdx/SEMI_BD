<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션태그</title>
</head>
<body>
	<h2>액션태그 활용하기</h2>
	<p>jsp페이지에서 java코드를 html태그 방식으로 작성할 수 있게 해주는 태그</p>
	<ul>
		<li>표준액션태그 : jsp에서 기본으로 제공하는 태그</li>
		<li>커스텀액션태그(JSTL) : 별도 라이브러리로 제공되는 태그 *외부 라이브러리 파일이 필요함.</li>
	</ul>
	<h2>표준액션태그 이용하기 *다양하게 있지만 2개 정도 소개</h2>
	<h3>jsp:include를 이용</h3>
	<p>다른 페이지를 불러와 출력해주는 태그</p>
	<%-- <!-- <%@ include%>태그와 동일한 기능을 하는 액션태그 --> --%>
	<p>사용방법 : jsp:include page="불러올 페이지 경로/페이지명"</p>
	<a href = "<%=request.getContextPath() %>/views/includeTest.jsp">include테스트</a>	

</body>
</html>
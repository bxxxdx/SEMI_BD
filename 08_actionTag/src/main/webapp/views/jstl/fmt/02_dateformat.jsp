<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>날짜 데이터 처리하기</title>
</head>
<body>
	<h2>날짜데이터 처리하기</h2>
	<c:set var="today" value="<%=new Date()%>"/>
	<c:out value="${today }"/>
	<p>
		fmt:formatDate태그를 이용해서 출력<br>
		type속성을 이용해서 설정<br>
		date : 년월일을 출력<br>
		time : 시분초를 출력<br>
		both : 날짜,시간 둘다 출력<br>
		defaultStyle로 출력방법을 설정 -> fmt 설정한 내용<br>
		dateStyle : default, short, long, full<br>
		timeStyle : medium, short, long, full<br>
	</p>
	
	<h4><fmt:formatDate value="${today}" type="date"/></h4>
	<h4><fmt:formatDate value="${today}" type="time"/></h4>
	<h4><fmt:formatDate value="${today}" type="both"/></h4>

	<h3>기본스타일을 적용해서 출력하기</h3>
	<h4>dateStyle</h4>
	<h4>default : <fmt:formatDate value="${today}" type="date" dateStyle="default"/></h4>
	<h4>short : <fmt:formatDate value="${today}" type="date" dateStyle="short"/></h4>
	<h4>long : <fmt:formatDate value="${today}" type="date" dateStyle="long"/></h4>
	<h4>full : <fmt:formatDate value="${today}" type="date" dateStyle="full"/></h4>

	<h4>timeStyle</h4>
	<h4>medium : <fmt:formatDate value="${today}" type="time" timeStyle="medium"/></h4>
	<h4>short : <fmt:formatDate value="${today}" type="time" timeStyle="short"/></h4>
	<h4>long : <fmt:formatDate value="${today}" type="time" timeStyle="long"/></h4>
	<h4>full : <fmt:formatDate value="${today}" type="time" timeStyle="full"/></h4>

	<h4>두개의 스타일 동시 적용하기</h4>
	<h4><fmt:formatDate value="${today}" type="both" dateStyle="short" timeStyle="full"/></h4>
	
	<h2>패턴을 지정하여 출력하기</h2>
	<p>
		pattern속성을 이용<br>
		yy(년) MM(월) dd(일) hh(시) ss(초) mm(분) SSS(ms) E(요일)<br>
	</p>
	<h4><fmt:formatDate value="${today }" type="date" pattern="yyyy/MM/dd"/></h4>
	<h4><fmt:formatDate value="${today }" type="time" pattern="hh:mm:ss.SSS"/></h4>
	<h4><fmt:formatDate value="${today }" type="both" pattern="yyyy-MM-dd(E) hh:mm:ss.SSS"/></h4>


	<h2>시간기준 설정하기</h2>
	<p>
		<fmt:timeZone value="GMT">
			<fmt:formatDate value="${today}" type="time" timeStyle="full"/>
		</fmt:timeZone>
	</p>
	<p>
		<fmt:timeZone value="GMT+9">
			<fmt:formatDate value="${today}" type="time" timeStyle="full"/>
		</fmt:timeZone>
	</p>
	<p>
		<fmt:setLocale value="ja_JP"/>
		<fmt:formatDate value="${today}" type="time" timeStyle="full"/>
		<fmt:formatDate value="${today}" type="date" dateStyle="full"/>
	</p>



































	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
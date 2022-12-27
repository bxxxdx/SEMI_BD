<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL함수 출력</title>
</head>
<body>
	<c:set var="data" value="How Are You? I am fine"/>
	<p><c:out value="${data}"/></p>
	<p><c:out value="${fn:toUpperCase(data)}"/></p>
	<p><c:out value="${fn:replace(data, 'fine', 'tired')}"/></p>
	<p><c:out value="${fn:contains(data, 'You')?'You가있네':'You가없네'}"/></p>
</body>
</html>
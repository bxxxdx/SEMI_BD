<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>추가 데이터 가져오기</title>
</head>
<body>
	<h2>cookie정보 가져오기</h2>
	<p>${cookie }</p>
	<p>${cookie.cValue }</p>
	<p>이름 : ${cookie.cValue.name }</p>
	<p>내용 : ${cookie.cValue.value }</p>
	<p>${cookie.aValue }</p>
	
	
	<h3>header에 대한 정보를 가져오기</h3>
	<p>${header }</p>
	<p>${header.referer }</p>
	<p>${header["referer"] }</p>
	<p>${header["user-agent"] }</p>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
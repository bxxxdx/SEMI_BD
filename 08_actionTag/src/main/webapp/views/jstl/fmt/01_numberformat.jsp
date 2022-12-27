<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- core는 데이터 저장, fmt는 숫자로 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숫자 표현하기</title>
</head>
<body>
	<h1>페이지에서 숫자 표현하기</h1>
	<p>fmt:formatNumber태그를 이용해서 숫자값을 패턴으로 표시</p>
	<h2>기본 숫자 표현</h2>
	<c:set var="numtest" value = "123456012"/>
	<c:set var="numtest1" value = "19883000"/>
	<c:set var="numtest2" value = "1"/>
	<c:set var="numtest3" value = "1234.567"/>
	<p>숫자출력 : ${numtest}</p>
	<p>formatNumber태그 이용 : <fmt:formatNumber value = "${numtest }"/></p>
	<p>formatNumber태그에서 쉼표 표시 제어하기 -> groupingUsed 속성 이용(true/false)</p>
	<p>groupingUsed true : <fmt:formatNumber value="${numtest }" groupingUsed="true"/></p>
	<p>groupingUsed false : <fmt:formatNumber value="${numtest }" groupingUsed="false"/></p>
	
	<h2>숫자를 화폐로 출력하기</h2>
	<p>formatNumber의 type 속성을 이용해서 처리 -> currency </p>
	<p>원화로 표시 : <fmt:formatNumber value="${numtest1}" type="currency"/></p>
	<p>원하는 화폐기호 사용하기 : currencySymbol 속성</p>
	<fmt:formatNumber value="${numtest1}" type="currency" currencySymbol="$"/>	
	<fmt:formatNumber value="${numtest1}" type="currency" currencySymbol="^.~"/>	
	
	<p><fmt:setLocale value="es_ES"/></p>
	<p><fmt:formatNumber value="${numtest1}" type="currency"/></p>
	<p>현재 로케일 확인 ${pageContext.request.locale }</p>
	
	<h3>퍼센트 표시</h3>
	<p>
		type 속성을 이용해서 표시 -> percent설정, value 0~1 사이 값을 입력
		1:100% 0:0%
	</p>
	<p><fmt:formatNumber value="${numtest2 }" type="percent"/></p>
	<p><fmt:formatNumber value="0.5" type="percent"/></p>
	<p><fmt:formatNumber value="0.25" type="percent"/></p>
	
	<h3>패턴으로 숫자를 표시하기</h3>
	<p>
		0 : 지정한 자리수에 수가 없으면 0으로 표시하는 패턴<br>
		# : 지정한 자리수에 수가 없으면 생략하는 패턴<br>
		pattern 속성을 이용해서 설정
	</p>
	<h3>0 : ${numtest3} -> <fmt:formatNumber value="${numtest3 }" pattern="000,000,000"/></h3>
	<h3>0 : ${numtest3} -> <fmt:formatNumber value="${numtest3 }" pattern="###,###,###"/></h3>
	<h3>0 : ${numtest3} -> <fmt:formatNumber value="${numtest3 }" pattern="000,000.000"/></h3>
	<h3>0 : ${numtest3} -> <fmt:formatNumber value="${numtest3 }" pattern="###,###.###"/></h3>
	<h3>0 : ${numtest3} -> <fmt:formatNumber value="${numtest3 }" pattern="000,000.0"/></h3>
	<h3>0 : ${numtest3} -> <fmt:formatNumber value="${numtest3 }" pattern="###,###.#"/></h3>
	<h3>0 : ${numtest3} -> <fmt:formatNumber value="${numtest3 }" pattern="###,###.0000"/></h3>
	
	<h3>소수점 자리에 대한 설정</h3>
	<p>
		minFractionDigits : 최소 소숫점 자리<br>
		masFractionDigits : 최대 소숫점 자리
	</p>
	<h3><fmt:formatNumber value="123.1" minFractionDigits="3"/></h3>
	<h3><fmt:formatNumber value="123.1123213" maxFractionDigits="5"/></h3>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.el.model.vo.Student" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>core 제어태그 활용하기</title>
</head>
<body>
	<h3>JSTL의 제어문 태그 이용하기</h3>
	<p>조건문의 반복문을 처리하는 태그</p>
	<h4>조건문 이용하기</h4>
	<h5>c:if 태그</h5>
	<p>
		자바에서 if문을 사용한 것과 동일, 스크립트릿에서 if문을 사용한 것과 동일..!<br>
		c:if test="조건문 true true" var="조건문의 결과를 저장하는 변수명" ~~~ /c:if<br>
		
	</p>
	
	<c:set var="su" value="30"/>
	<c:set var="su2" value="30"/>
	
	<c:if test="${su < su2 }">
		<p>${su}는 ${su2}보다 작다</p>
		<c:if test = "${su>5}">
			<p> ss</p>
		</c:if>
	</c:if>
	
	<%
		List<String> names = List.of("김유준","송현우","윤수빈","차차");
		request.setAttribute("names",names);
	%>
	<c:if test="${empty names }">
		<p>저장된 이름이 없습니다.</p>
	</c:if>
	<c:if test="${not empty names }">
		<p>저장된 사람은 ${names}다</p>
	</c:if>	

	<h3>c:choose 태그 이용하기</h3>
	<p>switch문과 작성법은 비슷 -> if ~ else if ~ else 문과 로직이 비슷</p>
	<c:choose>
		<c:when test="${su<10}">
			<h4>${su}는 10보다 작다</h4>
		</c:when>
		<c:when test="${su<20 }">
			<h4>${su}는 20보다 작다</h4>
		</c:when>
		<c:when test="${su<30 }">
			<h4>${su}는 30보다 작다</h4>
		</c:when>
		<c:otherwise>
			<h4>${su }는 30보다 크거나 같다</h4>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${empty list }" var="listEmpty">
		<p>저장된 이름이 없습니다.</p>
	</c:if>
	<c:if test = "${listEmpty }">
		<input type="text" name="name" placeholder="이름입력">
	</c:if>
	
	
	<h3>반복문 활용하기</h3>
	<p>두가지 방법으로 이용할 수 있음</p>
	<p>
		1. 기본반복문 : 1~10까지 출력, 원하는 횟수만큼 출력
	</p>
	<p>
		2. 리스트에 저장된 객체 출력(forEach), 배열이나, 리스트에 저장된 데이터를 출력
	</p>
	<p>c:forEach 태그를 이용</p>
	<ul>
		c:forEach 속성
		<li>begin : 초기값(시작값)</li>
		<li>end : 종료값</li>
		<li>step : 증감연산</li>
		<li>var : 인덱스번호나, 객체값을 저장하는 변수명</li>
		<li>items : 순회할 대상 리스트, 배열, Set 객체</li>
		<li>varStatus : 반복문에 대한 정보를 가지고 있는 객체</li>
		<li>index, 반복횟수, 첫번째, 끝에 대한 정보<li>
	</ul>
	
	<h4>기본 c:forEach문 이용하기</h4>
	<p>1~10까지 출력하기</p>
	<ul>
		<c:forEach begin="0" end="${names.size()-1 }" step="1" var="i">
			<li><c:out value="${names.get(i)}"/></li>
		</c:forEach>
	</ul>
	
	<c:forEach begin="1" end="6" step="1" var="i">
		<h${i }>너무행~너무행~!!</h${i }>
	</c:forEach>
	
	<h4>다중객체를 출력하는 반복문</h4>
	<%
		List<Student> students = List.of(
				new Student("유병승", 19, 180.5, '남'),
				new Student("홍나리", 28, 168.5, '여'),
				new Student("문지희", 26, 166.5, '여'),
				new Student("장선우", 40, 188.5, '남')
				);
		request.setAttribute("students",students);	
	%>	
	
	<table>
		<tr>
			<th>이름</th>
			<th>나이</th>
			<th>신장</th>
			<th>성별</th>
			<th>비고</th>
		</tr>
		<c:if test = "${empty students }">
			<tr>
				<td colspan="4">조회된 데이터가 없습니다.</td>
			</tr>
		</c:if>
		<c:if test = "${not empty students }">
			<c:forEach var="student" items="${students}" varStatus="vs">
				<%-- <c:if test="${student.age>=40 }"> --%>
				<c:if test="${vs.first or vs.last}">
					<tr style="${vs.first?"background-color:magenta":""}">
						<td><c:out value="${student.name}"/></td>
						<td><c:out value="${student.age}"/></td>
						<td><c:out value="${student.height}"/></td>
						<td><c:out value="${student.gender}"/></td>
						<td>${vs.count } ${vs.index } ${vs.first } ${vs.last }</td>
					</tr>
				</c:if>
				<%-- </c:if> --%>
			</c:forEach>
		</c:if>
	</table>
	
	<h4>c:forTokens 태그 이용하기</h4>
	<ul>
		<li>var : 값을 저장하는 변수 </li>
		<li>items : 특정 구분자로 구분할 수 있는 문자열 </li>
		<li>delims : 특정 구분자 </li>
	</ul>
	
	
	<c:set var="hobby" value="코딩,운동,여행,영화"/>
	<c:forTokens var="h" items="${hobby}" delims=",">
		<c:if test="${h=='운동'}">
			<p><c:out value="${h}"/></p>
		</c:if>
	</c:forTokens>
	
	<h4>그 외 core 태그</h4>
	<h4>c:import</h4>
	<p>외부에 있는 다른 페이지를 내부에 불러와 출력해주는 태그 jsp:include와 비슷</p>
	<p>페이지를 변수에 저장하고 변수를 출력했을 때 출력</p>
	
	<ul>
		<li>url : </li>
		<li>var : </li>
	</ul>
	<c:import url="https://gdu.co.kr" var="header1" />
	
	<div>
		<%-- ${header1} --%>
	</div>
	
	<%
		String name = null;
	%>
	<h4>c:catch태그 이용하기</h4>
	<p>에러발생시 에러페이지를 생략</p>
	<c:catch var="e">
		<%=name.length() %>
	</c:catch>
	<c:out value="${e }"/>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
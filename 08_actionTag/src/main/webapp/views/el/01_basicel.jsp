<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*, com.el.model.vo.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>기본EL표현식 사용하기</title>
</head>
<body>
	<h2>EL표현식 사용하기</h2>
	<p>
		EL은 <%-- ${} --%> 표현을 사용해서 <%-- ${} --%>내부에 있는 리터럴값, 
		내장객체에 저장된 값, vo에 저장된 값을 출력하는 용도로 사용.	
	</p>
	<h4>리터럴값 출력하기</h4>
	<p><%="리터럴값 출력ㅋ" %></p>
	<p>${"리터럴값출력!!"} </p>
	<p>${19} </p>
	<%-- <p>${new Date().toString()} </p> --%>
	
	<h4>내장객체에 저장된 내용 출력하기.</h4>
	<p>
		EL표현식으로 특정값을 호출하면 자동으로 
		내장객체(request,session,application,page)에 저장된 데이터를 찾음.
	</p>
	<%
		String name = "유병승";
		request.setAttribute("name",name);
	%>
	<p>${name}</p> <!-- 안나온다. 이유는 el표현식으로 특정한 값을 호출하면 자동으로 내장객체에 저장된 데이터를 찾는다. -->
	<p><%=request.getAttribute("name") %></p>
	<%-- <p> -> <%=request.getAttribute("name")||session.getAttribute("name")||application.getAttribute("name")%> 으로 쓴거나 다름 없음</p> --%>
	
	
	<a href = "<%=request.getContextPath()%>/el/data.do">el로 데이터 호출하기</a>
	
	<h3>파라미터 데이터 전송</h3>
	<form action="<%=request.getContextPath()%>/el/data.do">
		<input type="text" name = "str"><br>
		<input type="checkbox" name = "hobby" value="코딩"> 코딩
		<input type="checkbox" name = "hobby" value="운동"> 운동
		<input type="checkbox" name = "hobby" value="영화보기"> 영화보기
		<input type="checkbox" name = "hobby" value="파티"> 파티 <br>
		<input type="submit" value="제출"/>
	</form>
	
	
	<h3>EL표현식에서 연산처리하기</h3>
	<%
		request.setAttribute("su", 19);
		request.setAttribute("su2",30);
		request.setAttribute("su3",30);
		request.setAttribute("testData","admin");
		
		Student s = new Student("유병승", 19, 180.5, '남');
		Student s1 = new Student("김동훈", 26, 180.5, '남');
		Student s2 = new Student("송이현", 29, 180.5, '여');
		List<Student> students = new ArrayList();
		request.setAttribute("students",students);
		
	%>
	<h4>el산술연산</h4>
	<p>+ : ${su+su2}</p>
	<p>- : ${su-su2}</p>
	<p>* : ${su*su2}</p>
	<p>/ : ${su/su2} ${su div su2}</p>
	<p>% : ${su%su2} ${su mod su2 }</p>
	
	<h4>비교연산활용하기</h4>
	<h5>대소비교하기</h5>
	<p>
		&lt; : ${su < su2} ${su lt su2} <br>
		&gt; : ${su > su2} ${su gt su2} <br>
		&le; : ${su2 <= su3} ${su2 le su3} <br>
		&ge; : ${su2 >= su3} ${su2 ge su3} <br>
	</p>
	
	<h5>동등비교하기</h5>
	<p> == : ${su2 == su3} ${su2 eq su3}</p>
	<p> == : ${testData == "admin"} ${testData eq "admin"}</p>
	<p> != : ${su2 != su3} ${su2 ne su3}</p>
	<p> != : ${testData != "admin"} ${testData ne "admin"}</p>
	
	<h5>null값 비교하기</h5>
	<p>null인지 : ${testData == null} ${testData eq null} </p>
	<p>null이 아닌지 : ${testData != null} ${testData ne null} </p>
	<p>${test2Data2} /// ${test2Data2 == null} /// ${test2Data2 eq null}</p>
	<p>List값 확인하기 : ${students == null} ${students.size() > 0 } ${students.isEmpty() }</p>
	<p>empty 예약어 사용하기 : ${empty students} ${not empty students} </p> 
	
	<h5>삼항연산자 사용</h5>
	<p>삼항연산자 : ${su>19?"성인입니다.":"미성년입니다."}</p>
	<p>삼항연산자 : ${testData eq "admin"?"관리자입니다":"일반회원입니다"}</p>
	<input type="checkbox" name="adminCheck" ${testData eq "admin"?"checked":""}> 관리자
	
	<h4>논리연산</h4>
	<p>and, or, &&, || 모두 사용이 가능</p>
	<p>${su>10 && testData eq "admin"}</p>
	<p>${su>10 and testData eq "admin"}</p>
	
	<%-- <c:if test = "${testData ne "admin" }">
		<h2>하하하</h2>		
	</c:if> --%>
	
	<h4>기타 제공되는 데이터 출력하기</h4>
	<p>header의 정보, cookie의 정보를 가져올 수 있음</p>
	<a href="${pageContext.request.contextPath}/el/extraData.do">ㅎㅇ</a>
	
	
	
	
	
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>
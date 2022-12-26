<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>서블릿에서 보낸 데이터 받기</title>
</head>
<body>
	<h3>이메일 : ${email}</h3>
	<h3>등록된 이름 : ${names}</h3>
	<ul>
		<li>${names.get(0)}</li>
		<li>${names.get(1)}</li>
		<li>${names.get(2)}</li>
		<li>${names.get(3)}</li>
	</ul>
	<h3>userId : ${userId} </h3>
	
	<h3>전달된 vo객체 데이터 줄력하기</h3>
	<p>${s}</p>
	<p>이름 : ${s.name}</p>
	<p>나이 : ${s.age}</p>
	<p>키 : ${s.height}</p>
	<p>성별 : ${s.gender}</p>

	<h3>리스트로 전달된 vo객체 출력</h3>
	<p>${students}</p>
	<p>${students.get(1).name}</p>	
	<p>${students.get(1).age}</p>	
	<p>${students.get(1).height}</p>	
	
	<h3>키가 중복되는 것은??</h3>
	<p>중복시 가장 작은 단위부터, page, request, session 순으로 돌아간다.</p>
	<p>userId : ${userId}</p>
	<h4>중복값이 있을 때는 접근할 객체를 지정해서 호출한다.</h4>
	<p>
		requestScope, sessionScope, applicationScope
	</p>
	<p>request : ${requestScope.userId }</p>
	<p>session : ${sessionScope.userId }</p>
	<p>application : ${applicationScope.userId}</p>

	<h3>파라미터로 보낸 데이터 가져오기</h3>
	<p>
		단일값 : param.name으로 접근해서 가져온다.
		다수값 : paramValues.name이름 으로 접근해서 가져온다.
	</p>
	
	<p>단일값 : ${param.str} / ${str }</p>
	<p>다수값 : ${paramValues.hobby }</p>
	<p>다수값 : ${paramValues.hobby[0] }</p>
	<p>다수값 : ${paramValues.hobby[1] }</p>
	<p>다수값 : ${paramValues.hobby[2] }</p>
	<p>다수값 : ${paramValues.hobby[3] }</p>

	






</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ajax 통신하기</title>
</head>
<body>

	<h2>자바스크립트를 이용해서 ajax 통신하기</h2>
	<p>비동기식으로 서버와 통신하는 기술</p>
	<p>
		자바스크립트로 비동기식 통신을 하려면 js가 제공하는 객체를 이용
		XMLHttpRequest 클래스를 이용한다.
		1. XMLHttpRequest 클래스 생성
		2. 생성된 XMLHttpRequest객체를 설정
			- 서버가 응답한 결과에 따른 처리 내용을 설정
			- XMLHttpRequest.onreadystagechange 속성에 설정함.
				-> 처리함수를 대입
			- 요청설정 : open()함수의 매개변수에 요청주소, 방식을 설정
	</p>
	<div>
		<h2>서버에 요청 보내기!!</h2>
		<input id="name">
		<button onclick="ajaxTest();">javascript ajax 요청 보내기</button>
		<button onclick="ajaxTextPost();">javascript ajax(post)요청 보내기</button>
		<div id="ajaxContainer"></div>
	</div>
	
	<script>
		const ajaxTextPost = () => {
			const xhr = new XMLHttpRequest();
			const name = document.getElementById("name").value;
			
			xhr.open("post","<%=request.getContextPath()%>/ajax/ajaxText.do");
			xhr.onreadystatechange = () => {
				if(xhr.readyState==4){
					if(xhr.status==200){
						document.getElementById("ajaxContainer").innerHTML = xhr.responseText;			
					} else {
						alert("요청한 페이지에 문제가 발생했습니다 :(")
					}
				}
			}
			//여기까진 같다.. get-> post
			
			//post방식으로 send를 할때는 헤더에 대해 추가 설정을 해줘야 함.
			//content-type을 application/x-www-form-urlencoded로 설정해야함
			xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			
			//데이터를 전송할 때는 send에 인자로 데이터를 전송
			xhr.send("name=user11");
		}
	
		let i = 0;
		const ajaxTest = () => {
			//1. XMLHttpRequest클래스 생성하기
			const xhr = new XMLHttpRequest();
			const name = document.getElementById("name").value;
				
			//2. open() 함수를 이용해서 요청설정
			//open 함수는 세개의 매개변수를 설정
			//	2.1 : 방식(GET, POST)
			//	2.2 : 요청주소("http://~~blah~~blah~~","request.getContextPath,,") 
			//	2.3 : [동기식, 비동기식] 설정할 수 있다.
			xhr.open("get","<%=request.getContextPath()%>/ajax/jsAjax.do?name="+name);
			//get 대소문자 구분 x
			
			//3. 요청에 대한 서버 응답을 처리
			//onreadystatechange속성에 처리함수를 저장
			//xhr객체에 응답상태를 저장하는 속성
			//	-> readyState, status 두개의 속성이 있음
			//readyState : 전송상태를 알려주는 데이터. 0,1,2,3,4 등의 값을 가진다.
			//status : 응답코드. (200,404,500,403..etc)
			//두개의 속성값은 js가 진행상태에 따라서 알아서 변경
			//readyState속성이 변경될때마다 실행!
			xhr.onreadystatechange=()=>{
				console.log(xhr.readyState);
				console.log(xhr.status);
				if(xhr.readyState==4){
					// readyState는 4가 되는 순간 통신이 끝났다는 뜻.
					if(xhr.status==200){
						//status가 200이 되는건 정상적으로 입력이 완료 처리 되었을 때.
						//서버가 응답한 데이터는 xhr의 responseText속성에 저장됨
						console.log(xhr.responseText);
						document.getElementById("ajaxContainer").innerHTML = xhr.responseText;
						//$("#ajaxContainer").html("xhr.responseText");
					}
				} 
			}
			
			//4. 설정이 완료돠면 요청을 보내는 함수를 호출
			//send();
			xhr.send();
			document.getElementById("ajaxContainer").innerHTML = "Loading...."+(i++);
			
		}
	
	</script>
	



</body>
</html>
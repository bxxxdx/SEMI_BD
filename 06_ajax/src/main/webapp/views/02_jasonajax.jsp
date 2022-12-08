<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>json으로 데이터 처리하기</title>
<script src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
	<h2>jsonSimple 라이브러리 이용하기</h2>
	<p>java 객체를 javascript 객체 표현방식으로 변경해주는 라이브러리</p>
	
	<button id="btnObj">기본객체json으로 전송</button>
	<div id="objectcontainer"></div>
	<script>
		$("#btnObj").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/basicJson.do",
				dataType:"json",
				success:data=>{
				//ctrl + shift + c 주석
// 					$("#objectcontainer").append($("<h3>"+data["name"]+"</h3>"));
// 					$("#objectcontainer").append($("<h3>"+data["phone"]+"</h3>"));
// 					$("#objectcontainer").append($("<h3>"+data["profile"]+"</h3>"));
// 					$("#objectcontainer").append($("<h3>"+data["name"]+"</h3>"));
// 					$("#objectcontainer").append($("<h3>"+data["phone"]+"</h3>"));
// 					$("#objectcontainer").append($("<h3>"+data["profile"]+"</h3>"));
					console.log(data);
					
					const table = $("<table>");
					const header = $("<tr>").html("<th>아이디</th><th>이름</th><th>이메일</th><th>전화번호</th><th>주소</th>");
					table.append(header);
					data.forEach(v=>{
						const tr = $("<tr>");
						const id = $("<td>").text(v["userId"]);
						const name = $("<td>").text(v["userName"]);
						const email = $("<td>").text(v["email"]);
						const phone = $("<td>").text(v["phone"]);
						const addr = $("<td>").text(v["address"]);
						tr.append(id).append(name).append(email).append(phone).append(addr);
						table.append(tr);
					});
					$("#objectcontainer").html(table);
				},
				error:(r,e,m)=>{
					console.log(r);
					console.log(e);
					console.log(m);
				}
			})
		})
		
	</script>
	
	














	
</body>
</html>
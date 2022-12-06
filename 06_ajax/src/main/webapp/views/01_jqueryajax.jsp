<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jquery로 ajax 이용하기</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
	<h3>jquery를 이용해서 ajax 요청 처리하기</h3>
	<p>
		1. $.ajax()함수 : 요청에 대한 상세 설정을 할때 사용, 헤더에 설정 , 인코딩값, 다수의 데이터를 전송...
		2. $.get()함수 : 기본 get방식 요청을 처리할때 사용
		3. $.post()함수 : 기본 post방식 요청을 처리할때 사용
	</p>
	<h2>$.ajax()함수 이용하기</h2>
	<p>
		매개변수에 요청에 대한 설정내용을 전달
		javascript객체를 전달함. -> key값이 정해져있음
		url : 요청할 주소 (String) [<%=request.getContextPath() %>/blahblah]
		type(생략가능, 생략시 get) : 요청 Method(String) [get, post]
		data(생략가능) : 서버에 전송할 데이터 (js object) [{key:value, key:value, .... }]
		dataType(생략가능) : 응답받은 데이터 형식을 지정 [text, json, xml]
		success : 서버요청이 정상적으로 완료됐을때 실행할 callback 함수
		error(생략가능) : 서버요청이 정상적으로 완료되지 않았을 때 실행할 callback 함수
	</p>
	<input id="data">
	<button id="btn1">기본 $.ajax() 요청하기</button>
	<button id="btn2">$.get() 요청하기</button>
	<button id="btn3">$.post() 요청하기</button>
	<div id="container"></div>
	
	<script>
		$("#btn3").click(e=>{
			//외않되
			$.post("<%=request.getContextPath()%>/ajax/jsAjax.do",
					{
						name:$("#data").val()
					},
					data=>{
						$("#container").html(data);
					}
			)
		})
	
		$("#btn2").click(e=>{
			//첫번째 매개변수 : 주소
			//두번째 매개변수 : callback 함수
			$.get("<%=request.getContextPath()%>/ajax/jsAjax.do?name="+$("#data").val(), data=>{$("div#container").html(data);})	
		})
		
		$("#btn1").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/jsAjax.do",
				type:"get",
				data:{name:$("#data").val()},
				success:data=>{
					//responseText값이 매개변수로 들어옴
					console.log(data);
					$("div#container").html(data);
				}
			});
		})
	
	</script>
	
	<h2>서버에 있는 문서파일 불러오기</h2>
	<button id="btn4">test.txt 가져오기</button>
	<div id="container2"></div>
	<script>
		$("#btn4").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/upload/test.txt",
				dataType:"text",
				success:data=>{
					console.log(data);
					const dataArr = data.split("\r\n");
					console.log(dataArr);
					dataArr.forEach(v=>{
						/* let d = $("#container2").html();
						$("#container2").html(d+ "<h2>" +v +"</h2>"); */
						let d = $("<h2>").text(v);
						$("#container2").append(d);
					})
				}
			})
		})
	</script>
	
	<h2>서버에서 전송하는 csv방식의 데이터 처리하기</h2>
	<p>
		csv : 데이터를 특정 특수기호로 분리할 수 있게 작성한 문자열
		일반적으로 ,(쉼표) |(바), $, \n 등 데이터별 구별이 가능한 문자를 사용함
		 
	</p>
	<button id="btncsv">csv데이터 가져오기</button>
	<div id="csvcontainer"></div>
	<script>
		$("#btncsv").click(e=>{
			$.ajax({
				url:"<%=request.getContextPath()%>/ajax/csvdata.do",
				dataType:"text",
				success:data=>{
					const actors = data.split("\n");
					const table = $("<table>");
					const header=$("<tr>").html("<th>이름</th><th>전화번호</th><th>프로필</th>");
					table.append(header);
					actors.forEach(v=>{
						const a = v.split(",");
						let tr = $("<tr>");
						const name = $("<td>").text(a[0]);
						const phone = $("<td>").text(a[1]);
						const profile = $("<td>").append($("<img>").attr({
							src:"<%=request.getContextPath()%>/images/"+a[2],
							width:"100px",
							height:"100px"
						}))
						tr.append(name).append(phone).append(profile);
						table.append(tr);
					});
					$("#csvcontainer").append(table);
				}
			})
		})
	</script>
	
	<h2>xml 파일 가져와 처리하기</h2>
	<button id="btnxml">xml 데이터 가져오기</button>
	<div id="fiction">
		<h2>소설</h2>
		<table id="fiction-info"></table>
	</div>
	<div id="id">
		<h2>IT</h2>
		<table id="it-info"></table>
	</div>
	<script>
		$("#btnxml").click(e=>{
			let fictionHeader = "<tr><th>제목</th><th>저자</th></tr>";
			let itHeader = fictionHeader;
			$.ajax({
				url:"<%=request.getContextPath()%>/upload/books.xml",
				success:data=>{
					const root = $(data).find(":root");
					const books = $(root).find("book");
					books.each((i,v)=>{
						const title = $(v).find("title").text();
						const subject = $(v).find("subject").text();
						const writer = $(v).find("writer").text();
						
						const content = "<tr><td>"+title+"</td><td>"+writer+"</td></tr>";
						if(subject=='소설'){
							fictionHeader += content;
						} else {
							itHeader += content;
						}
					})
					$("#fiction-info").html(fictionHeader);
					$("#it-info").html(itHeader);
				}
			})
		})
	</script>
	
	
</body>
</html>
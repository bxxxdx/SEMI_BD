<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹소켓 기본채팅 구현하기</title>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
</head>
<body>
	<div id="container">
		보낸사람 <input type="text" size="4" id="sender"><br>
		받는사람 <input type="text" size="4" id="receiver"><br>
		<input type="text" id="msg" placeholder="전송할 메세지 작성!">
		<button id="btnSend">전송</button>
	</div>
	<div id="msgcontainer"></div>
	
	<script>
		//websocket 서버에 연결하기
		//주소는 두가지 방식으로 적을 수 있다. http, https 를 ws, wss로 구분하여 적는다.
		//ws : http 프로토콜 이용
		//wss : https 프로토콜 이용
		//ws: 서버주소(//localhost:9090)/프로젝트주소/mapping 주소
		//	* 나중에 웹소켓 쓰려면 서버명 꼭 localhost에서 서버명으로 수정해야함.. ㅎ
		const socket = new WebSocket("ws://localhost:9090/<%=request.getContextPath()%>/chatting");
		
		//서버에 접속이 완료됐을 때 실행되는 함수 등록하기
		socket.onopen = e => {
			alert("서버접속");
			console.log(e);
		}
		
		//서버에서 보낸 데이터를 처리하는 핸들러를 등록
		socket.onmessage=e=>{
			//const data = e.data.split(",");
			//json방식의 문자열을 다시 객체로 만들어주는 함수
			//JSON.parse();
			const data = JSON.parse(e.data);
			console.log(data);
			const div = $("<div>").css("display","flex")
			const msg = $("<h3>").text(data["msg"]);
			const sender=$("<sup>").text(data["sender"]);
			if($("#sender").val()==data["sender"]){
				div.append(sender).append(msg).css("justify-content","right");
			} else if($("#sender").val()==data["receiver"]){
				div.append(sender).append(msg).css({
					"justify-content":"left",
					"color":"lightgray"
					});
			} else if(data["receiver"] == ""){
				div.append(sender).append(msg).css("justify-content","left");
			}		
			$("#msgcontainer").append(div);			
					
			
		} 
		
		$("#btnSend").click(e=>{
			//작성한 메세지를 웹소켓 서버에 전송
			//1. 작성한 데이터를 가져오기
			//2. send(전송할 데이터) 함수를 이용해서 데이터를 서버에 전송
			//3. 보내는 데이터 패턴 만들기
			// 보낸사람, 메세지 로 작성 
			const data = new Message($("#sender").val(),$("#receiver").val(),$("#msg").val());
			console.log(data);
			//객체는 javascript 패턴 문자열로 변환해주는 함수
			socket.send(JSON.stringify(data));
			
		});
		
		class Message{
			constructor(sender, receiver, msg){
				this.sender = sender;
				this.receiver = receiver;
				this.msg = msg;
			}
		}
	
	</script>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>
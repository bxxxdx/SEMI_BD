package com.websocket.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.websocket.model.vo.Message;

@ServerEndpoint(value = "/chatting", encoders = { JsonEncoder.class }, decoders = { JsonDecoder.class })
public class ChattingServer { 

	private List<Session> client = new ArrayList();

	@OnOpen
	public void open(Session session, EndpointConfig config) {
		System.out.println("클라이언트접속!!");
	}

//	@OnMessage
//	public void messageManage(Session session, String data) {
////		System.out.println("메세지 수신!!");
////		System.out.println(data);
////		System.out.println(session.getId());
//
//		// JSON을 자바객체로 변환하기 -> GSON 이용
//		Message msg = new Gson().fromJson(data, Message.class);
//		System.out.println("gson " + msg);
//		// session에 대한 확인할 정보를 각 session객체에 저장함
//		session.getUserProperties().put("msg", msg);
//
//		// 전송된 메세지를 접속한 클라이언트한테 전송해주기
//		// session 객체가 제공하는 getBasicRemote() 메소드를 이용해서 sendText()
//		// 전송객체를 가져와 sendText() 메소드를 실행
//		try {
//			// 접속한 session을 모두 가져오는 메소드 제공
//			// getOpenSessions() -> Session[]
//			Set<Session> clients = session.getOpenSessions();
//
//			if (!msg.getReceiver().equals("")) {
//				// 귓속말
//				for (Session client : clients) {
//					Message id = (Message)client.getUserProperties().get("msg");
//					if(id.getSender().equals(msg.getReceiver())) {
//						client.getBasicRemote().sendText(data);
//					}
//				}
//			} else {
//				// 전체
//				for (Session client : clients) {
//					client.getBasicRemote().sendText(data);
//				}
//			}
//
//		} catch (IOException e) {
//
//		}
//	}

	@OnMessage
	public void messageObject(Session session, Message msg) {
		// session 사용자를 확인할 데이터를 저장
		session.getUserProperties().put("msg", msg);

		Set<Session> clients = session.getOpenSessions();
		// session.isOpen() 메소드도 있음.. !

		try {
			if (msg.getReceiver().equals("")) {
				for (Session client : clients) {
					client.getBasicRemote().sendObject(msg);
				}
			} else {
				for (Session client : clients) {
					Message userMsg = (Message) client.getUserProperties().get("msg");
					if (userMsg.getSender().equals(msg.getReceiver()) || userMsg.getSender().equals(msg.getSender())) {
						client.getBasicRemote().sendObject(msg);
					}
				}
			}
		} catch (IOException | EncodeException e) {
			e.printStackTrace();
		}
	}

}

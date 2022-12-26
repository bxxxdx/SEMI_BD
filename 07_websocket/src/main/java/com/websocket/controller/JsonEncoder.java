package com.websocket.controller;

import javax.websocket.EncodeException;
import javax.websocket.Encoder.Text;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import com.websocket.model.vo.Message;

public class JsonEncoder implements Text<Message> {
	//alt shift s v
	//init, destroy 는 추가로 필요하면 설정, 
	//핵심 인코딩 기능은 encode에 구현한다.
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String encode(Message arg0) throws EncodeException {
		return new Gson().toJson(arg0);
	}

}

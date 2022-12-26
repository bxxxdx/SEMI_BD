package com.websocket.controller;

import javax.websocket.DecodeException;
import javax.websocket.Decoder.Text;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;
import com.websocket.model.vo.Message;

public class JsonDecoder implements Text<Message> {
	//decode는 decode와 willDecode를 구현해야한다.
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(EndpointConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message decode(String arg0) throws DecodeException {
		return new Gson().fromJson(arg0, Message.class);
	}

	@Override
	public boolean willDecode(String arg0) {
		// 얘를 false에서 true로 바꿔주지 않으면 decoding을 하지 않는다.
		// 꼭 true로 바꿔줘야함.
		return true;
	}

}

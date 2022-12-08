package com.web.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncodingWrapper extends HttpServletRequestWrapper {
	
	public PasswordEncodingWrapper(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public String getParameter(String name) {
		if(name.contains("password")) {
			//데이터를 단방향 암호화해서 반환하기
			String ori = super.getParameter(name);
			System.out.println("암호화 전 : " + ori);
			
			String encrpt = getSHA512(super.getParameter(name));
			System.out.println("암호화 후 : " + encrpt);
			
			return getSHA512(super.getParameter(name));
		}
		return super.getParameter(name); //원본값
	}
	
	private String getSHA512(String oriVal) {
		//단방향 암호화
		//java에서 암호화처리 클래스를 제공해줌. -> MessageDigest 클래스
		MessageDigest md = null;
		try {
			//적용할 암호화 알고리즘을 선택해서 클래스 생성
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace();
		}
		//md 이용해서 암호화 처리를 진행 -> byte[]로 변경해서 암호화를 처리
		byte[] oriDataByte = oriVal.getBytes();
		md.update(oriDataByte); //SHA-512방식으로 암호화처리
		byte[] encryptData = md.digest();// 암호화된 값을 byte 배열로 가져옴
		
		//Base64 인코더를 이용해서 byte[]로 출력된 내용을 String으로 변환
		return Base64.getEncoder().encodeToString(encryptData);
	}
	
	
}

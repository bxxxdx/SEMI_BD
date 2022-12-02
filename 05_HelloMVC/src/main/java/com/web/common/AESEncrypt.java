package com.web.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//양방향 암호화하는 메소드를 제공하는 클래스
//양방향 암호화도 java에서 제공을 하고 있다.
//java.crypto, java.security패키지에서 제공하는 클래스를 이용함
//양뱡향 암호화는 key관리를 잘해야함.
//AES방식으로 대칭키를 이용 -> 키가 한개
public class AESEncrypt {

	private static SecretKey key;//암호화, 복호화하는 키
	private String path;//key를 저장하고 있는 경로
	
	public AESEncrypt() {
		//key값을 생성 또는 가져오기
		//1. 생성된 키가 있으면(key클래스를 저장한 파일이 있으면) 그 키를 가져와저장
		//2. 생성되 키가 없으면(key클래스를 저장한 파일이 없으면) 키를 생성 후 파일로 저장
		//key를 저장하는 파일명 -> bslove.bs
		this.path=AESEncrypt.class.getResource("/").getPath();
		this.path=this.path.substring(0,this.path.indexOf("classes"));
		System.out.println(this.path);
		File keyFile=new File(this.path+"bslove.bs");
		if(keyFile.exists()) {
			//파일이 있으면....
			try(ObjectInputStream ois
					=new ObjectInputStream(new FileInputStream(keyFile))) {
				
				this.key=(SecretKey)ois.readObject();
				System.out.println(key);
			}catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}else {
			//파일이 없으면....
			//key생성하고 생성된키를 지정된 파일에 저장
			getGeneratedKey();
		}
	}
	
	private void getGeneratedKey() {
		//SecretKey 클래스를 생성하고 지정파일에 저장하는 메소드
		SecureRandom rnd=new SecureRandom();
		//KeyGenerator클래스를 생성 -> key생성해주는 역할
		KeyGenerator keygen=null;
		try(ObjectOutputStream oos
				=new ObjectOutputStream(
						new FileOutputStream(this.path+"bslove.bs"))){
			keygen=KeyGenerator.getInstance("AES");
			keygen.init(128,rnd);
			//필드
			this.key=keygen.generateKey();
			//파일에 저장
			oos.writeObject(this.key);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//암호화하는 메소드 선언
	public static String encryptData(String oriVal) throws Exception{
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, AESEncrypt.key);
		
		byte[] targetData=oriVal.getBytes(Charset.forName("UTF-8"));
		byte[] encResult=cipher.doFinal(targetData);
		
		return Base64.getEncoder().encodeToString(encResult);
	}
	
	//복호화하는 메소는 선언
	public static String decryptData(String encData) throws Exception{
		
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, AESEncrypt.key);
		
		byte[] encTemp=Base64.getDecoder()
				.decode(encData.getBytes(Charset.forName("UTF-8")));
		byte[] decResult=cipher.doFinal(encTemp);
		
		return new String(decResult);
	}
	
	
	
}






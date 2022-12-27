package com.jsp.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;


@Data
@Builder 
public class Member {
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String[] hobby;
	private Date enrollDate;
}

package com.jsp.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Animal {
	private String name;
	private int age;
	private double  height;
	private double weight;
	private char gender;
}

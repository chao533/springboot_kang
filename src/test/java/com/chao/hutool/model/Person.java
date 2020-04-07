package com.chao.hutool.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
	
	private Integer age;
	
	private String name;
	
	private Boolean sex;
	
	private User user;
	
	private List<Goods> goodsList;

}

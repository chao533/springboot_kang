package com.kang.model.elastic;

import lombok.Data;

@Data
public class EsUser {

	
	private Long id;
	
	private String name;
	
	private Boolean gender;
	
	private String email;
}

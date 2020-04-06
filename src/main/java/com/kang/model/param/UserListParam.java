package com.kang.model.param;

import lombok.Data;

@Data
public class UserListParam {

	
	private int pageNo; //当前页
	
	private int pageSize; // 每页记录数
	
	private String search; // 搜索
}

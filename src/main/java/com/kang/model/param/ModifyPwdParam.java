package com.kang.model.param;

import lombok.Data;

@Data
public class ModifyPwdParam {

	private String username; // 用户名
	
	private String oldPwd; // 原密码
	
	private String newPwd; // 新密码
}

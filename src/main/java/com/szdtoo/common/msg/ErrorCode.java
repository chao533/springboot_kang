package com.szdtoo.common.msg;

/**
 * <p>Title: ErrorCode</p>  
 * <p>Description: 定义状态信息</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public enum ErrorCode {
	
	SUCCESS(200,"操作成功"),
	ERROR_AUTH(200,"无权限操作"),
	ERROR(500,"操作失败");
	
	private int code;
	
	private String msg;
	
	private ErrorCode(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}


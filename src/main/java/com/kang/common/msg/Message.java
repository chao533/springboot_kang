package com.kang.common.msg;

import java.io.Serializable;

/**
 * <p>Title: Message</p>  
 * <p>Description: 统一返回Message信息</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public class Message<T> implements Serializable{
	
	private static final long serialVersionUID = -3159473297776025114L;

	private int code; // 状态码
	
	private String msg; // 信息
	
	private boolean status; // 状态
	
	private T data; // 数据
	
	public Message(ErrorCode errorCode) {
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
		this.status = this.code == 200 ? true : false;
	}
	
	public Message(ErrorCode errorCode,T data) {
		this.code = errorCode.getCode();
		this.msg = errorCode.getMsg();
		this.status = this.code == 200 ? true : false;
		this.data = data;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Message [code=" + code + ", msg=" + msg + ", status=" + status + ", data=" + data + "]";
	}
	
	
}

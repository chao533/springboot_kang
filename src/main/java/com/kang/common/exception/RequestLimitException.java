package com.kang.common.exception;

public class RequestLimitException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5996068931069292047L;

	public RequestLimitException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RequestLimitException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RequestLimitException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RequestLimitException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RequestLimitException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
}

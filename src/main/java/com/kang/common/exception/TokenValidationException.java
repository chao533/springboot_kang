package com.kang.common.exception;

/**
 * <p>Title: TokenValidationException</p>  
 * <p>Description: token异常</p>  
 * @author chaokang  
 * @date 2018年12月3日
 */
public class TokenValidationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3665676184803633752L;

	public TokenValidationException() {
		super();
	}

	public TokenValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TokenValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenValidationException(String message) {
		super(message);
	}

	public TokenValidationException(Throwable cause) {
		super(cause);
	}

	
	
}

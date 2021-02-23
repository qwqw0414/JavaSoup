package com.java.soup.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1038066323763308432L;

	public AppException() {
	}
	
	public AppException(Throwable cause) {
		super(cause);
	}
	
	public AppException(String message) {
		super(message);
	}
	
	public AppException(String message, Throwable cause) {
		super(message, cause);
	}
}

package com.zhat.http.exceptions;

abstract public class ZLHttpRequestException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4550445042540936805L;

	public ZLHttpRequestException() {
		super();
	}
	
	public ZLHttpRequestException(String message) {
		super(message);
	}
}

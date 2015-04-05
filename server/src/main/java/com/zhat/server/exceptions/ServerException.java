package com.zhat.server.exceptions;

abstract public class ServerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2069526138008292154L;

	public ServerException() {
		super();
	}
	
	public ServerException(String message) {
		super(message);
	}
}

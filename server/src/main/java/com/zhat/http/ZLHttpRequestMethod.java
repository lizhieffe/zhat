package com.zhat.http;

import com.zhat.http.exceptions.ZLHttpRequestMethodException;

public enum ZLHttpRequestMethod {
	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	DELETE("DELETE"),
	NONE("NONE");
	
	private String methodText;
	
	ZLHttpRequestMethod(String methodText) {
		this.methodText = methodText;
	}
	
	public static ZLHttpRequestMethod parseMethodFromString(String str) throws ZLHttpRequestMethodException {
		if (str.equalsIgnoreCase("GET"))
			return GET;
		else if (str.equalsIgnoreCase("POST"))
			return POST;
		else if (str.equalsIgnoreCase("PUT"))
			return PUT;
		else if (str.equalsIgnoreCase("DELETE"))
			return DELETE;
		else
			throw new ZLHttpRequestMethodException();
	}
	
	public String getMethodText() {
		return methodText;
	}
}

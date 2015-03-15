package com.zhat.http;

public enum ZLHttpRequestContentType {
	APPLICATION_JSON,
	APPLICATION_X_WWW_FORM_URLENCODED;
	
	public static ZLHttpRequestContentType parseContentTypeFromString(String str) throws ZLHttpRequestContentTypeException {
		if (str.equalsIgnoreCase("application/json"))
			return APPLICATION_JSON;
		else if (str.equalsIgnoreCase("application/x-www-form-urlencoded"))
			return APPLICATION_X_WWW_FORM_URLENCODED;
		else
			throw new ZLHttpRequestContentTypeException();
	}
}

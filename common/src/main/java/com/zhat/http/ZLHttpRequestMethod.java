package com.zhat.http;

import com.zhat.http.exceptions.ZLHttpRequestMethodException;

public enum ZLHttpRequestMethod {
	GET,
	POST,
	PUT,
	DELETE,
	NONE;
	
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
}

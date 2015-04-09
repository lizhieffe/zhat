package com.zhat.http.response;

import org.apache.commons.httpclient.HttpStatus;

public class ZLHttpResponseFactory {
	
	public static ZLHttpServletResponse createSuccessResponse() {
		return new ZLHttpServletResponseBuilder().build();
				
	}
	
	public static ZLHttpServletResponse createFailureResponse() {
		return new ZLHttpServletResponseBuilder()
				.status(HttpStatus.SC_BAD_REQUEST)
				.build();
	}
}

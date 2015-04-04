package com.zhat.http.response;

import org.apache.commons.httpclient.HttpStatus;

import com.zhat.http.ZLHttpContentType;

public class ZLHttpResponseFactory {
	
	public static ZLHttpResponse createSuccessResponse() {
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(HttpStatus.SC_OK);
		response.setContentType(ZLHttpContentType.APPLICATION_JSON);
		return response;
	}
	
	public static ZLHttpResponse createFailureResponse() {
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setContentType(ZLHttpContentType.APPLICATION_JSON);
		return response;
	}
	
	public static ZLHttpResponse createFailureResponseWrongContentType() {
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(HttpStatus.SC_BAD_REQUEST);
		response.setContentType(ZLHttpContentType.APPLICATION_JSON);
		return response;
	}
}

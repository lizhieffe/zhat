package com.zhat.http.response;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import com.zhat.http.ZLHttpContentType;

public class HttpResponseFactory {
	
	public static HttpResponse createSuccessResponse() {
		return new HttpResponseBuilder()
				.status(HttpStatus.SC_OK)
				.contentType(ZLHttpContentType.APPLICATION_JSON)
				.build();
	}
	
	public static HttpResponse createFailureResponse() {
		return new HttpResponseBuilder()
				.status(HttpStatus.SC_BAD_REQUEST)
				.contentType(ZLHttpContentType.APPLICATION_JSON)
				.build();
	}
}

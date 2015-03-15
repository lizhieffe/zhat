package com.zhat.http;

import java.util.HashMap;
import java.util.Map;

public class ZLHttpRequest {
	private ZLHttpRequestMethod method;
	private String URI;
	private Map<String, String> headers = new HashMap<String, String>();
	
	public ZLHttpRequestMethod getMethod() {
		return method;
	}
	public void setMethod(ZLHttpRequestMethod method) {
		this.method = method;
	}
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	public String getContentType() {
		return headers.get("Content-Type");
	}
}

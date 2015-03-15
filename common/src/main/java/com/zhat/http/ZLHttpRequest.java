package com.zhat.http;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class ZLHttpRequest {
	private ZLHttpRequestMethod method;
	private String URI;
	private Map<String, String> headers = new HashMap<String, String>();
	private ZLHttpRequestContentType contentType;
	private JSONObject jsonData;
	
	public void setContentType(ZLHttpRequestContentType contentType) {
		this.contentType = contentType;
	}
	
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
	
	public ZLHttpRequestContentType getContentType() {
		return this.contentType;
	}
	
	public void setHttpRequestContentType(ZLHttpRequestContentType contentType) {
		this.contentType = contentType;
	}
	
	public JSONObject getJsonData() {
		return jsonData;
	}
	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}
}

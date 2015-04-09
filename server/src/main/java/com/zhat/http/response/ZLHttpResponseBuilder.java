package com.zhat.http.response;

import java.io.InputStream;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.json.JSONObject;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.zhat.http.ZLHttpContentType;

public class ZLHttpResponseBuilder {
	
	private int status = -1;
	private BasicHttpEntity entity;
	private Multimap<String, String> headers;
	private InputStream contentInputStream;
	private String contentStr;
	private JSONObject contentJSONObject;
	
	ZLHttpResponseBuilder() {
		this.entity = new BasicHttpEntity();
		this.headers = HashMultimap.create();
	}
	
	public ZLHttpResponseBuilder status(int status) {
		this.status = status;
		return this;
	}
	
	/**
	 * Add a header entry.
	 * @param key: header key.
	 * @param val: header value.
	 * @return: ZLHttpResponseBuilder
	 */
	public ZLHttpResponseBuilder header(String key, String val) {
		headers.put(key, val);
		return this;
	}
	
	public ZLHttpResponseBuilder contentType(ZLHttpContentType contentType) {
		entity.setContentType(contentType.getContentTypeText());
		return this;
	}
	
	public ZLHttpResponseBuilder content(InputStream inputStream) {
		clearContent();
		this.contentInputStream = inputStream;
		return this;
	}
	
	public ZLHttpResponseBuilder content(String str) {
		clearContent();
		this.contentStr = str;
		return this;
	}
	
	public ZLHttpResponseBuilder content(JSONObject json) {
		clearContent();
		this.contentJSONObject = json;
		return this;
	}
	
	private void clearContent() {
		contentInputStream = null;
		contentStr = null;
		contentJSONObject = null;
	}
	
	private boolean hasContent() {
		ProtocolVersion protocalVersion = new ProtocolVersion("HTTP", 1, 1);
		BasicHttpResponse r = new BasicHttpResponse(protocalVersion, HttpStatus.SC_BAD_GATEWAY, null);
		r.addHeader("dd", "bb");
		
		
		
		
		return contentInputStream == null && contentStr == null && contentJSONObject == null ? false : true;
	}
	
	public ZLHttpResponse build() {
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(status);
		if (hasContent()) {
			if (contentInputStream != null)
				response.setContent(contentInputStream);
			else if (contentStr != null)
				response.setContent(contentStr);
			else if (contentJSONObject != null)
				response.setContent(contentJSONObject);
		}
		return response;
	}
}

package com.zhat.http.response;

import org.apache.http.entity.BasicHttpEntity;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.zhat.http.ZLHttpContentType;

public class ZLHttpResponseBuilder {
	
	private int status = -1;
	private BasicHttpEntity entity;
	private Multimap<String, String> headers;
	
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
	
	public ZLHttpResponse build() {
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(status);
	}
}

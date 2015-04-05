package com.zhat.http.response;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.zhat.http.ZLHttpContentType;

/**
 * Fluid design pattern.
 * @author zhili
 *
 */
public class ZLHttpServletResponseBuilder {
		
	private int status = 200;
	private ZLHttpContentType contentType = ZLHttpContentType.APPLICATION_TEXT_PLAIN;
	private Multimap<String, String> headers;
	private String contentStr;
	private JSONObject contentJSONObject;
	
	public ZLHttpServletResponseBuilder() {
		this.headers = HashMultimap.create();
	}
	
	public ZLHttpServletResponseBuilder status(int status) {
		this.status = status;
		return this;
	}
	
	/**
	 * Add a header entry.
	 * @param key: header key.
	 * @param val: header value.
	 * @return: ZLHttpResponseBuilder
	 */
	public ZLHttpServletResponseBuilder header(String key, String val) {
		headers.put(key, val);
		return this;
	}
	
	public ZLHttpServletResponseBuilder contentType(ZLHttpContentType contentType) {
		this.contentType = contentType;
		return this;
	}
	
	public ZLHttpServletResponseBuilder content(String str) {
		clearContent();
		this.contentStr = str;
		return this;
	}
	
	public ZLHttpServletResponseBuilder content(JSONObject json) {
		clearContent();
		this.contentJSONObject = json;
		return this;
	}
	
	private void clearContent() {
		contentStr = null;
		contentJSONObject = null;
	}
	
	private boolean hasContent() {
		return contentStr == null && contentJSONObject == null ? false : true;
	}
	
	public ZLHttpServletResponse build() {
		
		ZLHttpServletResponse response = new ZLHttpServletResponse();
		
		response.setStatus(status);
		
		if (contentType != null)
			response.setContentType(contentType);
		
		if (headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entries()) {
				response.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		if (hasContent()) {
			try {
				if (contentStr != null)
					response.getWriter().println(contentStr);
				else if (contentJSONObject != null)
					response.getWriter().println(contentJSONObject.toString());
				response.getWriter().close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return response;
	}
}
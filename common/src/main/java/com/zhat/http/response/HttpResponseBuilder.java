package com.zhat.http.response;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHttpResponse;
import org.json.JSONObject;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.zhat.http.ZLHttpContentType;

/**
 * Fluid design pattern.
 * @author zhili
 *
 */
public class HttpResponseBuilder {
	
	private int status = 200;
	private BasicHttpEntity entity;
	private Multimap<String, String> headers;
	private InputStream contentInputStream;
	private String contentStr;
	private JSONObject contentJSONObject;
	
	HttpResponseBuilder() {
		this.headers = HashMultimap.create();
	}
	
	public HttpResponseBuilder status(int status) {
		this.status = status;
		return this;
	}
	
	/**
	 * Add a header entry.
	 * @param key: header key.
	 * @param val: header value.
	 * @return: ZLHttpResponseBuilder
	 */
	public HttpResponseBuilder header(String key, String val) {
		headers.put(key, val);
		return this;
	}
	
	public HttpResponseBuilder contentType(ZLHttpContentType contentType) {
		entity.setContentType(contentType.getContentTypeText());
		return this;
	}
	
	public HttpResponseBuilder content(InputStream inputStream) {
		clearContent();
		this.contentInputStream = inputStream;
		return this;
	}
	
	public HttpResponseBuilder content(String str) {
		clearContent();
		this.contentStr = str;
		return this;
	}
	
	public HttpResponseBuilder content(JSONObject json) {
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
		return contentInputStream == null && contentStr == null && contentJSONObject == null ? false : true;
	}
	
	public HttpResponse build() {
		
		ProtocolVersion protocalVersion = new ProtocolVersion("HTTP", 1, 1);
		BasicHttpResponse response = new BasicHttpResponse(protocalVersion, status, null);
		
		response.setEntity(new BasicHttpEntity());
		
		if (headers.size() > 0) {
			for (Map.Entry<String, String> entry : headers.entries()) {
				response.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		if (hasContent()) {
			try {
				if (contentInputStream != null)
					((BasicHttpEntity)response.getEntity()).setContent(contentInputStream);
				else if (contentStr != null)
					setContent((BasicHttpEntity)response.getEntity(), contentStr);
				else if (contentJSONObject != null)
					setContent((BasicHttpEntity)response.getEntity(), contentJSONObject);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return response;
	}
	
	private void setContent(BasicHttpEntity entity, String contentStr) throws IOException {
		entity.setContent(IOUtils.toInputStream(contentStr, "UTF-8"));
	}
	
	private void setContent(BasicHttpEntity entity, JSONObject contentJSONObject) throws IOException {
		setContent(entity, contentJSONObject.toString());
	}
}
package com.zhat.http;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONObject;

import com.zhat.http.exceptions.MapKeyNotExistException;

public class ZLHttpRequest {
	private ZLHttpRequestMethod method;
	private String URI;
	private Map<String, String> params = new HashMap<String, String>();
	private Map<String, String> headers = new HashMap<String, String>();
	private ZLHttpContentType contentType;
	private JSONObject jsonData;
	
	public void setContentType(ZLHttpContentType contentType) {
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
	
	/**
	 * Get the value for the key of the parameters.
	 * @param key
	 * @return: the value to which the specified key is mapped, or null if this map contains no mapping for the key.
	 */
	public String getParam(String key) throws MapKeyNotExistException {
		if (!params.containsKey(key))
			throw new MapKeyNotExistException();
		return params.get(key);
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	
	public void setParams(String key, String value) {
		params.put(key, value);
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}
	
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	
	public ZLHttpContentType getContentType() {
		return this.contentType;
	}
	
	public void setHttpRequestContentType(ZLHttpContentType contentType) {
		this.contentType = contentType;
	}
	
	public JSONObject getJsonData() {
		return jsonData;
	}
	
	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Method = " + this.method + "\n");
		builder.append("URI = " + this.URI + "\n");
		builder.append("\n------------------ Headers ------------------\n");
		builder.append("Content-Type = " + this.contentType + "\n");
		Iterator<Entry<String, String>> it = headers.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			builder.append(entry.getKey() + " = " + entry.getValue() + "\n");
		}
		builder.append("\n------------------ Body ------------------\n");
		if (this.contentType == ZLHttpContentType.APPLICATION_JSON)
			builder.append(this.jsonData);
		return builder.toString();
	}
}

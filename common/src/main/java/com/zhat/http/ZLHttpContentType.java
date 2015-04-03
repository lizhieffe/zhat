package com.zhat.http;

public enum ZLHttpContentType {
	APPLICATION_TEXT_PLAIN("text/plain"),
	APPLICATION_JSON("application/json"),
	APPLICATION_X_WWW_FORM_URLENCODED("application/x-www-form-urlencoded");
	
	private String contentTypeText;
	
	private ZLHttpContentType(String contentTypeText) {
		this.contentTypeText = contentTypeText;
	}
	
	public static ZLHttpContentType parseContentTypeFromString(String str) 
			throws ZLHttpRequestContentTypeException {
		
		for (ZLHttpContentType type : ZLHttpContentType.values())
			if (str.equalsIgnoreCase(type.getContentTypeText()))
				return type;
		
		throw new ZLHttpRequestContentTypeException();
	}
	
	public String getContentTypeText() {
		return this.contentTypeText;
	}
}

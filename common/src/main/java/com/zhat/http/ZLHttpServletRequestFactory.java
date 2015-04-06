package com.zhat.http;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhat.http.exceptions.ZLHttpRequestContentTypeException;
import com.zhat.http.exceptions.ZLHttpRequestMethodException;
import com.zhat.utils.StringUtils;

public class ZLHttpServletRequestFactory {

	public static ZLHttpServletRequest createHttpRequestByParsingInput(byte[] content) 
			throws IOException, ZLHttpRequestMethodException, ZLHttpRequestContentTypeException {
		boolean finishedReadingHeader = false;
		String[] lines = StringUtils.readLines(content);
		ZLHttpServletRequest request = new ZLHttpServletRequest();
		
		for (int i = 0; i < lines.length; ++i) {
			
			/*
			 * Parse the data part in request
			 */
			if (finishedReadingHeader) {
				StringBuilder builder = new StringBuilder();
				for (int j = i; j < lines.length; ++j)
					builder.append(lines[j]);
				
				if (request.getContentType()
						.equalsIgnoreCase(ZLHttpContentType.APPLICATION_JSON.getContentTypeText())) {
					try {
						request.setJsonData(new JSONObject(builder.toString()));
					}
					catch (JSONException e) {
//						e.printStackTrace();
						return request;
					}
				}
				break;
			}
			/*
			 * The data part starts
			 */
			if (lines[i].length() == 0) {
				finishedReadingHeader = true;
				continue;
			}
			
			/*
			 * Read the first line of request
			 */
			if (i == 0) {
				String[] parts = lines[i].split(" ");
				request.setMethod(ZLHttpRequestMethod.parseMethodFromString(parts[0]));
				
				String[] uriParts = parts[1].split("\\?");
				
				String uri = uriParts[0];
				request.setRequestURI(uri);
				
				if (uriParts.length == 2) {
					String[] params = uriParts[1].split("&");
					for (String param : params) {
						String[] keyValueParts = param.split("=");
						request.setParams(keyValueParts[0], keyValueParts[1]);
					}
				}
			}
			/*
			 * Read the headers part
			 */
			else {
				String[] parts = lines[i].split(":", 2);
				if (parts[0].trim().equalsIgnoreCase("Content-Type"))
					request.setHttpRequestContentType(ZLHttpContentType.parseContentTypeFromString(parts[1].trim()));
				else
					request.getHeaders().put(parts[0].trim(), parts[1].trim());
			}
		}
		
		return request;
	}
}

package com.zhat.http;

import java.io.IOException;

import org.json.JSONObject;

import com.zhat.utils.StringUtils;

public class ZLHttpRequestFactory {

	public static ZLHttpRequest createHttpRequestByParsingInput(byte[] content) 
			throws IOException, ZLHttpRequestMethodException, ZLHttpRequestContentTypeException {
		boolean finishedReadingHeader = false;
		String[] lines = StringUtils.readLines(content);
		ZLHttpRequest request = new ZLHttpRequest();
		
		for (int i = 0; i < lines.length; ++i) {
			
			/*
			 * Parse the data part in request
			 */
			if (finishedReadingHeader) {
				StringBuilder builder = new StringBuilder();
				for (int j = i; j < lines.length; ++j)
					builder.append(lines[j]);
				
				if (request.getContentType() == ZLHttpRequestContentType.APPLICATION_JSON)
					request.setJsonData(new JSONObject(builder.toString()));
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
				request.setURI(parts[1]);
			}
			/*
			 * Read the headers part
			 */
			else {
				String[] parts = lines[i].split(":", 2);
				if (parts[0].trim().equalsIgnoreCase("Content-Type"))
					request.setHttpRequestContentType(ZLHttpRequestContentType.parseContentTypeFromString(parts[1].trim()));
				else
					request.getHeaders().put(parts[0].trim(), parts[1].trim());
			}
		}
		
		return request;
	}
}

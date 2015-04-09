package com.zhat.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpStatus;

public class ZLHttpServletHelper {
	
	/**
	 * Capture the exception thrown by servlet and return 500 error.
	 * TODO: implement the feature to capture pre-defined exception and throws corresponding error code.
	 * @param server
	 * @param socket
	 * @param request
	 */
	public static void doGetException(HttpServletRequest request, HttpServletResponse response
			, Exception e) {
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		response.setHeader("ZL-Exception", e.getMessage());
	}
}

package com.zhat.abstracts;

import org.apache.commons.httpclient.HttpStatus;

import com.zhat.http.ZLHttpRequest;
import com.zhat.http.ZLHttpRequestMethod;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.interfaces.IZLHttpServlet;

public abstract class AZLHttpServlet implements IZLHttpServlet {
	
	@Override
	public void service(ZLHttpRequest request, ZLHttpServletResponse response) {
		try {
			if (request.getMethod() == ZLHttpRequestMethod.GET)
				doGet(request, response);
			else if (request.getMethod() == ZLHttpRequestMethod.POST)
				doPost(request, response);
		}
		catch (Exception e) {
			doGetException(request, response);
		}
	}
	
	protected abstract void doGet(ZLHttpRequest request, ZLHttpServletResponse response)
			throws Exception;
	protected abstract void doPost(ZLHttpRequest request, ZLHttpServletResponse response)
			throws Exception;
	
	/**
	 * Capture the exception thrown by servlet and return 500 error.
	 * TODO: implement the feature to capture pre-defined exception and throws corresponding error code.
	 * @param server
	 * @param socket
	 * @param request
	 */
	private void doGetException(ZLHttpRequest request, ZLHttpServletResponse response) {
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		response.setHeader("ZL-Exception", "Server Error");
	}
}

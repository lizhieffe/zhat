package com.zhat.abstracts;

import org.apache.commons.httpclient.HttpStatus;

import com.zhat.http.ZLHttpRequest;
import com.zhat.http.ZLHttpRequestMethod;
import com.zhat.http.exceptions.ZLHttpRequestException;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.interfaces.IZLHttpServlet;
import com.zhat.server.exceptions.ServerException;

public abstract class AZLHttpServlet implements IZLHttpServlet {
	
	@Override
	public void service(ZLHttpRequest request, ZLHttpServletResponse response) {
		try {
			if (request.getMethod() == ZLHttpRequestMethod.GET)
				doGet(request, response);
			else if (request.getMethod() == ZLHttpRequestMethod.POST)
				doPost(request, response);
		}
		catch (ZLHttpRequestException e) {
			doGetException(request, response, e);
		}
		catch (ServerException e) {
			doGetException(request, response, e);
		}
	}
	
	protected abstract void doGet(ZLHttpRequest request, ZLHttpServletResponse response)
			throws ZLHttpRequestException, ServerException;
	protected abstract void doPost(ZLHttpRequest request, ZLHttpServletResponse response)
			throws ZLHttpRequestException, ServerException;
	
	/**
	 * Capture the exception thrown by servlet and return 500 error.
	 * TODO: implement the feature to capture pre-defined exception and throws corresponding error code.
	 * @param server
	 * @param socket
	 * @param request
	 */
	private void doGetException(ZLHttpRequest request, ZLHttpServletResponse response
			, Exception e) {
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		response.setHeader("ZL-Exception", e.getMessage());
	}
}

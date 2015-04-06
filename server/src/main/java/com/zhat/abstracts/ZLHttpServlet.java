package com.zhat.abstracts;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.httpclient.HttpStatus;


public abstract class ZLHttpServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8947393565221432148L;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
//			if (request.getMethod()
//					.equalsIgnoreCase(ZLHttpRequestMethod.GET.getMethodText()))
//				doGet(request, response);
//			else if (request.getMethod()
//					.equalsIgnoreCase(ZLHttpRequestMethod.POST.getMethodText()))
//				doPost(request, response);
			super.service(request, response);
		}
		catch (ServletException e) {
			doGetException(request, response, e);
		} 
		catch (IOException e) {
			doGetException(request, response, e);
		}
	}
	
	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;
	
	/**
	 * Capture the exception thrown by servlet and return 500 error.
	 * TODO: implement the feature to capture pre-defined exception and throws corresponding error code.
	 * @param server
	 * @param socket
	 * @param request
	 */
	private void doGetException(HttpServletRequest request, HttpServletResponse response
			, Exception e) {
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		response.setHeader("ZL-Exception", e.getMessage());
	}
}

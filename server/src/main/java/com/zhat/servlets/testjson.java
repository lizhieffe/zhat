package com.zhat.servlets;

import java.io.IOException;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.exceptions.ZLHttpRequestContentException;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.server.exceptions.ServerInternalException;

public class testjson extends AZLHttpServlet {

	@Override
	protected void doGet(ZLHttpRequest request, ZLHttpServletResponse response) 
			throws ZLHttpRequestContentException, ServerInternalException {
		try {
			response.addHeader("ddd", "abcd");
			response.setContentType(ZLHttpContentType.APPLICATION_JSON);
			response.getWriter().println("{}");
			response.getWriter().close();
		}
		catch (IOException e) {
			throw new ServerInternalException();
		}
	}
	
	@Override
	protected void doPost(ZLHttpRequest request, ZLHttpServletResponse response) {
		
	}
}

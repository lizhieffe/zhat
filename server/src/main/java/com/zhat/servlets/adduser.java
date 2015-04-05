package com.zhat.servlets;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.exceptions.ZLHttpRequestMethodException;
import com.zhat.http.response.ZLHttpServletResponse;

public class adduser extends AZLHttpServlet {

	@Override
	protected void doGet(ZLHttpRequest request, ZLHttpServletResponse response) throws Exception {
		throw new ZLHttpRequestMethodException();
	}
	
	@Override
	protected void doPost(ZLHttpRequest request, ZLHttpServletResponse response) {
		
	}
}

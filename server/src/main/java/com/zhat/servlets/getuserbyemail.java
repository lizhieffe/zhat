package com.zhat.servlets;

import java.io.IOException;

import com.google.gson.Gson;
import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.exceptions.MapKeyNotExistException;
import com.zhat.http.exceptions.ZLHttpRequestContentException;
import com.zhat.http.exceptions.ZLHttpRequestException;
import com.zhat.http.exceptions.ZLHttpRequestMethodException;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.model.User;
import com.zhat.server.exceptions.ServerInternalException;
import com.zhat.utils.JSONUtils;

public class getuserbyemail extends AZLHttpServlet {
	
	@Override
	protected void doGet(ZLHttpRequest request, ZLHttpServletResponse response) 
			throws ZLHttpRequestException, ServerInternalException {
		try {
			String email = request.getParam("email");
			User user = User.getUserByEmail(email);
			String responseJsonText = null;
			if (user != null)
				responseJsonText = new Gson().toJson(user);
			else
				responseJsonText = JSONUtils.EMPTY_JSON_TEXT;
			
			response.setContentType(ZLHttpContentType.APPLICATION_JSON);
			response.getWriter().println(responseJsonText);
			response.getWriter().close();
		}
		catch (IOException e) {
			throw new ServerInternalException();
		}
		catch (MapKeyNotExistException e) {
			throw new ZLHttpRequestContentException();
		}
	}
	
	@Override
	protected void doPost(ZLHttpRequest request, ZLHttpServletResponse response) 
			throws ZLHttpRequestException {
		throw new ZLHttpRequestMethodException();
	}
}

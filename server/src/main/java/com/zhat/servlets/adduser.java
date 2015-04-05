package com.zhat.servlets;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.constants.HttpConstants;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.exceptions.ZLHttpRequestContentException;
import com.zhat.http.exceptions.ZLHttpRequestException;
import com.zhat.http.exceptions.ZLHttpRequestMethodException;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.model.User;
import com.zhat.server.exceptions.ServerInternalException;

public class adduser extends AZLHttpServlet {

	@Override
	protected void doGet(ZLHttpRequest request, ZLHttpServletResponse response) 
			throws ZLHttpRequestException {
		throw new ZLHttpRequestMethodException();
	}
	
	@Override
	protected void doPost(ZLHttpRequest request, ZLHttpServletResponse response) 
			throws ZLHttpRequestException, ServerInternalException {
		try {
			JSONObject json = request.getJsonData();
			String firstName = json.getString(HttpConstants.PARAM_FIRST_NAME);
			String lastName = json.getString(HttpConstants.PARAM_LAST_NAME);
			String sex = json.getString(HttpConstants.PARAM_SEX);
			String email = json.getString(HttpConstants.PARAM_EMAIL);
			User.addUser(firstName, lastName, sex, email);
			response.getWriter().println(HttpConstants.RESPONSE_SUCCESS_MSG);
			response.getWriter().close();
		}
		catch (JSONException e) {
			throw new ZLHttpRequestContentException();
		}
		catch (ZLHttpRequestContentException e) {
			throw e;
		}
		catch (IOException e) {
			throw new ServerInternalException();
		}
	}
}

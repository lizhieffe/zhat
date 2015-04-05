package com.zhat.servlets;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.exceptions.ZLHttpRequestContentException;
import com.zhat.http.exceptions.ZLHttpRequestException;
import com.zhat.http.exceptions.ZLHttpRequestMethodException;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.model.User;
import com.zhat.model.exceptions.UserSexException;
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
			String firstName = json.getString("first_name");
			String lastName = json.getString("last_name");
			String sex = json.getString("sex");
			String email = json.getString("email");
			User.addUser(firstName, lastName, sex, email);
			response.getWriter().println("true");
			response.getWriter().close();
		}
		catch (JSONException e) {
			throw new ZLHttpRequestContentException();
		}
		catch (UserSexException e) {
			throw new ZLHttpRequestContentException();
		}
		catch (IOException e) {
			throw new ServerInternalException();
		}
	}
}

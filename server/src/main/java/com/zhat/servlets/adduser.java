package com.zhat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhat.abstracts.ZLHttpServlet;
import com.zhat.constants.HttpConstants;
import com.zhat.http.ZLHttpServletRequest;
import com.zhat.http.exceptions.ZLHttpRequestContentException;
import com.zhat.model.User;

public class adduser extends ZLHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1144371992776275468L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException {
		throw new ServletException("Get method is not supported.");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			JSONObject json = ((ZLHttpServletRequest) request).getJsonData();
			String firstName = json.getString(HttpConstants.PARAM_FIRST_NAME);
			String lastName = json.getString(HttpConstants.PARAM_LAST_NAME);
			String sex = json.getString(HttpConstants.PARAM_SEX);
			String email = json.getString(HttpConstants.PARAM_EMAIL);
			User.addUser(firstName, lastName, sex, email);
			response.getWriter().println(HttpConstants.RESPONSE_SUCCESS_MSG);
			response.getWriter().close();
		}
		catch (JSONException e) {
			throw new ServletException("Wrong request parameters.");
		}
		catch (ZLHttpRequestContentException e) {
			throw new ServletException("Wrong request parameters.");
		}
	}
}

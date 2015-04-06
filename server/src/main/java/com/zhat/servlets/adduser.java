package com.zhat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.zhat.abstracts.ZLHttpServlet;
import com.zhat.constants.ServletConstants;
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
			String firstName = json.getString(ServletConstants.PARAM_FIRST_NAME);
			String lastName = json.getString(ServletConstants.PARAM_LAST_NAME);
			String sex = json.getString(ServletConstants.PARAM_SEX);
			String email = json.getString(ServletConstants.PARAM_EMAIL);
			User.addUser(firstName, lastName, sex, email);
			response.getWriter().println(ServletConstants.RESPONSE_SUCCESS_MSG);
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

package com.zhat.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhat.abstracts.ZLHttpServlet;
import com.zhat.constants.ServletConstants;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.model.User;
import com.zhat.utils.JSONUtils;

public class getuserbyemail extends ZLHttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5346977944580308496L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String email = request.getParameter(ServletConstants.PARAM_EMAIL);
		User user = User.getUserByEmail(email);
		String responseJsonText = null;
		if (user != null)
			responseJsonText = JSONUtils.toJSONObjectText(user);
		else
			responseJsonText = JSONUtils.EMPTY_JSON_TEXT;
		
		((ZLHttpServletResponse)response).setContentType(ZLHttpContentType.APPLICATION_JSON);
		response.getWriter().println(responseJsonText);
		response.getWriter().close();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException {
		throw new ServletException("POST method is not supported.");
	}
}

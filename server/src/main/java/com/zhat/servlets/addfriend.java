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
import com.zhat.model.Friend;

public class addfriend extends ZLHttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 906085321395907739L;

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
			int userId = json.getInt(ServletConstants.PARAM_USER_ID);
			int friendId = json.getInt(ServletConstants.PARAM_FRIEND_USER_ID);
			Friend.addFriend(userId, friendId);
			response.getWriter().println(ServletConstants.RESPONSE_SUCCESS_MSG);
			response.getWriter().close();
		}
		catch (JSONException e) {
			throw new ServletException("Wrong request parameter.");
		}
	}
}

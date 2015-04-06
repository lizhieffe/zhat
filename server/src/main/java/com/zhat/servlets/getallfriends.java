package com.zhat.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhat.abstracts.ZLHttpServlet;
import com.zhat.constants.HttpConstants;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpServletRequest;
import com.zhat.model.Friend;
import com.zhat.model.User;
import com.zhat.utils.JSONUtils;

public class getallfriends extends ZLHttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6120586465036465259L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(
					((ZLHttpServletRequest)request).getParameter(HttpConstants.PARAM_USER_ID));
			List<Friend> friends = Friend.getAllFriends(userId);
			
			List<User> friendsInfo = new ArrayList<User>();
			for (Friend friend : friends)
				friendsInfo.add(User.getUserById(friend.getId()));
			
			String responseJsonText = JSONUtils.toJSONObjectText(friendsInfo);
			
			response.setContentType(ZLHttpContentType.APPLICATION_JSON.getContentTypeText());
			response.getWriter().println(responseJsonText);
			response.getWriter().close();
		}
		catch (NumberFormatException e) {
			throw new ServletException("Wrong user_id format.");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException {
		throw new ServletException("POST method is not supported.");
	}
}

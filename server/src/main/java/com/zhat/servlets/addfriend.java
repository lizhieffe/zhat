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
import com.zhat.model.Friend;
import com.zhat.server.exceptions.ServerInternalException;

public class addfriend extends AZLHttpServlet {
	
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
			int userId = json.getInt(HttpConstants.PARAM_USER_ID);
			int friendId = json.getInt(HttpConstants.PARAM_FRIEND_USER_ID);
			Friend.addFriend(userId, friendId);
			response.getWriter().println(HttpConstants.RESPONSE_SUCCESS_MSG);
			response.getWriter().close();
		}
		catch (JSONException e) {
			throw new ZLHttpRequestContentException("Wrong request parameter.");
		}
		catch (IOException e) {
			throw new ServerInternalException();
		}
	}
}

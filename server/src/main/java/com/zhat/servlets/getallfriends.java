package com.zhat.servlets;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.constants.HttpConstants;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.exceptions.MapKeyNotExistException;
import com.zhat.http.exceptions.ZLHttpRequestContentException;
import com.zhat.http.exceptions.ZLHttpRequestException;
import com.zhat.http.exceptions.ZLHttpRequestMethodException;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.model.Friend;
import com.zhat.server.exceptions.ServerInternalException;
import com.zhat.utils.JSONUtils;

public class getallfriends extends AZLHttpServlet {
	
	@Override
	protected void doGet(ZLHttpRequest request, ZLHttpServletResponse response) 
			throws ZLHttpRequestException, ServerInternalException {
		try {
			int userId = Integer.parseInt(request.getParam(HttpConstants.PARAM_USER_ID));
			List<Friend> friends = Friend.getAllFriends(userId);
			
			String responseJsonText = null;
			if (friends != null)
				responseJsonText = new Gson().toJson(friends);
			else
				responseJsonText = JSONUtils.EMPTY_JSON_TEXT;
			
			response.setContentType(ZLHttpContentType.APPLICATION_JSON);
			response.getWriter().println(responseJsonText);
			response.getWriter().close();
		}
		catch (IOException e) {
			throw new ServerInternalException();
		}
		catch (NumberFormatException e) {
			throw new ZLHttpRequestContentException("Wrong user_id format.");
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

package com.zhat.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhat.abstracts.ZLHttpServlet;
import com.zhat.constants.ServletConstants;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpServletRequest;
import com.zhat.model.Friend;
import com.zhat.model.User;
import com.zhat.servlet.ZLAsyncContext;
import com.zhat.servlet.ZLHttpServletHelper;
import com.zhat.utils.JSONUtils;

public class getallfriends extends ZLHttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6120586465036465259L;
	
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) 
			throws ServletException, IOException {
		final ZLAsyncContext asyncCtx = ((ZLHttpServletRequest) request).startAsync(request, response);
		asyncCtx.addListener(new AsyncListener() {
			
			@Override
			public void onComplete(AsyncEvent event) throws IOException {
				
			}

			@Override
			public void onTimeout(AsyncEvent event) throws IOException {
				ZLHttpServletHelper.doGetException(
						request, response, new ServletException("Server time out."));
			}

			@Override
			public void onError(AsyncEvent event) throws IOException {
			
			}

			@Override
			public void onStartAsync(AsyncEvent event) throws IOException {

			}
		});
		
		asyncCtx.start(new Runnable() {

			@Override
			public void run() {
				try {
					int userId = Integer.parseInt(
							((ZLHttpServletRequest)request).getParameter(ServletConstants.PARAM_USER_ID));
					List<Friend> friends = Friend.getAllFriendsInCache(userId);
					
					if (friends == null) {
						friends = Friend.getAllFriendsInDB(userId);
						Friend.setAllFriendsInCache(userId, friends);
					}
					
					List<User> friendsInfo = new ArrayList<User>();
					for (Friend friend : friends)
						friendsInfo.add(User.getUserById(friend.getFriendUserId()));
					
					String responseJsonText = JSONUtils.toJSONObjectText(friendsInfo);
					
					response.setContentType(ZLHttpContentType.APPLICATION_JSON.getContentTypeText());
					response.getWriter().println(responseJsonText);
					response.getWriter().close();
				}
				catch (IOException e) {
					ZLHttpServletHelper.doGetException(request, response, e);
				}
				catch (NumberFormatException e) {
					ZLHttpServletHelper.doGetException(request, response, new ServletException("Wrong user_id format."));
				}
				finally {
					asyncCtx.complete();
				}
			}
		});
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException {
		throw new ServletException("POST method is not supported.");
	}
}

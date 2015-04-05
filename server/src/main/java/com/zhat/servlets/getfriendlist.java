package com.zhat.servlets;

import org.apache.http.HttpStatus;
import org.json.JSONException;
import org.json.JSONObject;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.exceptions.ZLHttpRequestContentException;
import com.zhat.http.response.ZLHttpServletResponse;

public class getfriendlist extends AZLHttpServlet {
	
	@Override
	protected void doGet(ZLHttpRequest request, ZLHttpServletResponse response) 
			throws ZLHttpRequestContentException {
//		int userId = -1;
//		
//		try {
//			JSONObject json = request.getJsonData();
//			userId = json.getInt("user_id");
//		}
//		catch (JSONException e) {
//			
//		}
//		
//		response.setStatus(HttpStatus.SC_OK);
//		response.setContentType(ZLHttpContentType.APPLICATION_JSON);
//		response.getWriter().println("{}");
//		response.getWriter().close();
	}
	
	@Override
	protected void doPost(ZLHttpRequest request, ZLHttpServletResponse response) {
		
	}
}

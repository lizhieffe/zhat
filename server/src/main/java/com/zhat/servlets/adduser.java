package com.zhat.servlets;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.response.ZLHttpResponse;
import com.zhat.http.response.ZLHttpResponseFactory;
import com.zhat.server.Server;

public class adduser extends AZLHttpServlet {

	@Override
	protected void doGet(Server server, SocketChannel socket,
			ZLHttpRequest request) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(Server server, SocketChannel socket,
			ZLHttpRequest request) throws IOException {
		
		if (request.getContentType() != ZLHttpContentType.APPLICATION_JSON)
		ZLHttpResponse response = ZLHttpResponseFactory.buildSuccessResponse();
		server.send(socket, response.toByteArray());
	}

}

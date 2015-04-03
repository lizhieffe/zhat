package com.zhat.servlets;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import org.apache.commons.httpclient.HttpStatus;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.response.ZLHttpResponse;
import com.zhat.server.Server;

public class getfriendlist extends AZLHttpServlet {

	@Override
	protected void doGet(Server server, SocketChannel socket,
			ZLHttpRequest request) throws IOException {
		
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(HttpStatus.SC_OK);
		response.setContentType(ZLHttpContentType.APPLICATION_JSON);
		response.setContent("{}");
		server.send(socket, response.toByteArray());
	}

	@Override
	protected void doPost(Server server, SocketChannel socket,
			ZLHttpRequest request) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}

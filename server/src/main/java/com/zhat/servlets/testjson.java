package com.zhat.servlets;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.http.response.ZLHttpServletResponseBuilder;
import com.zhat.server.Server;

public class testjson extends AZLHttpServlet {

	@Override
	protected void doGet(Server server, SocketChannel socket, ZLHttpRequest request) 
			throws IOException {
//		ZLHttpResponse response = new ZLHttpResponse();
//		response.setStatus(HttpStatus.SC_OK);
//		response.setContentType(ZLHttpContentType.APPLICATION_JSON);
//		response.setContent("{}");
//		server.send(socket, response.toByteArray());
		
		ZLHttpServletResponse response = new ZLHttpServletResponseBuilder()
				.header("ddd", "d")
				.content("{}")
				.contentType(ZLHttpContentType.APPLICATION_JSON)
				.build();
		server.send(socket, response.toByteArray());
		
	}

	@Override
	protected void doPost(Server server, SocketChannel socket, ZLHttpRequest request)
			throws IOException {
		doGet(server, socket, request);
	}
}

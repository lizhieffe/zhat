package com.zhat.servlets;

import java.nio.channels.SocketChannel;
import org.apache.commons.httpclient.HttpStatus;
import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.response.ZLHttpResponse;
import com.zhat.server.Server;

public class testjson extends AZLHttpServlet {

	@Override
	protected void doGet(Server server, SocketChannel socket, ZLHttpRequest request) {	
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(HttpStatus.SC_OK);
		response.setContentType(ZLHttpContentType.APPLICATION_JSON);
		response.setContent("{}");
		try {
			server.send(socket, response.toByteArray());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(Server server, SocketChannel socket, ZLHttpRequest request) {
		doGet(server, socket, request);
	}
}

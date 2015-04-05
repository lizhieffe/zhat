package com.zhat.servlets;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpContentType;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.response.HttpResponseBuilder;
import com.zhat.http.response.HttpResponseUtils;
import com.zhat.http.response.ZLHttpResponse;
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
		
		HttpResponse response = new HttpResponseBuilder()
				.header("ddd", "d")
				.content("{}")
				.contentType(ZLHttpContentType.APPLICATION_JSON)
				.build();
		server.send(socket, HttpResponseUtils.toByteArray(response));
		
	}

	@Override
	protected void doPost(Server server, SocketChannel socket, ZLHttpRequest request)
			throws IOException {
		doGet(server, socket, request);
	}
}

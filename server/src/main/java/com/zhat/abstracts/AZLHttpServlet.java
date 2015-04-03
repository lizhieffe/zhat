package com.zhat.abstracts;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import org.apache.commons.httpclient.HttpStatus;

import com.zhat.http.ZLHttpRequest;
import com.zhat.http.ZLHttpRequestMethod;
import com.zhat.http.response.ZLHttpResponse;
import com.zhat.interfaces.IZLHttpServlet;
import com.zhat.server.Server;

public abstract class AZLHttpServlet implements IZLHttpServlet {
	
	@Override
	public void service(Server server, SocketChannel socket, ZLHttpRequest request) {
		try {
			if (request.getMethod() == ZLHttpRequestMethod.GET)
				doGet(server, socket, request);
			else if (request.getMethod() == ZLHttpRequestMethod.POST)
				doPost(server, socket, request);
		}
		catch (IOException e) {
			doGetException(server, socket, request);
		}
	}
	
	protected abstract void doGet(Server server, SocketChannel socket, ZLHttpRequest request)
			throws IOException;
	protected abstract void doPost(Server server, SocketChannel socket, ZLHttpRequest request)
			throws IOException;
	
	/**
	 * Capture the exception thrown by servlet and return 500 error.
	 * TODO: implement the feature to capture pre-defined exception and throws corresponding error code.
	 * @param server
	 * @param socket
	 * @param request
	 */
	private void doGetException(Server server, SocketChannel socket, ZLHttpRequest request) {
		ZLHttpResponse response = new ZLHttpResponse();
		response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
		try {
			server.send(socket, response.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

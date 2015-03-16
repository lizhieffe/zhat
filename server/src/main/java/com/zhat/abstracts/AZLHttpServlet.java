package com.zhat.abstracts;

import java.nio.channels.SocketChannel;

import com.zhat.http.ZLHttpRequest;
import com.zhat.http.ZLHttpRequestMethod;
import com.zhat.interfaces.IZLHttpServlet;
import com.zhat.server.Server;

public abstract class AZLHttpServlet implements IZLHttpServlet {
	
	@Override
	public void service(Server server, SocketChannel socket, ZLHttpRequest request) {
		if (request.getMethod() == ZLHttpRequestMethod.GET)
			doGet(server, socket, request);
		else if (request.getMethod() == ZLHttpRequestMethod.POST)
			doPost(server, socket, request);
	}
	
	protected abstract void doGet(Server server, SocketChannel socket, ZLHttpRequest request);
	protected abstract void doPost(Server server, SocketChannel socket, ZLHttpRequest request);
}

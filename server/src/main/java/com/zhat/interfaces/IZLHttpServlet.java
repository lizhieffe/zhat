package com.zhat.interfaces;

import java.nio.channels.SocketChannel;

import com.zhat.http.ZLHttpRequest;
import com.zhat.server.Server;

public interface IZLHttpServlet {
	public void service(Server server, SocketChannel socket, ZLHttpRequest request);
}

package com.zhat.abstracts;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.server.Server;
import com.zhat.servlet.ZLHttpServletHelper;


public abstract class ZLHttpServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8947393565221432148L;

	private Server server;
	private SocketChannel socketChannel;
	
	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

	public void setSocketChannel(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		try {
			super.service(request, response);
		}
		catch (ServletException e) {
			ZLHttpServletHelper.doGetException(request, response, e);
		} 
		catch (IOException e) {
			ZLHttpServletHelper.doGetException(request, response, e);
		}
		finally {
			if (!request.isAsyncStarted())
				server.send(socketChannel, ((ZLHttpServletResponse)response).toByteArray());
		}
	}
	
//	protected abstract void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException;
//	protected abstract void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException;
}

package com.zhat.server;

import java.nio.channels.SocketChannel;

public class ServerDataEvent {
	private Server server;
	private SocketChannel socket;
	private byte[] data;
	
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	public SocketChannel getSocket() {
		return socket;
	}
	public void setSocket(SocketChannel socket) {
		this.socket = socket;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	public ServerDataEvent(Server server, SocketChannel socket, byte[] data) {
		this.server = server;
		this.socket = socket;
		this.data = data;
	}
}

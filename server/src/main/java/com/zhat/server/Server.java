package com.zhat.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class Server implements Runnable {
	// The host:port combination to listen on
	private InetAddress hostAddress;
	private int port;
	
	// The channel on which we'll accept connections
	private ServerSocketChannel serverChannel;
	
	// The selector we'll be monitoring
	private Selector selector;
	
	// The buffer into which we'll read data when it's available
	private ByteBuffer readBuffer = ByteBuffer.allocate(8192);
	
	public Server (InetAddress hostAddress, int port) throws IOException {
		this.hostAddress = hostAddress;
		this.port = port;
		this.serverChannel = this.initServerChannel();
		this.selector = this.initSelector();
	}
	
	@Override
	public void run() {
		while (true) {
			// Wait for an event one of the registered channels
			try {
				this.selector.select();
				// Iterate over the set of keys for which events are available
				Iterator<SelectionKey> selectedKeys = this.selector.selectedKeys().iterator();
				while (selectedKeys.hasNext()) {
					SelectionKey key = selectedKeys.next();
					selectedKeys.remove();
					
					if (!key.isValid()) {
						continue;
					}
					
					// Check what event is available and deal with it
					if (key.isAcceptable())
						this.accept(key);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private ServerSocketChannel initServerChannel() throws IOException {
		 // Create a new non-blocking server socket channel
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
				
		// Bind the server socket to the specified address and port
		InetSocketAddress isa = new InetSocketAddress(this.hostAddress, this.port);
		serverChannel.socket().bind(isa);
		
		return serverChannel;
	}
	
	private Selector initSelector() throws IOException {
		// Create a new selector
		Selector socketSelector = SelectorProvider.provider().openSelector();
		
		// Register the server socket channel, indicating an interest in accepting new connections
		this.serverChannel.register(socketSelector, SelectionKey.OP_ACCEPT);
		
		return socketSelector;
	}
	
	private void accept(SelectionKey key) throws IOException {
		// For an accept to be pending the channel must be a server socket channel
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		
		// Accept the connection and make it non-blocking
		SocketChannel socketChannel = serverSocketChannel.accept();
		Socket socket = socketChannel.socket();
		socketChannel.configureBlocking(false);
		
		// Register the new SocketChannel with our Selector, indicating we'd like to be notified when there's data waiting to be read
		socketChannel.register(this.selector, SelectionKey.OP_READ);
	}
}

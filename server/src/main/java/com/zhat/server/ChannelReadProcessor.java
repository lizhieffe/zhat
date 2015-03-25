package com.zhat.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.zhat.http.ZLHttpRequest;
import com.zhat.http.ZLHttpRequestContentTypeException;
import com.zhat.http.ZLHttpRequestFactory;
import com.zhat.http.ZLHttpRequestMethodException;
import com.zhat.interfaces.IChannelProcessor;
import com.zhat.interfaces.IZLHttpServlet;

public class ChannelReadProcessor implements IChannelProcessor {
	
	// The buffer into which we'll read data when it's available
	private ByteBuffer readBuffer;
	
	private Server server;
	
	public ChannelReadProcessor(Server server) {
		this.server = server;
		this.readBuffer = ByteBuffer.allocate(8192);
	}
	
	@Override
	public void process(SelectionKey key) throws IOException {
		
		SocketChannel socketChannel = (SocketChannel) key.channel();

		// Clear out our read buffer so it's ready for new data
		this.readBuffer.clear();

		// Attempt to read off the channel
		int numRead;
		try {
			numRead = socketChannel.read(this.readBuffer);
		} catch (IOException e) {
			// The remote forcibly closed the connection, cancel
			// the selection key and close the channel.
			key.channel().close();
			key.cancel();
			return;
		}

		if (numRead == -1) {
			// Remote entity shut the socket down cleanly. Do the
			// same from our end and cancel the channel.
			key.channel().close();
			key.cancel();
			return;
		}

		ZLHttpRequest request = null;
		try {
			request = ZLHttpRequestFactory.createHttpRequestByParsingInput(this.readBuffer.array());
			
			String URI = request.getURI();
			String servletName = URI.substring(URI.lastIndexOf('/') + 1);
			
			String packageName = this.getClass().getCanonicalName().substring(0, this.getClass().getCanonicalName().lastIndexOf('.'));
			packageName = packageName.substring(0, packageName.lastIndexOf('.') + 1);
			
			String className = packageName + "servlets." + servletName;
			IZLHttpServlet servlet = (IZLHttpServlet) Class.forName(className).newInstance();
			
			servlet.service(this.server, socketChannel, request);
		} 
		catch (Exception e) {
			e.printStackTrace();
			key.channel().close();
			key.cancel();
			return;
		} 
	}
}

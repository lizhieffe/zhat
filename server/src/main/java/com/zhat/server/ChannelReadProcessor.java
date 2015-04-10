package com.zhat.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.zhat.abstracts.ZLHttpServlet;
import com.zhat.http.ZLHttpServletRequest;
import com.zhat.http.ZLHttpServletRequestFactory;
import com.zhat.http.response.ZLHttpServletResponse;
import com.zhat.interfaces.IChannelProcessor;
import com.zhat.servlet.ZLHttpServletHelper;

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

		ZLHttpServletRequest request = null;
		ZLHttpServletResponse response = null;
		try {
			request = ZLHttpServletRequestFactory
					.createHttpRequestByParsingInput(this.readBuffer.array(), server, socketChannel);
			response = new ZLHttpServletResponse();
			
			String URI = request.getRequestURI();
			String servletName = URI.substring(URI.lastIndexOf('/') + 1);
			
			String packageName = this.getClass().getCanonicalName().substring(0, this.getClass().getCanonicalName().lastIndexOf('.'));
			packageName = packageName.substring(0, packageName.lastIndexOf('.') + 1);
			
			String className = packageName + "servlets." + servletName;
			try {
				ZLHttpServlet servlet = (ZLHttpServlet) Class.forName(className).newInstance();
				servlet.setServer(server);
				servlet.setSocketChannel(socketChannel);
				
				servlet.service(request, response);
			}
			catch (ClassNotFoundException e) {
				ZLHttpServletHelper.doGetException(request, response
						, new Exception("Servlet " + className + " not found."));
				server.send(socketChannel, ((ZLHttpServletResponse)response).toByteArray());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			key.channel().close();
			key.cancel();
			return;
		} 
	}
}

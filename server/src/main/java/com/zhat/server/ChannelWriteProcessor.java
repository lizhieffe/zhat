package com.zhat.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

import com.zhat.interfaces.IChannelProcessor;

public class ChannelWriteProcessor implements IChannelProcessor {
	
	private Server server;
	
	public ChannelWriteProcessor(Server server) {
		this.server = server;
	}
	
	@Override
	public void process(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();

		synchronized (this.server.getPendingData()) {
			List<ByteBuffer> queue = this.server.getPendingData().get(socketChannel);

			// Write until there's not more data ...
			while (!queue.isEmpty()) {
				ByteBuffer buf = (ByteBuffer) queue.get(0);
				socketChannel.write(buf);
				if (buf.remaining() > 0) {
					// ... or the socket's buffer fills up
					break;
				}
				queue.remove(0);
			}

			if (queue.isEmpty()) {
				// We wrote away all data, so we're no longer interested
				// in writing on this socket. Switch back to waiting for
				// data.
				key.interestOps(SelectionKey.OP_READ);
				
				// Close channel after sending the response
				key.channel().close();
			}
		}
	}	
}

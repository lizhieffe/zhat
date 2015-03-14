package com.zhat.server;

import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class EchoWorker implements Runnable {
	private List<ServerDataEvent> queue = new LinkedList<ServerDataEvent>();
	
	public void processData(Server server, SocketChannel socket, byte[] data, int count) {
		byte[] dataCopy = new byte[count];
		System.arraycopy(data, 0, dataCopy, 0, data.length);
		synchronized(queue) {
			queue.add(new ServerDataEvent(server, socket, dataCopy));
			queue.notify();
		}
	}
	
	@Override
	public void run() {
		System.out.println("Eco-worker is running");
		
		ServerDataEvent dataEvent = null;
		while (true) {
			// Wait for the data to become available
			synchronized (queue) {
				while (queue.isEmpty()) {
					try {
						queue.wait();
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				dataEvent = queue.remove(0);
				
				// Return to sender
				dataEvent.getServer().send(dataEvent.getSocket(), dataEvent.getData());
			}
		}
	}
}

package com.zhat.servlets;

import com.zhat.abstracts.AZLHttpServlet;
import com.zhat.http.ZLHttpRequest;
import com.zhat.http.response.ZLHttpServletResponse;

public class echo extends AZLHttpServlet {
	
//	private List<ServerDataEvent> queue = new LinkedList<ServerDataEvent>();
//	
//	public void processData(Server server, SocketChannel socket, byte[] data, int count) {
//		byte[] dataCopy = new byte[count];
//		System.arraycopy(data, 0, dataCopy, 0, count);
//		synchronized(queue) {
//			queue.add(new ServerDataEvent(server, socket, dataCopy));
//			queue.notify();
//		}
//	}
//	
//	@Override
//	public void run() {
//		System.out.println("Eco-worker is running");
//		
//		ServerDataEvent dataEvent = null;
//		while (true) {
//			// Wait for the data to become available
//			synchronized (queue) {
//				while (queue.isEmpty()) {
//					try {
//						queue.wait();
//					}
//					catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				dataEvent = queue.remove(0);
//				
//				try {
//					ZLHttpRequest request = ZLHttpRequestFactory.createHttpRequestByParsingInput(dataEvent.getData());
//					
//					// Return to sender
//					dataEvent.getServer().send(dataEvent.getSocket(), request.toString().getBytes());
//				}
//				catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	@Override
	protected void doGet(ZLHttpRequest request, ZLHttpServletResponse response) {
		
	}
	
	@Override
	protected void doPost(ZLHttpRequest request, ZLHttpServletResponse response) {
		
	}
}

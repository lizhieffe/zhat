package com.zhat;

import java.io.IOException;
import java.net.InetAddress;

import com.zhat.client.Client;
import com.zhat.server.RspHandler;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
    	try {
			Client client = new Client(InetAddress.getByName("www.google.com"), 80);
			Thread t = new Thread(client);
			t.setDaemon(true);
			t.start();
			RspHandler handler = new RspHandler();
			client.send("GET / HTTP/1.0\r\n\r\n".getBytes(), handler);
			handler.waitForResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

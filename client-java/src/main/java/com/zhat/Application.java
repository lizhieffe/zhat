package com.zhat;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

import com.zhat.client.Client;
import com.zhat.server.RspHandler;
import com.zhat.utils.AppProperties;

public class Application {
	
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	Scanner scanner = new Scanner(System.in);
    	
    	try {
    		String IP = AppProperties.get("server.ip");
    		int PORT = Integer.parseInt(AppProperties.get("server.port"));
    		
			Client client = new Client(InetAddress.getByName(IP), PORT);
			Thread t = new Thread(client);
			t.setDaemon(true);
			t.start();
			RspHandler handler = new RspHandler();
			client.send("GET / HTTP/1.0\r\n\r\n".getBytes(), handler);
			handler.waitForResponse();
			
			while (true) {
				String line = scanner.nextLine();
				handler = new RspHandler();
				client.send(line.getBytes(), handler);
				handler.waitForResponse();
			}
		}
    	catch (IOException e) {
			e.printStackTrace();
		}
    	catch (NumberFormatException e) {
    		System.err.println("Wrong server.port format in resource file!");
    		e.printStackTrace();
    	}
    	finally {
    		scanner.close();
    	}
    }
}
